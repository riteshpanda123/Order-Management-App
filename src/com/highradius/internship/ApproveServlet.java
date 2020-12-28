package com.highradius.internship;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/approve")
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDao = new OrderDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		
		orderDao.approveOrder(username, orderID);
		
		response.sendRedirect("dashboard?page=1");
	}
}
