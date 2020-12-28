package com.highradius.internship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	
	private static final String SELECT_USER = "select * from user_details where username = ? and password = ?";
	
	public boolean validate(User user) {
        boolean status = false;
        
        try {
        	Connection connection = DatabaseConnection.initializeDB();
        	
        	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
        	preparedStatement.setString(1, user.getUsername());
        	preparedStatement.setString(2, user.getPassword());
            
        	ResultSet rs = preparedStatement.executeQuery();
        
        	status = rs.next();
        	
        	user.setStatus(Boolean.toString(status));
        	user.setUserLevel(rs.getString("user_level"));
        	user.setOrderRange(rs.getString("order_range"));       
        } catch(SQLException  e) {
        	e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

        return status;
    }
	
}
