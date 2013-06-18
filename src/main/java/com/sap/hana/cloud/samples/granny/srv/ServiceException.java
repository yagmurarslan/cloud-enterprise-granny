package com.sap.hana.cloud.samples.granny.srv;

/**
 * {@link RuntimeException} used by the service layer.
 *
 */
public class ServiceException extends RuntimeException
{

	/**
	 * The <code>serialVersionUID</code> of this class.
	 */
    private static final long serialVersionUID = 1L;

 
	public ServiceException()
	{
		super();
	}

	public ServiceException(String arg0)
	{
		super(arg0);
	}

	public ServiceException(Throwable arg0)
	{
		super(arg0);
	}

	public ServiceException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

}
