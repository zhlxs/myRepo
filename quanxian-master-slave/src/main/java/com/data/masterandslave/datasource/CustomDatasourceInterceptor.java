package com.data.masterandslave.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
public class CustomDatasourceInterceptor implements Ordered {

//    @Pointcut("execution(* com.quanxian.readwrite.demo.service..*insert*(..))||" +
//            "execution(* com.quanxian.readwrite.demo.service..*update*(..))||" +
//            "execution(* com.quanxian.readwrite.demo.service..*delete*(..))||" +
//            "execution(* com.quanxian.readwrite.demo.service..*save*(..))||" +
//            "execution(* com.quanxian.readwrite.demo.mapper..*insert*(..))||" +
//            "execution(* com.quanxian.readwrite.demo.mapper..*update*(..))||" +
//            "execution(* com.quanxian.readwrite.demo.mapper..*delete*(..))||" +
//            "execution(* com.quanxian.readwrite.demo.mapper..*save*(..))")
//    public void pointCutInsert() {
//    }

    @Pointcut("execution(* com.data.masterandslave.service..*select*(..))||" +
            "execution(* com.data.masterandslave.service..*get*(..))||" +
            "execution(* com.data.masterandslave.mapper..*select*(..))||" +
            "execution(* com.data.masterandslave.mapper..*get*(..))||" +
            "@annotation(com.data.masterandslave.datasource.Datasource)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object setRead(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Signature sig = joinPoint.getSignature();
            MethodSignature methodSignature = null;
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能应用于方法");
            } else {
                methodSignature = (MethodSignature) sig;
                Method method = methodSignature.getMethod();
                Annotation[] annotations = method.getAnnotations();
                Set<String> collect = Arrays.stream(annotations).map(item -> item.annotationType().getTypeName()).collect(Collectors.toSet());
                if (method != null && collect.contains(Datasource.class.getName())) {
                    Datasource data = method.getAnnotation(Datasource.class);
                    DBContextHolder.set(data.value());
                }
            }
            return joinPoint.proceed();
        } finally {
            //清楚DbType一方面为了避免内存泄漏，更重要的是避免对后续在本线程上执行的操作产生影响
            DBContextHolder.clearDbType();
        }
    }


//
//    @Around("pointCutInsert()")
//    public Object setReadinsert(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            DBContextHolder.set(DatasourceName.MASTER);
//            return joinPoint.proceed();
//        } finally {
//            //清楚DbType一方面为了避免内存泄漏，更重要的是避免对后续在本线程上执行的操作产生影响
//            DBContextHolder.clearDbType();
//        }
//    }



    @Override
    public int getOrder() {
        return 1;
    }
}