package com.sap.hana.cloud.samples.granny.dao;

import java.io.Serializable;

public class PhoneNumberValidationResult implements Serializable
{

	private static final long serialVersionUID = 1L;

	boolean valid = false;
	
	String type = null;
	String number = null;
	String carrier = null;
	
	public boolean isValid()
	{
		return valid;
	}
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	public String getCarrier()
	{
		return carrier;
	}
	public void setCarrier(String carrier)
	{
		this.carrier = carrier;
	}
}
