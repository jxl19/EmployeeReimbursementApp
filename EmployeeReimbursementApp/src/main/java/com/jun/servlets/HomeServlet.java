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
import com.jun.model.Employee;
import com.jun.util.ConnectionUtil;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeDAO employeeDAO;
    public HomeServlet() {
        super();
        this.employeeDAO = new EmployeeDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		RequestDispatcher dispatcher;
		HttpSession session=request.getSession();
		System.out.println("id inside homepage " + session.getAttribute("id"));
		
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
				dispatcher = getServletContext().getRequestDispatcher("/jsp/employee.jsp");
				dispatcher.forward(request, response);
			} else if (isManager){
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
