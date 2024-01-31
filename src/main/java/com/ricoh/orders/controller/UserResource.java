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

import com.ricoh.orders.model.User;
import com.ricoh.orders.service.UserService;
import com.ricoh.orders.service.exception.OrdersNotFoundException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/user")
@Api(value = "users", description = "User API")
public class UserResource {

	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserResource.class);

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "get an user matching the ID")
	public User getUserById(@PathVariable Long id) {
		User q = userService.getUserById(id);
		if (q == null) {
			throw new OrdersNotFoundException("User " + id + " does not exist.");
		}
		return q;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insert User", notes = "Update or insert a User")
	public User insertUser(@RequestBody User user) {
		LOGGER.debug("Inserts user: {}", user);
		userService.computePassword( user );
		return userService.save(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "updateById", notes = "Updates an existing user")
	@ApiResponses({
			@ApiResponse(code = 200, message = "The updated user"),
			@ApiResponse(code = 400, message = "If the requested user update contains invalid values"),
			@ApiResponse(code = 403, message = "If user exists but does not belong to the current user"),
			@ApiResponse(code = 404, message = "If user does not exist") })
	public User updateById(@PathVariable("id") final String id,
			@Valid @RequestBody final User user) {
		LOGGER.debug("Updates a user: {} with id: {}", user, id);
		userService.computePassword( user );
		return userService.save(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a queue for a given ID")
	public void deleteUser(@PathVariable Long id) {
		LOGGER.debug("Deletes user with id: {}", id);
		userService.delete(id);
	}
	
	@RequestMapping(value = "/tenant/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "get a User for a tenantID")
	public Iterable<User> getUserByTenantId(@PathVariable Long id) {
		return userService.findByTenant(id);
	}
	
	
}
