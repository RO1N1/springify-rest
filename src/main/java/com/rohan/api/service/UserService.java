package com.rohan.api.service;

import java.util.List;

import com.rohan.api.entity.User;

public interface UserService {

	public List<User> findAll();
	public User findOne(String id);
	public User createUser(User user);
	public User updateUser(User user);
	public void deleteUser(User user);
}
