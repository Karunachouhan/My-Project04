package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(StudentCtl.class);

	/**
	 * Preloads the college list from the database and stores it in the request
	 * attribute for displaying the college dropdown.
	 * 
	 * @param request HttpServletRequest object
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		log.debug("StudentCtl preload method started");

		CollegeModel cmodel = new CollegeModel();
		List<CollegeBean> collegeList = cmodel.search(null, 0, 0);
		request.setAttribute("collegeList", collegeList);

		log.debug("StudentCtl preload method completed");
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

		log.debug("StudentCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			log.warn("College ID is missing");
			request.setAttribute("collegeId", "college is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			log.warn("Student first name is missing");
			request.setAttribute("firstName", "first name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			log.warn("Student last name is missing");
			request.setAttribute("lastName", "last name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dateOfBirth"))) {
			log.warn("Student date of birth is missing");
			request.setAttribute("dateOfBirth", "dateOfBirth is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			log.warn("Student mobile number is missing");
			request.setAttribute("mobileNo", "mobile no is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			log.warn("Student email is missing");
			request.setAttribute("email", "email is required");
			pass = false;
		}

		log.debug("StudentCtl validate method completed with result: " + pass);

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

		log.debug("StudentCtl populateBean method started");

		StudentBean bean = new StudentBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dateOfBirth")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		populateDTO(bean, request);

		log.debug("StudentCtl populateBean method completed");

		return bean;
	}

	/**
	 * Creates and returns StudentModel object.
	 * 
	 * @return StudentModel object
	 */
	@Override
	public StudentModel getModel() {

		log.debug("StudentCtl getModel method called");

		return new StudentModel();
	}

	/**
	 * Returns the Student View page.
	 * 
	 * @return Student View path
	 */
	@Override
	public String getView() {

		log.debug("StudentCtl getView method called");

		return ORSView.STUDENT_VIEW;
	}

}