package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RailwayBean;
import in.co.rays.proj4.model.RailwayModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
@WebServlet("/ctl/RailwayListCtl")
public class RailwayListCtl extends BaseListCtl<RailwayBean, RailwayModel> {

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
		return ORSView.RAILWAY_LIST_VIEW;
	}

}
