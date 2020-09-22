package com.benu.code.dao;

import java.util.List;
import com.benu.code.entity.Student;

public interface StudentDAO {
	public void add(Student obj);
	public void upd(Student obj);
	public List<Student> get();
}
