package tn.esprit.revisionsyrine.Aspects;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Around("execution(void tn.esprit.revisionsyrine.services.*.*(..))")
    public Object logMethodEntry(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Proceed with the actual method execution
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        String methodName = joinPoint.getSignature().getName();
        logger.info("Method: {}, Response Time: {}ms", methodName, responseTime);

        return result;
    }

    @Before("execution(* tn.esprit.revisionsyrine.services.*.*(..))")
    public void logMethodEntry2(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        logger.info("In method " + name + " : ");
    }
}
