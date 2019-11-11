package com.animebox.animebox.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.USER_AGENT;

public class BaseInterceptor implements HandlerInterceptor {
//public class BaseInterceptor  {
    private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("UserAgent: "+request.getHeader(USER_AGENT));
//        LOGGE.info("UserAgent: {}", request.getHeader(USER_AGENT));
//        LOGGE.info("用户访问地址: {}, 来路地址: {}", uri, IPKit.getIpAddrByRequest(request));

//        String uri = request.getRequestURI();
//        //拦截器处理用户权限
//        if (uri.startsWith("/admin") && !uri.startsWith("/user/info/edit/{id}")) {
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return false;
//        }
        System.out.println("拦截器执行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //这个方法可以往request中添加一些公共的工具类给前端页面进行调用
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //当请求处理完成调用
    }
}
