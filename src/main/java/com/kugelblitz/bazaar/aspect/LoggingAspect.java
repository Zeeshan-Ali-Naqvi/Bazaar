package com.kugelblitz.bazaar.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Before("execution(* com.kugelblitz.bazaar..*(..))")
  public void logBefore(JoinPoint joinPoint) {
    logger.info(
        "Entering method: {} in class: {}",
        joinPoint.getSignature().getName(),
        joinPoint.getSignature().getDeclaringTypeName());
  }

  // Log after method returns successfully
  @AfterReturning(pointcut = "execution(* com.kugelblitz.bazaar..*(..))", returning = "result")
  public void logAfterMethod(JoinPoint joinPoint, Object result) {
    logger.info("Method: {} returned with value: {}", joinPoint.getSignature().getName(), result);
  }

  // Log exceptions thrown by methods
  @AfterThrowing(pointcut = "execution(* com.kugelblitz.bazaar..*(..))", throwing = "error")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
    logger.error(
        "Method: {} threw exception: {}", joinPoint.getSignature().getName(), error.getMessage());
  }
}
