package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(CourseListCtl.class);

	/**
	 * Populates the CourseBean object with course details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing course details
	 * @return populated CourseBean object
	 */
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {
		log.debug("CourseListCtl populateBean method started");

		CourseBean bean = new CourseBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		log.info("CourseBean populated successfully");
		log.debug("CourseListCtl populateBean method completed");

		return bean;
	}

	/**
	 * Creates and returns the CourseModel object.
	 *
	 * @return CourseModel object
	 */
	@Override
	public CourseModel getModel() {
		log.debug("CourseListCtl getModel method started");

		CourseModel model = new CourseModel();

		log.info("CourseModel object created successfully");
		log.debug("CourseListCtl getModel method completed");

		return model;
	}

	/**
	 * Returns the view page for displaying the course list.
	 *
	 * @return course list view path
	 */
	@Override
	public String getView() {
		log.debug("CourseListCtl getView method started");
		log.info("Course list view returned successfully");
		log.debug("CourseListCtl getView method completed");

		return ORSView.COURSE_LIST_VIEW;
	}

}