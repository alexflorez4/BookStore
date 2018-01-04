package com.vpp.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SimpleLoggingAdvice implements MethodBeforeAdvice, AfterReturningAdvice, MethodInterceptor
{
    //MethodBeforeAdvice
    @Override
    public void before(Method method, Object[] arguments, Object targetObject) throws Throwable
    {
        System.out.println("Now about to call the " + method.getName() + " method.");
    }

    //AfterReturningAdvice
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("Now finished calling the " + method.getName() + " method.");
        System.out.println("The target method returned value " + returnValue);
    }

    //MethodInterceptor
    @Override
    public Object invoke(MethodInvocation method) throws Throwable {

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
            System.out.println("The method " + method.getMethod().getName() + " took " + timeTaken + " nanoseconds");
        }
    }
}
