package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ForgetPasswordCtl handles the forgot password functionality.
 * It validates the user's login ID and requests password recovery
 * through the UserModel.
 *
 * @author karunaChouhan
 */
@WebServlet("/ForgetPasswordCtl")
public class ForgetPasswordCtl extends BaseCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);

	/**
	 * Validates the login ID provided by the user.
	 *
	 * @param request HttpServletRequest containing the login ID
	 * @return true if the login ID is provided, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("ForgetPasswordCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login id is required");
			log.warn("Login ID is required");
			pass = false;
		}

		if (pass) {
			log.info("Login ID validation successful");
		} else {
			log.error("Login ID validation failed");
		}

		log.debug("ForgetPasswordCtl validate method completed");
		return pass;
	}

	/**
	 * Populates the UserBean object with the login ID
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing user details
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {
		log.debug("ForgetPasswordCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		log.info("UserBean populated successfully");
		log.debug("ForgetPasswordCtl populateBean method completed");

		return bean;
	}

	/**
	 * Handles the forgot password request.
	 * Recovers the user's password using the login ID and sets
	 * an appropriate success or error message.
	 *
	 * @param request HttpServletRequest containing the user request
	 * @param response HttpServletResponse used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ForgetPasswordCtl doPost method started");

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		log.info("Calling forgetPassword method");
		bean = model.forgetPassword(bean.getLogin());

		if (bean != null) {
			log.info("Password recovery request processed successfully");
			ServletUtility.setSuccessMessage("your password has been recovered check your email", request);
		} else {
			log.warn("Password recovery failed: user not found");
			ServletUtility.setErrorMessage("your email is incorrect", request);
		}

		log.debug("Forwarding to forgot password view");
		ServletUtility.forward(getView(), request, response);

		log.debug("ForgetPasswordCtl doPost method completed");
	}

	/**
	 * Creates and returns the UserModel object.
	 *
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {
		log.debug("ForgetPasswordCtl getModel method started");

		UserModel model = new UserModel();

		log.info("UserModel object created successfully");
		log.debug("ForgetPasswordCtl getModel method completed");

		return model;
	}

	/**
	 * Returns the view page for the forgot password form.
	 *
	 * @return forgot password view path
	 */
	@Override
	public String getView() {
		log.debug("ForgetPasswordCtl getView method started");
		log.info("Forgot password view returned successfully");
		log.debug("ForgetPasswordCtl getView method completed");

		return ORSView.FORGET_PASSWORD_VIEW;
	}

}