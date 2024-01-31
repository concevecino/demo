package com.ricoh.orders.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ricoh.orders.model.Device;



@Component
@Transactional
public class DeviceService {

	@Autowired
	private DeviceRepository repository; 	
	
	public Device save(Device d) {
		if( d.getUuid()  == null )
			d.setUuid( UUID.randomUUID().toString() );
		
		return repository.save(d);
	}

	public Device findByUuid(String uuid) {
		return repository.findByUuid(uuid);
	}
	
	public Device authenticate(String securityToken) {
		return repository.findByUuid( securityToken );
	}
	
	 
	
}
