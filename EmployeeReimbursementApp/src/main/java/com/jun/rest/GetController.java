package com.jun.rest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jun.dao.EmployeeDAO;
import com.jun.dao.EmployeeDAOImpl;
import com.jun.model.Reimbursement;
import com.jun.util.ConnectionUtil;

@Path("/get")
public class GetController {
	
	public EmployeeDAO employeeDAO;
	public GetController() {
		super();
		this.employeeDAO = new EmployeeDAOImpl();
	}
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello";
	}
	
	@GET
	@Path("/employee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmployeeRequests(@PathParam("id") int id) {
		try(Connection con = ConnectionUtil.getConnection()) {
			ObjectMapper map = new ObjectMapper();
			ArrayList<Reimbursement> rList = new ArrayList<>();
			rList = employeeDAO.getAllReimbursementRequests(id, con);
			try {
				return map.writeValueAsString(rList);
			} catch (JsonProcessingException e) {
				return e.getMessage();
			}
		} catch (SQLException e) {
			return e.getMessage();
		}
	}
}
