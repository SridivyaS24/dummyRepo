package com.arshaa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arshaa.entity.User;
import com.arshaa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Optional<User> findById(int employeeleaveId) {
		return repository.findById(employeeleaveId);
	}
//	public Optional<User> findByemployeeId(String employeeId) {
//		return repository.getAllUserByemployeeId(employeeId);
//
//	}

	public User save(User user) {
		 return repository.save(user);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll() ;
	}
	public List<User> findByemployeeId(String employeeId) {
		// TODO Auto-generated method stub
		return (List<User>) repository.findByemployeeId(employeeId);
	}
}
