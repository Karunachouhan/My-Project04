package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(HospitalListCtl.class);

	/**
	 * Populates the HospitalBean object with hospital details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing hospital details
	 * @return populated HospitalBean object
	 */
	@Override
	protected HospitalBean populateBean(HttpServletRequest request) {
		log.debug("HospitalListCtl populateBean method started");

		HospitalBean bean = new HospitalBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAge(DataUtility.getInt(request.getParameter("age")));
		bean.setBloodGroup(DataUtility.getString(request.getParameter("bloodGroup")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));

		log.info("HospitalBean populated successfully");
		log.debug("HospitalListCtl populateBean method completed");

		return bean;
	}

	/**
	 * Creates and returns the HospitalModel object.
	 *
	 * @return HospitalModel object
	 */
	@Override
	public HospitalModel getModel() {
		log.debug("HospitalListCtl getModel method started");

		HospitalModel model = new HospitalModel();

		log.info("HospitalModel object created successfully");
		log.debug("HospitalListCtl getModel method completed");

		return model;
	}

	/**
	 * Returns the view page for displaying the hospital list.
	 *
	 * @return hospital list view path
	 */
	@Override
	public String getView() {
		log.debug("HospitalListCtl getView method started");
		log.info("Hospital list view returned successfully");
		log.debug("HospitalListCtl getView method completed");

		return ORSView.HOSPITAL_LIST_VIEW;
	}

}