package com.mediconnect.controller;

import java.io.IOException;

import com.mediconnect.util.CookiesUtil;
import com.mediconnect.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles user logout by clearing cookies and invalidating the session.
 * Redirects user to the login page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles POST request for logout. Deletes user role cookie, invalidates
	 * session, and redirects to login page.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CookiesUtil.deleteCookie(resp, "role"); // Delete role cookie
		SessionUtil.invalidateSession(req); // Invalidate current user session
		resp.sendRedirect(req.getContextPath() + "/login"); // Redirect to login page
	}
}
