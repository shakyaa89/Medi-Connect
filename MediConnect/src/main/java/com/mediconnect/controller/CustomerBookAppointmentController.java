package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.model.AppointmentModel;
import com.mediconnect.service.AddService;
import com.mediconnect.service.DashboardService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class CustomerBookAppointmentController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CustomerBookAppointment" })
public class CustomerBookAppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
	ExtractionUtil extractionUtil;
	AddService addService;
	RedirectionUtil redirectionUtil;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerBookAppointmentController() {
		super();
		dashboardService = new DashboardService();
		extractionUtil = new ExtractionUtil();
		addService = new AddService();
		redirectionUtil = new RedirectionUtil();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String doctorId = request.getParameter("doctorId");
		SessionUtil.setAttribute(request, "doctorId", doctorId);

		SessionUtil.setAttribute(request, "doctorList", dashboardService.getDoctorList());

		request.getRequestDispatcher("/WEB-INF/pages/CustomerBookAppointment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int doctorId = Integer.parseInt((String) SessionUtil.getAttribute(request, "doctorId"));

			int userId = (int) SessionUtil.getAttribute(request, "userId");

			AppointmentModel appointmentModel = extractionUtil.extractAppointmentModel(request, response);
			Boolean isAdded = addService.addAppointment(appointmentModel, doctorId, userId);
			
			if(isAdded == null) {
				System.out.println("Error appointment");
			}else {
				redirectionUtil.redirectToPage(request, response, "CustomerDashboard");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
