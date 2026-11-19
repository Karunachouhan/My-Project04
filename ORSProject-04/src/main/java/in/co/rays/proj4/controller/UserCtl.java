package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * User Controller class handles user-related operations such as validating user
 * details, populating UserBean, preloading role data, and providing UserModel
 * and View.
 * 
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/ctl/UserCtl")
public class UserCtl extends BaseCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(UserCtl.class);

	/**
	 * Validates the user form input fields. Checks whether first name, last name,
	 * login, password, gender, date of birth, and role ID are provided.
	 * 
	 * @param request HttpServletRequest object containing form data
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			log.warn("First name is missing");
			request.setAttribute("firstName", "first name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			log.warn("Last name is missing");
			request.setAttribute("lastName", "last name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("login"))) {
			log.warn("Login is missing");
			request.setAttribute("login", "login is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			log.warn("Password is missing");
			request.setAttribute("password", "password is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			log.warn("Gender is missing");
			request.setAttribute("gender", "gender is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			log.warn("Date of birth is missing");
			request.setAttribute("dob", "dob is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("roleId"))) {
			log.warn("Role ID is missing");
			request.setAttribute("roleId", "role is required");
			pass = false;
		}

		log.debug("UserCtl validate method completed with result: " + pass);

		return pass;
	}

	/**
	 * Populates UserBean with user details received from the HTTP request
	 * parameters.
	 * 
	 * @param request HttpServletRequest object containing user data
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("UserCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		// bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		populateDTO(bean, request);

		log.debug("UserCtl populateBean method completed");

		return bean;
	}

	/**
	 * Preloads the role list from the database and stores it in the request
	 * attribute for displaying the role dropdown.
	 * 
	 * @param request HttpServletRequest object
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		log.debug("UserCtl preload method started");

		RoleModel rmodel = new RoleModel();
		List<RoleBean> roleList = rmodel.search(null, 0, 0);
		request.setAttribute("roleList", roleList);

		log.debug("UserCtl preload method completed");
	}

	/**
	 * Creates and returns UserModel object.
	 * 
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {

		log.debug("UserCtl getModel method called");

		return new UserModel();
	}

	/**
	 * Returns the User View page.
	 * 
	 * @return User View path
	 */
	@Override
	public String getView() {

		log.debug("UserCtl getView method called");

		return ORSView.USER_VIEW;
	}
}