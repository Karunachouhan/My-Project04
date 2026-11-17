package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CollegeCtl handles HTTP requests related to college operations.
 * It validates college form data, populates the CollegeBean,
 * and provides the CollegeModel and college view.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/CollegeCtl")
public class CollegeCtl extends BaseCtl<CollegeBean, CollegeModel> {

	/**
	 * Validates the college form fields.
	 *
	 * @param request HttpServletRequest containing college form data
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "college name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "address is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", "state is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", "city is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "phone no is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates a CollegeBean with college details received
	 * from the HTTP request.
	 *
	 * @param request HttpServletRequest containing college form data
	 * @return populated CollegeBean object
	 */
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {
		CollegeBean bean = new CollegeBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

		return bean;
	}

	/**
	 * Returns a new instance of CollegeModel.
	 *
	 * @return CollegeModel object
	 */
	@Override
	public CollegeModel getModel() {
		return new CollegeModel();
	}

	/**
	 * Returns the college view page.
	 *
	 * @return college view path
	 */
	@Override
	public String getView() {
		return ORSView.COLLEGE_VIEW;
	}
}