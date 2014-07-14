package com.sap.hana.cloud.samples.granny.web.util;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;

import com.sap.hana.cloud.samples.granny.model.StatusMessage;
import com.sap.hana.cloud.samples.granny.util.CustomObjectMapper;

/**
 * 
 * @see http://cxf.apache.org/docs/jax-rs-basics.html#JAX-RSBasics-Exceptionhandling
 * @see http://fusesource.com/docs/esb/4.2/rest/RESTExceptionMapper.html
 * 
 */
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException>
{

	/**
	 * The {@link ObjectMapper} to be used.
	 * 
	 * @see CustomObjectMapper
	 */
	@Inject
	ObjectMapper objectMapper = null;


	/**
	 * TODO
	 * 
	 * @param exception The caught {@link JsonMappingException}
	 * @return The corresponding {@link Response} containing a {@link StatusMessage}
	 */
	@Override
	public Response toResponse(JsonMappingException exception)
	{	
		StatusMessage message = new StatusMessage();

		message.setCode(HttpStatus.BAD_REQUEST.value());
		message.setDescription(exception.getMessage());
		message.setError("Mapping error");

		return Response.status(message.getCode()).entity(message).type(MediaType.APPLICATION_JSON).build();
	}
}
