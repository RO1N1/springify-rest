package com.rohan.api.repository;

import java.util.List;

import com.rohan.api.entity.User;

public interface UserRepository {

	public List<User> findAll();
	public User findOne(String id);
	public User createUser(User user);
	public User findByEmail(String email);
	public User updateUser(User user);
	public void deleteUser(User user);
}
