package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RailwayBean;
import in.co.rays.proj4.model.RailwayModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller class for handling railway information.
 * It validates railway details, populates the RailwayBean,
 * and provides the model and view for the railway form.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/RailwayCtl")
public class RailwayCtl extends BaseCtl<RailwayBean, RailwayModel> {

	private static Logger log = Logger.getLogger(RailwayCtl.class);

	/**
	 * Validates the railway details submitted through the request.
	 *
	 * @param request HttpServletRequest containing railway details
	 * @return true if all required fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("RailwayCtl validate method started");

		Boolean pass = true;

		if (DataValidator.isNull(request.getParameter("passengerName"))) {
			log.warn("Passenger name is missing");
			request.setAttribute("passengerName", "Passenger Name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("trainNo"))) {
			log.warn("Train number is missing");
			request.setAttribute("trainNo", "Train number is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("seatNo"))) {
			log.warn("Seat number is missing");
			request.setAttribute("seatNo", "Seat number is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("journeyDate"))) {
			log.warn("Journey date is missing");
			request.setAttribute("journeyDate", "Journey date is required");
			pass = false;
		}

		log.debug("RailwayCtl validate method completed with result: " + pass);

		return pass;
	}

	/**
	 * Populates the RailwayBean with railway details from the request.
	 *
	 * @param request HttpServletRequest containing railway information
	 * @return populated RailwayBean object
	 */
	@Override
	protected RailwayBean populateBean(HttpServletRequest request) {

		log.debug("RailwayCtl populateBean method started");

		RailwayBean bean = new RailwayBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setPassengerName(DataUtility.getString(request.getParameter("passengerName")));
		bean.setTrainNo(DataUtility.getInt(request.getParameter("trainNo")));
		bean.setSeatNo(DataUtility.getInt(request.getParameter("seatNo")));
		bean.setJourneyDate(DataUtility.getString(request.getParameter("journeyDate")));

		populateDTO(bean, request);

		log.debug("RailwayCtl populateBean method completed");

		return bean;
	}

	/**
	 * Returns the RailwayModel object.
	 *
	 * @return RailwayModel instance
	 */
	@Override
	public RailwayModel getModel() {

		log.debug("RailwayCtl getModel method called");

		return new RailwayModel();
	}

	/**
	 * Returns the view name for the railway form.
	 *
	 * @return railway view name
	 */
	@Override
	public String getView() {

		log.debug("RailwayCtl getView method called");

		return ORSView.RAILWAY_VIEW;
	}

}