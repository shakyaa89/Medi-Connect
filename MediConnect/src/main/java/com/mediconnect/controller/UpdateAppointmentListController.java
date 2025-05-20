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
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateAppointmentList" })
public class UpdateAppointmentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
	ExtractionUtil extractionUtil;
	DeleteService deleteService;
	AddService addService;
	RedirectionUtil redirectionUtil;   
    /**
     * @see HttpServlet#HttpServlet()
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionUtil.setAttribute(request, "appointmentList", dashboardService.getAppointmentListModel());
		
		request.getRequestDispatcher("/WEB-INF/pages/UpdateAppointmentList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDelete(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		Boolean isDeleted = deleteService.deleteAppointment(appointmentId, userId);
		
		if(isDeleted == null) {
			System.out.println("Error Deleting");
		}else {
			redirectionUtil.redirectToPage(request, response, "UpdateAppointmentList");
		}
	}

}
