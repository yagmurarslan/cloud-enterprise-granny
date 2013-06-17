package com.sap.hana.cloud.samples.granny.xcc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice
{
	
	private static Map<Class<?>, Logger> LOGGERS = new ConcurrentHashMap<Class<?>, Logger>();
	
	@Around("execution(* com.osintegrators.example.*.*(..))")
	public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable
	{
		Object retVal = null;

		Class<?> clazz = joinPoint.getSignature().getDeclaringType();

		Logger logger = LOGGERS.get(clazz);
		
		if (logger == null)
		{
			logger = LoggerFactory.getLogger(clazz);
			LOGGERS.put(clazz, logger);
		}

		logger.trace("entering: {}", joinPoint.getSignature().toLongString());
		
		// proceed with intercepted call
		retVal = joinPoint.proceed();

		logger.trace("exiting {}", joinPoint.getSignature().toLongString());
		
		return retVal;
	}
}
