package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.model.HospitalModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * HospitalListCtl handles listing and searching operations for hospital records.
 * It extends BaseListCtl to use centralized list functionality.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/HospitalListCtl")
public class HospitalListCtl extends BaseListCtl<HospitalBean, HospitalModel> {

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
	 * Returns the view page for displaying the hospital list.
	 *
	 * @return hospital list view path
	 */
	@Override
	public String getView() {
		return ORSView.HOSPITAL_LIST_VIEW;
	}

}