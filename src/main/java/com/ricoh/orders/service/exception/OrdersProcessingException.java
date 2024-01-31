package com.ricoh.orders.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrdersProcessingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ERR_REGISTRATION_EMAIL_REQUIRED = 100;
	public static final int ERR_REGISTRATION_PASSWORD_REQUIRED = 101;
	public static final int ERR_REGISTRATION_PASSWORD_LENGTH = 102;
	public static final int ERR_REGISTRATION_USER_EXISTS = 103;
	public static final int ERR_REGISTRATION_MALFORMED_EMAIL = 104;
	
	
	public static final int ERR_SCHEDULED_VISITS_NOT_PAST = 200;
	public static final int ERR_SCHEDULED_VISITS_NOT_ALLOWED = 204;
	public static final int ERR_SCHEDULED_VISITS_SLOTS_FULL = 206;
	public static final int ERR_SCHEDULED_VISITS_CLOSED = 207;
	
	public static final int ERR_ACTIVITY_TOJSON = 213;
		
	public static final int ERR_WRONG_CREDENTIALS = 300;
	public static final int ERR_VISITOR_INACTIVE = 301;
	
	public static final int ERR_NOTFOUND = 404;
	public static final int ERR_VISITOR_NOT_FOUND = 4043;
	public static final int ERR_TICKET_NOT_FOUND = 4044;
	
	public static final int ERR_TICKET_ALREADY_SCANNED = 500;
	
	public static final int ERR_EMAIL_NOT_PROVIDED = 600;
	public static final int ERR_SERVICE_NOT_PROVIDED = 601;
	public static final int ERR_LOCATION_NOT_PROVIDED = 602;
	public static final int ERR_VISITOR_NOT_PROVIDED = 603;
	public static final int ERR_TICKET_NOT_PROVIDED = 604;
	public static final int ERR_QUEUE_NOT_PROVIDED = 605;
	public static final int ERR_CHANNEL_NOT_PROVIDED = 606;
	public static final int ERR_STATUS_NOT_PROVIDED = 607;
	
	public static final int ERR_EXPRESSION_WITHOUT_SERVICEPOINT = 701;
	public static final int ERR_EXPRESSION_WITHOUT_SEGMENT = 702;
	
	public static final int ERR_USER_NOTSELECTED_WORKPROFILE = 800;
	public static final int ERR_INVALID_WORKPROFILE = 801;
	
	public static final int ERR_VARIABLENAME_ALREADY_EXISTS = 900;
	
	public static final int ERR_APPOINTMENT_USER_HAS_ONE = 10000;
	
	int errCode;
	String description;
	
	public OrdersProcessingException(int id, String errDescription) {
		super( "{   \"error\" : { \"id\" : " + id + ", \"description\": \"" + errDescription + "\"} }" );
		this.errCode = id;
		this.description = errDescription;		
	}
	
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

