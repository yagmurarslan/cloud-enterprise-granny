package com.sap.hana.cloud.samples.granny.srv;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.osintegrators.example.Address;
import com.sap.hana.cloud.samples.granny.dao.ContactDAO;
import com.sap.hana.cloud.samples.granny.model.Contact;
import com.sap.hana.cloud.samples.granny.model.EmailAddress;
import com.sap.hana.cloud.samples.granny.model.PhoneNumber;

/**
 * Default implementation of the {@link ContactService} interface.
 */
@Service
@Primary
public class ContactServiceImpl extends BaseService implements ContactService
{
	/**
	 * The {@link Logger} to be used. Declared here so that it shows up in logging console early on.
	 */
	@SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
	
	/** 
	 * The {@link ContactDAO} used for persistence operations. 
	 */
	@Autowired
	ContactDAO contactDAO;

	/**
	 * {@inheritDoc}
	 */
	public void createAddress(Address address) throws ServiceException
	{
		try
		{
			Contact contact = new Contact();
			
			String id = UUID.randomUUID().toString();
			address.set_id(id);
			
			mapAddressToContact(address, contact, null);
			
			contactDAO.save(contact);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Address> getAllAddresses() throws ServiceException
	{
		List<Address> retVal = null;
		
		try
		{
			List<Contact> contacts = contactDAO.findAll();
			
			if (contacts != null && (! contacts.isEmpty()))
			{
				retVal = new ArrayList<Address>(contacts.size());
				
				for (Contact contact : contacts)
				{
					Address address = new Address();
					mapContactToAddress(contact, address);
					retVal.add(address);
				}
			}
			
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteAddress(Address address) throws ServiceException
	{
		try
		{	
			contactDAO.delete(address.get_id().toString());
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Address getAddressById(String id) throws ServiceException
	{
		Address retVal = new Address();
		
		try
		{
			Contact contact = contactDAO.findOne(id.toString());
			mapContactToAddress(contact, retVal);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateAddress(Address address) throws ServiceException
	{
		try
		{
			Contact contact = new Contact();
			mapAddressToContact(address, contact, 1L);
			
			contactDAO.save(contact);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
	}
	
	/**
	 * Maps the specified {@link Address} to a corresponding {@link Contact} object.
	 * 
	 * Note: This is just a temp. work-around until the service layer is completely refactored! 
	 */
	public static void mapAddressToContact(Address source, Contact target, Long version)
	{
		// don't try this at home - this is a very bad example and just a temp. work-around that may work under certain cirumstances
		target.setId(source.get_id()); 
		target.setVersion(version);
		
		com.sap.hana.cloud.samples.granny.model.Address address = new com.sap.hana.cloud.samples.granny.model.Address();
		address.setId(source.get_id());
		address.setVersion(version);
		address.setStreet(source.getAddress());
		
		EmailAddress email = new EmailAddress();
		email.setId(source.get_id().toString());
		email.setVersion(version);
		email.setEmail(source.getEmail());
		
		target.setFirstName(source.getName());
		
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setId(source.get_id());
		phoneNumber.setVersion(version);
		phoneNumber.setNumber(source.getPhone());
		
		List<com.sap.hana.cloud.samples.granny.model.Address> addressList = new ArrayList<com.sap.hana.cloud.samples.granny.model.Address>(1);
		addressList.add(address);
		
		List<EmailAddress> emailList = new ArrayList<EmailAddress>(1);
		emailList.add(email);
		
		List<PhoneNumber> phoneList = new ArrayList<PhoneNumber>(1);
		phoneList.add(phoneNumber);
		
		target.setAddresses(addressList);
		target.setEmailAdresses(emailList);
		target.setPhoneNumbers(phoneList);
	}
	
	/**
	 * Maps the specified {@link Contact} to a corresponding {@link Address} object.
	 * 
	 * Note: This is just a temp. work-around until the service layer is completely refactored! 
	 */
	public static void mapContactToAddress(Contact source, Address target)
	{
		// don't try this at home - this is a very bad example and just a temp. work-around that may work under certain cirumstances
		
		target.set_id(source.getId());
		target.setAddress(source.getAddresses().get(0).getStreet());
		target.setEmail(source.getEmailAdresses().get(0).getEmail());
		target.setName(source.getFirstName());
		target.setPhone(source.getPhoneNumbers().get(0).getNumber());
	}
}