package com.ricoh.orders.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ricoh.orders.model.Generic;


@Repository
public interface GenericRepository extends CrudRepository<Generic, Long> {

	Iterable<Generic> findByParent(Generic parent);
	
	@Query(value = "SELECT g FROM Generic g WHERE g.parent.id = :parentId")
	Iterable<Generic> findByParent(@Param("parentId") Long parentId);
	
	Iterable<Generic> findByType(Integer type);
	
	@Query(value = "SELECT g FROM Generic g WHERE g.type IN :typesId")
	Iterable<Generic> findByTypes(@Param("typesId") int[] typesId);
	
	@Query(value = "SELECT g FROM Generic g WHERE g.type = 4 AND g.name LIKE :querystr")
	Iterable<Generic> findCitiesByString(@Param("querystr") String querystr);
	
}
