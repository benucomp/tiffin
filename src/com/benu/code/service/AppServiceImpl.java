package com.benu.code.service;

import java.util.List;
import com.benu.code.dao.AppDAO;
import com.benu.code.entity.User;
import com.benu.code.service.AppService;

import org.springframework.beans.factory.annotation.Autowired;

//Logging file imported
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AppServiceImpl implements AppService{

	@Autowired
	AppDAO appDAO;

	private static Log log = LogFactory.getLog(AppServiceImpl.class);

	public void addUser(User obj) {
		log.debug("In Add method with object " + obj);
		try {			
			appDAO.addUser(obj);			
			log.debug("Add object is successful");
			
		}catch (Exception e) {
			log.fatal("Add Object FAILED with Exception " + e.toString());
			throw e;		
		}        
	}
	
	public void updUser(User obj) {		
		log.debug("In Updated method with object " + obj);
		try {			
			appDAO.updUser(obj);			
			log.info("Object is updated.");
		}catch (Exception e) {
			log.fatal("Exception is " + e.toString());	
			throw e;
		}        
	}
	
	public User getUser(User obj) {
				
		log.debug("In GET method with dao Bean as " + appDAO);
		try {			
			obj = appDAO.getUser(obj);			
			log.info("Data is retrieved as " + obj);
		}catch (Exception e) {
			log.error("Exception is " + e.toString());	
			throw e;
		} 
		return obj;
	}	

	public List<User> getUsers() {

		List<User> rows = null;		
		log.debug("In GET method with dao Bean as " + appDAO);
		try {			
			rows = appDAO.getUsers();			
			log.info("Data is retrieved as " + rows);
		}catch (Exception e) {
			log.error("Exception is " + e.toString());	
			throw e;
		} 
		return rows;
	}
	
	public boolean deleteUser(User obj) {
		boolean isDeleted = false;
		log.debug("In GET method with dao Bean as " + appDAO);
		try {			
			isDeleted = appDAO.deleteUser(obj);			
			log.info("Data is retrieved as " + obj);
		}catch (Exception e) {
			log.error("Exception is " + e.toString());
			throw e;
		} 
		return isDeleted;
	}
}

