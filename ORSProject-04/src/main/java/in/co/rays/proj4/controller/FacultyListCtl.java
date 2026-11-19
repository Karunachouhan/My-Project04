package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(FacultyListCtl.class);

	/**
	 * Populates the FacultyBean object with faculty details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing faculty details
	 * @return populated FacultyBean object
	 */
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {
		log.debug("FacultyListCtl populateBean method started");

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

		log.info("FacultyBean populated successfully");
		log.debug("FacultyListCtl populateBean method completed");

		return bean;

	}

	/**
	 * Creates and returns the FacultyModel object.
	 *
	 * @return FacultyModel object
	 */
	@Override
	public FacultyModel getModel() {
		log.debug("FacultyListCtl getModel method started");

		FacultyModel model = new FacultyModel();

		log.info("FacultyModel object created successfully");
		log.debug("FacultyListCtl getModel method completed");

		return model;
	}

	/**
	 * Returns the view page for displaying the faculty list.
	 *
	 * @return faculty list view path
	 */
	@Override
	public String getView() {
		log.debug("FacultyListCtl getView method started");
		log.info("Faculty list view returned successfully");
		log.debug("FacultyListCtl getView method completed");

		return ORSView.FACULTY_LIST_VIEW;
	}
}