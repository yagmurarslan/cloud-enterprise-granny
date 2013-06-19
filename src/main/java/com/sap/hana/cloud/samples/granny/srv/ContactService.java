package com.sap.hana.cloud.samples.granny.srv;

import java.util.List;

import com.osintegrators.example.Address;

/**
 * Provides CRUD (Create, Read, Update, Delete) operations as well as other
 * life-cycle related functions for {@link Address} objects.
 */
public interface ContactService
{

	/**
	 * Creates the specified {@link Address}.
	 * 
	 * @param address The {@link Address} to create
	 * @throws ServiceException In case of an error
	 */
	void createAddress(Address address) throws ServiceException;

	/**
	 * Deletes the specified {@link Address}.
	 * 
	 * @param address The {@link Address} to delete
	 * @throws ServiceException In case of an error
	 */
	void deleteAddress(Address address) throws ServiceException;

	/**
	 * Returns a {@link List} of all {@link Address} objects.
	 * @return {@link List} of all {@link Address} objects
	 * @throws ServiceException In case of an error
	 */
	List<Address> getAllAddresses() throws ServiceException;

	/**
	 * Returns the {@link Address} with the specified ID or <code>NULL</code> if no 
	 * {@link Address} object with the specified ID exists.
	 * @param id The id of the  {@link Address} to retrieve
	 * @return The {@link Address} with the specified ID or <code>NULL</code> if no 
	 * {@link Address} object with the specified ID exists
	 * @throws ServiceException In case of an error
	 */
	Address getAddressById(Long id) throws ServiceException;

	/**
	 * Updates the specified {@link Address}.
	 * 
	 * @param address The {@link Address} to delete
	 * @throws ServiceException In case of an error
	 */
	void updateAddress(Address address) throws ServiceException;

}
