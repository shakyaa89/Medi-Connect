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
 * Handles requests for displaying the list of customers.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CustomerList" })
public class CustomerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
    
    /**
     * Constructor initializes DashboardService.
     */
    public CustomerListController() {
        super();
        dashboardService = new DashboardService();
    }

	/**
	 * Handles GET requests.
	 * Fetches the user list and sets it in session attribute "customerList".
	 * Forwards request to CustomerList JSP page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionUtil.setAttribute(request, "customerList", dashboardService.getUserList());
		request.getRequestDispatcher("/WEB-INF/pages/CustomerList.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to doGet method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
