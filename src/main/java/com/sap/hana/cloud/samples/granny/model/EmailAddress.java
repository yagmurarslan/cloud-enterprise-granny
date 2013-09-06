package com.sap.hana.cloud.samples.granny.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
	
	@Column(name="EMAIL", length = 70, nullable=true)
	@Size(max = 70, message = "{model.email_address.email.size.error}")
	@Pattern(regexp="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{model.email_address.email.pattern.error}" )
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

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
	    return new ToStringBuilder(this).appendSuper(super.toString()).append("type", this.type).append("email", this.email).toString();
	}
	
}
