package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
//        String url= httpServletRequest.getRequestURI().toString();
//
//        log.info("url为:{}",url);
//        if(url.contains("login")){
//            log.info("登录操作，放行。。。");
//
//            return true;
//        }

        //        获取请求头中的token值
        String jwt=httpServletRequest.getHeader("token");

        //        如果请求头为空则报错，返回登录页面
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头为空，返回错误");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notLogin);
            return false;
        }

        //        令牌解析失败则报错，返回登录页面
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌解析失败，返回错误");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notLogin);

//            返回false表示不执行
            return false;
        }

        log.info("令牌合法，放行");
//        返回true表示执行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
