package com.sap.hana.cloud.samples.granny.web;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.osintegrators.example.Address;
import com.sap.hana.cloud.samples.granny.srv.ContactService;
import com.sap.hana.cloud.samples.granny.srv.ServiceException;

/**
 * Handles requests for the application home page.
 */
@Controller()
@Primary
public class ContactController
{
	/**
	 * The {@link ContactService} to be used.
	 */
	@Autowired
	ContactService contactService;

	/**
	 * Simply dispatches to the home view.
	 * 
	 * @param locale The {@link Locale} of the request
	 * @param model The {@link Model} associated with this request
	 * @return <code>home</code>
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model)
	{
		return "home";
	}

	/**
	 * Returns the {@link Address} matching the specified ID.
	 * 
	 * @param _id The ID of the {@link Address} to return
	 * @return The {@link Address} matching the specified ID
	 */
/*	
	@RequestMapping(value = "/get/{_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Address get(@PathVariable String _id)
	{
		return contactService.getAddressById(_id);
	}
*/
	/**
	 * Creates the specified {@link Address}.
	 * 
	 * @param address The {@link Address} to create
	 */
/*
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Address address)
	{
		contactService.createAddress(address);
	}
*/
	/**
	 * Updates the specified {@link Address}.
	 * 
	 * @param address The {@link Address} to update
	 */
/*
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Address address)
	{
		contactService.updateAddress(address);
	}
*/
	/**
	 * Deletes the specified {@link Address}.
	 * 
	 * @param address The {@link Address} to delete
	 */
/*	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody Address address)
	{
		contactService.deleteAddress(address);
	}
*/
	/**
	 * Returns a {@link List} of all {@link Address} objects.
	 * 
	 * @return {@link List} of all {@link Address} objects
	 */
/*
	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Address> list()
	{
		return contactService.getAllAddresses();
	}
*/
	/**
	 * Handles a {@link ServiceException} and returns some *meaningful* error message.
	 * 
	 * @param ex The {@link ServiceException} to handle
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public  @ResponseBody String handleServiceException(ServiceException ex) 
	{
		// do something really sophisticated here!
		return "We screwed up!";
	}

}
