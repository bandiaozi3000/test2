//package com.test.aspect;
//
//import com.sun.deploy.util.ArrayUtil;
//import com.test.annotation.Valid;
//import com.test.exception.MyException;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
//@Component
//@Aspect
//public class MyAspect {
//
////  @Before("execution(* com.test.method.MyTestMethod.test())")
////  @Before(value = "@annotation(com.test.annotation.Valid)")
//    @Around(value = "@annotation(com.test.annotation.Valid)")
//    public void valid(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        String[] parameterNames = methodSignature.getParameterNames();
//        Object[] args = joinPoint.getArgs();
//        System.out.println(method.getName()+"........................");
//        System.out.println(ArrayUtil.arrayToString(parameterNames)+"........................");
//       for(Object arg:args){
//           System.out.println(((Integer) arg).intValue());
//       }
//       joinPoint.proceed();
//    }
//
//    private void valid(Valid annotation, Object target, Class<?> type) throws MyException {
//        if (annotation.required()) {
//            if (target == null) {
//                throw new MyException("005", String.format("[%s]字段是必传参数 ", annotation.remark()));
//            }
//        }
//        String parameter = target.toString();
//        if (type == String.class) {
//            if (annotation.length() > 0 && annotation.length() != parameter.length()) {
//                throw new MyException("006", String.format("[%s]字段的长度必须为 %s", annotation.remark(), annotation.length()));
//            }
//        }
//    }
//}
