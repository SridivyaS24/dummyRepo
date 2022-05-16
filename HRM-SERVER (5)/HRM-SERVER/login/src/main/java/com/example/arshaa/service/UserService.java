package com.example.arshaa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.arshaa.entity.EmployeeLogin;
import com.example.arshaa.model.ResetPassword;
import com.example.arshaa.model.Response;
import com.example.arshaa.model.UserModel;
import com.example.arshaa.repository.UserRepository;
@Service
public class UserService {
	@Autowired(required=true)
	private UserRepository repository;

	public ResponseEntity updatePasswordByUsername(ResetPassword reset) {
		EmployeeLogin returnedUser = repository.findByPassword(reset.getOldPassword());
		try {
			if (returnedUser != null) {
				if (returnedUser.getPassword().equals(reset.getOldPassword())) {
					// Date cpDate = new Date(reset.getChangePasswordDate().getTime());
					// returnedUser.setPassword(returnedUser.getPassword());
					// reset.setChangePasswordDate(cpDate);
					returnedUser.setPassword(reset.getConformNewPassword());
					repository.save(returnedUser);
					return new ResponseEntity(returnedUser, HttpStatus.OK);
				} else {
					return new ResponseEntity("Enter valid UserName", HttpStatus.OK);
				}
			} else {
				return new ResponseEntity("Enter valid UserName", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity(returnedUser, HttpStatus.OK);
		}
	}
	
    public  ResponseEntity<UserModel> getUsersByEmailId(String userName, String password) {
		Response<UserModel> response=new Response<UserModel>();
    	//List<User> dataUser=userRepo.findAll();
		UserModel um=new UserModel();
		try {
		Optional<EmployeeLogin> user=repository.getByUserName(userName);

    	if(user.isPresent())
    	{    	    	
    		if(user.get().getPassword().equals(password))
    		{

    			response.setStatus(true);
    			response.setMessage("Login Success");
    		    if(response.isStatus()==true)
        		{
    		    	EmployeeLogin user1=repository.findByUserName(userName);
        	        um.setEmployeeId(user1.getEmployeeId());
        	        um.setUserType(user1.getUserType());
        			    		}
    		        		    response.setData(um);
    	        return new ResponseEntity(response,HttpStatus.OK);
    		}
    		
    		else {
    			response.setStatus(false);
    	    	response.setMessage("Invalid Mail or Password");
    	        return new ResponseEntity(response,HttpStatus.OK);
    		}
    		
    	}
    	else {
    		response.setStatus(false);
    		response.setMessage("Enter valid emailId");
	        return new ResponseEntity(response,HttpStatus.OK);
    	}
		}
		catch(Exception e)
		{
			response.setStatus(false);
			response.setMessage("Something went wrong please try again");
	        return new ResponseEntity(response,HttpStatus.OK);
		}
    }

    
    public void  saveUsers(EmployeeLogin user) {
    	try {
    		user.setFlag(true);
			
    		repository.save(user);
    new ResponseEntity("User Added Successfully",HttpStatus.OK);
    	}
    	catch(Exception e)
    	{
    		    new ResponseEntity(e.getMessage(),HttpStatus.OK);

    	}
    	
    }
}
