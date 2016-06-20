package com.sap.hana.cloud.samples.granny.dao;

/**
 * Service for validating & formatting phone numbers.
 */
public interface PhoneNumberValidationDAO
{
	/**
	 * Validates the specified phone number taking into account the specified region code.
	 * 
	 * @param phoneNumber The phone number to validate
	 * @param region Region Code string using ISO 3166-1 two-letter country-code format in upper-case. 
	 * @return The validation result
	 */
	public PhoneNumberValidationResult validatePhoneNumber(String phoneNumber, String region);
}
