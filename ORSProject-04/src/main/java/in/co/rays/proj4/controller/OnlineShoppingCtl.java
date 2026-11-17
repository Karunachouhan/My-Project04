package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.model.OnlineShoppingModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
@WebServlet("/ctl/OnlineShoppingCtl")
public class OnlineShoppingCtl extends BaseCtl<OnlineShoppingBean, OnlineShoppingModel> {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "product name is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", "price is required");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("category"))) {
			 request.setAttribute("category", "category is required");
			 pass = false;
		}
		if (DataValidator.isNull(request.getParameter("stock"))) {
			request.setAttribute("stock", "stock is required");
		}
		return pass;
	}
	
	@Override
	protected OnlineShoppingBean populateBean(HttpServletRequest request) {
		OnlineShoppingBean bean = new OnlineShoppingBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
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
		return ORSView.ONLINE_SHOPPING_VIEW;
	}
        
}
