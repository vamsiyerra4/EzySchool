package com.eazybytes.eazyschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.eazybytes.eazyschool..*.*(..))*")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{

        log.info(joinPoint.getSignature().toString() + "method execution start");
        Instant start = Instant.now();
        Object returnObj = joinPoint.proceed();
        Instant finish = Instant.now();
        long timestamp = Duration.between(start,finish).toMillis();
        log.info("Time took to execute " + joinPoint.getSignature().toString() + " method is : " + timestamp);
        log.info(joinPoint.getSignature().toString() + " method execution end");
        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.eazybytes.eazyschool.*.*(..))",throwing = "e")
    public void logException(JoinPoint joinPoint,Exception e){
        log.error(joinPoint.getSignature() + " An exception happened due to : " + e.getMessage());
    }


}
