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
 * Servlet implementation class CustomerDoctorListController
 * Handles requests to display the list of doctors and their availability for customers.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CustomerDoctorList" })
public class CustomerDoctorListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
    
    /**
     * Constructor initializes DashboardService instance.
     */
    public CustomerDoctorListController() {
        super();
        dashboardService = new DashboardService();
    }

	/**
	 * Handles GET requests.
	 * Retrieves doctor list and their availability and sets them as session attributes.
	 * Forwards the request to CustomerDoctorList JSP page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionUtil.setAttribute(request, "doctorList", dashboardService.getDoctorList());
		SessionUtil.setAttribute(request, "doctorAvailabilityList", dashboardService.getDoctorAvailabilityList());
		
		request.getRequestDispatcher("/WEB-INF/pages/CustomerDoctorList.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
