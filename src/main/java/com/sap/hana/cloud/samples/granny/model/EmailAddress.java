package com.sap.hana.cloud.samples.granny.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * EmailAddress address of a {@link Contact}.
 */
@Entity
@Table(name = "GRANNY_EMAIL")
public class EmailAddress extends BaseObject implements Serializable
{
	/**
	 * The <code>serialVersionUID</code> of the class.
	 */
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	protected AddressType type = null;
	
	@Column(name="STREET", length = 50, nullable=true)
	protected String email = null;

	public AddressType getType()
	{
		return type;
	}

	public void setType(AddressType type)
	{
		this.type = type;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
}
