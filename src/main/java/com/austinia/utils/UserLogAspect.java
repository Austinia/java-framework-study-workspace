package com.austinia.utils;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class UserLogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // PointCut : 적용할 지점 또는 범위
    @Pointcut("execution(public * com.austinia.repository.UserDao.*(..))")
    private void publicTarget() {
    }

    // Around : 실제 부가기능 구현
    @SneakyThrows
    @Around("publicTarget()")
    public Object queryPerformanceAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        logger.info("쿼리 성능 측정을 시작합니다");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // Service Method
        Object result = proceedingJoinPoint.proceed();

        stopWatch.stop();
        logger.info("쿼리 성능 측정이 끝났습니다");
        logger.info("쿼리 실행 시간: {} ms", stopWatch.getLastTaskTimeMillis());
        return result;
    }
}
