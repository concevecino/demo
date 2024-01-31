package com.ricoh.orders.controller.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricoh.orders.model.User;

public class TokenResponse {
    @JsonProperty
    private String token;
    
    @JsonProperty
    private Object user;
    
    @JsonProperty
    private Collection roles;

    public TokenResponse() {
    }

    public TokenResponse(Authentication auth) {    	
        this.token = auth.getDetails().toString();
        this.user = auth.getPrincipal();
        
        if( auth.getPrincipal() != null && auth.getPrincipal() instanceof User ) {
        	User u = (User) this.user;
            u.setPassword("");	
        }
        
        this.roles = auth.getAuthorities();
    }
}
