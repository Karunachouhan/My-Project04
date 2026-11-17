package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CourseCtl is a controller that handles course-related operations
 * such as validation, populating course data, and managing the course view.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/CourseCtl")
public class CourseCtl extends BaseCtl<CourseBean, CourseModel> {

	/**
	 * Validates the course details received from the request.
	 * Checks whether name, description, and duration are provided.
	 *
	 * @param request HttpServletRequest containing course details
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		Boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "Name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "Description is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", "Duration is required");
			pass = false;
		}
		return pass;
	}

	/**
	 * Populates the CourseBean object with course details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing course details
	 * @return populated CourseBean object
	 */
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {
		CourseBean bean = new CourseBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		return bean;
	}

	/**
	 * Creates and returns the CourseModel object.
	 *
	 * @return CourseModel object
	 */
	@Override
	public CourseModel getModel() {
		return new CourseModel();
	}

	/**
	 * Returns the view page for the course form.
	 *
	 * @return course view path
	 */
	@Override
	public String getView() {
		return ORSView.COURSE_VIEW;
	}
}