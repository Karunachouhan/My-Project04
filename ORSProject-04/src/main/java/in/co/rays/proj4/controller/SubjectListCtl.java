package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

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

	private static Logger log = Logger.getLogger(SubjectListCtl.class);

	/**
	 * Populates SubjectBean with subject details received
	 * from the HTTP request parameters.
	 * 
	 * @param request HttpServletRequest object containing subject data
	 * @return populated SubjectBean object
	 */
	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {

		log.debug("SubjectListCtl populateBean method started");

		SubjectBean bean = new SubjectBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		log.debug("SubjectListCtl populateBean method completed");

		return bean;
	}

	/**
	 * Creates and returns SubjectModel object.
	 * 
	 * @return SubjectModel object
	 */
	@Override
	public SubjectModel getModel() {

		log.debug("SubjectListCtl getModel method called");

		return new SubjectModel();
	}

	/**
	 * Returns the Subject List View page.
	 * 
	 * @return Subject List View path
	 */
	@Override
	public String getView() {

		log.debug("SubjectListCtl getView method called");

		return ORSView.SUBJECT_LIST_VIEW;
	}

}