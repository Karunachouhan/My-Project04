package in.co.rays.proj4.controller;

import java.util.List;

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

	/**
	 * Validates the faculty details received from the HTTP request.
	 * Checks whether all required fields are provided.
	 *
	 * @param request HttpServletRequest containing faculty details
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", "College is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "First name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "Last name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", "Email is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile number is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "Address is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "Gender is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "Dob is required");
			pass = false;
		}

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
		FacultyBean bean = new FacultyBean();

		bean.setCollegeId(DataUtility.getInt(request.getParameter("collegeId")));
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
	 * Loads the faculty list from the database and stores it
	 * as a request attribute for use in the view.
	 *
	 * @param request HttpServletRequest used to store the faculty list
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		FacultyModel fmodel = new FacultyModel();
		List<FacultyBean> facultyList = fmodel.search(null, 0, 0);
		request.setAttribute("facultyList", facultyList);
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
	 * Returns the view page for the faculty form.
	 *
	 * @return faculty view path
	 */
	@Override
	public String getView() {
		return ORSView.FACULTY_VIEW;
	}
}