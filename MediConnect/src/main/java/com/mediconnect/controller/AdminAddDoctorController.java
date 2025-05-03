package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mediconnect.model.UserModel;
import com.mediconnect.model.DoctorModel;
import com.mediconnect.model.DoctorAvailabilityModel;
import com.mediconnect.service.AddService;
import com.mediconnect.util.ExtractionUtil;
import com.mediconnect.util.ImageUtil;
import com.mediconnect.util.RedirectionUtil;

/**
 * Servlet implementation class AdminAddDoctorController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminAddDoctor" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50)
public class AdminAddDoctorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AddService addService;
	private RedirectionUtil redirectionUtil;
	private ExtractionUtil extractionUtil;
	private ImageUtil imageUtil;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddDoctorController() {
        super();
        this.extractionUtil = new ExtractionUtil();
		this.imageUtil = new ImageUtil();
		this.addService = new AddService();
		this.redirectionUtil = new RedirectionUtil();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/AdminAddDoctor.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			DoctorModel doctorModel = extractionUtil.extractDoctorModel(request, response);
			DoctorAvailabilityModel doctorAvailabilityModel = extractionUtil.extractDoctorAvailabilityModel(request, response);
			Boolean isAdded = addService.addDoctor(doctorModel, doctorAvailabilityModel);
			
			if(isAdded == null) {
				System.out.println("Error adding");
			}else if(isAdded) {
				try {
					if (extractionUtil.uploadDoctorImage(request)) {
						redirectionUtil.redirectToPage(request, response, "AdminDashboard");
						return;
					} else {
						System.out.println("Error adding image");
					}
				} catch (IOException | ServletException e) {
					System.out.println("Error adding");
					e.printStackTrace(); 
				}
				
			} else {
				System.out.println("Error adding");
			}
		} catch (Exception e) {
			System.out.println("Error Adding!");
			e.printStackTrace();
		}
	}

}
