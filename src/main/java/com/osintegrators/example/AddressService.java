package com.osintegrators.example;

import java.util.List;

import com.osintegrators.example.Address;

/**
* @deprecated please use {@link com.sap.hana.cloud.samples.granny.srv.AddressService} instead
*/
public interface AddressService {

	void createAddress(Address add);

	void deleteAddress(Address add);

	List<Address> getAllAddresses();

	Address getAddressById(Long id);

	void updateAddress(Address address);

}
