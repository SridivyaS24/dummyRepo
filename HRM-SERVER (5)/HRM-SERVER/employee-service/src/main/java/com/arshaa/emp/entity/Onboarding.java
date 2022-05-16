package com.arshaa.emp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.arshaa.emp.entity.StringSequenceGenerator;

@Entity(name="onboarding")
public class Onboarding {

	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "onboardingId")
    @GenericGenerator(name = "onboardingId", strategy = "com.arshaa.emp.entity.StringSequenceGenerator",
            parameters = {@Parameter(name = StringSequenceGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "OBD"),
                    @Parameter(name = StringSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
   private String onboardingId;

	@Column
	private String desgination;

	@Column
	private String firstName;
	@Column
	private String middleName;
	@Column
	private String lastName;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Column
	private String phoneNumber;
	@Column(name = "Years_of_experience")
	private String yearsOfExperience;
	@Column
	private String employeeId;
	@Column
	private boolean approvedStatus;
	@Column
	private boolean rejectedStatus;
	@Column
	private boolean waitingforapprovalStatus;
	
	private String department;
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}




	@Column
	private String email;
	@Temporal(TemporalType.DATE)
    private java.util.Date onboardDate = new java.util.Date(System.currentTimeMillis());
    private java.util.Date approvedDate;
    private java.util.Date rejectDate;
	@Temporal(TemporalType.DATE)
    private java.util.Date updatedOn = new java.util.Date(System.currentTimeMillis());
    private Date dateOfJoining;
    //private Date approvedDate;
    //private Date rejectDate;
	@Column
	private String updatedBy;
	public String getOnboardingId() {
		return onboardingId;
	}
	public void setOnboardingId(String onboardingId) {
		this.onboardingId = onboardingId;
	}
	
	public String getDesgination() {
		return desgination;
	}
	public void setDesgination(String desgination) {
		this.desgination = desgination;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public boolean isApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(boolean approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
	public boolean isRejectedStatus() {
		return rejectedStatus;
	}
	public void setRejectedStatus(boolean rejectedStatus) {
		this.rejectedStatus = rejectedStatus;
	}
	public boolean isWaitingforapprovalStatus() {
		return waitingforapprovalStatus;
	}
	public void setWaitingforapprovalStatus(boolean waitingforapprovalStatus) {
		this.waitingforapprovalStatus = waitingforapprovalStatus;
	}
	public Date getOnboardDate() {
		return onboardDate;
	}
	public void setOnboardDate(Date onboardDate) {
		this.onboardDate = onboardDate;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Date getRejectDate() {
		return rejectDate;
	}
	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
		
	
	public Onboarding() {
		// TODO Auto-generated constructor stub
	}
	public Onboarding(String onboardingId, String desgination, String firstName, String middleName, String lastName,
			String phoneNumber, String yearsOfExperience, String employeeId, boolean approvedStatus,
			boolean rejectedStatus, boolean waitingforapprovalStatus, String department, String email, Date onboardDate,
			Date approvedDate, Date rejectDate, Date updatedOn, Date dateOfJoining, String updatedBy) {
		super();
		this.onboardingId = onboardingId;
		this.desgination = desgination;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.yearsOfExperience = yearsOfExperience;
		this.employeeId = employeeId;
		this.approvedStatus = approvedStatus;
		this.rejectedStatus = rejectedStatus;
		this.waitingforapprovalStatus = waitingforapprovalStatus;
		this.department = department;
		this.email = email;
		this.onboardDate = onboardDate;
		this.approvedDate = approvedDate;
		this.rejectDate = rejectDate;
		this.updatedOn = updatedOn;
		this.dateOfJoining = dateOfJoining;
		this.updatedBy = updatedBy;
	}

}