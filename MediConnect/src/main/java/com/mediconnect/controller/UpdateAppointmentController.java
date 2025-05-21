package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.mediconnect.model.AppointmentModel;
import com.mediconnect.model.DoctorAvailabilityModel;
import com.mediconnect.service.DashboardService;
import com.mediconnect.service.UpdateService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;
import com.mediconnect.util.ValidationUtil;

/**
 * Servlet implementation class UpdateAppointmentController Handles updating an
 * existing appointment.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateAppointment" })
public class UpdateAppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Service and utility instances for dashboard data, extraction, update,
	// redirection, and validation
	DashboardService dashboardService;
	ExtractionUtil extractionUtil;
	UpdateService updateService;
	RedirectionUtil redirectionUtil;
	ValidationUtil validationUtil;

	/**
	 * Default constructor initializing services and utilities.
	 */
	public UpdateAppointmentController() {
		super();
		dashboardService = new DashboardService();
		extractionUtil = new ExtractionUtil();
		updateService = new UpdateService();
		redirectionUtil = new RedirectionUtil();
		validationUtil = new ValidationUtil();
	}

	/**
	 * Handles GET requests - loads appointment info and doctor availability, sets
	 * attributes, forwards to JSP.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String doctorId = request.getParameter("doctorId");
		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

		// Fetch appointment details for update
		AppointmentModel appointment = dashboardService.getAppointmentModelUpdate(appointmentId);

		// Set appointment and doctor info in session for access in JSP and validation
		SessionUtil.setAttribute(request, "doctorId", doctorId);
		SessionUtil.setAttribute(request, "appointmentId", appointmentId);
		SessionUtil.setAttribute(request, "appointmentTime", appointment.getAppointment_time());
		SessionUtil.setAttribute(request, "appointmentDate", appointment.getAppointment_date());

		// Retrieve doctor availability and set start/end time for selected doctor
		List<DoctorAvailabilityModel> doctorAvailabilityList = dashboardService.getDoctorAvailabilityList();
		for (DoctorAvailabilityModel doctorAv : doctorAvailabilityList) {
			if (doctorAv.getDoctor_id() == Integer.parseInt(doctorId)) {
				SessionUtil.setAttribute(request, "startTime", doctorAv.getStart_time());
				SessionUtil.setAttribute(request, "endTime", doctorAv.getEnd_time());
			}
		}

		// Set list of doctors in session for display in JSP
		SessionUtil.setAttribute(request, "doctorList", dashboardService.getDoctorList());

		// Forward to UpdateAppointment JSP page
		request.getRequestDispatcher("/WEB-INF/pages/UpdateAppointment.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests - delegates to doPut for update processing.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPut(request, response);
	}

	/**
	 * Handles PUT requests - validates input and updates appointment in database.
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int appointmentId = (Integer) SessionUtil.getAttribute(request, "appointmentId");

			// Retrieve valid time range from session and user entered time & date from
			// request
			String startTime = (String) SessionUtil.getAttribute(request, "startTime");
			String endTime = (String) SessionUtil.getAttribute(request, "endTime");
			String enteredTime = request.getParameter("time");
			LocalDate appDate = LocalDate.parse(request.getParameter("date"));

			// Validate appointment date range
			if (!validationUtil.isAppointmentValid(appDate)) {
				redirectionUtil.setMsgAttribute(request, "error", "Date should be between tomorrow and 6 months!");
				request.getRequestDispatcher("WEB-INF/pages/UpdateAppointment.jsp").forward(request, response);
				return;
			}
			// Validate appointment time within doctor's availability
			else if (!validationUtil.isTimeValid(startTime, endTime, enteredTime)) {
				redirectionUtil.setMsgAttribute(request, "error",
						"Time should be between " + startTime + " and " + endTime);
				request.getRequestDispatcher("WEB-INF/pages/UpdateAppointment.jsp").forward(request, response);
				return;
			}

			// Extract appointment model from request
			AppointmentModel appointmentModel = extractionUtil.extractAppointmentModelUpdate(request, response);

			// Update appointment using update service
			Boolean isUpdated = updateService.updateAppointment(appointmentModel, appointmentId);

			if (isUpdated == null) {
				System.out.println("Error appointment");
			} else {
				// Redirect to appointment list on successful update
				redirectionUtil.redirectToPage(request, response, "UpdateAppointmentList");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
