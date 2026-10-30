package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RailwayBean;
import in.co.rays.proj4.model.RailwayModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
@WebServlet("/ctl/RailwayCtl")
public class RailwayCtl extends BaseCtl<RailwayBean, RailwayModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {
		Boolean pass = true;

		if (DataValidator.isNull(request.getParameter("passenger_name"))) {
			request.setAttribute("passengerName", "Passenger Name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("train_no"))) {
			request.setAttribute("trainNo", "Train number is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("seat_no"))) {
			request.setAttribute("seatNo", "Seat number is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("journey_date"))) {
			request.setAttribute("journeyDate", "Journey date is required");
			pass = false;
		}
		return pass;
	}

	@Override
	protected RailwayBean populateBean(HttpServletRequest request) {
		RailwayBean bean = new RailwayBean();

		bean.setPassengerName(DataUtility.getString(request.getParameter("passengerName")));
		bean.setTrainNo(DataUtility.getInt(request.getParameter("trainNo")));
		bean.setSeatNo(DataUtility.getInt(request.getParameter("seatNo")));
		bean.setJourneyDate(DataUtility.getString(request.getParameter("journeyDate")));

		return bean;
	}

	@Override
	public RailwayModel getModel() {
		return new RailwayModel();
	}

	@Override
	public String getView() {
		return ORSView.RAILWAY_VIEW;
	}

}
