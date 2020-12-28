package com.highradius.internship;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDao = new LoginDAO();
	private Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User user = new User(username, password);
        
        if (loginDao.validate(user)) {
        	List<String> userDetails = Arrays.asList(user.getUsername(), user.getPassword(), user.getUserLevel(), user.getOrderRange(), user.getStatus());
        	
        	JsonElement jsonElement = gson.toJsonTree(userDetails);
        	System.out.println(jsonElement);
        	
		    HttpSession session = request.getSession();
		    session.setAttribute("username", user.getUsername());
		    session.setAttribute("user_level", user.getUserLevel());
		    session.setAttribute("order_range", user.getOrderRange());
		    
		    response.sendRedirect("dashboard?page=1");
		} else {
			request.setAttribute("loginError", "Incorrect Credentials");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	
}
