package com.sap.hana.cloud.samples.granny.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sap.hana.cloud.samples.granny.model.Address;
import com.sap.hana.cloud.samples.granny.model.AddressType;
import com.sap.hana.cloud.samples.granny.model.CommunicationType;
import com.sap.hana.cloud.samples.granny.model.Contact;
import com.sap.hana.cloud.samples.granny.model.Email;
import com.sap.hana.cloud.samples.granny.model.PhoneNumber;
import com.sap.hana.cloud.samples.granny.model.Salutation;



@ContextConfiguration("classpath:/META-INF/spring/app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class TestContactDAO
{
	@Autowired
	ContactDAO contactDAO = null;

	
	@Before
	public void setUp() throws Exception 
	{
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testCRUD() 
	{
		Contact testPerson = createTestContact();
		
		long noOfRecords = contactDAO.count();
		
		// create
		Contact contact = contactDAO.save(testPerson);
		
		assertNotNull(contact);
		assertTrue("Contact should have been created!", (noOfRecords == (contactDAO.count() - 1)));
		
		// read
		Contact anotherContact = contactDAO.findOne(contact.getId());
		
		assertNotNull(anotherContact);
		
		// update
		anotherContact.setFirstName("NEO");
		
		Contact updatedContact = contactDAO.save(anotherContact);
		
		assertTrue("Contact name should be updated!", anotherContact.getFirstName().equals(updatedContact.getFirstName()));
		
		// delete
		contactDAO.delete(updatedContact);
		
		assertTrue("Contact should have been created!", (noOfRecords == contactDAO.count()));
		
	}
	
	@Test(expected=JpaOptimisticLockingFailureException.class)
	public void testOptimisticLocking() throws JpaOptimisticLockingFailureException
	{
		Contact testPerson = createTestContact();
		
		long noOfRecords = contactDAO.count();
		
		// create
		Contact contact = contactDAO.save(testPerson);
		
		assertNotNull(contact);
		assertTrue("Contact should have been created!", (noOfRecords == (contactDAO.count() - 1)));
		
		// read
		Contact anotherContact = contactDAO.findOne(contact.getId());
				
		assertNotNull(anotherContact);
		
		// update
		anotherContact.setFirstName("NEO");
				
		Contact updatedContact = contactDAO.save(anotherContact);
				
		assertTrue("Contact name should be updated!", anotherContact.getFirstName().equals(updatedContact.getFirstName()));
 
		// 2nd update - should fail
		contact = contactDAO.save(contact);
		
	}
	
	/**
	 * Creates a demo test {@link Contact}.
	 * 
	 * @return A test {@link Contact}
	 */
	static Contact createTestContact()
	{
		Contact contact = new Contact();
		Address address = new Address();
		Email email = new Email();
		PhoneNumber homePhone = new PhoneNumber();
		PhoneNumber cellPhone = new PhoneNumber();
	
		homePhone.setType(CommunicationType.HOME);
		homePhone.setNumber("+1 610 661 1000");
		
		cellPhone.setType(CommunicationType.CELL);
		cellPhone.setNumber("+1 555 123 4567");
		
		email.setType(AddressType.WORK);
		email.setEmail("thomas.anderson@sap.com");
		
		address.setType(AddressType.WORK);
		address.setCity("Newton Square");
		address.setCountry("US");
		address.setState("PA");
		address.setStreet("3999 W Chester Pike");
		
		contact.setFirstName("Thomas");
		contact.setLastName("Anderson");
		contact.setSalutation(Salutation.MR);
		
		List<Address> addressList = new ArrayList<Address>(1);
		List<Email> emailList = new ArrayList<Email>(1);
		List<PhoneNumber> phoneNumberList = new ArrayList<PhoneNumber>(2);
	
		addressList.add(address);
		emailList.add(email);
		phoneNumberList.add(homePhone);
		phoneNumberList.add(cellPhone);
		
		contact.setAddresses(addressList);
		contact.setEmailAdresses(emailList);
		contact.setPhoneNumbers(phoneNumberList);
		
		return contact;
	}
	

	
}
