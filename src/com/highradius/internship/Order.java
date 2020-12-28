package com.highradius.internship;

public class Order {
	int orderID;
	int orderAmount;
	int customerID;
	String customerName;
	String approvalStatus;
	String approvedBy;
	String notes;
	String orderDate;

	public Order() {
		super();
	}

	public Order(int orderID, int orderAmount, int customerID, String customerName, String approvalStatus,
			String approvedBy, String notes, String orderDate) {
		super();
		this.orderID = orderID;
		this.orderAmount = orderAmount;
		this.customerID = customerID;
		this.customerName = customerName;
		this.approvalStatus = approvalStatus;
		this.approvedBy = approvedBy;
		this.notes = notes;
		this.orderDate = orderDate;
	}

	public int getOrderID() {
		return orderID;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public int getOrderAmount() {
		return orderAmount;
	}
	
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getApprovalStatus() {
		return approvalStatus;
	}
	
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public String getApprovedBy() {
		return approvedBy;
	}
	
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}
