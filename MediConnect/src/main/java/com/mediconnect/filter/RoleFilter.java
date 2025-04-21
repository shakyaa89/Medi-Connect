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
 * Servlet Filter implementation class RoleFilter
 */
@WebFilter("/*")
public class RoleFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public RoleFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;
        String uri = req.getRequestURI();
        
        if (uri.endsWith("index") ||
                uri.endsWith("login") ||
                uri.endsWith("/") ||
                uri.endsWith("AccessDenied") ||
                uri.endsWith("register") ||
                uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/")) {
                chain.doFilter(request, response);
                return;
            }
        
        if(role == null) {
        	res.sendRedirect(req.getContextPath() + "/AccessDenied");
        	return;
        }


        // Map URIs to role access (basic keyword-based mapping)
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
            chain.doFilter(request, response); // Valid access
        }
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
