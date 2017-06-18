package com.rohan.api.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rohan.api.entity.User;
import com.rohan.api.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public User findOne(String id) {
		System.out.println(id);
		return em.find(User.class, id);
	}

	@Override
	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		return em.merge(user);
	}

	@Override
	public void deleteUser(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("pEmail", email);
		List<User> list = query.getResultList();
		if(!list.isEmpty()) {
			return list.get(0);
		}
		else {
			return null;
		}
	}

}
