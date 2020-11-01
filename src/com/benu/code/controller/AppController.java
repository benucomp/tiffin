package com.benu.code.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

//Logging file imported
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.benu.code.entity.User;
import com.benu.code.service.*;

@Path("Services")
public class AppController {

	@Autowired
	private AppService service;

	private static Log log = LogFactory.getLog(AppController.class);

	@RolesAllowed("ADMIN")
	@POST
	@Path("/addUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User obj) {	
		System.out.println("1");
		log.debug("In Add method with object "+obj);
		try {
			service.addUser(obj);
			log.info("Object added");
			return Response
					.ok()
					.type(MediaType.APPLICATION_JSON)
					.entity(obj)
					.build();

		} catch (Exception e) {
			log.fatal("Exception is " + e.toString());
			return Response
					.status(Response.Status.NOT_FOUND)
					.type(MediaType.APPLICATION_JSON)
					.entity(obj)
					.build();
		}		
	}

	@RolesAllowed("ADMIN")
	@POST
	@Path("/updUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updUser(User obj) {	

		log.debug("In Add method with object "+obj);
		try {
			service.updUser(obj);
			log.info("Object updated");
			return Response
					.ok()
					.type(MediaType.APPLICATION_JSON)
					.entity(obj)
					.build();

		} catch (Exception e) {
			log.fatal("Exception is " + e.toString());
			return Response
					.status(Response.Status.NOT_FOUND)
					.type(MediaType.APPLICATION_JSON)
					.entity(obj)
					.build();
		}		

	}

	@RolesAllowed("ADMIN")
	@GET
	@Path("/getUsers")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getUsers() {				
		List<User> usersListObj = null;
		try {
			usersListObj=service.getUsers();
			log.info("Users list retrieved");
			return Response
					.ok()
					.type(MediaType.APPLICATION_JSON)
					.entity(usersListObj)
					.build();

		} catch (Exception e) {
			log.fatal("Exception is " + e.toString());
			return Response
					.status(Response.Status.NOT_FOUND)
					.type(MediaType.APPLICATION_JSON)
					.entity(usersListObj)
					.build();
		}

	}

	@RolesAllowed("ADMIN")
	@POST
	@Path("/getUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(User userObj) {				
		log.debug("In GET method");
		try {
			log.debug("In GET method with bean as : " + service);
			userObj = service.getUser(userObj);
			log.info("Objects retrieved " +userObj.getUserName());
			return Response
					.ok()
					.type(MediaType.APPLICATION_JSON)
					.entity(userObj)
					.build();
		} catch (Exception e) {
			log.fatal("Exception is " + e.toString());
			return Response
					.status(Response.Status.NOT_FOUND)
					.type(MediaType.APPLICATION_JSON)
					.entity(userObj)
					.build();
		}		
	}
	@RolesAllowed("ADMIN")
	@POST
	@Path("/deleteUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(User obj) {	
		boolean isDeleted = false;
		String deleteStatus = "";
		log.debug("In Add method with object "+obj);
		try {
			isDeleted = service.deleteUser(obj);

			deleteStatus = Boolean.toString(isDeleted);
			log.info("Object deleted");
			return Response
					.ok()
					.type(MediaType.TEXT_PLAIN)
					.entity(deleteStatus)					.build();

		} catch (Exception e) {
			log.fatal("Exception is " + e.toString());
			return Response
					.status(Response.Status.NOT_FOUND)
					.type(MediaType.TEXT_PLAIN)
					.entity(deleteStatus)
					.build();
		}
	}	 	 
}