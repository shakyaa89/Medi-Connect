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
 * Servlet implementation class AdminStaffListController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminStaffList" })
public class AdminStaffListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
	DeleteService deleteService;
	RedirectionUtil redirectionUtil;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminStaffListController() {
        super();
        dashboardService = new DashboardService();
        deleteService = new DeleteService();
        redirectionUtil = new RedirectionUtil();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SessionUtil.setAttribute(request, "staffList", dashboardService.getStaffList());
		
		request.getRequestDispatcher("/WEB-INF/pages/AdminStaffList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		
		Boolean isDeleted = deleteService.deleteStaff(staffId);
		
		if(isDeleted == null) {
			System.out.println("Error deleting staff");
		}else {
			redirectionUtil.redirectToPage(request, response, "AdminStaffList");
		}
				
	}

}
