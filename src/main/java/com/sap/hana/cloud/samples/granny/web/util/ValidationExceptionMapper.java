package com.sap.hana.cloud.samples.granny.web.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.validation.ResponseConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.hana.cloud.samples.granny.model.StatusMessage;
import com.sap.hana.cloud.samples.granny.model.ValidationError;

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

			List<ValidationError> validationErrors = new ArrayList<ValidationError>(constraint.getConstraintViolations().size());
			
			for (final ConstraintViolation<?> violation : constraint.getConstraintViolations())
			{
				// log.debug(violation.getRootBeanClass().getSimpleName() + "." + violation.getPropertyPath() + ": " + violation.getMessage());
				ValidationError error = new ValidationError(violation.getMessage(), violation.getMessageTemplate(), violation.getPropertyPath().toString(), String.valueOf(violation.getInvalidValue()), null);
				validationErrors.add(error);
			}

			if (isResponseException)
			{
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}

			ValidationError[] errors = new ValidationError[validationErrors.size()];
			
			StatusMessage msg = new StatusMessage("api.data_validation.error", null, "Data validation failed!", validationErrors.toArray(errors));
			
			
			return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
		}
		else
		{
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}
}
