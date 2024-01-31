package com.ricoh.orders.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Optional;
import com.ricoh.orders.model.User;
import com.ricoh.orders.service.DeviceService;
import com.ricoh.orders.service.UserService;



public class DomainUsernamePasswordAuthenticationProvider implements
		AuthenticationProvider {

	 
    @Autowired
	private UserService userService;

    @Autowired
	private DeviceService deviceService;

	@Autowired
	private TokenService tokenService;

	public DomainUsernamePasswordAuthenticationProvider(
			TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		Optional<String> username = (Optional) authentication.getPrincipal();

		if ( !username.isPresent() ) {
			throw new BadCredentialsException("Invalid Domain User Credentials");
		}

		UserDetails user = null;
		
		  
            user = userService.authenticate( username.get()  );
            
            if( user != null ) {
                User u1 = (User)user;
            }
		
            user = deviceService.authenticate( username.get() );
            
            if( user == null ) {
                user = userService.authenticate( username.get()  );
                
                if( user != null ) {
                    User u1 = (User)user; 
                    
                }
            }
			
		if (user == null) {
			throw new BadCredentialsException("Credenciales incorrectas");
		}

		AuthenticationWithToken resultOfAuthentication = new AuthenticationWithToken(
				user, null, user.getAuthorities());

		String newToken = tokenService.generateNewToken();
		resultOfAuthentication.setToken(newToken);
		tokenService.store(newToken, resultOfAuthentication);

		return resultOfAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
