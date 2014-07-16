package com.sap.hana.cloud.samples.granny.srv;

import javax.validation.ConstraintViolationException;

import com.sap.hana.cloud.samples.granny.model.StatusMessage;

/**
 * {@link RuntimeException} used by the service layer.
 */
public class ValidationException extends ServiceException
{

	/**
	 * The <code>serialVersionUID</code> of this class.
	 */
    private static final long serialVersionUID = 1L;

    StatusMessage msg = null;
 
	public ValidationException()
	{	
		super();
		msg = new StatusMessage();
	}

	public ValidationException(String arg0)
	{
		super(arg0);
		msg = new StatusMessage();
		msg.setMessage(arg0);
	}

	public ValidationException(ConstraintViolationException arg0)
	{
		super(arg0);
		msg = new StatusMessage();
		msg.setDescription(arg0.getMessage());
	}

	public ValidationException(String arg0, ConstraintViolationException arg1)
	{
		super(arg0, arg1);
		msg = new StatusMessage();
		msg.setMessage(arg0);
	}
	
	public ValidationException(StatusMessage msg)
	{
		super();
		this.msg = msg;
	}
	
	public StatusMessage getStatusMessage()
	{
		return this.msg;
	}

}
