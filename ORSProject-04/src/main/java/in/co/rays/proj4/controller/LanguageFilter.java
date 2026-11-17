package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * LanguageFilter is a servlet filter used to manage the application language.
 * It checks the language parameter in the request and updates the locale
 * accordingly before passing the request to the next resource.
 *
 * @author karunaChouhan
 */
@WebFilter("/*")
public class LanguageFilter implements Filter {

	/**
	 * Initializes the language filter.
	 *
	 * @param filterConfig configuration information for the filter
	 * @throws ServletException if an error occurs during filter initialization
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * Checks the language parameter in the HTTP request.
	 * If a language is provided, it updates the application's locale
	 * and passes the request to the next filter or resource.
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
		HttpServletRequest req = (HttpServletRequest) request;
		MessageSource messagesource = MessageSource.getInstance();

		String lang = DataUtility.getString(req.getParameter("lang"));
		if (DataValidator.isNotNull(lang)) {
			messagesource.setLocale(lang);
		}

		chain.doFilter(req, response);

	}

	/**
	 * Destroys the language filter and releases any resources held by it.
	 */
	@Override
	public void destroy() {

	}

}