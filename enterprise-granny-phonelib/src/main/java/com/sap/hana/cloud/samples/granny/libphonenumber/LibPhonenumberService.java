package com.sap.hana.cloud.samples.granny.libphonenumber;

import java.util.Arrays;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 *  Service for validating & formatting phone numbers.
 *  
 *  Basically an HTTP-based wrapper around the Google I18N <code>libphonenumber</code> library.
 *  
 *  @see https://github.com/googlei18n/libphonenumber
 */
@Service("libPhonenumberService")
@Path("/phone")
@Produces({ "application/json" })
@com.webcohesion.enunciate.metadata.Facet(value="PhoneNumber Service", documentation="Provides a microservice for Google's libphonenumber library.")
@com.webcohesion.enunciate.metadata.rs.ResourceLabel(value="PhoneNumber Service")
@com.webcohesion.enunciate.metadata.rs.ServiceContextRoot(value="/api/v1")
public class LibPhonenumberService
{
	/**
	 * Validates the specified phone number taking into account the specified region code.
	 * 
	 * @param phoneNumber The phone number to validate
	 * @param region Region Code string using ISO 3166-1 two-letter country-code format in upper-case. 
	 * @return The validation result
	 * 
	 * @see The list of the codes can be found here: http://www.iso.org/iso/country_codes/iso_3166_code_lists/country_names_and_code_elements.htm
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@com.webcohesion.enunciate.metadata.rs.TypeHint(PhoneNumberValidationResult.class)
	@com.webcohesion.enunciate.metadata.rs.StatusCodes(value = {
			   @com.webcohesion.enunciate.metadata.rs.ResponseCode(code = 200, condition = "In case of success"),
			   @com.webcohesion.enunciate.metadata.rs.ResponseCode(code = 400, condition = "In case no phonenumber or region code was provided")})

	public Response validate(@QueryParam("phonenumber") String phoneNumber, 
			                 @QueryParam("region") String region,
			                 @Context HttpHeaders header) 
	{	
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		
		PhoneNumber phoneNo = null;
		
		PhoneNumberValidationResult result = new PhoneNumberValidationResult();
		result.setNumber(phoneNumber);
		
		try 
		{
			boolean knownCountry = false;
			String country = null;
			
			if (region != null && ! region.isEmpty())
			{
				country = region.toUpperCase();
			}
			else
			{
				if (header.getLanguage() != null)
				{
					country = header.getLanguage().getCountry();
				}
			}
			
			if (country != null)
			{
				knownCountry = Arrays.asList(Locale.getISOCountries()).contains(country);
			}
			else
			{
				return Response.status(Status.BAD_REQUEST).build();
			}
			
			if (! knownCountry)
			{
				// fallback 
			}
			
			phoneNo = phoneUtil.parse(phoneNumber, country);
			
			// check for validity
			boolean isValid = phoneUtil.isValidNumber(phoneNo);

			
			if (isValid)
			{
				PhoneNumberType type = phoneUtil.getNumberType(phoneNo);
				PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();
				
				String intFormat = phoneUtil.format(phoneNo, PhoneNumberFormat.INTERNATIONAL);
				String phoneNoType = type.toString().toUpperCase();
				String carrier = carrierMapper.getNameForNumber(phoneNo, Locale.ENGLISH);
				
				result.setValid(isValid);
				result.setNumber(intFormat);
				result.setType(phoneNoType);
				result.setCarrier(carrier);
			}
		} 
		catch (NumberParseException e) 
		{
			System.err.println("NumberParseException was thrown: " + e.toString());
			return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
		}
		
		return Response.ok(result).build();
	}
}
