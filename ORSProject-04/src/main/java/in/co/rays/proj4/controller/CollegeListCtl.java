package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CollegeListCtl is a controller that handles the listing and searching
 * operations for college records.
 * It extends BaseListCtl to use centralized list functionality.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/CollegeListCtl")
public class CollegeListCtl extends BaseListCtl<CollegeBean, CollegeModel> {

	private static Logger log = Logger.getLogger(CollegeListCtl.class);

	/**
	 * Populates the CollegeBean object with college details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing college details
	 * @return populated CollegeBean object
	 */
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {
		log.debug("CollegeListCtl populateBean method started");

		CollegeBean bean = new CollegeBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		log.info("CollegeBean populated successfully");
		log.debug("CollegeListCtl populateBean method completed");

		return bean;
	}

	/**
	 * Creates and returns the CollegeModel object.
	 *
	 * @return CollegeModel object
	 */
	@Override
	public CollegeModel getModel() {
		log.debug("CollegeListCtl getModel method started");

		CollegeModel model = new CollegeModel();

		log.info("CollegeModel object created successfully");
		log.debug("CollegeListCtl getModel method completed");

		return model;
	}

	/**
	 * Returns the view page for displaying the college list.
	 *
	 * @return college list view path
	 */
	@Override
	public String getView() {
		log.debug("CollegeListCtl getView method started");
		log.info("College list view returned successfully");
		log.debug("CollegeListCtl getView method completed");

		return ORSView.COLLEGE_LIST_VIEW;
	}
}