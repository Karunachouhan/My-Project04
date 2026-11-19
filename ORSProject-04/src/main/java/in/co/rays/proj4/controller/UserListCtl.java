package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * UserList Controller class handles listing and searching of user records. It
 * populates UserBean with request parameters and provides UserModel and User
 * List View.
 * 
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/ctl/UserListCtl")
public class UserListCtl extends BaseListCtl<UserBean, UserModel> {

	private static Logger log = Logger.getLogger(UserListCtl.class);

	/**
	 * Populates UserBean with user details received from the HTTP request
	 * parameters.
	 * 
	 * @param request HttpServletRequest object containing user data
	 * @return populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("UserListCtl populateBean started");

		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		log.debug("UserListCtl populateBean completed");

		return bean;
	}

	/**
	 * Creates and returns UserModel object.
	 * 
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {

		log.debug("UserListCtl getModel started");

		UserModel model = new UserModel();

		log.debug("UserListCtl getModel completed");

		return model;
	}

	/**
	 * Returns the User List View page.
	 * 
	 * @return User List View path
	 */
	@Override
	public String getView() {

		log.debug("UserListCtl getView started");

		String view = ORSView.USER_LIST_VIEW;

		log.debug("UserListCtl getView completed");

		return view;
	}
}