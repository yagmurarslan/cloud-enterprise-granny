package com.sap.hana.cloud.samples.granny.dao;

import java.util.List;

import com.sap.hana.cloud.samples.granny.model.Contact;

/**
 * Interface describing the life-cycle operations (e.g. CRUD operations) for {@link Contact} objects.
 */
public interface ContactDAO 
{
	
    public List<Contact> findAll();
	
    public Contact save(Contact entity);
	
    public Iterable<Contact> save(Iterable<? extends Contact> entities);
    
    public Contact findOne(String id);

	
    public boolean exists(String id);

	
    public long count();

	
    public void delete(String id);

	
    public void delete(Contact entity);

	
    public void delete(Iterable<? extends Contact> entities);

	
    public void deleteAll();
}
