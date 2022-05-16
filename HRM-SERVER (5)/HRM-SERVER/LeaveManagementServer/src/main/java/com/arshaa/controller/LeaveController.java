package com.arshaa.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arshaa.entity.User;
import com.arshaa.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/leave")
public class LeaveController {

	@Autowired
	private UserService service;

	//@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getLeaveHistoryByEmpid/{employeeleaveId}")
	
	private Optional<User> getUsers(@PathVariable Integer employeeleaveId) {
		
		
			
		return service.findById(employeeleaveId);
	}
	@GetMapping("/getLeaveHistoryByEmployeeid/{employeeId}")	
	public List<User> findUsers(@PathVariable String employeeId) {
				
		return service.findByemployeeId(employeeId);
	}
	@GetMapping("/getAllEmployees")
	public List<User> getAllUsers() {
	return service.findAll();
	}

	@PostMapping("/applyLeave")
	private User saveUser(@RequestBody User user) {
		return 		service.save(user);

	}
}