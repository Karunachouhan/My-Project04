package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * MarksheetCtl handles marksheet-related operations such as validating
 * marksheet details, populating the MarksheetBean, and loading marksheet data.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/MarksheetCtl")
public class MarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {

	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	/**
	 * Validates the marksheet details received from the HTTP request.
	 * Checks whether roll number, student ID, name, and subject marks are provided.
	 *
	 * @param request HttpServletRequest containing marksheet details
	 * @return true if all required fields are provided, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MarksheetCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", "Roll No is required");
			log.warn("Roll number is missing");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId", "Student id is required");
			log.warn("Student ID is missing");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics", "physics marks is required");
			log.warn("Physics marks are missing");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", "chemistry marks is required");
			log.warn("Chemistry marks are missing");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths", "Maths marks is required");
			log.warn("Maths marks are missing");
			pass = false;
		}

		if (pass) {
			log.info("Marksheet validation successful");
		} else {
			log.warn("Marksheet validation failed");
		}

		log.debug("MarksheetCtl validate method completed");

		return pass;
	}

	/**
	 * Populates the MarksheetBean object with marksheet details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing marksheet details
	 * @return populated MarksheetBean object
	 */
	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {

		log.debug("MarksheetCtl populateBean method started");

		MarksheetBean bean = new MarksheetBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

		populateDTO(bean, request);

		log.info("MarksheetBean populated successfully");
		log.debug("MarksheetCtl populateBean method completed");

		return bean;

	}

	/**
	 * Loads all marksheet records and stores them in the request
	 * for use in the marksheet view.
	 *
	 * @param request HttpServletRequest used to store the marksheet list
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		log.debug("MarksheetCtl preload method started");

		StudentModel model = new StudentModel();
		List<StudentBean> studentList =  model.search(null, 0, 0);
		request.setAttribute("studentList", studentList);

		log.info("Student list loaded successfully");
		log.debug("MarksheetCtl preload method completed");
	}

	/**
	 * Creates and returns the MarksheetModel object.
	 *
	 * @return MarksheetModel object
	 */
	@Override
	public MarksheetModel getModel() {

		log.debug("MarksheetCtl getModel method called");

		return new MarksheetModel();
	}

	/**
	 * Returns the view page for the marksheet form.
	 *
	 * @return marksheet view path
	 */
	@Override
	public String getView() {

		log.debug("MarksheetCtl getView method called");

		return ORSView.MARKSHEET_VIEW;
	}

}