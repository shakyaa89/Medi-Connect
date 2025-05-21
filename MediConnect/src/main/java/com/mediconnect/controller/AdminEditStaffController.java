package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.DashboardService;
import com.mediconnect.service.UpdateService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.RedirectionUtil;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class AdminEditStaffController
 * Handles displaying and updating staff details by admin.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminEditStaff" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
	maxFileSize = 1024 * 1024 * 10,
	maxRequestSize = 1024 * 1024 * 50)
public class AdminEditStaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DashboardService dashboardService;
	private RedirectionUtil redirectionUtil;
	private ExtractionUtil extractionUtil;
	private UpdateService updateService;
	
    /**
     * Constructor initializing required services and utilities.
     */
    public AdminEditStaffController() {
        super();
        dashboardService = new DashboardService();
        this.redirectionUtil = new RedirectionUtil();
		this.extractionUtil = new ExtractionUtil();
		this.updateService = new UpdateService();
    }

	/**
	 * Handles GET request.
	 * Fetches staff details by staffId and sets it in session.
	 * Forwards to AdminEditStaff.jsp for editing.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		List<UserModel> staffList = dashboardService.getStaffList();
		
		for(UserModel staff : staffList) {
			if(staff.getUser_id() == staffId) {
				SessionUtil.setAttribute(request, "staffObj", staff);
			}
		}
				
		request.getRequestDispatcher("/WEB-INF/pages/AdminEditStaff.jsp").forward(request, response);
	}
	
	/**
	 * Handles POST request.
	 * Extracts updated staff info and updates staff in database.
	 * Handles image upload and redirects back to staff list on success.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserModel staffObjectOld = (UserModel) SessionUtil.getAttribute(request, "staffObj");
			
			UserModel staffModel = extractionUtil.extractStaffModelUpdate(request, response);
			Boolean isUpdated = updateService.updateStaff(staffModel, staffObjectOld.getUser_id());
			
			if(isUpdated == null) {
				System.out.println("Error adding staff!");
			}else if(isUpdated) {
				try {
					if (extractionUtil.uploadStaffImage(request)) {
						redirectionUtil.redirectToPage(request, response, "AdminStaffList");
						return;
					} else {
						System.out.println("Error Updating staff image");
					}
				} catch (IOException | ServletException e) {
					System.out.println("Error Updating");
					e.printStackTrace(); 
				}
				
			} else {
				System.out.println("Error Updating");
			}
		} catch (Exception e) {
			System.out.println("Error Updating Staff!");
			e.printStackTrace();
		}
	}

}
