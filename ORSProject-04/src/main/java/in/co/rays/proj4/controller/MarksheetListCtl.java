package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Controller class for displaying the marksheet list.
 * It populates the MarksheetBean with request parameters
 * and provides the model and view for the marksheet list.
 * 
 * @author karunaChouhan
 */
@WebServlet("/ctl/MarksheetListCtl")
public class MarksheetListCtl extends BaseListCtl<MarksheetBean, MarksheetModel> {

	private static Logger log = Logger.getLogger(MarksheetListCtl.class);

	/**
	 * Populates the MarksheetBean with data received from the HTTP request.
	 * 
	 * @param request HttpServletRequest containing marksheet details
	 * @return populated MarksheetBean object
	 */
	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {

		log.debug("MarksheetListCtl populateBean method started");

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(DataUtility.getString(request.getParameter("rollId")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

		log.info("MarksheetBean populated successfully");
		log.debug("MarksheetListCtl populateBean method completed");

		return bean;
	}

	/**
	 * Returns the MarksheetModel object.
	 * 
	 * @return MarksheetModel instance
	 */
	@Override
	public MarksheetModel getModel() {

		log.debug("MarksheetListCtl getModel method called");

		return new MarksheetModel();
	}

	/**
	 * Returns the view name for the marksheet list page.
	 * 
	 * @return marksheet list view name
	 */
	@Override
	public String getView() {

		log.debug("MarksheetListCtl getView method called");

		return ORSView.MARKSHEET_LIST_VIEW;
	}

}