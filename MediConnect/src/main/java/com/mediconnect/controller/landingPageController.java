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
 * Servlet implementation class landingPageController
 * Handles requests to the landing page and prepares data for display.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/index.jsp", "/" })
public class landingPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
    
    /**
     * Constructor initializes DashboardService.
     */
    public landingPageController() {
        super();
        dashboardService = new DashboardService();
    }

	/**
	 * Handles GET requests.
	 * Retrieves doctor list and availability list and stores in session.
	 * Forwards request to the index JSP page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionUtil.setAttribute(request, "doctorList", dashboardService.getDoctorList());
		SessionUtil.setAttribute(request, "doctorAvailabilityList", dashboardService.getDoctorAvailabilityList());
		
		request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to doGet method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
