package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * UserRegistrationCtl handles the registration of new users. It validates user
 * input, populates the UserBean, registers the user, and forwards the request
 * to the registration view.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/UserRegistrationCtl")
public class UserRegistrationCtl extends BaseCtl<UserBean, UserModel> {

	/**
	 * Operation constant used for user sign-up.
	 */
	public static final String OP_SIGN_UP = "SignUp";

	/**
	 * Validates the user registration form fields.
	 *
	 * @param request HttpServletRequest containing user registration details
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "firstName is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "lastName is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "login is not in valid formate");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "confirmPassword is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "gender is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "dob is required");
			pass = false;
		}
		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "Confirm  Password  should not be matched.");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates the UserBean with registration details received from the request.
	 *
	 * @param request HttpServletRequest containing user registration details
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setRoleId(2L);

		populateDTO(bean, request);
		return bean;

	}

	/**
	 * Processes the user registration form submission. Registers the user and sets
	 * success or error messages. Finally, forwards the request to the registration
	 * view.
	 *
	 * @param request  HttpServletRequest containing registration data
	 * @param response HttpServletResponse used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException      if an input/output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			try {
				model.register(bean);
				// model.add(bean);
				ServletUtility.setSuccessMessage("User is registered, Login now", request);
			} catch (DuplicateRecordException e) {
				ServletUtility.setErrorMessage("Login id already exist", request);
			}
		}

		// field allready fill
		bean = populateBean(request);
		ServletUtility.setBean(bean, request);

		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the UserModel object.
	 *
	 * @return new UserModel instance
	 */
	@Override
	public UserModel getModel() {
		return new UserModel();
	}

	/**
	 * Returns the registration view page.
	 *
	 * @return registration view path
	 */
	@Override
	public String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}