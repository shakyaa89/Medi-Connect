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
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminDoctorList" })
public class AdminDoctorListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
	DeleteService deleteService;
	RedirectionUtil redirectionUtil;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDoctorListController() {
        super();
        dashboardService = new DashboardService();
        deleteService = new DeleteService();
        redirectionUtil = new RedirectionUtil();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SessionUtil.setAttribute(request, "doctorList", dashboardService.getDoctorList());
		
		request.getRequestDispatcher("/WEB-INF/pages/AdminDoctorList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDelete(request, response);
	}
	
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
