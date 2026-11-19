package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * FrontClt is a front controller filter that checks whether a user
 * is logged in before allowing access to protected controller URLs.
 * If the user session is not available, it redirects the request
 * to the login page.
 *
 * @author karunaChouhan
 */
@WebFilter("/ctl/*")
public class FrontClt implements Filter {

	private static Logger log = Logger.getLogger(FrontClt.class);

	// front control design pattern is used to prevent/stop any user to access
	// application without login

	/**
	 * Initializes the filter.
	 *
	 * @param filterConfig configuration information for the filter
	 * @throws ServletException if an error occurs during filter initialization
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("FrontClt filter initialization started");
		log.info("FrontClt filter initialized successfully");
	}

	/**
	 * Checks whether the user is logged in before allowing the request
	 * to proceed to the next filter or controller.
	 *
	 * @param request ServletRequest object
	 * @param response ServletResponse object
	 * @param chain FilterChain used to pass the request to the next resource
	 * @throws IOException if an input or output error occurs
	 * @throws ServletException if a servlet-related error occurs
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		log.debug("FrontClt doFilter method started");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		log.debug("Checking user session");

		if (session.getAttribute("user") == null) {
			log.warn("User session expired or user is not logged in");

			ServletUtility.setErrorMessage("session has been expired please re-login", req);
			ServletUtility.forward(ORSView.LOGIN_VIEW, req, res);

			log.info("Request forwarded to login page");
			return;

		}

		log.info("User session is valid");
		log.debug("Passing request to next filter or controller");

		chain.doFilter(request, response); // call next config filter/controller in the chain

		log.debug("FrontClt doFilter method completed");
	}

	/**
	 * Destroys the filter and releases any resources held by it.
	 */
	@Override
	public void destroy() {
		log.debug("FrontClt filter destroy method started");
		log.info("FrontClt filter destroyed successfully");
	}
}