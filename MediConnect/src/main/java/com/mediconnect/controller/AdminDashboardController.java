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
 * Servlet implementation class AdminDashboardController
 * Handles displaying the admin dashboard with summary information.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminDashboard" })
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashboardService dashboardService;

    /**
     * Constructor initializes DashboardService instance.
     */
    public AdminDashboardController() {
        super();
        dashboardService = new DashboardService();
    }

	/**
	 * Handles GET request.
	 * Retrieves dashboard summary data and sets it in session.
	 * Forwards to AdminDashboard.jsp to display the dashboard.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionUtil.setAttribute(request, "dashboardNumbers", dashboardService.getAdminDashboardInfo());
		request.getRequestDispatcher("/WEB-INF/pages/AdminDashboard.jsp").forward(request, response);
	}

	/**
	 * Handles POST request by delegating to doGet method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
