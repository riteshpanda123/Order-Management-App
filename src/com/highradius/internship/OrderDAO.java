package com.highradius.internship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
	
    private static final String ADD_ORDER = "insert into order_details values(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER = "update order_details set Order_Amount = ?, Notes = ?, Approved_by = ?, Approval_Status = ? where Order_ID = ?";
    private static final String APPROVE_ORDER = "update order_details set Approved_By = ?, Approval_Status = ? where Order_ID = ?";
    private static final String REJECT_ORDER = "update order_details set Approved_By = ?, Approval_Status = ? where Order_ID = ?";
    
    public int getTotalRows(String level) {
    	int totalRows = 0;
    	String query;
    	if(level.equals("Level 1")) {
    		query = "select count(Order_ID) from order_details";
    	} 
    	else if(level.equals("Level 2")) {
    		query = "select count(Order_ID) from order_details where Order_Amount > 10000 and Order_Amount <=50000 ";
    	}
    	else {
    		query = "select count(Order_ID) from order_details where Order_Amount > 50000";
    	}
    	
    	try {
    		Connection connection = DatabaseConnection.initializeDB();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(query);
    		
    		ResultSet rs = preparedStatement.executeQuery();
    		rs.next();
    		totalRows = rs.getInt(1);
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	return totalRows;
    }
    
    public List<Order> selectAllOrders(int currentPage, int recordsPerPage, String level) {
    	List<Order> orders = new ArrayList<Order>();
    	int start = currentPage * recordsPerPage - recordsPerPage;
    	String query;
    	if(level.equals("Level 1")) {
    		query = "select * from order_details limit ?, ?";
    	} 
    	else if(level.equals("Level 2")) {
    		query = "select * from order_details where Order_Amount > 10000 and Order_Amount <= 50000 limit ?, ?";
    	}
    	else {
    		query = "select * from order_details where Order_Amount > 50000 limit ?, ?";
    	}
    	
    	try {
    		Connection connection = DatabaseConnection.initializeDB();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(query);
    		preparedStatement.setInt(1, start);
    		preparedStatement.setInt(2, recordsPerPage);
    		
    		ResultSet rs = preparedStatement.executeQuery();
    		
    		while(rs.next()) {
    			Order order = new Order();
    			order.setApprovalStatus(rs.getString("Approval_Status"));
    			order.setApprovedBy(rs.getString("Approved_By"));
    			order.setCustomerID(rs.getInt("Customer_ID"));
    			order.setCustomerName(rs.getString("Customer_Name"));
    			order.setNotes(rs.getString("Notes"));
    			order.setOrderAmount(rs.getInt("Order_Amount"));
    			order.setOrderDate(rs.getString("Order_Date"));
    			order.setOrderID(rs.getInt("Order_ID"));
    			orders.add(order);
    		}
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	return orders;
    }
    
    public List<Order> selectOrder(int id, String level) {
    	List<Order> order = new ArrayList<Order>();
    	
    	String orderID = String.valueOf(id);
    	orderID = orderID.concat("%");
    	
    	String query;
    	if(level.equals("Level 1")) {
			  query = "select * from order_details where Order_ID Like ?";
		  }else if(level.equals("Level 2")) {
			  query = "select * from order_details where Order_ID Like ? and Order_Amount > 10000 and Order_Amount <= 50000";
		  }else {
			  query = "select * from order_details where Order_ID Like ? and Order_Amount > 50000";
		  }
    	
    	try {
    		Connection connection = DatabaseConnection.initializeDB();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(query);
    		preparedStatement.setString(1, orderID);
    		
    		ResultSet rs = preparedStatement.executeQuery();
    		
    		while(rs.next()) {
    			Order temp = new Order();
    			temp.setApprovalStatus(rs.getString("Approval_Status"));
    			temp.setApprovedBy(rs.getString("Approved_By"));
    			temp.setCustomerID(rs.getInt("Customer_ID"));
    			temp.setCustomerName(rs.getString("Customer_Name"));
    			temp.setNotes(rs.getString("Notes"));
    			temp.setOrderAmount(rs.getInt("Order_Amount"));
    			temp.setOrderDate(rs.getString("Order_Date"));
    			temp.setOrderID(rs.getInt("Order_ID"));
    			order.add(temp);
    		}
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	return order;
    }   
    
    public void addOrder(Order order) {
    	try {
    		Connection connection = DatabaseConnection.initializeDB();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
    		preparedStatement.setInt(1, order.getOrderID());
    		preparedStatement.setString(2, order.getCustomerName());
    		preparedStatement.setInt(3, order.getCustomerID());
    		preparedStatement.setInt(4, order.getOrderAmount());
    		preparedStatement.setString(7, order.getNotes());
    		preparedStatement.setString(8, order.getOrderDate());
    		
    		if(order.getOrderAmount() <= 10000) {
    			preparedStatement.setString(5, "Approved");
    			preparedStatement.setString(6, "David Lee");
    		} else if(order.getOrderAmount() > 10000 && order.getOrderAmount() <= 50000) {
    			preparedStatement.setString(5, "Awaiting Approval");
    			preparedStatement.setString(6, "");
    		} else {
    			preparedStatement.setString(5, "Awaiting Approval");
    			preparedStatement.setString(6, "");
    		}
    		
    		preparedStatement.executeUpdate();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void editOrder(Order order) {
    	try {
    		Connection connection = DatabaseConnection.initializeDB();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER);
    		preparedStatement.setInt(1, order.getOrderAmount());
    		preparedStatement.setString(2, order.getNotes());
    		preparedStatement.setString(3, order.getApprovedBy());
    		preparedStatement.setInt(5, order.getOrderID());
    		preparedStatement.setString(4, order.getApprovalStatus());
    		
    		preparedStatement.executeUpdate();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void approveOrder(String username, int orderID) {
    	try {
    		Connection connection = DatabaseConnection.initializeDB();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(APPROVE_ORDER);
    		preparedStatement.setString(1, username);
    		preparedStatement.setString(2, "Approved");
    		preparedStatement.setInt(3, orderID);
    		
    		preparedStatement.executeUpdate();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void rejectOrder(String username, int orderID) {
    	try {
    		Connection connection = DatabaseConnection.initializeDB();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(REJECT_ORDER);
    		preparedStatement.setString(1, username);
    		preparedStatement.setString(2, "Rejected");
    		preparedStatement.setInt(3, orderID);
    		
    		preparedStatement.executeUpdate();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
}
