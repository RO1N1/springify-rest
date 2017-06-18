package com.rohan.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rohan.api.entity.User;
import com.rohan.api.exception.BadRequestException;
import com.rohan.api.exception.NotFoundException;
import com.rohan.api.repository.UserRepository;
import com.rohan.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository repository;
	
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public User findOne(String id) {
		User existing = repository.findOne(id);
		if(existing == null) {
			throw new NotFoundException("User with id "+ id+ " does not exist");
		}
		return existing;
	}

	@Override
	@Transactional
	public User createUser(User user) {
		User existing = repository.findByEmail(user.getEmail());
		if(existing != null) {
			throw new BadRequestException("User with email "+user.getEmail()+" already exists");
		}
		return repository.createUser(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		User existing = repository.findOne(user.getId());
		if(existing == null) {
			throw new NotFoundException("User with id "+ user.getId()+ " does not exist");
		}
		return repository.updateUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		User existing = repository.findOne(user.getId());
		if(existing == null) {
			throw new NotFoundException("User with id "+ user.getId()+ " does not exist");
		}
		repository.deleteUser(user);
	}

}
