package com.mediconnect.controller;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.model.AppointmentModel;
import com.mediconnect.model.UserModel;
import com.mediconnect.service.AddService;
import com.mediconnect.service.DashboardService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;
import com.mediconnect.util.ValidationUtil;

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
	ValidationUtil validationUtil;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerBookAppointmentController() {
		super();
		dashboardService = new DashboardService();
		extractionUtil = new ExtractionUtil();
		addService = new AddService();
		redirectionUtil = new RedirectionUtil();
		validationUtil = new ValidationUtil();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String doctorId = request.getParameter("doctorId");
		String doctorStartTime = request.getParameter("startTime");
		String doctorEndTime = request.getParameter("endTime");
		
		SessionUtil.setAttribute(request, "doctorId", doctorId);
		SessionUtil.setAttribute(request, "startTime", doctorStartTime);
		SessionUtil.setAttribute(request, "endTime", doctorEndTime);
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
			String startTime = (String) SessionUtil.getAttribute(request, "startTime");
			String endTime = (String) SessionUtil.getAttribute(request, "endTime");
			String enteredTime = request.getParameter("time");

			UserModel user = (UserModel) SessionUtil.getAttribute(request, "userObj");
			int userId = user.getUser_id();
			
			LocalDate appDate = LocalDate.parse(request.getParameter("date"));
			
			if(!validationUtil.isAppointmentValid(appDate)) {
				redirectionUtil.setMsgAttribute(request, "error", "Date should be between tomorrow and 6 months!");
				request.getRequestDispatcher("WEB-INF/pages/CustomerBookAppointment.jsp").forward(request, response);
				return;
			}else if(!validationUtil.isTimeValid(startTime, endTime, enteredTime)) {
				redirectionUtil.setMsgAttribute(request, "error", "Time should be between " + startTime + " and " + endTime);
				request.getRequestDispatcher("WEB-INF/pages/CustomerBookAppointment.jsp").forward(request, response);
				return;
			}

			AppointmentModel appointmentModel = extractionUtil.extractAppointmentModel(request, response);
			
			Boolean isAdded = addService.addAppointment(appointmentModel, doctorId, userId);
			
			
			
			if(isAdded == null) {
				redirectionUtil.setMsgAttribute(request, "error", "Internal Server Error!");
				request.getRequestDispatcher("WEB-INF/pages/CustomerBookAppointment.jsp").forward(request, response);
				System.out.println("Error appointment");
				return;
			}else if(isAdded == false){
				redirectionUtil.setMsgAttribute(request, "error", "You already have an appointment with this doctor!");
				request.getRequestDispatcher("WEB-INF/pages/CustomerBookAppointment.jsp").forward(request, response);
				return;
			}else {
				redirectionUtil.redirectToPage(request, response, "CustomerDashboard");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
