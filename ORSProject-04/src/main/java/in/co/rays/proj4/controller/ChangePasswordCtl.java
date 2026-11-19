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
 * ChangePasswordCtl handles requests related to changing a user's password.
 * It validates the password change form, populates the UserBean,
 * and interacts with UserModel to change the password.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/ChangePasswordCtl")
public class ChangePasswordCtl extends BaseCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(ChangePasswordCtl.class);

	/**
	 * Validates the login ID, old password, and new password fields.
	 *
	 * @param request HttpServletRequest containing form data
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ChangePasswordCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login id is required");
			log.warn("Login ID is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", "Old Password is required");
			log.warn("Old password is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", "New Password is required");
			log.warn("New password is required");
			pass = false;
		}

		if (pass) {
			log.info("Change password form validation successful");
		} else {
			log.error("Change password form validation failed");
		}

		log.debug("ChangePasswordCtl validate method completed");

		return pass;
	}

	/**
	 * Populates a UserBean with login ID and old password
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing form data
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("ChangePasswordCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));

		log.info("UserBean populated successfully");
		log.debug("ChangePasswordCtl populateBean method completed");

		return bean;
	}

	/**
	 * Handles HTTP POST requests to change the user's password.
	 * Calls the model to update the password and sets a success
	 * or error message in the request.
	 *
	 * @param request HttpServletRequest containing password details
	 * @param response HttpServletResponse used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException if an input/output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ChangePasswordCtl doPost method started");

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		log.info("Calling changePassword method");

		model.changePassword(request.getParameter("newPassword"), bean.getPassword(), bean.getLogin());

		log.info("changePassword method executed");

		if (bean != null) {
			log.info("Password change success message set");
			ServletUtility.setSuccessMessage("your password has been changed check your email", request);
		} 
		else {
			log.warn("UserBean is null");
			ServletUtility.setErrorMessage("your email is incorrect", request);
		}

		log.debug("Calling BaseCtl doPost method");

		super.doPost(request, response);

		log.debug("ChangePasswordCtl doPost method completed");
	}

	/**
	 * Returns a new instance of UserModel.
	 *
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {

		log.debug("ChangePasswordCtl getModel method started");

		log.info("UserModel object created successfully");
		log.debug("ChangePasswordCtl getModel method completed");

		return new UserModel();
	}

	/**
	 * Returns the change password view page.
	 *
	 * @return change password view path
	 */
	@Override
	public String getView() {

		log.debug("ChangePasswordCtl getView method started");
		log.info("Change password view returned successfully");
		log.debug("ChangePasswordCtl getView method completed");

		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}