package com.sap.hana.cloud.samples.granny.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The phone number of a {@link Contact}.
 */
@Entity
@Table(name = "GRANNY_PHONE")
public class PhoneNumber extends BaseObject implements Serializable
{
	/**
	 * The <code>serialVersionUID</code> of the class.
	 */
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	protected CommunicationType type = null;
	
	@Column(name="NUMBER", length = 30, nullable=true)
	protected String number = null;

	public CommunicationType getType()
	{
		return type;
	}

	public void setType(CommunicationType type)
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
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
	    return new ToStringBuilder(this).appendSuper(super.toString()).append("type", this.type).append("number", this.number).toString();
	}
}
