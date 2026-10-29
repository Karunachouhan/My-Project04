package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl<FacultyBean, FacultyModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", "College is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "First name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "Last name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", "Email is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile number is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "Address is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "Gender is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "Dob is required");
			pass = false;
		}

		return pass;
	}

	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {
		FacultyBean bean = new FacultyBean();

		bean.setCollegeId(DataUtility.getInt(request.getParameter("collegeId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));
      
		return bean;

	}
	
	@Override
	protected void preload(HttpServletRequest request) {
		FacultyModel fmodel = new FacultyModel();
		List<FacultyBean> facultyList = fmodel.search(null, 0, 0);
		request.setAttribute("facultyList", facultyList);
	}

	@Override
	public FacultyModel getModel() {
		return new FacultyModel();
	}

	@Override
	public String getView() {
		return ORSView.FACULTY_VIEW;
	}
}
