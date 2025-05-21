package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AccessDeniedController
 * Handles requests when access is denied by forwarding to an access denied page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AccessDenied" })
public class AccessDeniedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default constructor.
     */
    public AccessDeniedController() {
        super();
    }

	/**
	 * Handles GET request.
	 * Forwards the request to AccessDenied.jsp page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/AccessDenied.jsp").forward(request, response);
	}

	/**
	 * Handles POST request.
	 * Delegates POST to GET to show the same page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
