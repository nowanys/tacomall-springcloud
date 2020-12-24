package store.tacomall.common.aspect;

import java.util.HashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import store.tacomall.common.feign.MqKafkaFeignClient;

@Aspect
@Component
@Order(1)
public class UserLoggerAspect {

    @Autowired
    private MqKafkaFeignClient kafkaFeignClient;

    @Pointcut("execution(public * store.tacomall.api*.controller..*.*(..))")
    public void logger() {
    }

    @Around("logger()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object res = point.proceed();
        kafkaFeignClient.logSend(new HashMap<>());
        return res;
    }

}
