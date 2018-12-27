package club.yusei37.managesystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yusei on 2018/12/26
 */
@Aspect
@Component
public class CacheAspect {

    @Resource
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(club.yusei37.managesystem.aop.Cacheable)")
    private void CachePointCut() {
    }

    @Around("CachePointCut() && @annotation(Cacheable)")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Cacheable cache = ((MethodSignature)proceedingJoinPoint.getSignature()).getMethod().getAnnotation(Cacheable
                .class);
        if(cache != null ) {
            Object obj = redisTemplate.opsForValue().get(cache.key());
            if (obj == null) {
                obj = proceedingJoinPoint.proceed();
                if (obj != null) {
                    if (cache.expired() > 0) {
                        redisTemplate.opsForValue().set(cache.key(), obj, cache.expired(), TimeUnit.SECONDS);
                    } else {
                        redisTemplate.opsForValue().set(cache.key(), obj);
                    }
                }
            }
            else {
                System.out.println("命中缓存");
            }
            return obj;
        }
        return null;
    }

}
