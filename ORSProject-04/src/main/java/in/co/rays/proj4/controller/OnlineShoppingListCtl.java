package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.model.OnlineShoppingModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * OnlineShoppingListCtl handles the listing of online shopping products.
 * It populates the OnlineShoppingBean with request parameters and provides
 * the model and view required for the online shopping list.
 *
 * @author karunaChouhan
 * @version 1.0
 */
@WebServlet("/ctl/OnlineShoppingListCtl")
public class OnlineShoppingListCtl extends BaseListCtl<OnlineShoppingBean, OnlineShoppingModel> {

	private static Logger log = Logger.getLogger(OnlineShoppingListCtl.class);

	/**
	 * Populates the OnlineShoppingBean with data received from the request.
	 *
	 * @param request HttpServletRequest object containing request parameters
	 * @return populated OnlineShoppingBean
	 */
	@Override
	protected OnlineShoppingBean populateBean(HttpServletRequest request) {

		log.debug("OnlineShoppingListCtl populateBean method started");

		OnlineShoppingBean bean = new OnlineShoppingBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setCategory(DataUtility.getString(request.getParameter("category")));
		bean.setStock(DataUtility.getInt(request.getParameter("stock")));

		log.debug("OnlineShoppingListCtl populateBean method completed");

		return bean;
	}

	/**
	 * Returns the OnlineShoppingModel object.
	 *
	 * @return OnlineShoppingModel instance
	 */
	@Override
	public OnlineShoppingModel getModel() {

		log.debug("OnlineShoppingListCtl getModel method called");

		return new OnlineShoppingModel();
	}

	/**
	 * Returns the view page for the online shopping list.
	 *
	 * @return online shopping list view path
	 */
	@Override
	public String getView() {

		log.debug("OnlineShoppingListCtl getView method called");

		return ORSView.ONLINE_SHOPPING_LIST_VIEW;
	}

}