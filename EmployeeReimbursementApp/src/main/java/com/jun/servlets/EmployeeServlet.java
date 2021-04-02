package com.jun.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jun.dao.EmployeeDAO;
import com.jun.dao.EmployeeDAOImpl;

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public EmployeeDAO employeeDAO;
	private static Logger log = Logger.getLogger(EmployeeServlet.class);
	
    public EmployeeServlet() {
        super();
        this.employeeDAO = new EmployeeDAOImpl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    		response.setContentType("text/html");
    		RequestDispatcher dispatcher;
    		HttpSession session=request.getSession();  
   		 	log.info("User " + session.getAttribute("id") + " has accessed the update information page.");
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
