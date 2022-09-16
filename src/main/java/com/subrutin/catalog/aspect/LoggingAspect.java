package com.subrutin.catalog.aspect;

import org.springframework.util.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component    
@Slf4j
@Aspect
public class LoggingAspect {
  
  // execution: for matching method execution join points
  // within: limits matching to join points within certain types
  // args: limits matching to join points where the argumets are instance of the given type

  // @Pointcut("execution(* com.subrutin.catalog.web.BookResource.findBookDetail(..))")
  // @Pointcut("execution(* com.subrutin.catalog.web.AuthorResource.*(..))")
  @Pointcut("execution(* com.subrutin.catalog.web.*.*(..))") //semua kelas semua method akan menjadi aspek
  // @Pointcut("execution(* com.subrutin.catalog.web.*.*(com.subrutin.catalog.dto.PublisherCreateRequestDTO))") //semua kelas semua method akan menjadi aspek
  private void restAPI(){} 

  @Pointcut("within(com.subrutin.catalog.web.*)")
  private void withinPointcutExample(){}

  @Pointcut("args(com.subrutin.catalog.dto.PublisherCreateRequestDTO)")
  private void argsPointcutExample(){}

  @Pointcut("@args(com.subrutin.catalog.annotation.LogThisArgs)")
  private void argsAnnotationPointcutExample(){}

  @Pointcut("@annotation(com.subrutin.catalog.annotation.LogThisMethod)")
  private void annotationPointcutExample(){}
  
  // @Before("restAPI()")
  // @Before("withinPointcutExample()")
  // @Before("restAPI() && argsPointcutExample()")
  // @Before("restAPI() && argsAnnotationPointcutExample()")
  @Before("annotationPointcutExample()")
  public void beforeExecutedLogging(){
    log.info("this is log from aspect before method executed");
  }

  @After("annotationPointcutExample()")
  public void anterExecutedLogging(){
    log.info("this is log from aspect after method executed");
  }

  @AfterReturning("annotationPointcutExample()")
  public void anterReturnExecutedLogging(){
    log.info("this is log from aspect after returning method executed");
  }

  @AfterThrowing("annotationPointcutExample()")
  public void anterThrowingExecutedLogging(){
    log.info("this is log from aspect after throwing method executed");
  }

  @Around("restAPI()")
  public Object processingTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable{
    StopWatch stopWatch = new StopWatch();
    
      try {
        log.info("start {}.{} ", joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName());
        stopWatch.start();
        return joinPoint.proceed();
      } finally{
        stopWatch.stop();
        log.info("finish {}.{} execute time = {}", joinPoint.getTarget().getClass().getName(),
        joinPoint.getSignature().getName(),
        stopWatch.getTotalTimeMillis());
      }
      

    
  }


}
