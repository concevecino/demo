package com.ricoh.orders.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ricoh.orders.model.Queue;
import com.ricoh.orders.model.User;



@Component
@Transactional
public class QueueService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(QueueService.class);
	
	@Autowired
	private QueueRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	 
	
	@Autowired
	private UserService userService;
	
	
	public Iterable<Queue> getAll() {
		return repository.findAll();
	}
	
	public Queue getQueueById(Long id) {
		return repository.findOne(id);
	}

	public Iterable<Queue> findByTenant(Long tenantId) {
		return repository.findByTenant(tenantId);
	}
	
	/*public Iterable<Queue> findByUser(Long userId) {
		User u = userRepository.findOne( userId );
		if( u != null )
			return u.getQueues();
		else 
			return null;
	}*/


	public Queue save(Queue queue) {
		return repository.save(queue);
	}
	
	public void delete(Long id) {
		repository.delete(id);
	}

	public Iterable<Queue> findByQueryString(String querystr) {
		return repository.findByQueryString( "%" + querystr + "%" );
	}

	public Iterable<Queue> findByCity(Long cityId) {
		return repository.findByCity( cityId );
	}
	
	  
	 
	
}

