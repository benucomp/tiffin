package com.benu.code.service;

import java.util.List;
import com.benu.code.dao.StudentDAO;
import com.benu.code.entity.Student;
import com.benu.code.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;

//Logging file imported
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentDAO studentDAO;

	private static Log log = LogFactory.getLog(StudentServiceImpl.class);

	@Override
	public String helloStudent(String name) {
		return "Hello "+name;
	}

	public void add(Student obj) {
		log.debug("In Add method with object " + obj);
		try {			
			studentDAO.add(obj);			
			log.info("Add object is successful");
		}catch (Exception e) {
			log.fatal("Add Object FAILED with Exception " + e.toString());			
		}        
	}

	public void upd(Student obj) {		
		log.debug("In Updated method with object " + obj);
		try {			
			studentDAO.upd(obj);			
			log.info("Object is updated.");
		}catch (Exception e) {
			log.fatal("Exception is " + e.toString());			
		}        
	}

	public List<Student> get() {

		List<Student> rows = null;		
		log.debug("In GET method with dao Bean as " + studentDAO);
		try {			
			rows = studentDAO.get();			
			log.info("Data is retrieved as " + rows);
		}catch (Exception e) {
			log.error("Exception is " + e.toString());			
		} 
		return rows;
	}		
}

