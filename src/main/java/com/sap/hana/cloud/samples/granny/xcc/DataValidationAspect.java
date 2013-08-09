package com.sap.hana.cloud.samples.granny.xcc;

import javax.validation.Valid;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Ensures data validation based on JSR-303.
 * 
 * @see http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/aop.html
 */
@Aspect
@Component
public class DataValidationAspect
{
		
	/**
	 * Validates the data of any method parameter annotated with {@link Valid}.
	 * 
	 * @param joinPoint The intercepted {@link JoinPoint}
	 * @throws Throwable In case of an error
	 */
	@Before("execution(* com.sap.hana.cloud.samples.granny.srv.*.*(@javax.validation.Valid (*)))")
	public void validateIncomingData(JoinPoint joinPoint) throws Throwable
	{
		System.out.println("Validating...");
	}

	
}
