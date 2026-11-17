package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CourseListCtl handles listing and searching operations for course records.
 * It extends BaseListCtl to use centralized list functionality.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/CourseListCtl")
public class CourseListCtl extends BaseListCtl<CourseBean, CourseModel> {

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
	 * Returns the view page for displaying the course list.
	 *
	 * @return course list view path
	 */
	@Override
	public String getView() {
		return ORSView.COURSE_LIST_VIEW;
	}

}