package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * FacultyCtl handles faculty-related operations such as validation,
 * populating faculty details, loading faculty data, and managing the faculty view.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl<FacultyBean, FacultyModel> {

	private static Logger log = Logger.getLogger(FacultyCtl.class);

	/**
	 * Validates the faculty details received from the HTTP request.
	 * Checks whether all required fields are provided.
	 *
	 * @param request HttpServletRequest containing faculty details
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("FacultyCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", "College is required");
			log.warn("College is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "First name is required");
			log.warn("First name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "Last name is required");
			log.warn("Last name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", "Email is required");
			log.warn("Email is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile number is required");
			log.warn("Mobile number is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "Address is required");
			log.warn("Address is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "Gender is required");
			log.warn("Gender is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "Dob is required");
			log.warn("Date of birth is required");
			pass = false;
		}

		if (pass) {
			log.info("Faculty form validation successful");
		} else {
			log.error("Faculty form validation failed");
		}

		log.debug("FacultyCtl validate method completed");
		return pass;
	}

	/**
	 * Populates the FacultyBean object with faculty details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing faculty details
	 * @return populated FacultyBean object
	 */
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {
		log.debug("FacultyCtl populateBean method started");

		FacultyBean bean = new FacultyBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCollegeId(DataUtility.getInt(request.getParameter("collegeId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));

		populateDTO(bean, request);

		log.info("FacultyBean populated successfully");
		log.debug("FacultyCtl populateBean method completed");

		return bean;

	}

	/**
	 * Loads the faculty list from the database and stores it
	 * as a request attribute for use in the view.
	 *
	 * @param request HttpServletRequest used to store the faculty list
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("FacultyCtl preload method started");

		FacultyModel fmodel = new FacultyModel();
		List<FacultyBean> facultyList = fmodel.search(null, 0, 0);
		request.setAttribute("facultyList", facultyList);

		log.info("Faculty list loaded successfully");
		log.debug("FacultyCtl preload method completed");
	}

	/**
	 * Creates and returns the FacultyModel object.
	 *
	 * @return FacultyModel object
	 */
	@Override
	public FacultyModel getModel() {
		log.debug("FacultyCtl getModel method started");

		FacultyModel model = new FacultyModel();

		log.info("FacultyModel object created successfully");
		log.debug("FacultyCtl getModel method completed");

		return model;
	}

	/**
	 * Returns the view page for the faculty form.
	 *
	 * @return faculty view path
	 */
	@Override
	public String getView() {
		log.debug("FacultyCtl getView method started");
		log.info("Faculty view returned successfully");
		log.debug("FacultyCtl getView method completed");

		return ORSView.FACULTY_VIEW;
	}
}