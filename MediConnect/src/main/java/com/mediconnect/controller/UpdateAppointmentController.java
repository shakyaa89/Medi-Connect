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
import com.mediconnect.model.DoctorModel;
import com.mediconnect.model.DoctorAvailabilityModel;
import com.mediconnect.service.AddService;
import com.mediconnect.service.DashboardService;
import com.mediconnect.service.UpdateService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;
import com.mediconnect.util.ValidationUtil;

/**
 * Servlet implementation class UpdateAppointmentController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateAppointment" })
public class UpdateAppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
	ExtractionUtil extractionUtil;
	UpdateService updateService;
	RedirectionUtil redirectionUtil;
	ValidationUtil validationUtil;
    /**
     * @see HttpServlet#HttpServlet()
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doctorId = request.getParameter("doctorId");
		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		AppointmentModel appointment = dashboardService.getAppointmentModelUpdate(appointmentId);
		
		SessionUtil.setAttribute(request, "doctorId", doctorId);
		SessionUtil.setAttribute(request, "appointmentId", appointmentId);
		SessionUtil.setAttribute(request, "appointmentTime", appointment.getAppointment_time());
		SessionUtil.setAttribute(request, "appointmentDate", appointment.getAppointment_date());
		
		List<DoctorAvailabilityModel> doctorAvailabilityList = dashboardService.getDoctorAvailabilityList();
		
		for(DoctorAvailabilityModel doctorAv : doctorAvailabilityList) {
			if(doctorAv.getDoctor_id() ==  Integer.parseInt(doctorId)) {
				SessionUtil.setAttribute(request, "startTime", doctorAv.getStart_time());
				SessionUtil.setAttribute(request, "endTime", doctorAv.getEnd_time());
			}
		}
		
		SessionUtil.setAttribute(request, "doctorList", dashboardService.getDoctorList());

		request.getRequestDispatcher("/WEB-INF/pages/UpdateAppointment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPut(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			int appointmentId = (Integer) SessionUtil.getAttribute(request, "appointmentId");
			
			String startTime = (String) SessionUtil.getAttribute(request, "startTime");
			String endTime = (String) SessionUtil.getAttribute(request, "endTime");
			String enteredTime = request.getParameter("time");
			
			LocalDate appDate = LocalDate.parse(request.getParameter("date"));
			
			if(!validationUtil.isAppointmentValid(appDate)) {
				redirectionUtil.setMsgAttribute(request, "error", "Date should be between tomorrow and 6 months!");
				request.getRequestDispatcher("WEB-INF/pages/UpdateAppointment.jsp").forward(request, response);
				return;
			}else if(!validationUtil.isTimeValid(startTime, endTime, enteredTime)) {
				redirectionUtil.setMsgAttribute(request, "error", "Time should be between " + startTime + " and " + endTime);
				request.getRequestDispatcher("WEB-INF/pages/UpdateAppointment.jsp").forward(request, response);
				return;
			}

			AppointmentModel appointmentModel = extractionUtil.extractAppointmentModelUpdate(request, response);
			Boolean isUpdated = updateService.updateAppointment(appointmentModel, appointmentId);
			
			if(isUpdated == null) {
				System.out.println("Error appointment");
			}else {
				redirectionUtil.redirectToPage(request, response, "UpdateAppointmentList");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
