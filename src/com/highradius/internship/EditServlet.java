package com.highradius.internship;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDao = new  OrderDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = new Order();
		order.setOrderID(Integer.parseInt(request.getParameter("orderID")));
		order.setOrderAmount(Integer.parseInt(request.getParameter("orderAmount")));
		order.setNotes(request.getParameter("notes"));
		order.setApprovedBy(request.getParameter("approvedBy"));
		order.setApprovalStatus("Awaiting Approval");
		
		orderDao.editOrder(order);
		
		response.sendRedirect("dashboard?page=1");
	}

}
