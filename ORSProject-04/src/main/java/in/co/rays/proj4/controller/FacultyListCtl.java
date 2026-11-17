package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * FacultyListCtl handles listing and searching operations for faculty records.
 * It extends BaseListCtl to use centralized list functionality.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/FacultyListCtl")
public class FacultyListCtl extends BaseListCtl<FacultyBean, FacultyModel> {

	/**
	 * Populates the FacultyBean object with faculty details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing faculty details
	 * @return populated FacultyBean object
	 */
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {
		FacultyBean bean = new FacultyBean();

		bean.setCollegeId(DataUtility.getInt(request.getParameter("collegeId")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));

		return bean;

	}

	/**
	 * Creates and returns the FacultyModel object.
	 *
	 * @return FacultyModel object
	 */
	@Override
	public FacultyModel getModel() {
		return new FacultyModel();
	}

	/**
	 * Returns the view page for displaying the faculty list.
	 *
	 * @return faculty list view path
	 */
	@Override
	public String getView() {
		return ORSView.FACULTY_LIST_VIEW;
	}
}