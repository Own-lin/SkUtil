package com.lynn.skutil.spring.annotation;

import com.lynn.skutil.spring.OpDataInterface;

import java.lang.annotation.*;

/**
 * 注意，此注解在POST请求中使用最佳
 * @author zhan yan
 * @date 2022/11/16
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpRecord {

    String desc() default "";

    @SuppressWarnings("rawtypes")
    Class<? extends OpDataInterface> target();

}
