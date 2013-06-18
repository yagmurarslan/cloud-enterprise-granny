package com.sap.hana.cloud.samples.granny.api;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.osintegrators.example.Address;
import com.sap.hana.cloud.samples.granny.srv.AddressService;

/**
 * Handles requests for the application home page.
 */
@Controller()
@Primary
public class AddressFacade
{

	@Autowired
	AddressService addressService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model)
	{

		return "home";
	}

	@RequestMapping(value = "/get/{_id}", method = RequestMethod.GET)
	public @ResponseBody
	Address get(@PathVariable Long _id)
	{
		return addressService.getAddressById(_id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody Address address)
	{

		addressService.createAddress(address);

	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Address address)
	{

		addressService.createAddress(address);

	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody Address address)
	{

		addressService.deleteAddress(address);
	}

	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<Address> list()
	{

		return addressService.getAllAddresses();
	}

}
