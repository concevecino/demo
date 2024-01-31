package com.ricoh.orders.model;

public enum UserRole {

	ADMIN_ROLE(Authority.ADMIN), 
	DEVICE_ROLE(Authority.DEVICE), 
	USER_ROLE(Authority.USER) ;

	UserRole(String string) {
	}
	
	public static class Name {
		public static final String ADMIN = "ADMIN_ROLE"; 
		public static final String USER = "USER_ROLE"; 
		public static final String DEVICE = "DEVICE_ROLE";
	}

	public static class Authority {
		/**
		 * tenant users. Unathenticated API calls are performed using this type of principal.
		 */
		public static final String DEVICE = "hasAuthority('" + Name.DEVICE + "')"; 
		
		/**
		 * People working in the service. it is a super-role for any other worker. This role is required to log into the 
		 * admin-ui
		 */
		public static final String USER = "hasAuthority('" + Name.USER+ "')";
		
		/**
		 * Platform administrator. Creates tenants.
		 */
		public static final String ADMIN = "hasAuthority('" + Name.ADMIN + "')";
		
		 
		
		
	}
}
