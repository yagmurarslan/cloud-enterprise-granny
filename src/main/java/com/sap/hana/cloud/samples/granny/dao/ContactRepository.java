package com.sap.hana.cloud.samples.granny.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sap.hana.cloud.samples.granny.model.Address;
import com.sap.hana.cloud.samples.granny.model.Contact;

/**
 * Interface describing the life-cycle operations (e.g. CRUD operations) for {@link Contact} objects.
 */
public interface ContactRepository extends CrudRepository<Contact, String> 
{
	/**
	 * Returns a {@link List} of all {@link Contact} objects.
	 * 
	 * @return A {@link List} of all {@link Contact} objects
	 */
	public List<Contact> findAll();

	/**
	 * Returns a {@link List} of all {@link Contact} objects with an {@link Address}
	 * matching the specified 2-letter country code.
	 * 
	 * @param country The country to search for
	 * @return {@link List} of all {@link Contact} objects with an {@link Address} matching the specified 2-letter country code
	 * 
	 * @see http://www.davros.org/misc/iso3166.txt
	 */
	public List<Contact> findByAddressesCountry(String country);
	
}
