package com.ricoh.orders.controller.security;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.Authentication;

public class TokenService {

	private static final Logger logger = LoggerFactory
			.getLogger(TokenService.class);

	@Autowired
	private CacheManager cache;

	private Cache getTokenCache() {
		return cache.getCache("restApiAuthTokenCache");
	}

	public String generateNewToken() {
		return UUID.randomUUID().toString();
	}

	public void store(String token, Authentication authentication) {
		getTokenCache().put(token, authentication);
	}

	public boolean contains(String token) {
		return getTokenCache().get(token) != null;
	}

	public Authentication retrieve(String token) {
		return (Authentication) getTokenCache().get(token).get();
	}
}
