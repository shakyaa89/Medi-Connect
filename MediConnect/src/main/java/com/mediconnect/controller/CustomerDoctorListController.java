package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.service.DashboardService;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class CustomerDoctorListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CustomerDoctorList" })
public class CustomerDoctorListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDoctorListController() {
        super();
        dashboardService = new DashboardService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SessionUtil.setAttribute(request, "doctorList", dashboardService.getDoctorList());
		
		request.getRequestDispatcher("/WEB-INF/pages/CustomerDoctorList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
