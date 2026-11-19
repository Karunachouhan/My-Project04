package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * MyProfileCtl handles the user's profile information. It validates profile
 * data, populates the UserBean, and provides the model and view required for
 * the profile page.
 *
 * @author Karuna
 * 
 * @version 1.0
 */
@WebServlet("/ctl/MyProfileCtl")
public class MyProfileCtl extends BaseCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(MyProfileCtl.class);

	/**
	 * Validates the user profile information received from the request.
	 *
	 * @param request HTTP servlet request
	 * @return true if all profile fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MyProfileCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "firstName is required");
			log.warn("First name is missing");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "lastName is required");
			log.warn("Last name is missing");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			log.warn("Login is missing");
			pass = false;
		} /*
			 * else if (!request.getParameter("login").matches("[A-Za-z][A-Za-z0-9_]*")) {
			 * request.setAttribute("login", "login is not in valid formate"); pass = false;
			 * }
			 */
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			log.warn("Password is missing");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "gender is required");
			log.warn("Gender is missing");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "dob is required");
			log.warn("Date of birth is missing");
			pass = false;
		}

		if (pass) {
			log.info("Profile validation successful");
		} else {
			log.warn("Profile validation failed");
		}

		log.debug("MyProfileCtl validate method completed");

		return pass;
	}

	/**
	 * Populates the UserBean with profile information received from the request.
	 *
	 * @param request HTTP servlet request
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("MyProfileCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRoleId(DataUtility.getInt(request.getParameter("roleId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
//		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		populateDTO(bean, request); // Its work is to set only 4 attributes

		log.info("UserBean populated successfully");
		log.debug("MyProfileCtl populateBean method completed");

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MyProfileCtl doGet method started");

		UserBean user = (UserBean) request.getSession().getAttribute("user");

		UserModel model = getModel();
		UserBean bean = model.findByPk(user.getId());

		ServletUtility.setBean(bean, request);
		ServletUtility.forward(getView(), request, response);

		log.info("User profile loaded successfully");
		log.debug("MyProfileCtl doGet method completed");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MyProfileCtl doPost method started");

		UserBean user = (UserBean) request.getSession().getAttribute("user");

		UserBean bean = populateBean(request);

		bean.setId(user.getId());

		UserModel model = getModel();

		try {
			model.updateProfile(bean);

			log.info("User profile updated successfully");

			ServletUtility.setBean(bean, request);
			ServletUtility.setSuccessMessage("Profile updated successfully", request);

		} catch (Exception e) {

			log.error("Error occurred while updating user profile", e);

			ServletUtility.setBean(bean, request);
			ServletUtility.setErrorMessage("Profile update failed", request);
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("MyProfileCtl doPost method completed");
	}

	/**
	 * Returns the view for the user's profile page.
	 *
	 * @return profile view
	 */
	@Override
	public String getView() {

		log.debug("MyProfileCtl getView method called");

		return ORSView.MY_PROFILE_VIEW;
	}

	/**
	 * Returns the UserModel instance used by this controller.
	 *
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {

		log.debug("MyProfileCtl getModel method called");

		return new UserModel();
	}

}