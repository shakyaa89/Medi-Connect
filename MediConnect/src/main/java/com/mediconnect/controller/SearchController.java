package com.mediconnect.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.mediconnect.model.DoctorModel;
import com.mediconnect.service.SearchService;
import com.mediconnect.util.SessionUtil;

/**
 * Servlet implementation class SearchController
 * Handles search requests for doctors and forwards results to the customer doctor list page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SearchController" })
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchService searchService;

    /**
     * Default constructor initializing the SearchService.
     */
    public SearchController() {
        super();
        searchService = new SearchService();
    }

	/**
	 * Handles GET requests for doctor search.
	 * Extracts search query, fetches matching doctors, sets list in session, and forwards to JSP.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get search query from request and trim whitespace
		String search = request.getParameter("search").trim();
		
		// Perform doctor search using service
		List<DoctorModel> searchedDoctorList = searchService.searchDoctor(search);
		
		// Store the search results in session attribute
		SessionUtil.setAttribute(request, "doctorList", searchedDoctorList);
		
		// Forward to customer doctor list JSP page to display results
		request.getRequestDispatcher("/WEB-INF/pages/CustomerDoctorList.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to doGet to process search.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
