package com.osintegrators.example;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * @deprecated please use {@link com.sap.hana.cloud.samples.granny.dao.ContactRepository} instead
 */
public interface AddressRepository extends CrudRepository<Address, Long> {
	
	List<Address> findAll();

}
