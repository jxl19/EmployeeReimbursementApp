package com.jun.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jun.dao.LoginDAO;
import com.jun.dao.LoginDAOImpl;
import com.jun.model.Login;
import com.jun.util.ConnectionUtil;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public LoginDAO loginDAO;
	
    public LoginServlet() {
        super();
        this.loginDAO = new LoginDAOImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	try (Connection con = ConnectionUtil.getConnection()) {
    		RequestDispatcher dispatcher;
    		HttpSession session=request.getSession();
    		
    		String username = request.getParameter("username");
    		String password = request.getParameter("password");
    		session.setAttribute("username", username);
    		Login user = loginDAO.authenticateUser(username, password, con);
    		System.out.println(user);
//		if(username.equals("jlei")) {
//			dispatcher = getServletContext().getRequestDispatcher("/employee.jsp");
//			dispatcher.forward(request, response);
//		} else if(username.equals("manager")){
//			dispatcher = getServletContext().getRequestDispatcher("/manager.html");
//			dispatcher.forward(request, response);
//		}
//		else {
//			dispatcher = getServletContext().getRequestDispatcher("/login.html");
//			dispatcher.forward(request,response);
//		}
    	}
		
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
