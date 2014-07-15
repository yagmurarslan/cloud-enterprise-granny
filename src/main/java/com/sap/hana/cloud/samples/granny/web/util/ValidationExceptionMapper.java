package com.sap.hana.cloud.samples.granny.web.util;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.validation.ResponseConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException>
{
	Logger log = LoggerFactory.getLogger(ValidationExceptionMapper.class);

	@Override
	public Response toResponse(ValidationException exception)
	{
		if (exception instanceof ConstraintViolationException)
		{

			final ConstraintViolationException constraint = (ConstraintViolationException) exception;
			final boolean isResponseException = constraint instanceof ResponseConstraintViolationException;

			for (final ConstraintViolation<?> violation : constraint.getConstraintViolations())
			{
				log.debug(violation.getRootBeanClass().getSimpleName() + "." + violation.getPropertyPath() + ": " + violation.getMessage());
			}

			if (isResponseException)
			{
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}

			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		else
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}
}
