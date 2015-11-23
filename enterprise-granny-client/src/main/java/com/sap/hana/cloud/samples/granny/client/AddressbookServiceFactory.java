package com.sap.hana.cloud.samples.granny.client;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.core.connectivity.api.http.HttpDestination;
import com.sap.hana.cloud.samples.granny.api.ContactFacade;

public class AddressbookServiceFactory
{
	private static final Logger logger = LoggerFactory.getLogger(AddressbookServiceFactory.class);

	/**
	 * Name of the logical destination of the Addressbook service to be used.
	 * Should match the resource name specified in the <code>web.xml</code>.
	 */
	final static String DESTINATION_NAME = "Addressbook-Service";

	/**
	 * The HTTP URL of the Addressbook service to be used.
	 */
	final static String endPoint = getEndPoint();

	/**
	 * Returns the URI of the Addressbook service to be used.
	 * 
	 * @return URI of the Addressbook service to be used
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
			retVal = "http://localhost:8080/api/v1";
		}
		else 
		{
			if (landscape != null) // NEO
			{
				retVal = getAddressbookServiceDestination(destinationName);
			}
			
			if (vcap != null) // Cloud Foundry
			{
				destinationName = DESTINATION_NAME.toUpperCase().replace('-', '_');
				retVal = System.getenv(destinationName);
			}
		}
		
		if (retVal == null)
		{
			logger.error("No API destination with name '{}‘ found!", destinationName);			
		}
		else
		{
			if (logger.isInfoEnabled())
			{
				logger.info("API destination: " + retVal);			
			}
		}

		return retVal;
	}

	/**
	 * Returns the URI of the Addressbook service to be used via the corresponding 
	 * destination of the Connectivity service or <code>NULL</code> if no valid
	 * destination was found. 
	 * 
	 * @param destinationName The name of the {@link HttpDestination} to be used
	 * @return URI of the Addressbook service to be used via the corresponding 
	 * destination of the Connectivity service or <code>NULL</code> if no valid
	 * destination was found
	 * 
	 * @see https://help.hana.ondemand.com/help/frameset.htm?e5c9867dbb571014957ef9d7a8846b1c.html
	 */
	static String getAddressbookServiceDestination(String destinationName)
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
			logger.error("No API destination with name '{}‘ found!", destinationName);			
		}
        catch (URISyntaxException ex)
        {
        	logger.error("Invalid URI defined for API destination with name '{}‘!", destinationName);        }
		
		return retVal;

	}

	/**
	 * Returns a reference to the Addressbook service (proxy) to be used. 
	 * 
	 * @return A reference to the Addressbook service (proxy) to be used
	 */
	public ContactFacade getService()
	{
		ContactFacade retVal = null;

		// set up providers
		List<JacksonJsonProvider> providers = new ArrayList<JacksonJsonProvider>();
		JacksonJaxbJsonProvider jsonProvider = new JacksonJaxbJsonProvider();
		providers.add(jsonProvider);
		
		if (endPoint != null)
		{
			
			// initialize proxy
			retVal  = JAXRSClientFactory.create(endPoint, ContactFacade.class, providers);
		}
		
		return retVal;
	}
}
