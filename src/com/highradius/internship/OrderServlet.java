package com.highradius.internship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDao = new OrderDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userLevel = session.getAttribute("user_level").toString();
//		String orderRange = session.getAttribute("order_range").toString();
//		int range = Integer.parseInt(orderRange);
		
		int recordsPerPage = 10;
		int totalRows = orderDao.getTotalRows(userLevel);
		int numOfPages = totalRows / recordsPerPage;
		if(totalRows % recordsPerPage > 0)
			numOfPages++;
		int currentPage = Integer.parseInt(request.getParameter("page"));
		
		List<Order> orders = new ArrayList<Order>();
		orders = orderDao.selectAllOrders(currentPage, recordsPerPage, userLevel);
		
		request.setAttribute("level", userLevel);
		request.setAttribute("orders", orders);
		request.setAttribute("numOfPages", numOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("totalRows", totalRows);
			
	    RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
