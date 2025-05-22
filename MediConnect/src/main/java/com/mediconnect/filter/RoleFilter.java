package com.mediconnect.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Servlet Filter implementation class RoleFilter This filter manages role-based
 * access control for the application.
 */
@WebFilter("/*")
public class RoleFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RoleFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Main filtering method to control access based on user role and request URI.
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Cast to HTTP servlet request/response
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Get current session without creating a new one
		HttpSession session = req.getSession(false);
		// Get user role from session attribute
		String role = (session != null) ? (String) session.getAttribute("role") : null;
		// Get requested URI
		String uri = req.getRequestURI();

		// Allow public resources and pages without login
		if (uri.endsWith("index") || uri.endsWith("login") || uri.endsWith("AboutUs") || uri.endsWith("Blog") || uri.endsWith("ContactUs")
				|| uri.endsWith("/") || uri.endsWith("AccessDenied") || uri.endsWith("register") || uri.endsWith(".css")
				|| uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
			chain.doFilter(request, response);
			return;
		}

		// If no role in session, redirect to Access Denied page
		if (role == null) {
			res.sendRedirect(req.getContextPath() + "/AccessDenied");
			return;
		}

		// Specific pages allowed only for Admin role
		if (uri.contains("AdminStaffList") && "Admin".equals(role)) {
			chain.doFilter(request, response);
			return;
		} else if (uri.contains("AdminAddStaff") && "Admin".equals(role)) {
			chain.doFilter(request, response);
			return;
		} else if (uri.contains("AdminEditStaff") && "Admin".equals(role)) {
			chain.doFilter(request, response);
			return;
		}

		// CustomerList allowed for Admin and Staff roles only
		if (uri.contains("CustomerList")) {
			if ("Admin".equals(role) || ("Staff".equals(role))) {
				chain.doFilter(request, response);
				return;
			} else {
				res.sendRedirect(req.getContextPath() + "/AccessDenied");
				return;
			}
		}

		// Role-based access restrictions on URL patterns
		if (uri.contains("Admin") && !"Admin".equals(role)) {
			res.sendRedirect(req.getContextPath() + "/AccessDenied");
			return;
		} else if (uri.contains("Staff") && !"Staff".equals(role)) {
			res.sendRedirect(req.getContextPath() + "/AccessDenied");
			return;
		} else if (uri.contains("Customer") && !"Customer".equals(role)) {
			res.sendRedirect(req.getContextPath() + "/AccessDenied");
			return;
		} else {
			// If no restrictions apply, proceed with request
			chain.doFilter(request, response);
		}
	}

	/**
	 * Filter initialization method.
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// No initialization required
	}

	/**
	 * Filter destruction method.
	 * 
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// No cleanup required
	}

}
