package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.model.HospitalModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * HospitalCtl handles hospital-related operations such as validating
 * hospital details, populating the HospitalBean, and managing the hospital view.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/HospitalCtl")
public class HospitalCtl extends BaseCtl<HospitalBean, HospitalModel>{

	/**
	 * Validates the hospital details received from the HTTP request.
	 * Checks whether name, age, blood group, and disease are provided.
	 *
	 * @param request HttpServletRequest containing hospital details
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if(DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "name is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("age"))) {
			request.setAttribute("age", "age is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("bloodGroup"))) {
			request.setAttribute("bloodGroup", "blood group is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("disease"))) {
			request.setAttribute("disease", "disease is required");
			pass = false;
		}

		return pass;
	}

	/**
	 * Populates the HospitalBean object with hospital details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing hospital details
	 * @return populated HospitalBean object
	 */
	@Override
	protected HospitalBean populateBean(HttpServletRequest request) {
		HospitalBean bean = new HospitalBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAge(DataUtility.getInt(request.getParameter("age")));
		bean.setBloodGroup(DataUtility.getString(request.getParameter("bloodGroup")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));
		return bean;

	}

	/**
	 * Creates and returns the HospitalModel object.
	 *
	 * @return HospitalModel object
	 */
	@Override
	public HospitalModel getModel() {
		return new HospitalModel();
	}

	/**
	 * Returns the view page for the hospital form.
	 *
	 * @return hospital view path
	 */
	@Override
	public String getView() {
		return ORSView.HOSPITAL_VIEW;
	}

}