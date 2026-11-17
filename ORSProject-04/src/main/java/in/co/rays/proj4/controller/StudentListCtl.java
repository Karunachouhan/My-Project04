package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * StudentList Controller class handles listing and searching of student records.
 * It populates StudentBean with request parameters and provides StudentModel
 * and Student List View.
 * 
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/ctl/StudentListCtl")
public class StudentListCtl extends BaseListCtl<StudentBean, StudentModel> {

	/**
	 * Populates StudentBean with student details received
	 * from the HTTP request parameters.
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
	 * Returns the Student List View page.
	 * 
	 * @return Student List View path
	 */
	@Override
	public String getView() {
		return ORSView.STUDENT_LIST_VIEW;
	}

}