package com.jun.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/get")
public class GetController {
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello";
	}
}
