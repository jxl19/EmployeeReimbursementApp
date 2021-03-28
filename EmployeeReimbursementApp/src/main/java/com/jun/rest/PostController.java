package com.jun.rest;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jun.dao.EmployeeDAO;
import com.jun.dao.EmployeeDAOImpl;
import com.jun.model.Reimbursement;
import com.jun.util.ConnectionUtil;

@Path("/post")
public class PostController {
	
	public EmployeeDAO employeeDAO;
	public PostController() {
		super();
		this.employeeDAO = new EmployeeDAOImpl();
	}
	
	@POST
	@Path("/createrequest")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRequest(String body) {
		ObjectMapper map = new ObjectMapper();
		String response = null;
		try {
			Reimbursement reimburse = map.readValue(body, Reimbursement.class);
			try(Connection con = ConnectionUtil.getConnection()) {				
				Reimbursement r = employeeDAO.createNewReimbursement(reimburse.getrId(), reimburse.getAmount(), reimburse.getReason(), con);
				response = map.writeValueAsString(r);
			} catch (SQLException e) {
				e.getMessage();
			}
		} catch (JsonProcessingException e) {
			e.getMessage();
		}
		return Response.status(200).entity(response).build();
	}

}
