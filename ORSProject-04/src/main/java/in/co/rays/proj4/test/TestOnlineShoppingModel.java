package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.model.OnlineShoppingModel;

public class TestOnlineShoppingModel {
  
	public static OnlineShoppingModel model = new OnlineShoppingModel();
	
	public static void main(String[] args) {
	testAdd();
	
	}

	private static void testAdd() {
		OnlineShoppingBean bean = new OnlineShoppingBean();
        
		bean.setId(1);
		bean.setName("Smart Watch");
		bean.setPrice(1200.00);
		bean.setCategory("Accessories");
		bean.setStock(12);
		bean.setCreatedBy("Karuna");
		bean.setModifiedBy("Karuna");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
		
	}
	
}
