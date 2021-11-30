package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(2)
@Component
public class CacheAspect {
    private final Map<Long, Object> cache = new HashMap<>();

    @Pointcut("execution(public * aop.calculator..*(..))")
    public void cacheTarget() {
        
    }
    
    @Around("cacheTarget()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Long num = (Long) joinPoint.getArgs()[0];
        Signature sig = joinPoint.getSignature();
        if (cache.containsKey(num)) {
            System.out.printf("%s.%s(%s) 캐시에 저장된 값 사용함.\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(), Arrays.toString(joinPoint.getArgs()));
            return cache.get(num);
        }
        
        Object result = joinPoint.proceed();
        cache.put(num, result);
        System.out.printf("%s.%s(%s) 캐시에 새로운 값을 저장함.\n",
                joinPoint.getTarget().getClass().getSimpleName(),
                sig.getName(), Arrays.toString(joinPoint.getArgs()));
        return result;
    }
}
