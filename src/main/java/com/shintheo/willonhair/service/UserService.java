package com.shintheo.willonhair.service;

import java.util.List;

import com.shintheo.willonhair.entity.UserDao;

public interface UserService {
	UserDao submitUser(UserDao user);

	UserDao getUserById(long id);

	List<UserDao> getAllUsers();
	
	void resetPassword(String emailAddress);

	UserDao getUserByEmail(String email);

	UserDao updateUser(UserDao user);

	UserDao deleteUser(UserDao user);

}
