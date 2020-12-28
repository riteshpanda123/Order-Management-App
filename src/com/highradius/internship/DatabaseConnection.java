package com.highradius.internship;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class DatabaseConnection {
	public static Connection initializeDB() throws SQLException, ClassNotFoundException { 
	        String dbDriver = "com.mysql.jdbc.Driver"; 
	        String dbURL = "jdbc:mysql:// localhost:3306/"; 
	        String dbName = "winter_internship"; 
	        String dbUsername = "root"; 
	        String dbPassword = "zakritz123"; 
	  
	        Class.forName(dbDriver); 
	        Connection connection = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword); 
	        return connection; 
	    } 
}
