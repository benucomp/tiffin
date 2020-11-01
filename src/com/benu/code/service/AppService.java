package com.benu.code.service;

import java.util.List;
import com.benu.code.entity.User;

public interface AppService {
	
	public void addUser(User obj);
	public void updUser(User obj);
	public User getUser(User obj);
	public List<User> getUsers();
	public boolean deleteUser(User obj);

}