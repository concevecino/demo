package com.ricoh.orders.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ricoh.orders.model.Queue;


public interface QueueRepository extends CrudRepository<Queue, Long> {

	@Query(value = "SELECT q FROM Queue q WHERE q.tenant.id= :tenantid")
	Iterable<Queue> findByTenant(@Param("tenantid") Long tenantid);
	
	@Query(value = "SELECT q FROM Queue q WHERE q.name LIKE :querystr OR q.tenant.name LIKE :querystr")
	Iterable<Queue> findByQueryString(@Param("querystr") String querystr);
	
	@Query(value = "SELECT q FROM Queue q WHERE q.city.id = :cityId")
	Iterable<Queue> findByCity(@Param("cityId") Long cityId);

	@Query(value = "SELECT q FROM Queue q WHERE q.resetQueueHour = :nowH AND (q.lastReset <= :lastResetThreshold OR q.lastReset = NULL)")
	List<Queue> findClosingQueues(@Param("nowH") Integer nowH, @Param("lastResetThreshold") Date lastResetThreshold);

}
