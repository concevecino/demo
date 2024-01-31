package com.ricoh.orders.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrdersNotFoundException extends OrdersProcessingException {
	
	private static final long serialVersionUID = 1L;

	public OrdersNotFoundException(String errDescription) {
		super( OrdersProcessingException.ERR_NOTFOUND, errDescription);
	}

}
