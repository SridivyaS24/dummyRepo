package com.arshaa.emp.model;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class HrApprovalStatus {

	@Column
	private boolean approvedStatus;
	@Column
	private boolean rejectedStatus;
	@Column
	private boolean waitingforapprovalStatus;
	@Temporal(TemporalType.DATE)
	private java.util.Date approvedDate = new java.util.Date(System.currentTimeMillis());
	@Temporal(TemporalType.DATE)
	private java.util.Date rejectDate = new java.util.Date(System.currentTimeMillis());

	public HrApprovalStatus() {
		// TODO Auto-generated constructor stub
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

	public java.util.Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(java.util.Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public java.util.Date getRejectDate() {
		return rejectDate;
	}

	public void setRejectDate(java.util.Date rejectDate) {
		this.rejectDate = rejectDate;
	}

}
