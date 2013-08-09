package com.sap.hana.cloud.samples.granny.api;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.hana.cloud.samples.granny.model.Address;
import com.sap.hana.cloud.samples.granny.model.Contact;
import com.sap.hana.cloud.samples.granny.srv.ContactService;

/**
 *  Provides the public API for {@link Contact}-related operations and services.
 */
@Service()
@Path("/contacts")
@Produces({ "application/json" })
public class ContactFacade extends BaseFacade
{
	@Autowired
	ContactService contactSrv = null;
	
	/**
	 * Returns a {@link List} of all {@link Contact} objects.
	 * 
	 * @return {@link Response} representation of the {@link List} of all {@link Contact} objects
	 * @throws FacadeException In case of an error
	 */
	@GET
	public Response findAll() throws FacadeException
	{
		List<Contact> contacts = null;
		
		try
		{
			contacts = contactSrv.getAllContactes();
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return Response.ok(contacts).build();
	}
	
	/**
	 * Creates a new {@link Contact} object.
	 * 
	 * @param contact The {@link Contact} to be created
	 * @return {@link Response} representation of the created {@link Contact}
	 * @throws FacadeException In case of an error
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Valid Contact contact) throws FacadeException
	{	
		try
		{	
			contact = contactSrv.createContact(contact);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return Response.ok(contact).status(Status.CREATED).build();
	}
	
	/**
	 * Returns the {@link Contact} with the specified ID or <code>NULL</code> if no {@link Contact} object with the specified ID exists.
	 * 
	 * @param id The id of the  {@link Contact} to retrieve
	 * @return {@link Response} representation of the {@link Contact} or a {@link Status.NOT_FOUND} (404) error code
	 * @throws FacadeException In case of an error
	 */
	@GET
	@Path("/{id}")
	public Response findOne(@PathParam("id") String id) throws FacadeException
	{
		Contact contact = null;
		
		try
		{
			contact = contactSrv.getContactById(id);
			
			if (contact == null)
			{
				return Response.status(Status.NOT_FOUND).build();
			}
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return Response.ok(contact).build();
	}
	
	/**
	 * Updates the {@link Contact} object with the specified ID with the properties of the specified {@link Contact}.
	 * 
	 * @param id The ID of the {@link Contact} to return
	 * @param contact The {@link Contact} object with the new property values to be used 
	 * @return {@link Response} representation of the updated {@link Contact}
	 * @throws FacadeException In case of an error
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, @Valid Contact contact) throws FacadeException
	{
		try
		{	
			contact = contactSrv.updateContact(contact);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return Response.ok(contact).build();
	}

	/**
	 * Deletes the {@link Contact} object with the specified ID.
	 * 
	 * @param id The ID of the {@link Contact} to delete
	 * @response {@link Status.NOT_FOUND} (200) status code
	 * @throws FacadeException In case of an error
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) throws FacadeException
	{
		try
		{	
			Contact contact = contactSrv.getContactById(id);
			contactSrv.deleteContact(contact);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return Response.status(Status.OK).build();
	}
	
	/**
	 * Returns a {@link List} of all {@link Address} objects for the {@link Contact} with the specified ID.
	 * 
	 * @param id The ID of the {@link Contact}
	 * @return {@link Response} representation of the {@link List} of all {@link Address} objects for the {@link Contact} with the specified ID.
	 * @throws FacadeException In case of an error
	 */
	@GET
	@Path("/{id}/addresses")
	public Response getAllAddressesByContactID(@PathParam("id") String id) throws FacadeException
	{
		List<Address> addresses = null;
		
		try
		{	
			addresses = contactSrv.getContactById(id).getAddresses();
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return Response.ok(addresses).build();
	}

	
}
