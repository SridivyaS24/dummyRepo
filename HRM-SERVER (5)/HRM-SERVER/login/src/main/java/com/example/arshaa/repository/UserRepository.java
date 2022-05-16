package com.example.arshaa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.arshaa.entity.EmployeeLogin;

@Repository
public interface UserRepository extends JpaRepository<EmployeeLogin,Integer>
{
	Optional<EmployeeLogin> getByUserName(String userName);
	EmployeeLogin findByUserName(String userName);
	
	EmployeeLogin findByPassword(String oldPassword);

}
