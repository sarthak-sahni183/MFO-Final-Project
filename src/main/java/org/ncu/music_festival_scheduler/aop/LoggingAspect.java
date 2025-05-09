package org.ncu.music_festival_scheduler.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    // Pointcut: all methods in controller and service packages
    @Pointcut("execution(* org.ncu.music_festival_scheduler.controller..*(..)) || execution(* org.ncu.music_festival_scheduler.service..*(..))")
    public void appFlow() {}

    // 1. BEFORE Advice
    @Before("appFlow()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("🔵 BEFORE: " + joinPoint.getSignature());
        System.out.println("📥 Args: " + Arrays.toString(joinPoint.getArgs()));
    }

    // 2. AFTER Advice (runs regardless of outcome)
    @After("appFlow()")
    public void logAfterMethod(JoinPoint joinPoint) {
        System.out.println("🟡 AFTER (finally): " + joinPoint.getSignature());
    }

    // 3. AFTER RETURNING Advice
    @AfterReturning(pointcut = "appFlow()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("🟢 AFTER RETURNING: " + joinPoint.getSignature());
        System.out.println("📦 Returned: " + result);
    }

    // 4. AFTER THROWING Advice
    @AfterThrowing(pointcut = "appFlow()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("🔴 AFTER THROWING: " + joinPoint.getSignature());
        System.out.println("❌ Exception: " + ex.getMessage());
    }

    // 5. AROUND Advice (wraps method execution)
    @Around("appFlow()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("🌀 AROUND (BEFORE): " + pjp.getSignature());
        long start = System.currentTimeMillis();

        try {
            Object result = pjp.proceed();  // proceed to target method
            long duration = System.currentTimeMillis() - start;
            System.out.println("🌀 AROUND (AFTER): " + pjp.getSignature() + " took " + duration + "ms");
            return result;
        } catch (Throwable ex) {
            System.out.println("💥 AROUND Exception in: " + pjp.getSignature() + " → " + ex.getMessage());
            throw ex;
        }
    }
}
