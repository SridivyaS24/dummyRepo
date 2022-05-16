package com.arshaa.emp.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arshaa.emp.common.EmployeeLogin;
import com.arshaa.emp.common.UserModel;
import com.arshaa.emp.common.Users;
import com.arshaa.emp.entity.EmployeeMaster;
import com.arshaa.emp.entity.Onboarding;
import com.arshaa.emp.model.HrApprovalStatus;
import com.arshaa.emp.model.Response;
import com.arshaa.emp.model.StringConstants;
import com.arshaa.emp.repository.EmployeeMasterRepository;
import com.arshaa.emp.repository.OnboardRepository;
import com.thoughtworks.xstream.mapper.Mapper.Null;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	OnboardRepository onRepo;
	@Autowired
	EmployeeMasterRepository emRepo;
	@Autowired
	@Lazy
	private RestTemplate template;
	
	StringConstants sConstants=new StringConstants();

	public ResponseEntity onBoardUser(Onboarding newOnboard) {
		Response r = new Response<>();
		try {
			java.sql.Date tSqlDate = new java.sql.Date(newOnboard.getOnboardDate().getTime());
			newOnboard.setOnboardDate(tSqlDate);

			java.sql.Date tsqDate1 = new java.sql.Date(newOnboard.getUpdatedOn().getTime());
			newOnboard.setUpdatedOn(tsqDate1);
			newOnboard.setWaitingforapprovalStatus(true);
			newOnboard.setRejectedStatus(false);
			newOnboard.setApprovedStatus(false);
			Onboarding newData = onRepo.save(newOnboard);
			r.setStatus(true);
			r.setMessage(sConstants.POST_SUCCESS);
			r.setData(newData);
			return new ResponseEntity(r, HttpStatus.OK);
		} catch (Exception e) {

			r.setStatus(false);
			r.setMessage(e.getMessage());
			return new ResponseEntity(r, HttpStatus.OK);
		}
	}

	public ResponseEntity<Onboarding> waitingForApprovelStatus() {
		Response r = new Response<>();
		try {
			List<Onboarding> onboarding = onRepo.findByWaitingforapprovalStatusOrRejectedStatus(true, true);
			if (!onboarding.isEmpty()) {
				r.setStatus(true);
				r.setMessage(sConstants.GET_RESPONSE);
				r.setData(onboarding);
				return new ResponseEntity(r, HttpStatus.OK);
			} else {
				r.setStatus(true);
				r.setMessage(sConstants.NO_ENTRIES_FOUND);
				return new ResponseEntity(r, HttpStatus.OK);
			}
		} catch (Exception e) {
			r.setStatus(false);
			r.setMessage(sConstants.FAILURE_RESPONSE + e.getMessage());
			return new ResponseEntity(r, HttpStatus.OK);
		}

	}

	public ResponseEntity updateApprovStatus(String onboardingId, HrApprovalStatus newOnboard) {
		Response response=new Response();
		String userURL="http://urpService/user/addUser";
		String loginURL="http://loginservice/login/addUsers";
		String emailURL="http://emailService/mail/sendmail";
		try {
			Onboarding getOnboarding = onRepo.getByOnboardingId(onboardingId);
			System.out.println(getOnboarding);
			if (!getOnboarding.equals(null)) {
				getOnboarding.setApprovedStatus(newOnboard.isApprovedStatus());
				getOnboarding.setRejectedStatus(newOnboard.isRejectedStatus());
				getOnboarding.setWaitingforapprovalStatus(newOnboard.isWaitingforapprovalStatus());
				Onboarding saveList = onRepo.save(getOnboarding);

				if (saveList.isApprovedStatus() == true) {
					java.sql.Date tSqlDate = new java.sql.Date(newOnboard.getApprovedDate().getTime());
					newOnboard.setApprovedDate(tSqlDate);
					getOnboarding.setRejectedStatus(false);
					getOnboarding.setWaitingforapprovalStatus(false);
					onRepo.save(getOnboarding);
					
					// Posting data to EmployeeMaster
					EmployeeMaster employeeMaster = new EmployeeMaster();
					employeeMaster.setFirstName(getOnboarding.getFirstName());
					employeeMaster.setEmail(getOnboarding.getEmail());
					employeeMaster.setDepartmentName(getOnboarding.getDepartment());
					employeeMaster.setDesignationName(getOnboarding.getDesgination());
					employeeMaster.setLastName(getOnboarding.getLastName());
					employeeMaster.setMiddleName(getOnboarding.getMiddleName());
					employeeMaster.setPrimaryPhoneNumber(getOnboarding.getPhoneNumber());
					employeeMaster.setDateOfJoining(getOnboarding.getDateOfJoining());
					employeeMaster.setYearsOfExperience(getOnboarding.getYearsOfExperience());
					emRepo.save(employeeMaster);

					//Generating Random userId and Password
					Random rand = new Random();
					Integer intRandom = rand.nextInt(9999);
					String userId = getOnboarding.getFirstName() + intRandom;
					int n = getOnboarding.getFirstName().length();
					char first = getOnboarding.getFirstName().charAt(0);
					char last = getOnboarding.getFirstName().charAt(n - 1);
					String password = "user" + first + last + intRandom;
					//System.out.println("Username :" + userId + "Password" + password);

					//Posting data to UserMaster table
					Users users = new Users();
					users.setEmployeeId(employeeMaster.getEmployeeId());
					users.setUserName(userId);
					template.postForObject(userURL, users, Users.class);
					
					//Posting data to Employee login table
					EmployeeLogin login = new EmployeeLogin();
					login.setEmail(getOnboarding.getEmail());
					login.setUserName(userId);
					login.setPassword(password);
					login.setFlag(true);
					login.setEmployeeId(employeeMaster.getEmployeeId());
					login.setUserType("employee");
					template.postForObject(loginURL, login, EmployeeLogin.class);
					
					UserModel model=new UserModel();
					model.setName("Sandhya");
					model.setEmail("sandhyabandaru15@gmail.com");
					model.setUserName(userId);
					model.setPassword(password);
					model.setEmployeeId(employeeMaster.getEmployeeId());
					template.postForObject(emailURL, model, UserModel.class);

					response.setStatus(true);
					response.setMessage("Hr Approved successfully");
					//onRepo.delete(getOnboarding);
					return new ResponseEntity("Success", HttpStatus.OK);
				} else if (saveList.isRejectedStatus() == true) {
					java.sql.Date tSqlDate = new java.sql.Date(newOnboard.getRejectDate().getTime());
					newOnboard.setRejectDate(tSqlDate);
					getOnboarding.setApprovedStatus(false);
					getOnboarding.setWaitingforapprovalStatus(false);
					onRepo.save(getOnboarding);
					return new ResponseEntity("HR Rejected Successfully", HttpStatus.OK);
				} else {
					return new ResponseEntity("Waiting for HR Approval", HttpStatus.OK);
				}
			}
			else {
				return new ResponseEntity("Data not Found", HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity(e.getMessage() , HttpStatus.OK);

		}
	}

	public ResponseEntity getEmployeeDataByEmployeeId(String employeeId) {
		Response r = new Response<>();
		try {
			EmployeeMaster employeeMaster = emRepo.getById(employeeId);
             if(!employeeMaster.equals(null))
             {
            	 r.setStatus(true);
            	 r.setMessage(sConstants.GET_RESPONSE);
            	 r.setData(employeeMaster);
     			return new ResponseEntity(r, HttpStatus.OK);
             }
             else {
            	 r.setStatus(false);
            	 r.setMessage(sConstants.INVALID_DATA+employeeId);
            	 return new ResponseEntity(r,HttpStatus.OK);
             }
		} catch (Exception e) {
			r.setStatus(false);
			r.setMessage(sConstants.FAILURE_RESPONSE);
			return new ResponseEntity(r, HttpStatus.OK);
		}
	}

	public ResponseEntity updateEmployeeDataByEmployeeId(String employeeId, EmployeeMaster empMaster) {
		Response r = new Response<>();
		try {
			EmployeeMaster master = emRepo.getById(employeeId);

			master.setFirstName(empMaster.getFirstName());
			master.setMiddleName(empMaster.getMiddleName());
			master.setLastName(empMaster.getLastName());
			master.setPrimaryPhoneNumber(empMaster.getPrimaryPhoneNumber());
			master.setSecondaryPhoneNumber(empMaster.getSecondaryPhoneNumber());
			master.setEmail(empMaster.getEmail());
			master.setYearsOfExperience(empMaster.getYearsOfExperience());
			master.setDateOfBirth(empMaster.getDateOfBirth());
			master.setBloodGroup(empMaster.getBloodGroup());
			master.setGender(empMaster.getGender());
			master.setMaritalStatus(empMaster.getMaritalStatus());
			master.setDateOfJoining(empMaster.getDateOfJoining());
			master.setDesignationName(empMaster.getDesignationName());
			master.setDepartmentName(empMaster.getDepartmentName());
			master.setPermanentAdress(empMaster.getPermanentAdress());
			master.setPermanentState(empMaster.getPermanentState());
			master.setPermanentCountry(empMaster.getPermanentCountry());
			master.setPermanentPincode(empMaster.getPermanentPincode());
			master.setCurrentAdress(empMaster.getCurrentAdress());
			master.setCurrentState(empMaster.getCurrentState());
			master.setCurrentCountry(empMaster.getCurrentCountry());
			master.setCurrentPincode(empMaster.getCurrentPincode());
			master.setPostgraduationBoardOfUniversity(empMaster.getPostgraduationBoardOfUniversity());
			master.setPostgraduationInstituteName(empMaster.getPostgraduationInstituteName());
			master.setPostgraduationInstituteCity(empMaster.getPostgraduationInstituteCity());
			master.setPostgraduationCourseName(empMaster.getPostgraduationCourseName());
			master.setPostgraduationJoiningYear(empMaster.getPostgraduationJoiningYear());
			master.setPostgraduationPassedYear(empMaster.getPostgraduationPassedYear());
			master.setPostgraduationGrade(empMaster.getPostgraduationGrade());
			master.setGraduationBoardOfUniversity(empMaster.getGraduationBoardOfUniversity());
			master.setGraduationInstituteName(empMaster.getGraduationInstituteName());
			master.setGraduationInstituteCity(empMaster.getGraduationInstituteCity());
			master.setGraduationCourseName(empMaster.getGraduationCourseName());
			master.setGraduationGrade(empMaster.getGraduationGrade());
			master.setIntermediateBoardOfUniversity(empMaster.getIntermediateBoardOfUniversity());
			master.setIntermediateInstituteName(empMaster.getIntermediateInstituteName());
			master.setIntermediateInstituteCity(empMaster.getIntermediateInstituteCity());
			master.setIntermediateCourseName(empMaster.getIntermediateCourseName());
			master.setIntermediateJoiningYear(empMaster.getIntermediateJoiningYear());
			master.setIntermediatePassedYear(empMaster.getIntermediatePassedYear());
			master.setIntermediateGrade(empMaster.getIntermediateGrade());
			master.setSscBoardOfUniversity(empMaster.getSscBoardOfUniversity());
			master.setSscInstituteName(empMaster.getSscInstituteName());
			master.setSscInstituteCity(empMaster.getSscInstituteCity());
			master.setSscCourseName(empMaster.getSscCourseName());
			master.setSscJoiningYear(empMaster.getSscJoiningYear());
			master.setSscPassedYear(empMaster.getSscPassedYear());
			master.setSscGrade(empMaster.getSscGrade());
			master.setPreviousCompany1_name(empMaster.getPreviousCompany1_name());
			master.setPreviousCompany1_designation(empMaster.getPreviousCompany1_designation());
			master.setPreviousCompany1_joiningDate(empMaster.getPreviousCompany1_joiningDate());
			master.setPreviousCompany1_relievingDate(empMaster.getPreviousCompany1_relievingDate());
			master.setPreviousCompany1_employeeId(empMaster.getPreviousCompany1_employeeId());
			master.setPreviousCompany1_grossSalary(empMaster.getPreviousCompany1_grossSalary());
			master.setPreviousCompany1_typeOfEmployment(empMaster.getPreviousCompany1_typeOfEmployment());
			master.setPreviousCompany1_reasonForRelieving(empMaster.getPreviousCompany1_reasonForRelieving());
			master.setPreviousCompany2_name(empMaster.getPreviousCompany2_name());
			master.setPreviousCompany2_designation(empMaster.getPreviousCompany2_designation());
			master.setPreviousCompany2_joiningDate(empMaster.getPreviousCompany2_joiningDate());
			master.setPreviousCompany2_relievingDate(empMaster.getPreviousCompany2_relievingDate());
			master.setPreviousCompany2_employeeId(empMaster.getPreviousCompany2_employeeId());
			master.setPreviousCompany2_grossSalary(empMaster.getPreviousCompany2_grossSalary());
			master.setPreviousCompany2_typeOfEmployment(empMaster.getPreviousCompany2_typeOfEmployment());
			master.setPreviousCompany2_reasonForRelieving(empMaster.getPreviousCompany2_reasonForRelieving());
			master.setPreviousCompany3_name(empMaster.getPreviousCompany3_name());
			master.setPreviousCompany3_designation(empMaster.getPreviousCompany3_designation());
			master.setPreviousCompany3_joiningDate(empMaster.getPreviousCompany3_joiningDate());
			master.setPreviousCompany3_relievingDate(empMaster.getPreviousCompany3_relievingDate());
			master.setPreviousCompany3_employeeId(empMaster.getPreviousCompany3_employeeId());
			master.setPreviousCompany3_grossSalary(empMaster.getPreviousCompany3_grossSalary());
			master.setPreviousCompany3_typeOfEmployment(empMaster.getPreviousCompany3_typeOfEmployment());
			master.setPreviousCompany3_reasonForRelieving(empMaster.getPreviousCompany3_reasonForRelieving());
			master.setReporting_manager(empMaster.getReporting_manager());

			EmployeeMaster master1 = emRepo.save(master);
			System.out.println(master1);
			r.setStatus(true);
			r.setMessage(sConstants.PUT_RESPONSE);
			return new ResponseEntity(r, HttpStatus.OK);

		} catch (Exception e) {
			r.setStatus(false);
			r.setMessage(sConstants.FAILURE_RESPONSE);
			return new ResponseEntity(r, HttpStatus.OK);
		}
	}
	
	public ResponseEntity<EmployeeMaster> getALLData()
	{		Response r = new Response<>();
       try {
    	   List<EmployeeMaster> employeeMasters= emRepo.findAll();
  		 if(!employeeMasters.isEmpty())
  		 {
  	    	   r.setStatus(true);
  	    	   r.setMessage(sConstants.GET_RESPONSE);
  	    	   r.setData(employeeMasters);
  			 return new ResponseEntity(r,HttpStatus.OK);
  		 }
  		 else {
  			 r.setStatus(false);
  			 r.setMessage(sConstants.NO_ENTRIES_FOUND);
  			 
  			return new ResponseEntity(r,HttpStatus.OK);
  		}
       }
       catch (Exception e) {
              r.setStatus(true);
              r.setMessage(sConstants.FAILURE_RESPONSE);
    		return new ResponseEntity(r,HttpStatus.OK);
       }
		 
	}

}
