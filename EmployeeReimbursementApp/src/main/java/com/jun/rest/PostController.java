package com.jun.rest;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jun.dao.EmployeeDAO;
import com.jun.dao.EmployeeDAOImpl;
import com.jun.dao.ManagerDAO;
import com.jun.dao.ManagerDAOImpl;
import com.jun.model.Employee;
import com.jun.model.Reimbursement;
import com.jun.model.UpdateRequests;
import com.jun.util.ConnectionUtil;

@Path("/post")
public class PostController {
	
	public EmployeeDAO employeeDAO;
	public ManagerDAO managerDAO;
	private static Logger log = Logger.getLogger(PostController.class);
	public PostController() {
		super();
		this.employeeDAO = new EmployeeDAOImpl();
		this.managerDAO = new ManagerDAOImpl();
	}
	
	@POST
	@Path("/createrequest")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRequest(String body) {
		log.info("Request to create new reimbursement.");
		ObjectMapper map = new ObjectMapper();
		String response = null;
		try {
			System.out.println("bodyinside " + body);
			Reimbursement reimburse = map.readValue(body, Reimbursement.class);
			System.out.println("re: " + reimburse);
			try(Connection con = ConnectionUtil.getConnection()) {				
				Reimbursement r = employeeDAO.createNewReimbursement(reimburse.getUserId(), reimburse.getAmount(), reimburse.getReason(), con);
				response = map.writeValueAsString(r);
			} catch (SQLException e) {
				log.info("Error trying to create new reimbursement.");
				e.getMessage();
			}
		} catch (JsonProcessingException e) {
			log.info("Error trying to create new reimbursement.");
			e.getMessage();
		}
		return Response.status(200).entity(response).build();
	}

	@POST 
	@Path("/review-request")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRequest(String body) {
		log.info("Manager updating reimbursement request.");
		String response = null;
		ObjectMapper map = new ObjectMapper();
		try {
			UpdateRequests ur = map.readValue(body, UpdateRequests.class);
			try(Connection con = ConnectionUtil.getConnection()) {
				UpdateRequests approved = managerDAO.reviewReimbursement(ur.isApproved(), ur.getrId(), con);
				log.info("Reimbursement request is: " + ur.isApproved());
				response = map.writeValueAsString(approved);
			} catch (SQLException e) {
				e.getMessage();
			}
		} catch (JsonProcessingException e) {
			e.getMessage();
		}
		return Response.status(200).entity(response).build();
	}

	@PUT 
	@Path("/update-user/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(String body, @PathParam("id") String id) {
		String response = null;
		ObjectMapper map = new ObjectMapper();
		try(Connection con = ConnectionUtil.getConnection()) {
			log.info("User " + id +" is updating information");
			JsonNode jsonNode = map.readTree(body);
			String firstName = jsonNode.get("firstName").asText();
			String lastName = jsonNode.get("lastName").asText();
			String email = jsonNode.get("email").asText();
			String password = jsonNode.get("password").asText();
			Employee e = employeeDAO.updateEmployee(id, firstName, lastName, email, password, con);
			response = map.writeValueAsString(e);
		} catch (SQLException | JsonProcessingException e) {
			log.info("Error updating information");
			e.getMessage();
		} 
		log.info("User " + id +" has successfully updated their information");
		return Response.status(200).entity(response).build();
	}
}
