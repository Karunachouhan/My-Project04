package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.model.OnlineShoppingModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * OnlineShoppingCtl handles online shopping product operations.
 * It validates product details, populates the OnlineShoppingBean,
 * and provides the model and view for the online shopping form.
 *
 * @author karunaChouhan
 * @version 1.0
 */
@WebServlet("/ctl/OnlineShoppingCtl")
public class OnlineShoppingCtl extends BaseCtl<OnlineShoppingBean, OnlineShoppingModel> {

	private static Logger log = Logger.getLogger(OnlineShoppingCtl.class);

	/**
	 * Validates the product details received from the HTTP request.
	 * Checks whether product name, price, category, and stock are provided.
	 *
	 * @param request HttpServletRequest containing product details
	 * @return true if validation passes, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("OnlineShoppingCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "product name is required");
			log.warn("Product name is missing");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", "price is required");
			log.warn("Product price is missing");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("category"))) {
			 request.setAttribute("category", "category is required");
			 log.warn("Product category is missing");
			 pass = false;
		}
		if (DataValidator.isNull(request.getParameter("stock"))) {
			request.setAttribute("stock", "stock is required");
			log.warn("Product stock is missing");
		}

		if (pass) {
			log.info("Online shopping validation successful");
		} else {
			log.error("Online shopping validation failed");
		}

		log.debug("OnlineShoppingCtl validate method completed");

		return pass;
	}

	/**
	 * Populates the OnlineShoppingBean with product details
	 * received from the HTTP request.
	 *
	 * @param request HttpServletRequest containing product details
	 * @return populated OnlineShoppingBean object
	 */
	@Override
	protected OnlineShoppingBean populateBean(HttpServletRequest request) {

		log.debug("OnlineShoppingCtl populateBean method started");

		OnlineShoppingBean bean = new OnlineShoppingBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setCategory(DataUtility.getString(request.getParameter("category")));
		bean.setStock(DataUtility.getInt(request.getParameter("stock")));

		populateDTO(bean, request);

		log.info("OnlineShoppingBean populated successfully");
		log.debug("OnlineShoppingCtl populateBean method completed");

		return bean;
	}

	/**
	 * Creates and returns the OnlineShoppingModel object.
	 *
	 * @return OnlineShoppingModel instance
	 */
	@Override
	public OnlineShoppingModel getModel() {

		log.debug("OnlineShoppingCtl getModel method called");

		return new OnlineShoppingModel();
	}

	/**
	 * Returns the view page for the online shopping form.
	 *
	 * @return online shopping view path
	 */
	@Override
	public String getView() {

		log.debug("OnlineShoppingCtl getView method called");

		return ORSView.ONLINE_SHOPPING_VIEW;
	}

}