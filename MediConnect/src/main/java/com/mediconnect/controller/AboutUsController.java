package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AboutUsController
 * Handles requests to display the About Us page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AboutUs" })
public class AboutUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default constructor.
     */
    public AboutUsController() {
        super();
    }
    
	/**
	 * Handles GET request.
	 * Forwards request to the AboutUs.jsp page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/AboutUs.jsp").forward(request, response);
	}

	/**
	 * Handles POST request.
	 * Delegates POST to GET to show the same page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
