package com.ricoh.orders.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ricoh.orders.model.Queue;
import com.ricoh.orders.service.QueueService;
import com.ricoh.orders.service.exception.OrdersNotFoundException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/queue")
@Api(value = "queues", description = "Queue API")
public class QueueResource extends OrderBaseResource {

	@Autowired
	private QueueService queueService;
	
	 

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QueueResource.class);
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "get a queue matching the ID")
	public Queue getQueueById(@PathVariable Long id) {
		Queue q = queueService.getQueueById(id);
		if( q == null ) {
			throw new OrdersNotFoundException( "Queue " + id + " does not exist." );
		}
		return q;
	}

	@RequestMapping(value = "/tenant/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "list queues for a tenantID")
	public Iterable<Queue> listQueuesByTenantId(@PathVariable Long id) {
		return queueService.findByTenant(id);
	}

	@RequestMapping(value = "/bystring/{querystr}", method = RequestMethod.GET)
	@ApiOperation(value = "find queues containing a query string")
	public Iterable<Queue> findByQueryString(@PathVariable String querystr) {
		return queueService.findByQueryString(querystr	);
	}
	
	@RequestMapping(value = "/bycity/{cityId}", method = RequestMethod.GET)
	@ApiOperation(value = "find queues in cities containing a query string")
	public Iterable<Queue> findByCity(@PathVariable Long cityId) {
		return queueService.findByCity(cityId);
	}	
	
	
	/*@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "get queues where a user can work")
	public Iterable<Queue> getQueueByUserId(@PathVariable Long id) {
		return SizeAdapter.queueS( queueService.findByUser(id) );
	}*/
	
		
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insert queue", notes = "Update or insert a queue")
	public Queue insertQueue(@RequestBody Queue queue) {
		queueService.save(queue);
		return queue;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Update queue", notes = "Update queue")
	public Queue updaQueue(@RequestBody Queue queue) {
		return queueService.save(queue);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a queue for a given ID")
	public void deleteLocation(@PathVariable Long id) {
		queueService.delete(id);
	}

}
