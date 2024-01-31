package com.ricoh.orders.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ricoh.orders.model.Device;
 

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
	Device findByUuid(String uuid);
}
