package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.model.OnlineShoppingModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
@WebServlet("/ctl/OnlineShoppingListCtl")
public class OnlineShoppingListCtl extends BaseListCtl<OnlineShoppingBean, OnlineShoppingModel> {
	
	@Override
	protected OnlineShoppingBean populateBean(HttpServletRequest request) {
		OnlineShoppingBean bean = new OnlineShoppingBean();
		
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));
		bean.setCategory(DataUtility.getString(request.getParameter("category")));
		bean.setStock(DataUtility.getInt(request.getParameter("stock")));
		
		return bean;
	}

	@Override
	public OnlineShoppingModel getModel() {
		return new OnlineShoppingModel();
	}

	@Override
	public String getView() {
		return ORSView.ONLINE_SHOPPING_LIST_VIEW;
	}

}
