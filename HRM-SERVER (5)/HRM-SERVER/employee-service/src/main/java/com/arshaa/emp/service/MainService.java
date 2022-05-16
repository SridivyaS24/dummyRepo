package com.arshaa.emp.service;

import org.springframework.http.ResponseEntity;

import com.arshaa.emp.entity.EmployeeMaster;
import com.arshaa.emp.entity.Onboarding;
import com.arshaa.emp.model.HrApprovalStatus;


public interface MainService {

	public ResponseEntity  onBoardUser(Onboarding newOnboard);
	public ResponseEntity<Onboarding> waitingForApprovelStatus();
	public ResponseEntity updateApprovStatus(String onboardingId,HrApprovalStatus newOnboard);
	public ResponseEntity getEmployeeDataByEmployeeId(String employeeId);
	
	public ResponseEntity updateEmployeeDataByEmployeeId(String employeeId, EmployeeMaster empMaster);
	public ResponseEntity<EmployeeMaster> getALLData();

}
