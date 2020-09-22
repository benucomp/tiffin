package com.benu.code.dao;

import java.util.List;



//import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.benu.code.dao.HibernateUtil;
import com.benu.code.entity.Student;


public class StudentDAOImpl implements StudentDAO {

	private static Log log = LogFactory.getLog(StudentDAOImpl.class);
	
	@Override
	public void add(Student obj) {
		log.debug("Entering add method with data " + obj);
		try {			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();			
			log.info("The object is added");
		} catch(Exception e) {			
			log.fatal("Exception is " + e.toString());
		}
		
	}

	@Override
	public void upd(Student obj) {
		log.debug("In update distributor with object as " + obj);
		try {			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();			
			log.info("The obj is updated");
		} catch(Exception e) {
			//System.out.println("in EmployeeDAOImpl failure "+e.toString());
			log.fatal("Exception is " + e.toString());
		}
		
	}

	@Override
	public List<Student> get() {
		log.debug("In view method");
		List<Student> rows = null;
		try {			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "select * from student";
			SQLQuery query = session.createSQLQuery(hql);
			query.addEntity(Student.class);
			rows = query.list();
			session.getTransaction().commit();
			
			log.info("The distributor data is retrieved " + rows);
		} catch(Exception e) {
			
			log.fatal("Exception is " + e.toString());
			rows = null;
		}
		return rows;
	}	
}
