package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.model.ATMModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
@WebServlet("/ctl/ATMCtl")
public class ATMCtl extends BaseCtl<ATMBean, ATMModel> {

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

	@Override
	protected ATMBean populateBean(HttpServletRequest request) {
		ATMBean bean = new ATMBean();

		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		bean.setBankName(DataUtility.getString(request.getParameter("bank_name")));
		bean.setCashAvailable(DataUtility.getDouble(request.getParameter("cash_available")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		return bean;
	}

	@Override
	public ATMModel getModel() {
		return new ATMModel();
	}

	@Override
	public String getView() {
		return ORSView.ATM_VIEW;
	}

}
