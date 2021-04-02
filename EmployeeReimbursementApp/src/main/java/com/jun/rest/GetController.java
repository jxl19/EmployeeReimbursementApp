package com.jun.rest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jun.dao.EmployeeDAO;
import com.jun.dao.EmployeeDAOImpl;
import com.jun.dao.ManagerDAO;
import com.jun.dao.ManagerDAOImpl;
import com.jun.model.Employee;
import com.jun.model.Reimbursement;
import com.jun.servlets.EmployeeServlet;
import com.jun.util.ConnectionUtil;

@Path("/get")
public class GetController {
	
	public EmployeeDAO employeeDAO;
	public ManagerDAO managerDAO;
	private static Logger log = Logger.getLogger(GetController.class);
	public GetController() {
		super();
		this.employeeDAO = new EmployeeDAOImpl();
		this.managerDAO = new ManagerDAOImpl();
	}

	@GET
	@Path("/employee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmployeeRequests(@PathParam("id") int id) {
		try(Connection con = ConnectionUtil.getConnection()) {
			log.info("Request for employee  " + id + "'s reimbursement requests");
			ObjectMapper map = new ObjectMapper();
			ArrayList<Reimbursement> rList = new ArrayList<>();
			rList = employeeDAO.getAllReimbursementRequests(id, con);
			try {
				return map.writeValueAsString(rList);
			} catch (JsonProcessingException e) {
				log.warn("There was an error trying to grab reimbursement requests for employee " + id);
				return e.getMessage();
			}
		} catch (SQLException e) {
			log.warn("There was an error trying to grab reimbursement requests for employee " + id);
			return e.getMessage();
		}
	}
	
	@GET
	@Path("/all-requests")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllEmployeeRequests() {
		try(Connection con = ConnectionUtil.getConnection()) {
			log.info("Request for all employee reimbursement requests");
			ObjectMapper map = new ObjectMapper();
			ArrayList<Reimbursement> rList = new ArrayList<>();
			rList = managerDAO.getAllReimbursements(con);
			try {
				return map.writeValueAsString(rList);
			} catch (JsonProcessingException e) {
				log.info("Error requesting for all employee reimbursement requests");
				return e.getMessage();
			}
		} catch (SQLException e) {
			log.info("Error requesting for all employee reimbursement requests");
			return e.getMessage();
		}
	}
	
	@GET
	@Path("/all-pending-requests")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllPendingEmployeeRequests() {
		try(Connection con = ConnectionUtil.getConnection()) {
			log.info("Request for all pending employee reimbursement requests");
			ObjectMapper map = new ObjectMapper();
			ArrayList<Reimbursement> rList = new ArrayList<>();
			rList = managerDAO.getAllPendingReimbursements(con);
			try {
				return map.writeValueAsString(rList);
			} catch (JsonProcessingException e) {
				log.info("Error requesting for all pending employee reimbursement requests");
				return e.getMessage();
			}
		} catch (SQLException e) {
			log.info("Error requesting for all pending employee reimbursement requests");
			return e.getMessage();
		}
	}
	
	@GET
	@Path("/all-completed-requests")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCompletedEmployeeRequests() {
		try(Connection con = ConnectionUtil.getConnection()) {
			log.info("Request for all completed employee reimbursement requests");
			ObjectMapper map = new ObjectMapper();
			ArrayList<Reimbursement> rList = new ArrayList<>();
			rList = managerDAO.getAllCompletedReimbursements(con);
			try {
				return map.writeValueAsString(rList);
			} catch (JsonProcessingException e) {
				log.info("Error requesting for all completed employee reimbursement requests");
				return e.getMessage();
			}
		} catch (SQLException e) {
			log.info("Error requesting for all completed employee reimbursement requests");
			return e.getMessage();
		}
	}
	
	@GET
	@Path("/all-employees")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllEmployees() {
		try(Connection con = ConnectionUtil.getConnection()) {
			log.info("Request for all completed employee information");
			ObjectMapper map = new ObjectMapper();
			ArrayList<Employee> eList = new ArrayList<>();
			eList = managerDAO.getAllEmployees(con);
			try {
				return map.writeValueAsString(eList);
			} catch (JsonProcessingException e) {
				log.info("Error requesting for all completed employee information");
				return e.getMessage();
			}
		} catch (SQLException e) {
			log.info("Error requesting for all completed employee information");
			return e.getMessage();
		}
	}
	
}
