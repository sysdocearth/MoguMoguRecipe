package com.sysdoccode.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginRecipeFilter implements Filter {
    
    private final UserService userService = UserServiceFactory.getUserService();
    
    @Override
    public void init(FilterConfig fConfig) throws ServletException
    {
        //パラメータ指定はない
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
    }
    
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        User user = userService.getCurrentUser();
        if(user != null) {
            chain.doFilter(request, response);

        }else{
            //((HttpServletResponse) response).sendRedirect(userService.createLoginURL("/recipe"));
            ((HttpServletResponse) response).sendRedirect(userService.createLoginURL("/recipe/index.html"));

            return;
        }
    }
    
    @Override
    public void destroy()
    {
    }
}
