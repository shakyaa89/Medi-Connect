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
 * Servlet implementation class AdminAppointmentListController
 * Handles the display of appointment list for admin users.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AppointmentList" })
public class AppointmentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DashboardService dashboardService;

    /**
     * Default constructor initializing DashboardService.
     */
    public AppointmentListController() {
        super();
        dashboardService = new DashboardService();
    }

	/**
	 * Handles GET request.
	 * Retrieves appointment list and forwards to AppointmentList.jsp.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionUtil.setAttribute(request, "appointmentList", dashboardService.getAppointmentListModel());
		request.getRequestDispatcher("/WEB-INF/pages/AppointmentList.jsp").forward(request, response);
	}

	/**
	 * Handles POST request.
	 * Forwards the request to doGet method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
