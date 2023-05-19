package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 */
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;
//        获取前端请求的路径
        String url= httpServletRequest.getRequestURI().toString();

        log.info("url为:{}",url);
//        如果是登录操作则直接放行
        if(url.contains("login")){
            log.info("登录操作，放行。。。");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

//        获取请求头中的token值
        String jwt=httpServletRequest.getHeader("token");

//        如果请求头为空则报错，返回登录页面
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头为空，返回错误");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            httpServletResponse.getWriter().write(notLogin);
            return;
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
            return;
        }

        log.info("令牌合法，放行");
//        执行操作
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
