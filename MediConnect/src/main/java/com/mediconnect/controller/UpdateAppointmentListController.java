package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.service.AddService;
import com.mediconnect.service.DashboardService;
import com.mediconnect.service.DeleteService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class UpdateAppointmentListController
 * Handles displaying and deleting appointments from the appointment list.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateAppointmentList" })
public class UpdateAppointmentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Service and utility instances for dashboard data, extraction, add/delete, and redirection
	DashboardService dashboardService;
	ExtractionUtil extractionUtil;
	DeleteService deleteService;
	AddService addService;
	RedirectionUtil redirectionUtil;   
    
    /**
     * Default constructor initializing services and utilities.
     */
    public UpdateAppointmentListController() {
        super();
        dashboardService = new DashboardService();
		extractionUtil = new ExtractionUtil();
		addService = new AddService();
		redirectionUtil = new RedirectionUtil();
        deleteService = new DeleteService();
    }

	/**
	 * Handles GET requests - loads appointment list and forwards to JSP page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Fetch appointment list from dashboard service and set it in session
		SessionUtil.setAttribute(request, "appointmentList", dashboardService.getAppointmentListModel());
		
		// Forward request to UpdateAppointmentList JSP page for display
		request.getRequestDispatcher("/WEB-INF/pages/UpdateAppointmentList.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests - delegates to doDelete method.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDelete(request, response);
	}
	
	/**
	 * Handles DELETE requests - deletes appointment by appointmentId and userId.
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Parse appointmentId and userId parameters from request
		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		// Attempt to delete the appointment using delete service
		Boolean isDeleted = deleteService.deleteAppointment(appointmentId, userId);
		
		if(isDeleted == null) {
			// Log error if deletion failed or returned null
			System.out.println("Error Deleting");
		}else {
			// Redirect back to UpdateAppointmentList page on success
			redirectionUtil.redirectToPage(request, response, "UpdateAppointmentList");
		}
	}

}
