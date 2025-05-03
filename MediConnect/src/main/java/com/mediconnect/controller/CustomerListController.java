package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.service.DashboardService;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class CustomerListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CustomerList" })
public class CustomerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerListController() {
        super();
        dashboardService = new DashboardService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SessionUtil.setAttribute(request, "customerList", dashboardService.getUserList());
		
		request.getRequestDispatcher("/WEB-INF/pages/CustomerList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
