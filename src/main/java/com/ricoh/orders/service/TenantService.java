package com.ricoh.orders.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ricoh.orders.model.Tenant;
 

@Component
@Transactional
public class TenantService {

	@Autowired
	private TenantRepository repository;

	public Iterable<Tenant> getAll() {
		return repository.findAll();
	}
	
	public Tenant save(Tenant tenant) {
		if(tenant.getId() == null || tenant.getCreationDate() == null )
			tenant.setCreationDate( new Date() );
		
		return repository.save(tenant);
	}
	
	public void delete(Long id) {
		repository.delete(id);
	}

	public Tenant get(Long id) {
		return repository.findOne(id);
	}
}
