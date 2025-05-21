package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.DashboardService;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class CustomerDashboardController
 * Handles customer dashboard requests by fetching user-specific dashboard data.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CustomerDashboard" })
public class CustomerDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashboardService dashboardService; 
    
    /**
     * Constructor initializes DashboardService instance.
     */
    public CustomerDashboardController() {
        super();
        dashboardService = new DashboardService();
    }

	/**
	 * Handles GET requests.
	 * Retrieves user from session, fetches dashboard data for that user,
	 * and forwards to CustomerDashboard JSP page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel userObj = (UserModel) SessionUtil.getAttribute(request, "userObj");
		
		int userId = userObj.getUser_id();
		
		SessionUtil.setAttribute(request, "dashboardNumbers", dashboardService.customerDashboardModel(userId));

		request.getRequestDispatcher("/WEB-INF/pages/CustomerDashboard.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
