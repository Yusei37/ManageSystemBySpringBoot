package club.yusei37.managesystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
        Method method= getMethod(proceedingJoinPoint);
        String fieldKey =parseKey(cache.key(), method, proceedingJoinPoint.getArgs());
        System.out.println(fieldKey);
        if(cache != null ) {
            Object obj = redisTemplate.opsForValue().get(fieldKey);
            if (obj == null) {
                obj = proceedingJoinPoint.proceed();
                if (obj != null) {
                    if (cache.expired() > 0) {
                        redisTemplate.opsForValue().set(fieldKey, obj, cache.expired(), TimeUnit.SECONDS);
                    } else {
                        redisTemplate.opsForValue().set(fieldKey, obj);
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

    /**
     *  获取被拦截方法对象
     *
     *  MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     *    而缓存的注解在实现类的方法上
     *  所以应该使用反射获取当前对象的方法对象
     */
    public Method getMethod(ProceedingJoinPoint pjp){
        //获取参数的类型
        Object [] args=pjp.getArgs();
        Class [] argTypes=new Class[pjp.getArgs().length];
        for(int i=0;i<args.length;i++){
            argTypes[i]=args[i].getClass();
        }
        Method method=null;
        try {
            method=pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(),argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return method;

    }

    /**
     *    获取缓存的key
     *    key 定义在注解上，支持SPEL表达式
     * @param pjp
     * @return
     */
    private String parseKey(String key, Method method, Object [] args){


        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        String [] paraNameArr=u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for(int i=0;i<paraNameArr.length;i++){
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context,String.class);
    }
}
