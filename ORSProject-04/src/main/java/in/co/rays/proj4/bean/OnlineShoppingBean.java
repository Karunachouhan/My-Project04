package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import org.apache.log4j.Logger;

public class OnlineShoppingBean extends BaseBean {

	private static Logger log = Logger.getLogger(OnlineShoppingBean.class);

	private String name;

	private double price;

	private String category;

	private int stock;

	public String getName() {
		log.debug("OnlineShoppingBean.getName()");
		return name;
	}

	public void setName(String name) {
		log.debug("OnlineShoppingBean.setName()");
		this.name = name;
		log.info("Product name set successfully");
	}

	public double getPrice() {
		log.debug("OnlineShoppingBean.getPrice()");
		return price;
	}

	public void setPrice(double price) {
		log.debug("OnlineShoppingBean.setPrice()");
		this.price = price;
		log.info("Product price set successfully");
	}

	public String getCategory() {
		log.debug("OnlineShoppingBean.getCategory()");
		return category;
	}

	public void setCategory(String category) {
		log.debug("OnlineShoppingBean.setCategory()");
		this.category = category;
		log.info("Product category set successfully");
	}

	public int getStock() {
		log.debug("OnlineShoppingBean.getStock()");
		return stock;
	}

	public void setStock(int stock) {
		log.debug("OnlineShoppingBean.setStock()");
		this.stock = stock;
		log.info("Product stock set successfully");
	}

	@Override
	public String getValue() {
		log.debug("OnlineShoppingBean.getValue()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResultSet(ResultSet rs) {
		log.debug("OnlineShoppingBean.setResultSet() started");

		try {
			setId(rs.getLong("id"));
			setName(rs.getString("name"));
			setPrice(rs.getDouble("price"));
			setCategory(rs.getString("category"));
			setStock(rs.getInt("stock"));

			log.info("OnlineShoppingBean populated successfully");

		} catch (Exception e) {
			log.error("Error while populating OnlineShoppingBean", e);
			e.printStackTrace();
		}

		super.setResultSet(rs);
		log.debug("OnlineShoppingBean.setResultSet() completed");
	}

}