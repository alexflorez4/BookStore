package com.vpp.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * This implementation of aspectj framework replaces the invoke method on SimpleLoggingAdvice
 * but no interface impl. is needed.  As long as the ProceedingJoinPoint parameter is in there.
 * In Addition, this implementation is needed in order to use AspectJ Pointcut syntax.
 */
@Aspect
@Component
public class PerformanceTimingAdvice
{
    @Pointcut("execution( * com.vpp.services.*.* (..))")
    public void allServiceMethods(){}


    //ProceedingJoinPoint argument is only needed when applying around advice.
    @Around("allServiceMethods()")
    public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable
    {
        //before
        long startTime = System.nanoTime();

        try {
            //proceed to target
            Object returnedValue = method.proceed();
            return returnedValue;
        }
        finally {
            //after
            long endTime = System.nanoTime();

            long timeTaken = endTime - startTime;
            System.out.println("The method " + method.getSignature().getName() + " took " + timeTaken + " nanoseconds");
        }
    }

    //Argument JoinPoint is optional.
    @Before("allServiceMethods()")
    public void beforeAdviceTesting(JoinPoint jp)
    {
        System.out.println("Now entering a method " + jp.getSignature().getName() );
    }
}
