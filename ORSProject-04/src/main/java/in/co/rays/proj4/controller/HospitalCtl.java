package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(HospitalCtl.class);

	/**
	 * Validates the hospital details received from the HTTP request.
	 * Checks whether name, age, blood group, and disease are provided.
	 *
	 * @param request HttpServletRequest containing hospital details
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("HospitalCtl validate method started");

		boolean pass = true;

		if(DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "name is required");
			log.warn("Hospital name is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("age"))) {
			request.setAttribute("age", "age is required");
			log.warn("Hospital age is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("bloodGroup"))) {
			request.setAttribute("bloodGroup", "blood group is required");
			log.warn("Blood group is required");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("disease"))) {
			request.setAttribute("disease", "disease is required");
			log.warn("Disease is required");
			pass = false;
		}

		if (pass) {
			log.info("Hospital form validation successful");
		} else {
			log.error("Hospital form validation failed");
		}

		log.debug("HospitalCtl validate method completed");
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
		log.debug("HospitalCtl populateBean method started");

		HospitalBean bean = new HospitalBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAge(DataUtility.getInt(request.getParameter("age")));
		bean.setBloodGroup(DataUtility.getString(request.getParameter("bloodGroup")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));

		populateDTO(bean, request);

		log.info("HospitalBean populated successfully");
		log.debug("HospitalCtl populateBean method completed");

		return bean;

	}

	/**
	 * Creates and returns the HospitalModel object.
	 *
	 * @return HospitalModel object
	 */
	@Override
	public HospitalModel getModel() {
		log.debug("HospitalCtl getModel method started");

		HospitalModel model = new HospitalModel();

		log.info("HospitalModel object created successfully");
		log.debug("HospitalCtl getModel method completed");

		return model;
	}

	/**
	 * Returns the view page for the hospital form.
	 *
	 * @return hospital view path
	 */
	@Override
	public String getView() {
		log.debug("HospitalCtl getView method started");
		log.info("Hospital view returned successfully");
		log.debug("HospitalCtl getView method completed");

		return ORSView.HOSPITAL_VIEW;
	}

}