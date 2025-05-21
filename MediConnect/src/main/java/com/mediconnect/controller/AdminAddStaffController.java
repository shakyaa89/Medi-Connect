package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.model.UserModel;
import com.mediconnect.service.AddService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.ImageUtil;
import com.mediconnect.util.RedirectionUtil;

/**
 * Servlet implementation class AdminAddStaffController
 * Handles adding new staff members including uploading their image.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminAddStaff" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
maxFileSize = 1024 * 1024 * 10,
maxRequestSize = 1024 * 1024 * 50)
public class AdminAddStaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AddService addService;
	private RedirectionUtil redirectionUtil;
	private ExtractionUtil extractionUtil;
	
	/**
	 * Initializes service and utility instances.
	 */
	public void init() throws ServletException {
		this.addService = new AddService();
		this.redirectionUtil = new RedirectionUtil();
		this.extractionUtil = new ExtractionUtil();
	}
	
    /**
     * Default constructor.
     */
    public AdminAddStaffController() {
        super();
    }

	/**
	 * Handles GET request.
	 * Forwards to AdminAddStaff.jsp page to show the staff addition form.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/AdminAddStaff.jsp").forward(request, response);
	}

	/**
	 * Handles POST request.
	 * Extracts staff data from the form, attempts to add staff, and upload staff image.
	 * Redirects to AdminDashboard on success.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserModel staffModel = extractionUtil.extractStaffModel(request, response);
			Boolean isAdded = addService.addStaff(staffModel);
			
			if(isAdded == null) {
				System.out.println("Error adding staff!");
			}else if(isAdded) {
				try {
					if (extractionUtil.uploadStaffImage(request)) {
						redirectionUtil.redirectToPage(request, response, "AdminDashboard");
						return;
					} else {
						System.out.println("Error adding staff image");
					}
				} catch (IOException | ServletException e) {
					System.out.println("Error adding");
					e.printStackTrace(); 
				}
			} else {
				System.out.println("Error adding");
			}
		} catch (Exception e) {
			System.out.println("Error Adding Staff!");
			e.printStackTrace();
		}
	}

}
