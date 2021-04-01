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

import com.jun.dao.EmployeeDAO;
import com.jun.dao.EmployeeDAOImpl;
import com.jun.dao.LoginDAO;
import com.jun.dao.LoginDAOImpl;
import com.jun.model.Employee;
import com.jun.util.ConnectionUtil;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public EmployeeDAO employeeDAO;
	
    public EmployeeServlet() {
        super();
        this.employeeDAO = new EmployeeDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    		response.setContentType("text/html");
    		RequestDispatcher dispatcher;
    		dispatcher = getServletContext().getRequestDispatcher("/jsp/update-info.jsp");
    		dispatcher.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException | NullPointerException e) {
			e.getMessage();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException | NullPointerException e) {
			e.getMessage();
		}
	}

}
