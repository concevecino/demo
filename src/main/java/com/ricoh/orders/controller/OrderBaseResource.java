package com.ricoh.orders.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ricoh.orders.service.exception.OrdersNotFoundException;


/**
 * Adds basic, common functionality to all resources - especially error handling 
 */
public class OrderBaseResource {
	
	private static final Logger logger = LoggerFactory
			.getLogger(OrderBaseResource.class);
	
	
	@ExceptionHandler(OrdersNotFoundException.class)
	public @ResponseBody String handleException(OrdersNotFoundException exception, HttpServletRequest request, HttpServletResponse response ) {
		logger.debug( "Handling exception {}", exception );
		response.setStatus( HttpStatus.BAD_REQUEST.value() );
		return exception.getMessage();
		
	}
}
