package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * RoleList Controller class handles listing and searching of role records. It
 * populates RoleBean with request parameters and provides RoleModel and Role
 * List View.
 * 
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/ctl/RoleListCtl")
public class RoleListCtl extends BaseListCtl<RoleBean, RoleModel> {

	/**
	 * Populates RoleBean with data received from the request.
	 * 
	 * @param request HttpServletRequest object containing request parameters
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
	 * Returns the Role List View page.
	 * 
	 * @return Role List View path
	 */
	@Override
	public String getView() {
		return ORSView.ROLE_LIST_VIEW;
	}
}