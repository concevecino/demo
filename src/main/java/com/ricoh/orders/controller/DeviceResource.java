package com.ricoh.orders.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ricoh.orders.model.Device;
import com.ricoh.orders.service.DeviceService;
import com.ricoh.orders.service.exception.OrdersNotFoundException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/device")
@Api(value = "device", description = "DeviceAPI")
public class DeviceResource {

	private final static Logger logger = LoggerFactory
			.getLogger(DeviceResource.class);

	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value = "/uuid/{uuid}", method = RequestMethod.GET)
	@ApiOperation(value = "get a tenant matching the ID")
	public Device findByUuid(@PathVariable String uuid) {
		Device q = deviceService.findByUuid(uuid);
		if( q == null ) {
			throw new OrdersNotFoundException( "Device" + uuid + " does not exist." );
		}
		return q;
	}
	
	
}
