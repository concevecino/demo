package com.ricoh.orders.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ricoh.orders.model.Tenant;
import com.ricoh.orders.service.TenantService;
import com.ricoh.orders.service.exception.OrdersNotFoundException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/tenant")
@Api(value = "tenants", description = "TenantAPI")
//@PreAuthorize(UserRole.Authority.STATION)
public class TenantResource {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TenantResource.class);

	@Autowired
	private TenantService tenantService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "get a tenant matching the ID")
	public Tenant getUserById(@PathVariable Long id) {
		Tenant q = tenantService.get(id);
		if( q == null ) {
			throw new OrdersNotFoundException( "Tenant " + id + " does not exist." );
		}
		return q;
	}
	

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "get all tenants")
	@ApiResponses({ @ApiResponse(code = 200, message = "All tenants") })
	public Iterable<Tenant> getAll() {
		return tenantService.getAll();
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insert User", notes = "Update or insert a User")
	public Tenant insertTenant(@RequestBody Tenant tenant) {
		LOGGER.debug("Inserts user: {}", tenant);
		return tenantService.save(tenant);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "updateById", notes = "Updates an existing user")
	@ApiResponses({
			@ApiResponse(code = 200, message = "The updated entity"),
			@ApiResponse(code = 400, message = "If the requested tenant update contains invalid values"),
			@ApiResponse(code = 403, message = "If tenant exists but does not belong to the current user"),
			@ApiResponse(code = 404, message = "If tenant does not exist") })
	public Tenant updateById(@PathVariable("id") final String id,
			@Valid @RequestBody final Tenant tenant) {
		LOGGER.debug("Updates a tenant: {} with id: {}", tenant, id);
		return tenantService.save(tenant);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Delete a tenant for a given ID")
	public void delete(@PathVariable Long id) {
		tenantService.delete( id );
	}
	
}
