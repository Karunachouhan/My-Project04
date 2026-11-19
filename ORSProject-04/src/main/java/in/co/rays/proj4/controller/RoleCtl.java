package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(RoleCtl.class);

	/**
	 * Validates the role form input fields.
	 * Checks whether role name and description are provided.
	 * 
	 * @param request HttpServletRequest object
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("RoleCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			log.warn("Role name is missing");
			request.setAttribute("name", "role name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			log.warn("Role description is missing");
			request.setAttribute("description", "description is required");
			pass = false;
		}

		log.debug("RoleCtl validate method completed with result: " + pass);

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

		log.debug("RoleCtl populateBean method started");

		RoleBean bean = new RoleBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		log.debug("RoleCtl populateBean method completed");

		return bean;
	}

	/**
	 * Creates and returns RoleModel object.
	 * 
	 * @return RoleModel object
	 */
	@Override
	public RoleModel getModel() {

		log.debug("RoleCtl getModel method called");

		return new RoleModel();
	}

	/**
	 * Returns the Role View page.
	 * 
	 * @return Role View path
	 */
	@Override
	public String getView() {

		log.debug("RoleCtl getView method called");

		return ORSView.ROLE_VIEW;
	}
}