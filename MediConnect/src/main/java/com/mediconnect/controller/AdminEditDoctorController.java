package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.model.DoctorAvailabilityModel;
import com.mediconnect.model.DoctorModel;
import com.mediconnect.service.AddService;
import com.mediconnect.service.DashboardService;
import com.mediconnect.service.UpdateService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class AdminEditDoctorController
 * Handles fetching doctor details for editing and updating doctor info.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminEditDoctor" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
maxFileSize = 1024 * 1024 * 10,
maxRequestSize = 1024 * 1024 * 50)
public class AdminEditDoctorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashboardService dashboardService;
	private ExtractionUtil extractionUtil;
	private RedirectionUtil redirectionUtil;
	private UpdateService updateService;

    /**
     * Constructor initializes services and utilities.
     */
    public AdminEditDoctorController() {
        super();
        this.dashboardService = new DashboardService();
        this.extractionUtil = new ExtractionUtil();
		this.redirectionUtil = new RedirectionUtil();
		this.updateService = new UpdateService();
    }

	/**
	 * Handles GET request.
	 * Sets doctorId, doctor list, and availability list in session for the edit page.
	 * Forwards to AdminEditDoctor.jsp.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doctorId = request.getParameter("doctorId");
				
		SessionUtil.setAttribute(request, "doctorId", doctorId);
		SessionUtil.setAttribute(request, "doctorList", dashboardService.getDoctorList());
		SessionUtil.setAttribute(request, "doctorAvailabilityList", dashboardService.getDoctorAvailabilityList());
		
		request.getRequestDispatcher("/WEB-INF/pages/AdminEditDoctor.jsp").forward(request, response);
	}

	/**
	 * Handles POST request by forwarding to doPut method for update operation.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPut(request, response);
	}
	
	/**
	 * Handles PUT request (called internally) to update doctor details.
	 * Extracts updated doctor and availability models, updates database, and handles image upload.
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int doctorId = Integer.parseInt((String) SessionUtil.getAttribute(request, "doctorId"));

			DoctorModel doctorModel = extractionUtil.extractDoctorModel(request, response);
			DoctorAvailabilityModel doctorAvailabilityModel = extractionUtil.extractDoctorAvailabilityModel(request,
					response);
			if(doctorModel == null) {
	            request.getRequestDispatcher("WEB-INF/pages/AdminEditDoctor.jsp").forward(request, response);
	            return;
			}
			
			Boolean isUpdated = updateService.updateDoctor(doctorModel, doctorAvailabilityModel, doctorId);

			if (isUpdated == null) {
				System.out.println("Error Updating Doctor");
			} else {
				if (extractionUtil.uploadDoctorImage(request)) {
					redirectionUtil.redirectToPage(request, response, "AdminDashboard");
					return;
				} else {
					redirectionUtil.setMsgAttribute(request, "error", "Error adding image <br> Please try again later!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
