package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.model.ATMModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * ATMCtl handles HTTP requests related to ATM information.
 * It validates request parameters, populates the ATMBean,
 * and provides the ATMModel and corresponding view.
 *
 * @author karunaChouhan
 */
@WebServlet("/ctl/ATMCtl")
public class ATMCtl extends BaseCtl<ATMBean, ATMModel> {

	/**
	 * Validates the ATM form request parameters.
	 *
	 * @param request HttpServletRequest containing form data
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("location"))) {
			request.setAttribute("location", "location is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("bank_name"))) {
			request.setAttribute("bank_name", "bank name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("cash_available"))) {
			request.setAttribute("cash_available", "cash is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", "status is required");
			pass = false;
		}
		return pass;
	}

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
	 * Returns the ATM view page.
	 *
	 * @return ATM view path
	 */
	@Override
	public String getView() {
		return ORSView.ATM_VIEW;
	}

}