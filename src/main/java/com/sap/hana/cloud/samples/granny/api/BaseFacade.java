package com.sap.hana.cloud.samples.granny.api;


/**
 * Abstract base class for all facades.
 *
 */
public abstract class BaseFacade
{
	/**
	 * Creates an enclosing {@link FacadeException} for the specified {@link Exception} or
	 * propagates it directly if the specified {@link Exception} is an instance of 
	 * {@link FacadeException}. 
	 * 
	 * @param ex The {@link Exception} to handle
	 * @throws FacadeException The {@link FacadeException}
	 */
	protected void handleException(Exception ex) throws FacadeException
	{
		
		// final Logger logger = LoggerFactory.getLogger(this.getClass());
		
		if (ex instanceof FacadeException)
		{
			throw (FacadeException) ex;
		}
		else
		{
			FacadeException up = new FacadeException(ex);
			throw up;
		}
	}
}
