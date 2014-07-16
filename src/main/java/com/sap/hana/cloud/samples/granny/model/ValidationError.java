package com.sap.hana.cloud.samples.granny.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Object used to report information about validation errors.
 *
 */
@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationError implements Serializable
{
	/**
	 * The <code>serialVersionUID</code> of the class.
	 */
    private static final long serialVersionUID = 1L;

    String message = null;
    
    String messageTemplate = null;
    
    String path = null;
    
    String invalidValue = null;
    
    List<String> messageParameter = null;
    
    public ValidationError() {}
    
    public ValidationError(String message, String messageTemplate, String path, String invalidValue, String... parameter)
    {
    	this.setMessage(message);
    	this.setMessageTemplate(messageTemplate);
    	this.setPath(path);
    	this.setInvalidValue(invalidValue);
    	
    	if (parameter != null)
    	{
    		this.setMessageParameter(Arrays.asList(parameter));
    	}
    }
    

	public List<String> getMessageParameter()
	{
		return messageParameter;
	}

	public void setMessageParameter(List<String> messageParameter)
	{
		this.messageParameter = messageParameter;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getMessageTemplate()
	{
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate)
	{
		this.messageTemplate = messageTemplate;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getInvalidValue()
	{
		return invalidValue;
	}

	public void setInvalidValue(String invalidValue)
	{
		this.invalidValue = invalidValue;
	}
}