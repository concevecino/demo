package com.ricoh.orders.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ricoh.orders.controller.security.StringUtil;
import com.ricoh.orders.model.User;



@Component
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	 
	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {

		User user = repository.findByName(name);

		if (user == null) {
			throw new UsernameNotFoundException("Username " + name
					+ " not found");
		}

		return user;
	}

	public User authenticate(String credential) {
		credential = StringUtil.getMD5Sum( credential );
		
		User user = repository.findByPassword( credential );

		if (user == null) {
			return null;
		}
		

		return user;
	}

	public User getUserById(Long id) {
		return repository.findOne(id);
	}

	 

	/**
	 * Passwords are not sent out of the sec. realm. Therefore, there are two scenarios:
	 * <ul>
	 * <li>User is updated but the password is not sent to the REST service. Then, db password must be preserved.</li>
	 * <li>User is updated and password is sent as a part of the REST request. Then, password must be updated.</li>
	 * </ul>
	 * 
	 * @param user
	 * @return
	 */
	public void computePassword(User user) {
		if( user.getPassword() == null && user.getId() != null ) {
			User db = repository.findOne( user.getId() );
			user.setPassword( db.getPassword() );
		} else {			
			byte[] up = Base64.getEncoder().encode( (user.getUsername() + user.getPassword()).getBytes() ) ;
			String crypto = StringUtil.getMD5Sum( new String( up ) );
			user.setPassword( crypto );			
		}
	}
	
	public User save(User user) {
		return repository.save(user);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public Iterable<User> findByTenant(Long tenantId) {
		return repository.findByTenant(tenantId);
	}
}
