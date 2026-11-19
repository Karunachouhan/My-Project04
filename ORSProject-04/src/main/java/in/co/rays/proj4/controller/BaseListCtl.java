package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * BaseListCtl is an abstract controller class used to handle
 * list operations such as searching, deleting, and pagination.
 *
 * It provides common functionality for all list controllers.
 *
 * @param <B> type of Bean extending BaseBean
 * @param <M> type of Model extending BaseModel
 * @author karunaChouhan
 */
public abstract class BaseListCtl<B extends BaseBean, M extends BaseModel> extends BaseCtl<B, M> {

	private static Logger log = Logger.getLogger(BaseListCtl.class);

	/**
	 * Handles HTTP GET requests.
	 * Retrieves records from the model and forwards them to the list view.
	 *
	 * @param request HttpServletRequest containing client request data
	 * @param response HttpServletResponse used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException if an input/output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("BaseListCtl doGet method started");

		M model = getModel();
		B base = populateBean(request);
		int pageNo = 1;
		int pageSize = 5;

		log.debug("Searching records for page number: " + pageNo);

		List<B> list = model.search(base, pageNo, pageSize);
		List<B> nextList = model.search(base, pageNo + 1, pageSize);

		log.info("Records fetched successfully");

		request.setAttribute("nextList", nextList);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		log.debug("Forwarding request to list view");

		ServletUtility.forward(getView(), request, response);

		log.debug("BaseListCtl doGet method completed");
	}

	/**
	 * Handles HTTP POST requests.
	 * Performs operations such as deleting records, navigating between pages,
	 * and searching records based on the submitted operation.
	 *
	 * @param request HttpServletRequest containing form data
	 * @param response HttpServletResponse used to send the response
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException if an input/output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("BaseListCtl doPost method started");

		String op = DataUtility.getString(request.getParameter("operation"));
		log.info("Operation received: " + op);

		M model = getModel();
		B bean = populateBean(request);
		int pageNo = 1;
		int pageSize = 5;

		if (OP_DELETE.equalsIgnoreCase(op)) {

			log.debug("Delete operation started");

			String[] ids = request.getParameterValues("ids");

			if (ids != null && ids.length > 0) {

				for (String id : ids) {
					log.info("Deleting record with ID: " + id);

					model.delete(DataUtility.getInt(id));

					ServletUtility.setSuccessMessage("record deleted successfully", request);
				}

				log.info("Record deletion completed successfully");

			} else {
				log.warn("No records selected for deletion");
				ServletUtility.setErrorMessage("select at least one record to delete", request);
			}
		}

		if (OP_NEXT.equalsIgnoreCase(op)) {

			log.debug("Next page operation started");

			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			pageNo++;

			log.info("Navigating to next page: " + pageNo);
		}

		if (OP_PREVIOUS.equalsIgnoreCase(op)) {

			log.debug("Previous page operation started");

			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			pageNo--;

			log.info("Navigating to previous page: " + pageNo);
		}

		if (OP_SEARCH.equalsIgnoreCase(op)) {

			log.debug("Search operation started");

			pageNo = 1;

			log.info("Searching records from first page");
		}

		log.debug("Fetching records for page number: " + pageNo);

		List<B> list = model.search(bean, pageNo, pageSize);
		List<B> nextList = model.search(bean, pageNo + 1, pageSize);

		log.info("Search operation completed successfully");

		request.setAttribute("nextList", nextList);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		log.debug("Forwarding request to list view");

		ServletUtility.forward(getView(), request, response);

		log.debug("BaseListCtl doPost method completed");
	}

}