package com.sap.hana.cloud.samples.granny.api;

/**
 * {@link RuntimeException} used by the service layer.
 *
 */
public class FacadeException extends RuntimeException
{

	/**
	 * The <code>serialVersionUID</code> of this class.
	 */
    private static final long serialVersionUID = 1L;

 
	public FacadeException()
	{
		super();
	}

	public FacadeException(String arg0)
	{
		super(arg0);
	}

	public FacadeException(Throwable arg0)
	{
		super(arg0);
	}

	public FacadeException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

}
