package com.sap.hana.cloud.samples.granny.dao;

import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sap.core.connectivity.api.http.HttpDestination;

/**
 * Default implementation of the {@link PhoneNumberValidationDAO} 
 * based on calling an HTTP-based wrapper around the Google I18N <code>libphonenumber</code> library.
 *  
 *  @see https://github.com/googlei18n/libphonenumber
 */
@Service
public class PhoneNumberValidationDAOImpl implements PhoneNumberValidationDAO
{
	private static final Logger logger = LoggerFactory.getLogger(PhoneNumberValidationDAOImpl.class);

	/**
	 * Name of the logical destination of the PhoneNumber service to be used.
	 * Should match the resource name specified in the <code>web.xml</code>.
	 */
	final static String DESTINATION_NAME = "PhoneNumber-Service";

	/**
	 * The HTTP URL of the PhoneNumber service to be used.
	 */
	final static String endPoint = getEndPoint();

	/**
	 * Returns the URI of the PhoneNumber service to be used.
	 * 
	 * @return URI of the PhoneNumber service to be used
	 */
	static String getEndPoint()
	{
		String retVal = null;
		
		String destinationName = DESTINATION_NAME; 
		
		final String landscape = System.getenv("HC_LANDSCAPE"); // NEO
		final String vcap = System.getenv("VCAP_APPLICATION");  // Cloud Foundry
		
		if (landscape == null && vcap == null)
		{
			// no landscape indicator found, assume we are running locally
			retVal = "http://localhost:8080/phone/api/v1";
		}
		else 
		{
			if (landscape != null) // NEO
			{
				retVal = getPhoneNumberServiceDestination(destinationName);
			}
			
			if (vcap != null) // Cloud Foundry
			{
				destinationName = DESTINATION_NAME.toUpperCase().replace('-', '_');
				retVal = System.getenv(destinationName);
			}
		}
		
		if (retVal == null)
		{
			logger.error("No Phone number API destination with name '{}‘ found!", destinationName);			
		}
		else
		{
			if (logger.isInfoEnabled())
			{
				logger.info("Phone number API destination: " + retVal);			
			}
		}

		return retVal;
	}

	/**
	 * Returns the URI of the PhoneNumber service to be used via the corresponding 
	 * destination of the Connectivity service or <code>NULL</code> if no valid
	 * destination was found. 
	 * 
	 * @param destinationName The name of the {@link HttpDestination} to be used
	 * @return URI of the PhoneNumber service to be used via the corresponding 
	 * destination of the Connectivity service or <code>NULL</code> if no valid
	 * destination was found
	 * 
	 * @see https://help.hana.ondemand.com/help/frameset.htm?e5c9867dbb571014957ef9d7a8846b1c.html
	 */
	static String getPhoneNumberServiceDestination(String destinationName)
	{
		String retVal = null;
		
		try
		{
			Context ctx = new InitialContext();

			HttpDestination destination = null;

			destination = (HttpDestination) ctx.lookup("java:comp/env/" + destinationName);
			
			if (destination != null && destination.getURI() != null)
			{
				retVal = destination.getURI().toASCIIString();
			}
		}
		catch (NamingException ex)
		{
			logger.error("No Phone number API destination with name '{}‘ found!", destinationName);			
		}
        catch (URISyntaxException ex)
        {
        	logger.error("Invalid URI defined for API destination with name '{}‘!", destinationName);        }
		
		return retVal;

	}
	
	/**
	 * Validates the specified phone number taking into account the specified region code.
	 * 
	 * @param phoneNumber The phone number to validate
	 * @param region Region Code string using ISO 3166-1 two-letter country-code format in upper-case. 
	 * @return The validation result
	 */
	public PhoneNumberValidationResult validatePhoneNumber(String phoneNumber, String region)
	{
		PhoneNumberValidationResult retVal = null;
		
		RestTemplate restTemplate = new RestTemplate();
		
		StringWriter str = new StringWriter();
		str.append(endPoint);
		str.append("?phonenumber={phonenumber}&region={region}");
		
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("phonenumber", phoneNumber);
		urlVariables.put("region", region);
		
		retVal = (PhoneNumberValidationResult) restTemplate.getForObject(str.toString(), PhoneNumberValidationResult.class, urlVariables);
		
		return retVal;
	}
	
}
