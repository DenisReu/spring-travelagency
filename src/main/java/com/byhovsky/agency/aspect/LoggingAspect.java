package com.byhovsky.agency.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("repositoryLogging() || serviceLogging()")
    public void loggingInfo(JoinPoint joinPoint) {
        logger.info("class " + joinPoint.getTarget().getClass() + "method" + joinPoint.getSignature().getName() + "args" + Arrays.toString(joinPoint.getArgs()));
    }

    @Pointcut("execution(* com.byhovsky.agency.repository.jdbctemplate..*.*(..))")
    public void repositoryLogging() {
    }

    @Pointcut("execution(* com.byhovsky.agency.service.impl..*.*(..))")
    public void serviceLogging() {
    }

    @AfterThrowing(pointcut = "repositoryLogging()", throwing = "exception")
    public void loggingError(JoinPoint joinPoint, Throwable exception) {
        logger.error("class" + joinPoint.getTarget().getClass().getName() + "method" + joinPoint.getSignature().getName() + "args" + Arrays.toString(joinPoint.getArgs()) + "exceptions" + exception.getMessage());
    }
}
