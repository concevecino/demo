package com.ricoh.orders.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ricoh.orders.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByName(String name);
	
	@Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.roles r  WHERE u.password = :password")
	User findByPassword( @Param("password") String password);
	
	@Query(value = "SELECT u FROM User u WHERE u.tenant.id= :tenantid")
	Iterable<User> findByTenant(@Param("tenantid") Long tenantid);
	

	@Query(value = "SELECT u FROM User u JOIN u.roles r WHERE r = :queueid")
	Iterable<User> findByQueue(@Param("queueid") String queueid);
}
