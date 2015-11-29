package com.sap.hana.cloud.samples.granny.dao;

import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.sap.hana.cloud.samples.granny.model.PhoneNumber;
import com.sap.hana.cloud.samples.granny.xcc.validations.ValidPhoneNumberValidator;

/**
 * Tests for the {@link ValidPhoneNumberValidator} class.  
 */
@ContextConfiguration("classpath:/META-INF/spring/spring-persistence-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class ValidPhoneNumberValidatorTest
{
	
	@Autowired
	ValidatorFactory validator = null;
	
	@Autowired
	MessageSource source = null;
	
	private final static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

	@Before
	public void setUp() throws Exception
	{
	}
	
	@Test
	public void testPhoneNumberUtil()
	{
		com.google.i18n.phonenumbers.Phonenumber.PhoneNumber no = null;
		
        try
        {
	        no = phoneUtil.parse("+49 6227 712345", Locale.getDefault().getCountry());
        }
        catch (NumberParseException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
		boolean valid = phoneUtil.isValidNumber(no);
		        
		assertTrue("Number should be valid!", valid);
	}
	
	@Test
	public void testValidation() 
	{		
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setNumber("+49 6227 712345");
		
		Validator validator = this.validator.getValidator();
		
		Set<ConstraintViolation<PhoneNumber>> constraintViolations = validator.validate(phoneNumber);
		
		assertTrue("Number should be valid!", constraintViolations.isEmpty());
		
	}
}
