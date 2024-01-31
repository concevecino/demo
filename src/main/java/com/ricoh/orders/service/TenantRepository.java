package com.ricoh.orders.service;

import org.springframework.data.repository.CrudRepository;

import com.ricoh.orders.model.Tenant;


public interface TenantRepository extends CrudRepository<Tenant, Long> {
}
