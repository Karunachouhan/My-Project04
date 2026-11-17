package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Subject Controller class handles subject-related operations such as
 * preloading course data, validating subject details, populating
 * SubjectBean, and providing SubjectModel and View.
 * 
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/ctl/SubjectCtl")
public class SubjectCtl extends BaseCtl<SubjectBean, SubjectModel> {

	/**
	 * Preloads the course list from the database and stores it
	 * in the request attribute for displaying the course dropdown.
	 * 
	 * @param request HttpServletRequest object
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		CourseModel cmodel = new CourseModel();
		List<CourseBean> courseList = cmodel.search(null, 0, 0);
		request.setAttribute("courseList", courseList);
	}

	/**
	 * Validates the subject form input fields.
	 * Checks whether name, description, and course ID are provided.
	 * 
	 * @param request HttpServletRequest object containing form data
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "description is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", "courseId is required");
			pass = false;
		}
		return pass;
	}

	/**
	 * Populates SubjectBean with subject details received
	 * from the HTTP request parameters.
	 * 
	 * @param request HttpServletRequest object containing subject data
	 * @return populated SubjectBean object
	 */
	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {
		SubjectBean bean = new SubjectBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		return bean;
	}

	/**
	 * Creates and returns SubjectModel object.
	 * 
	 * @return SubjectModel object
	 */
	@Override
	public SubjectModel getModel() {
		return new SubjectModel();
	}

	/**
	 * Returns the Subject View page.
	 * 
	 * @return Subject View path
	 */
	@Override
	public String getView() {
		return ORSView.SUBJECT_VIEW;
	}

}