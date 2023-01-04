package com.sktuil.spring.spring.annotation;

import com.sktuil.spring.spring.OpDataInterface;
import com.sktuil.spring.spring.unit.OpStandardData;

import java.lang.annotation.*;

/**
 * <P>此注解在POST请求中使用最佳</p>
 * <b>作用：</b>可通过实现 {@link OpDataInterface} 接口重写convert方法，使输入值转换为继承于{@link OpStandardData}的对象即可
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
