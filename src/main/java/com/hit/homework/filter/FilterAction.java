package com.hit.homework.filter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.hit.homework.conmon.JwtUtils;
import com.hit.homework.conmon.Result;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class FilterAction implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.请求url
        String url = request.getRequestURI().toString();

        //2.判断是否为登录请求
        if(url.contains("login")||url.contains("register")){
            //登录与注册请求,直接放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //3.获取请求头中的令牌--密钥
        String jwt = request.getHeader("token");
        //postman测试应该放在header里面

        //4.判断令牌是否存在
        if (StringUtils.isEmpty(jwt)){
            //请求头不存在
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }

        //5.判断令牌是否合法
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            //jwt解析失败
            Result error = Result.error("NOT_LOGIN");
            String JWTError = JSONObject.toJSONString(error);
            response.getWriter().write(JWTError);
            return;
        }

        //令牌合法
        filterChain.doFilter(servletRequest,servletResponse);
    }
}