package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Student Controller class handles student-related operations such as
 * preloading college data, validating student details, populating StudentBean,
 * and providing StudentModel and View.
 * 
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/ctl/StudentCtl")
public class StudentCtl extends BaseCtl<StudentBean, StudentModel> {

	/**
	 * Preloads the college list from the database and stores it in the request
	 * attribute for displaying the college dropdown.
	 * 
	 * @param request HttpServletRequest object
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel cmodel = new CollegeModel();
		List<CollegeBean> collegeList = cmodel.search(null, 0, 0);
		request.setAttribute("collegeList", collegeList);
	}

	/**
	 * Validates the student form input fields. Checks whether college, first name,
	 * last name, date of birth, mobile number, and email are provided.
	 * 
	 * @param request HttpServletRequest object containing form data
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", "college is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "first name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "last name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dateOfBirth"))) {
			request.setAttribute("dateOfBirth", "dateOfBirth is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "mobile no is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", "email is required");
			pass = false;
		}
		return pass;
	}

	/**
	 * Populates StudentBean with student details received from the HTTP request
	 * parameters.
	 * 
	 * @param request HttpServletRequest object containing student data
	 * @return populated StudentBean object
	 */
	@Override
	protected StudentBean populateBean(HttpServletRequest request) {
		StudentBean bean = new StudentBean();

		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dateOfBirth")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		return bean;
	}

	/**
	 * Creates and returns StudentModel object.
	 * 
	 * @return StudentModel object
	 */
	@Override
	public StudentModel getModel() {
		return new StudentModel();
	}

	/**
	 * Returns the Student View page.
	 * 
	 * @return Student View path
	 */
	@Override
	public String getView() {
		return ORSView.STUDENT_VIEW;
	}

}