package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.model.UserModel;
import com.mediconnect.model.DoctorModel;
import com.mediconnect.model.DoctorAvailabilityModel;
import com.mediconnect.service.AddService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.ImageUtil;
import com.mediconnect.util.RedirectionUtil;

/**
 * Servlet implementation class AdminAddDoctorController
 * Handles adding new doctor and doctor availability including image upload.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminAddDoctor" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AdminAddDoctorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AddService addService;
	private RedirectionUtil redirectionUtil;
	private ExtractionUtil extractionUtil;

	/**
	 * Constructor initializing service and utility instances.
	 */
	public AdminAddDoctorController() {
		super();
		this.extractionUtil = new ExtractionUtil();
		this.addService = new AddService();
		this.redirectionUtil = new RedirectionUtil();
	}

	/**
	 * Handles GET request.
	 * Forwards to AdminAddDoctor.jsp to show the add doctor form.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/AdminAddDoctor.jsp").forward(request, response);
	}

	/**
	 * Handles POST request.
	 * Extracts doctor and availability data from the form.
	 * Attempts to add doctor and upload doctor image.
	 * Redirects to AdminDoctorList on success, else stays on the form with error messages.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DoctorModel doctorModel = extractionUtil.extractDoctorModel(request, response);
			DoctorAvailabilityModel doctorAvailabilityModel = extractionUtil.extractDoctorAvailabilityModel(request,
					response);
			if (doctorModel == null) {
				// If extraction fails, reload form
				request.getRequestDispatcher("WEB-INF/pages/AdminAddDoctor.jsp").forward(request, response);
				return;
			}

			Boolean isAdded = addService.addDoctor(doctorModel, doctorAvailabilityModel);

			if (isAdded == null) {
				// Error adding doctor
				redirectionUtil.setMsgAttribute(request, "error", "Error Adding Doctor!!");
				System.out.println("Error adding");
			} else if (isAdded) {
				try {
					// Try to upload doctor image
					if (extractionUtil.uploadDoctorImage(request)) {
						redirectionUtil.redirectToPage(request, response, "AdminDoctorList");
						return;
					} else {
						// Image upload failed
						redirectionUtil.setMsgAttribute(request, "error", "Error Adding Doctor!!");
						System.out.println("Error adding image");
					}
				} catch (IOException | ServletException e) {
					// Exception during image upload
					redirectionUtil.setMsgAttribute(request, "error", "Error Adding Doctor!!");
					System.out.println("Error adding");
					e.printStackTrace();
				}
			}

			// Forward back to form if not redirected
			request.getRequestDispatcher("WEB-INF/pages/AdminAddDoctor.jsp").forward(request, response);

		} catch (Exception e) {
			// General exception handling
			redirectionUtil.setMsgAttribute(request, "error", "Error Adding Doctor!!");
			request.getRequestDispatcher("WEB-INF/pages/AdminAddDoctor.jsp").forward(request, response);
			System.out.println("Error Adding!");
			e.printStackTrace();
		}
	}

}
