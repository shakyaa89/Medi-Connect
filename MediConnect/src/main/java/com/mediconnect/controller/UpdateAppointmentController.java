package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.mediconnect.model.AppointmentModel;
import com.mediconnect.service.AddService;
import com.mediconnect.service.DashboardService;
import com.mediconnect.service.UpdateService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;

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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAppointmentController() {
        super();
        dashboardService = new DashboardService();
		extractionUtil = new ExtractionUtil();
		updateService = new UpdateService();
		redirectionUtil = new RedirectionUtil();
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
