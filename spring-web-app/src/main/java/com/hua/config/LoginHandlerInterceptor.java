package com.hua.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser") ;
        if(loginUser==null) {
            request.setAttribute("msg","没有权限请登录");
            // 这部分是转发到，而不是拦截到
            request.getRequestDispatcher("/index").forward(request,response);
            return false ;
        }else{
            return true ;
        }
    }

}
