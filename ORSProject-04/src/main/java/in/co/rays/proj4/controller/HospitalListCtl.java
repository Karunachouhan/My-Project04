package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.model.HospitalModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/HospitalListCtl")
public class HospitalListCtl extends BaseListCtl<HospitalBean, HospitalModel> {

	@Override
	protected HospitalBean populateBean(HttpServletRequest request) {
		HospitalBean bean = new HospitalBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAge(DataUtility.getInt(request.getParameter("age")));
		bean.setBloodGroup(DataUtility.getString(request.getParameter("bloodGroup")));
		bean.setDisease(DataUtility.getString(request.getParameter("disease")));
		return bean;
	}

	@Override
	public HospitalModel getModel() {
		return new HospitalModel();
	}

	@Override
	public String getView() {
		return ORSView.HOSPITAL_LIST_VIEW;
	}

}
