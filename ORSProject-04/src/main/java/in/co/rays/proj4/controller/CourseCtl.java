package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(CourseCtl.class);

	/**
	 * Validates the course details received from the request.
	 * Checks whether name, description, and duration are provided.
	 *
	 * @param request HttpServletRequest containing course details
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("CourseCtl validate method started");

		Boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "Name is required");
			log.warn("Course name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "Description is required");
			log.warn("Course description is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", "Duration is required");
			log.warn("Course duration is required");
			pass = false;
		}

		if (pass) {
			log.info("Course form validation successful");
		} else {
			log.error("Course form validation failed");
		}

		log.debug("CourseCtl validate method completed");
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
		log.debug("CourseCtl populateBean method started");

		CourseBean bean = new CourseBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		populateDTO(bean, request);

		log.info("CourseBean populated successfully");
		log.debug("CourseCtl populateBean method completed");

		return bean;
	}

	/**
	 * Creates and returns the CourseModel object.
	 *
	 * @return CourseModel object
	 */
	@Override
	public CourseModel getModel() {
		log.debug("CourseCtl getModel method started");

		CourseModel model = new CourseModel();

		log.info("CourseModel object created successfully");
		log.debug("CourseCtl getModel method completed");

		return model;
	}

	/**
	 * Returns the view page for the course form.
	 *
	 * @return course view path
	 */
	@Override
	public String getView() {
		log.debug("CourseCtl getView method started");
		log.info("Course view returned successfully");
		log.debug("CourseCtl getView method completed");

		return ORSView.COURSE_VIEW;
	}
}