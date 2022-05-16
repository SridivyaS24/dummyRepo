package com.arshaa.designation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.arshaa.designation.entity.Designationmaster;
import com.arshaa.designation.repository.DesignationInterface;
import com.arshaa.designation.service.DesignationServcie;

@RequestMapping("/designation")
@RestController
@CrossOrigin("*")
public class DesignationController {

	public DesignationController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired(required = true)
	private DesignationServcie service;

@Autowired
private DesignationInterface repo;

@PostMapping("/postDesignationMaster")
public ResponseEntity<Designationmaster> saveData(@RequestBody Designationmaster newDesignationMaster) {
	return service.saveDesignationMaster(newDesignationMaster);
}

@GetMapping("/getAllDesignations")
public List<Designationmaster> getAllDesignations() {
	return repo.findAll();
}
@PutMapping("/updateDesignations/{designationId}")
public Designationmaster updateDesignationById(@PathVariable Integer designationId, @RequestBody Designationmaster newDesignationmaster) {
	Designationmaster dm = repo.findByDesignationId(designationId);
	dm.setDesignationName(newDesignationmaster.getDesignationName());
	return repo.save(dm);
}

@DeleteMapping("/deleteDesignation/{designationId}")
public String  deleteDesignationById(@PathVariable Integer designationId) {
	Designationmaster dm = repo.findByDesignationId(designationId);
	repo.delete(dm);
	return "Deleted Successfully";
}


}
