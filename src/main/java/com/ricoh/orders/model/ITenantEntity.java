package com.ricoh.orders.model;

import java.io.Serializable;

public interface ITenantEntity extends Serializable {
	
	
	public Tenant getTenant();
	public void setTenant(Tenant tenant);
}
