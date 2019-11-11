package com.animebox.animebox.interceptor;

import com.animebox.animebox.bean.user.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName UpdateUserInfoInterceptor
 * @Description: TODO 对更改用户信息的拦截器
 * @Author: LuckyRabbit
 * @create: 2019-10-17 13:33
 * @Version 1.0
 */
//public class UpdateUserInfoInterceptor {
public class UpdateUserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("loginUser");
        if(user != null){
            String strStarsWith = "/user/info/edit/"+user.getIntUserId();
            if(uri.startsWith(strStarsWith)){
                return true;
            }
            strStarsWith = "/circle/"+user.getIntUserId()+"/settings";
            if(uri.startsWith(strStarsWith)){
                return true;
            }
        }
        System.out.println(request.getRemoteAddr()+"这逼尝试修改别人的数据");
        response.sendRedirect(request.getContextPath() + "/user/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}