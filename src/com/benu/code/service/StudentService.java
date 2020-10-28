package com.benu.code.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;

import com.benu.code.entity.Student;

public interface StudentService {
	public String helloStudent(String name);
	public void add(Student obj);
	public void upd(Student obj);
	public List<Student> get();

}