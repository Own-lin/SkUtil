package com.lynn.skutil.spring.annotation.aspect;

import com.lynn.skutil.spring.OpDataInterface;
import com.lynn.skutil.spring.annotation.OpRecord;
import com.lynn.skutil.spring.unit.OpStandardData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author zhan yan
 * @date 2022/11/16
 */
@Component
public class OpAspect {


    @Pointcut("@annotation(com.lynn.skutil.spring.annotation.OpRecord)")
    public void cut(){}

    @SuppressWarnings({"rawtypes", "deprecation", "unchecked"})
    @Around("cut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        try {
            result = point.proceed();
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            OpRecord annotation = methodSignature.getMethod().getAnnotation(OpRecord.class);
            Class<? extends OpDataInterface> clazz = annotation.target();
            OpDataInterface ovClazz = clazz.newInstance();
            OpStandardData converted;
            converted = ovClazz.convert(point.getArgs()[0]);
            //  Do tings after this command

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return result;
    }


}
