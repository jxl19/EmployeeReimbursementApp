package com.jun.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//use a dao here to post the data
@Path("/post")
public class PostController {
	
	@POST
	@Path("/createrequest")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRequest(String body) {
		System.out.println("body: " + body);
		return Response.status(200).entity(body).build();
	}
}
