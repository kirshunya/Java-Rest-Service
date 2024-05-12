package com.rateservice.aop;

import com.rateservice.service.RequestCounter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** JavaDoc COMMENT. */
@Aspect
@Component
public class LoggingAspect {
  /** JavaDoc COMMENT. */
  private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
  private final RequestCounter requestCounter;

  public LoggingAspect(RequestCounter requestCounterService) {
    this.requestCounter = requestCounterService;
  }


  /** JavaDoc COMMENT. */
  @After("execution(* com.rateservice.exception.ExceptionController.*(..))")
  public void loggerExceptionController(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.info("Exception Request: {}.{}", className, methodName);
  }

  /** JavaDoc COMMENT. */
  @After("execution(* com.rateservice.controller.UserController.*(..))")
  public void loggerUserController(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.info("Blog Request: {}.{}", className, methodName);
  }

  /** JavaDoc COMMENT. */
  @After("execution(* com.rateservice.controller.BankController.*(..))")
  public void loggerBankController(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.info("Bank Request: {}.{}", className, methodName);
  }

  /** JavaDoc COMMENT. */
  @After("execution(* com.rateservice.controller.CreditController.*(..))")
  public void loggerCreditController(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.info("Credit Request: {}.{}", className, methodName);
  }

  /** JavaDoc COMMENT. */
  @After("execution(* com.rateservice.controller.RateController.*(..))")
  public void loggerRateController(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.info("Rate Request: {}.{}", className, methodName);
  }

  /** Java Doc */
  @After("execution(* com.rateservice.service.UserService.getAllUsers())")
  public void logRequestsCounter(JoinPoint joinPoint) {
    logger.info("[NEW COUNTER VALUE]: - Counter: {}",  requestCounter.increment());
  }
}
