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

import org.apache.log4j.Logger;

import com.jun.dao.EmployeeDAO;
import com.jun.dao.EmployeeDAOImpl;
import com.jun.model.Employee;
import com.jun.util.ConnectionUtil;


public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(HomeServlet.class);
	public EmployeeDAO employeeDAO;
    public HomeServlet() {
        super();
        this.employeeDAO = new EmployeeDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher dispatcher;
		HttpSession session=request.getSession();
		
		try (Connection con = ConnectionUtil.getConnection()) {
		Employee user = employeeDAO.getEmployeeInfo((int) session.getAttribute("id"), con);
		
			con.close();
			int id = user.getLoginId();
			session.setAttribute("firstName", user.getFirstName());
			session.setAttribute("lastName", user.getLastName());
			session.setAttribute("id", id);
			session.setAttribute("email", user.getEmail());
			boolean isManager = user.isManager();
			
			if (!isManager) {
				log.info("User " + id + " has accessed the home page.");
				dispatcher = getServletContext().getRequestDispatcher("/jsp/employee.jsp");
				dispatcher.forward(request, response);
			} else if (isManager){
				log.info("Manager " + id + " has accessed the home page.");
				dispatcher = getServletContext().getRequestDispatcher("/jsp/manager.html");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
