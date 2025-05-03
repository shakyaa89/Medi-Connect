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
	private ImageUtil imageUtil;
	
	public void init() throws ServletException {
		this.addService = new AddService();
		this.redirectionUtil = new RedirectionUtil();
		this.extractionUtil = new ExtractionUtil();
		this.imageUtil = new ImageUtil();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddStaffController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/AdminAddStaff.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
