package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * SubjectList Controller class handles listing and searching of subject records.
 * It populates SubjectBean with request parameters and provides SubjectModel
 * and Subject List View.
 * 
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
@WebServlet("/ctl/SubjectListCtl")
public class SubjectListCtl extends BaseListCtl<SubjectBean, SubjectModel> {

	/**
	 * Populates SubjectBean with subject details received
	 * from the HTTP request parameters.
	 * 
	 * @param request HttpServletRequest object containing subject data
	 * @return populated SubjectBean object
	 */
	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {
		SubjectBean bean = new SubjectBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		return bean;
	}

	/**
	 * Creates and returns SubjectModel object.
	 * 
	 * @return SubjectModel object
	 */
	@Override
	public SubjectModel getModel() {
		return new SubjectModel();
	}

	/**
	 * Returns the Subject List View page.
	 * 
	 * @return Subject List View path
	 */
	@Override
	public String getView() {
		return ORSView.SUBJECT_LIST_VIEW;
	}

}