package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.RailwayBean;
import in.co.rays.proj4.model.RailwayModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller class for displaying the railway list.
 * It populates the RailwayBean with railway details
 * and provides the model and view for the railway list.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/RailwayListCtl")
public class RailwayListCtl extends BaseListCtl<RailwayBean, RailwayModel> {

	/**
	 * Populates the RailwayBean with railway details received
	 * from the HTTP request.
	 *
	 * @param request HttpServletRequest containing railway details
	 * @return populated RailwayBean object
	 */
	@Override
	protected RailwayBean populateBean(HttpServletRequest request) {
		RailwayBean bean = new RailwayBean();

		bean.setPassengerName(DataUtility.getString(request.getParameter("passengerName")));
		bean.setTrainNo(DataUtility.getInt(request.getParameter("trainNo")));
		bean.setSeatNo(DataUtility.getInt(request.getParameter("seatNo")));
		bean.setJourneyDate(DataUtility.getString(request.getParameter("journeyDate")));

		return bean;
	}

	/**
	 * Returns the RailwayModel object.
	 *
	 * @return RailwayModel instance
	 */
	@Override
	public RailwayModel getModel() {
		return new RailwayModel();
	}

	/**
	 * Returns the view name for the railway list page.
	 *
	 * @return railway list view name
	 */
	@Override
	public String getView() {
		return ORSView.RAILWAY_LIST_VIEW;
	}

}
