package com.lzscoding.demozookeeper.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 180626
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ZooLock {
    /**
     * 分布式锁的键
     */
    String key();

    /**
     *  锁释放时间,默认
     */
    long timeout() default 5 * 1000;

    /**
     * 时间格式,默认:毫秒
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
