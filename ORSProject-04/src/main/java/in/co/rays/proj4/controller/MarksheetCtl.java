package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * MarksheetCtl handles marksheet-related operations such as validating
 * marksheet details, populating the MarksheetBean, and loading marksheet data.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/MarksheetCtl")
public class MarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {

	/**
	 * Validates the marksheet details received from the HTTP request.
	 * Checks whether roll number, student ID, name, and subject marks are provided.
	 *
	 * @param request HttpServletRequest containing marksheet details
	 * @return true if all required fields are provided, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", "Roll No is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId", "Student id is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "Name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics", "physics marks is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", "chemistry marks is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths", "Maths marks is required");
			pass = false;
		}
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
		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

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
		MarksheetModel model = new MarksheetModel();
		List<MarksheetBean> marksheetList =  model.search(null, 0, 0);
		request.setAttribute("marksheetList", marksheetList);
	}

	/**
	 * Creates and returns the MarksheetModel object.
	 *
	 * @return MarksheetModel object
	 */
	@Override
	public MarksheetModel getModel() {
		return new MarksheetModel();
	}

	/**
	 * Returns the view page for the marksheet form.
	 *
	 * @return marksheet view path
	 */
	@Override
	public String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

}