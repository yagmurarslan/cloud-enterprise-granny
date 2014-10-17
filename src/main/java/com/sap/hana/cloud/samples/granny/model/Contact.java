package com.sap.hana.cloud.samples.granny.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A contact person.
 */
@XmlRootElement
@Entity
@Table(name = "GRANNY_CONTACT")
public class Contact extends BaseObject implements Serializable
{
	/**
	 * The <code>serialVersionUID</code> of the class.
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="SALUTATION", length = 10, nullable=true)
	@Enumerated(EnumType.STRING)
	protected Salutation salutation = null;
	
	@Column(name="TITLE", length = 10, nullable=true)
	@Enumerated(EnumType.STRING)
	protected Title title = null;
	
	@Column(name="FIRSTNAME", length = 30, nullable=true)
	@Size(max = 30, message = "{api.data_validation.max_length.error}")
	protected String firstName = null;
	
	@Column(name="LASTNAME", length = 30, nullable=true)
	@Size(max = 30, message = "{api.data_validation.max_length.error}")
	protected String lastName = null;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="CONTACT_ID", referencedColumnName="ID")
	@Valid
	protected List<Address> addresses = null;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="CONTACT_ID", referencedColumnName="ID")
	@Valid
	protected List<PhoneNumber> phoneNumbers = null;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="CONTACT_ID", referencedColumnName="ID")
	@Valid
	protected List<EmailAddress> emailAddresses = null;

	/**
	 * The salutation
	 */
	public Salutation getSalutation()
	{
		return salutation;
	}

	public void setSalutation(Salutation salutation)
	{
		this.salutation = salutation;
	}

	/**
	 * The title
	 */
	public Title getTitle()
	{
		return title;
	}

	public void setTitle(Title title)
	{
		this.title = title;
	}

	/**
	 * The first name 
	 */
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/*
	 * The last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public List<Address> getAddresses()
	{
		return addresses;
	}

	public void setAddresses(List<Address> addresses)
	{
		this.addresses = addresses;
	}

	public List<PhoneNumber> getPhoneNumbers()
	{
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers)
	{
		this.phoneNumbers = phoneNumbers;
	}

	public List<EmailAddress> getEmailAddresses()
	{
		return emailAddresses;
	}

	public void setEmailAddresses(List<EmailAddress> emailAdresses)
	{
		this.emailAddresses = emailAdresses;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
	    return new ToStringBuilder(this).appendSuper(super.toString()).append("salutation", this.salutation).append("title", this.title)
	            .append("firstName", this.firstName).append("lastName", this.lastName).append("addresses", this.addresses)
	            .append("phoneNumbers", this.phoneNumbers).append("emailAddresses", this.emailAddresses).toString();
	}
	
}
