package com.sun.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginFilter implements Filter {

   
    public LoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 =(HttpServletResponse) response;
		HttpSession session = request2.getSession();
		if (session.getAttribute("user")!=null) {
			chain.doFilter(request, response);
		}else {
			response2.sendRedirect("user");
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
