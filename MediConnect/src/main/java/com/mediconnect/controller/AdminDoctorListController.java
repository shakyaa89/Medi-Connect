package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.service.DashboardService;
import com.mediconnect.service.DeleteService;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class AdminDoctorListController
 * Handles displaying the list of doctors and deleting a doctor.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminDoctorList" })
public class AdminDoctorListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashboardService dashboardService;
	private DeleteService deleteService;
	private RedirectionUtil redirectionUtil;
    
    /**
     * Constructor initializes required services and utilities.
     */
    public AdminDoctorListController() {
        super();
        dashboardService = new DashboardService();
        deleteService = new DeleteService();
        redirectionUtil = new RedirectionUtil();
    }

	/**
	 * Handles GET request.
	 * Retrieves doctor list and sets it in session.
	 * Forwards to AdminDoctorList.jsp to display doctors.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionUtil.setAttribute(request, "doctorList", dashboardService.getDoctorList());
		request.getRequestDispatcher("/WEB-INF/pages/AdminDoctorList.jsp").forward(request, response);
	}

	/**
	 * Handles POST request.
	 * Internally calls doDelete to process doctor deletion.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDelete(request, response);
	}
	
	/**
	 * Handles DELETE request.
	 * Deletes doctor based on doctorId parameter.
	 * Redirects to AdminDashboard on success or prints error on failure.
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int doctorId = Integer.parseInt(request.getParameter("doctorId"));
		
		Boolean isDeleted = deleteService.deleteDoctor(doctorId);
		
		if(isDeleted == null) {
			System.out.println("Error Deleting");
		}else {
			redirectionUtil.redirectToPage(request, response, "AdminDashboard");
		}
	}

}
