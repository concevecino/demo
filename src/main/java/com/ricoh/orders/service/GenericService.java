package com.ricoh.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ricoh.orders.model.Generic;



@Component
@Transactional
public class GenericService {

	@Autowired
	private GenericRepository repository;

	@Cacheable(value = "generics")
	public Generic getGenericById(Long id) {
		return repository.findOne(id);
	}

	public Iterable<Generic> getAll() {
		return repository.findAll();
	}

	@Cacheable(value = "generics")
	public Iterable<Generic> findByParent(Generic parent) {
		return repository.findByParent(parent);
	}

	@Cacheable(value = "generics")
	public Iterable<Generic> findByParent(Long parentId) {
		return repository.findByParent(parentId);
	}

	@Cacheable(value = "generics")
	public Iterable<Generic> findByType(Integer type) {
		return repository.findByType(type);
	}
	
	@Cacheable(value = "generics")
	public Iterable<Generic> findByTypes(int[] idTypes) {
		return repository.findByTypes(idTypes);
	}

	public Iterable<Generic> findCitiesByString(String querystr) {
		return repository.findCitiesByString("%" + querystr + "%");
	}
	

	public void save(Generic location) {
		repository.save(location);
	}
}
