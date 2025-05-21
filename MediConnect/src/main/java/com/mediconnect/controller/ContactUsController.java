package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ContactUsController
 * Handles requests to the Contact Us page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ContactUs" })
public class ContactUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default constructor.
     */
    public ContactUsController() {
        super();
    }

	/**
	 * Handles GET request.
	 * Forwards request to ContactUs JSP page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward to ContactUs page
		request.getRequestDispatcher("/WEB-INF/pages/ContactUs.jsp").forward(request, response);
	}

	/**
	 * Handles POST request.
	 * Forwards POST requests to doGet to display ContactUs page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
