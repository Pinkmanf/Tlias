package com.itheima.aop;


import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 *切面类
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        获取前端的token-jwt令牌
        String jwt = httpServletRequest.getHeader("token");
//        解析jwt令牌
        Claims claims = JwtUtils.parseJWT(jwt);
//        获取操作者的id
        Integer operateUser = (Integer) claims.get("id");


//        记录操作时间为当前
        LocalDateTime operateTime = LocalDateTime.now();

//        获取类名
        String className = joinPoint.getTarget().getClass().getName();

//        获取方法名
        String methodName = joinPoint.getSignature().getName();

//        获取参数
        Object[] args = joinPoint.getArgs();
        String methodParams= Arrays.toString(args);

//        记录操作耗时
        long begin = System.currentTimeMillis();
//        执行操作
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

//        获取返回值
        String returnValue = JSONObject.toJSONString(result);

//        计算耗时
        Long costTime=end-begin;

//        封装到对象中并且插入到数据库里面
        OperateLog operateLog=new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}",operateLog);

        return result;
    }
}
