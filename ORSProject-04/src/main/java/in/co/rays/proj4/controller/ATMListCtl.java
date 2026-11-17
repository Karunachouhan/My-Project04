package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.model.ATMModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * ATMListCtl handles requests related to displaying and searching
 * ATM records.
 * It populates the ATMBean and provides the ATMModel and list view.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/ATMListCtl")
public class ATMListCtl extends BaseListCtl<ATMBean, ATMModel> {

	/**
	 * Populates an ATMBean with data received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing ATM form data
	 * @return populated ATMBean object
	 */
	@Override
	protected ATMBean populateBean(HttpServletRequest request) {
		ATMBean bean = new ATMBean();

		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		bean.setBankName(DataUtility.getString(request.getParameter("bank_name")));
		bean.setCashAvailable(DataUtility.getDouble(request.getParameter("cash_available")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		return bean;
	}

	/**
	 * Returns a new instance of ATMModel.
	 *
	 * @return ATMModel object
	 */
	@Override
	public ATMModel getModel() {
		return new ATMModel();
	}

	/**
	 * Returns the ATM list view page.
	 *
	 * @return ATM list view path
	 */
	@Override
	public String getView() {
		return ORSView.ATM_LIST_VIEW;
	}

}