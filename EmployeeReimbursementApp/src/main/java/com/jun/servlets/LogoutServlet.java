package com.jun.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LogoutServlet.class);

    public LogoutServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("logging out");
		 HttpSession session=request.getSession();  
		 log.info("User " + session.getAttribute("id") + " has logged out");
         session.invalidate();  
         response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
	}

}
