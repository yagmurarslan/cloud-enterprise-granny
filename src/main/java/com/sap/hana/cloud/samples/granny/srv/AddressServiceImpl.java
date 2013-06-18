package com.sap.hana.cloud.samples.granny.srv;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.osintegrators.example.Address;
import com.osintegrators.example.AddressRepository;

/**
 * Default implementation of the {@link AddressService} interface.
 */
@Service
@Primary
public class AddressServiceImpl extends BaseService implements AddressService
{
	/**
	 * The {@link Logger} to be used. Declared here so that it shows up in logging console early on.
	 */
	@SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	/** 
	 * The {@link AdressRepository} used for persistence operations. 
	 */
	@Autowired
	AddressRepository addressRepository;

	/**
	 * {@inheritDoc}
	 */
	public void createAddress(Address address) throws ServiceException
	{
		try
		{
			throw new NullPointerException("on purpose!");
			//addressRepository.save(address);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Address> getAllAddresses() throws ServiceException
	{
		List<Address> retVal = null;
		
		try
		{
			retVal = addressRepository.findAll();
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteAddress(Address address) throws ServiceException
	{
		try
		{
			addressRepository.delete(address);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Address getAddressById(Long id) throws ServiceException
	{
		Address retVal = null;
		
		try
		{
			retVal = addressRepository.findOne(id);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
		
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateAddress(Address address) throws ServiceException
	{
		try
		{
			addressRepository.save(address);
		}
		catch (Exception ex)
		{
			this.handleException(ex);
		}
	}
}