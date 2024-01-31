package com.ricoh.orders.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ricoh.orders.controller.filter.AuthenticationFilter;
import com.ricoh.orders.controller.security.DomainUsernamePasswordAuthenticationProvider;
import com.ricoh.orders.controller.security.TokenAuthenticationProvider;
import com.ricoh.orders.controller.security.TokenService;




@Configuration
@Profile("production")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(
					"/v2/api-docs",
					"/configuration/ui",
					"/swagger-resources",
					"/configuration/security",
					"/swagger-ui.html",
					"/webjars/**");
	}
	
	 
	@Override	
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.csrf().disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)			
			.and().antMatcher("/api/**")
			.anonymous().disable().exceptionHandling()
			.authenticationEntryPoint(unauthorizedEntryPoint());
		
		http.addFilterBefore(new AuthenticationFilter(authenticationManager()),
				BasicAuthenticationFilter.class);
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(
				domainUsernamePasswordAuthenticationProvider())
				.authenticationProvider(tokenAuthenticationProvider());
	}

	@Bean
	public TokenService tokenService() {
		return new TokenService();
	}

	@Bean
	public AuthenticationProvider domainUsernamePasswordAuthenticationProvider() {
		return new DomainUsernamePasswordAuthenticationProvider(tokenService());
	}

	@Bean
	public AuthenticationProvider tokenAuthenticationProvider() {
		return new TokenAuthenticationProvider(tokenService());
	}

	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {

		return new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request,
					HttpServletResponse response,
					AuthenticationException authException) throws IOException,
					ServletException {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		};
	}

}
