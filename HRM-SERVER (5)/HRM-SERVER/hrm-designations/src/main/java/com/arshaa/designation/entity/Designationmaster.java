package com.arshaa.designation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Designationmaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int designationId;
	@Column
	private String designationName;
	@Column
	private int departmentId;
	@Column
	private boolean designationStatus;
	@Column
	private int updatedBy;
	@Temporal(TemporalType.DATE)
	private java.util.Date updatedOn = new java.util.Date(System.currentTimeMillis());

	public Designationmaster(int designationId, String designationName, int departmentId, boolean designationStatus,
			int updatedBy, Date updatedOn) {
		super();
		this.designationId = designationId;
		this.designationName = designationName;
		this.departmentId = departmentId;
		this.designationStatus = designationStatus;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public boolean isDesignationStatus() {
		return designationStatus;
	}

	public void setDesignationStatus(boolean designationStatus) {
		this.designationStatus = designationStatus;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public java.util.Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Designationmaster() {
		// TODO Auto-generated constructor stub
	}

}
