package in.co.rays.proj4.controller;

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

	/**
	 * Populates UserBean with user details received from the HTTP request
	 * parameters.
	 * 
	 * @param request HttpServletRequest object containing user data
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
		return bean;
	}

	/**
	 * Creates and returns UserModel object.
	 * 
	 * @return UserModel object
	 */
	@Override
	public UserModel getModel() {
		return new UserModel();
	}

	/**
	 * Returns the User List View page.
	 * 
	 * @return User List View path
	 */
	@Override
	public String getView() {
		return ORSView.USER_LIST_VIEW;
	}
}