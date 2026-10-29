package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.model.ATMModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/ATMListCtl")
public class ATMListCtl extends BaseListCtl<ATMBean, ATMModel> {

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
		return ORSView.ATM_LIST_VIEW;
	}

}
