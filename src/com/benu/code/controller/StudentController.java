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
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

//Logging file imported
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.benu.code.entity.Student;
import com.benu.code.service.*;

@Path("Services")
public class StudentController {

	@Autowired
	private StudentService service;

	private static Log log = LogFactory.getLog(StudentController.class);
	
	@RolesAllowed("ADMIN")
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Student obj) {	
		
		log.debug("In Add method with object "+obj);
		try {
			service.add(obj);
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

	@GET
	@Path("/getName")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getName() {				
		Student student = new Student();
		student.setName("Rockey");		
		return student;
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> get() {				
		log.debug("In GET method");
		List<Student> obj = null;		
		
		try {
			log.debug("In GET method with bean as : " + service);
			obj = service.get();
			log.info("Objects retrieved");
		} catch (Exception e) {
			log.fatal("Exception is " + e.toString());
		}		
		return obj;
	}	
}