package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Role Controller class handles role-related operations such as
 * validation, populating RoleBean, and providing RoleModel and View.
 * 
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/ctl/RoleCtl")
public class RoleCtl extends BaseCtl<RoleBean, RoleModel> {

	/**
	 * Validates the role form input fields.
	 * Checks whether role name and description are provided.
	 * 
	 * @param request HttpServletRequest object
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "role name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "description is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates RoleBean with data received from the request.
	 * 
	 * @param request HttpServletRequest object containing form data
	 * @return populated RoleBean object
	 */
	@Override
	protected RoleBean populateBean(HttpServletRequest request) {
		RoleBean bean = new RoleBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		return bean;
	}

	/**
	 * Creates and returns RoleModel object.
	 * 
	 * @return RoleModel object
	 */
	@Override
	public RoleModel getModel() {
		return new RoleModel();
	}

	/**
	 * Returns the Role View page.
	 * 
	 * @return Role View path
	 */
	@Override
	public String getView() {
		return ORSView.ROLE_VIEW;
	}
}