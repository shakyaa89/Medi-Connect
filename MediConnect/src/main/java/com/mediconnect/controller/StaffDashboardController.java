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
 * Servlet implementation class StaffDashboardController
 * Handles requests to display the staff dashboard page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/StaffDashboard" })
public class StaffDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashboardService dashboardService;

    /**
     * Default constructor initializing the DashboardService.
     */
    public StaffDashboardController() {
        super();
        dashboardService = new DashboardService();
    }

	/**
	 * Handles GET requests to load dashboard data and forward to StaffDashboard JSP.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve admin dashboard info and set it in session for display
		SessionUtil.setAttribute(request, "dashboardNumbers", dashboardService.getAdminDashboardInfo());

		// Forward request to StaffDashboard JSP page
		request.getRequestDispatcher("/WEB-INF/pages/StaffDashboard.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to doGet to display dashboard.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
