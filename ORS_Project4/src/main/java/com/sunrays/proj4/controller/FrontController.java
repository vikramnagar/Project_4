 	package com.sunrays.proj4.controller;

import com.sunrays.proj4.controller.ORSView;
import com.sunrays.proj4.util.ServletUtility;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller. It prevents any user to access
 * application without login.
 * 
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebFilter(filterName = "FrontController", urlPatterns = { "/ctl/*", "/doc/*" })
public class FrontController implements Filter 
{
	public void init(FilterConfig conf) throws ServletException {	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)  //FilterChain is an Interface
			throws IOException, ServletException 
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
        
 		HttpSession session = request.getSession(true);
     	if (session.getAttribute("user") == null) 
     	{   
      		request.setAttribute("message", "Your session has been expired. Please re-Login.");
			String str = request.getRequestURI();
   		    request.setAttribute("uri", str);
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
		}else 
		{
			chain.doFilter(req, resp);         //call next filter Or target servlet
		}
     }
	public void destroy() {	}
}