package club.yusei37.managesystem.aop;

import java.lang.annotation.*;

/**
 * Created by Yusei on 2018/12/26
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Cacheable {
    /**
     * 键
     * @return
     */
    String key() default "";

    /**
     * 过期时间
     * @return
     */
    long expired() default -1;

}
