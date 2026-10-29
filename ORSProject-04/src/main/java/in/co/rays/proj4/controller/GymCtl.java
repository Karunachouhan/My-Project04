package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.GymBean;
import in.co.rays.proj4.model.GymModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/GymCtl")
public class GymCtl extends BaseCtl<GymBean, GymModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNotNull(request.getParameter("name"))) {
			request.setAttribute("name", "name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("membershipType"))) {
			request.setAttribute("membershipType", "membership type is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", "joining date is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("trainerName"))) {
			request.setAttribute("trainerName", "trainer name is required");
			pass = false;
		}
		return pass;
	}

	@Override
	protected GymBean populateBean(HttpServletRequest request) {
		GymBean bean = new GymBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setMembershipType(DataUtility.getString(request.getParameter("membershipType")));
		bean.setJoiningDate(DataUtility.getString(request.getParameter("joiningDate")));
		bean.setTrainerName(DataUtility.getString(request.getParameter("trainerName")));
		return bean;

	}

	@Override
	public GymModel getModel() {
		return new GymModel();
	}

	@Override
	public String getView() {
		return ORSView.GYM_VIEW;
	}

}
