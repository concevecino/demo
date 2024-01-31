package com.ricoh.orders.model;

import java.io.Serializable;

public interface INamedEntity extends Serializable {
	public Long getId();
	public void setId(Long id);
	public String getName();
	public void setName(String name);	
}
