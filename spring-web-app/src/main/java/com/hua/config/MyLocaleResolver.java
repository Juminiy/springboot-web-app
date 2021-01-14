package com.hua.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String language = httpServletRequest.getParameter("lang");
        Locale locale = Locale.getDefault();
        //请求的携带了国际化的参数
        if(!StringUtils.isEmpty(language)){
            String []str = language.split("_") ;
            locale = new Locale(str[0],str[1]) ;
        }
        return locale ;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
