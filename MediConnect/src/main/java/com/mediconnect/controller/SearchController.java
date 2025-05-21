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
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SearchController" })
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchService searchService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        searchService = new SearchService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search").trim();
		
		List<DoctorModel> searchedDoctorList = searchService.searchDoctor(search);
		
		SessionUtil.setAttribute(request, "doctorList", searchedDoctorList);
		
		request.getRequestDispatcher("/WEB-INF/pages/CustomerDoctorList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
