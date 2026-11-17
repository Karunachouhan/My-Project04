package in.co.rays.proj4.controller;

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

	/**
	 * Populates the CollegeBean object with college details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing college details
	 * @return populated CollegeBean object
	 */
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {
		CollegeBean bean = new CollegeBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		return bean;
	}

	/**
	 * Creates and returns the CollegeModel object.
	 *
	 * @return CollegeModel object
	 */
	@Override
	public CollegeModel getModel() {
		return new CollegeModel();
	}

	/**
	 * Returns the view page for displaying the college list.
	 *
	 * @return college list view path
	 */
	@Override
	public String getView() {
		return ORSView.COLLEGE_LIST_VIEW;
	}
}