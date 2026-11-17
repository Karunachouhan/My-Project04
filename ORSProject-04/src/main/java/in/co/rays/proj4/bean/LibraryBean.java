package in.co.rays.proj4.bean;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

/**
 * LibraryBean is a Bean class that stores library-related information.
 *
 * It extends BaseBean to inherit common properties such as id, createdBy,
 * modifiedBy, createdDatetime, and modifiedDatetime.
 *
 * It contains book details such as title, author, price, and availability.
 *
 * @author karunaChouhan
 */
public class LibraryBean extends BaseBean {

	private static Logger log = Logger.getLogger(LibraryBean.class);

	private String title;

	private String author;

	private double price;

	private boolean availability;

	/**
	 * Returns the title of the book.
	 *
	 * @return book title
	 */
	public String getTitle() {
		log.debug("LibraryBean.getTitle() started");
		return title;
	}

	/**
	 * Sets the title of the book.
	 *
	 * @param title book title
	 */
	public void setTitle(String title) {
		log.debug("LibraryBean.setTitle() started");
		this.title = title;
		log.info("Book title set successfully");
	}

	/**
	 * Returns the author of the book.
	 *
	 * @return book author
	 */
	public String getAuthor() {
		log.debug("LibraryBean.getAuthor() started");
		return author;
	}

	/**
	 * Sets the author of the book.
	 *
	 * @param author book author
	 */
	public void setAuthor(String author) {
		log.debug("LibraryBean.setAuthor() started");
		this.author = author;
		log.info("Book author set successfully");
	}

	/**
	 * Returns the price of the book.
	 *
	 * @return book price
	 */
	public double getPrice() {
		log.debug("LibraryBean.getPrice() started");
		return price;
	}

	/**
	 * Sets the price of the book.
	 *
	 * @param price book price
	 */
	public void setPrice(double price) {
		log.debug("LibraryBean.setPrice() started");
		this.price = price;
		log.info("Book price set successfully");
	}

	/**
	 * Returns the availability status of the book.
	 *
	 * @return true if the book is available, otherwise false
	 */
	public boolean isAvailability() {
		log.debug("LibraryBean.isAvailability() started");
		return availability;
	}

	/**
	 * Sets the availability status of the book.
	 *
	 * @param availability book availability status
	 */
	public void setAvailability(boolean availability) {
		log.debug("LibraryBean.setAvailability() started");
		this.availability = availability;
		log.info("Book availability status set successfully");
	}

	/**
	 * Populates the LibraryBean properties using data from ResultSet. It also calls
	 * the parent class method to set common properties.
	 *
	 * @param rs ResultSet containing library details
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("LibraryBean.setResultSet() started");

		if (rs == null) {
			log.warn("ResultSet is null in LibraryBean");
		}

		try {

			setTitle(rs.getString("title"));

			setAuthor(rs.getString("author"));

			setPrice(rs.getDouble("price"));

			setAvailability(rs.getBoolean("availability"));

			log.info("LibraryBean populated successfully");

		} catch (Exception e) {

			log.error("Error while populating LibraryBean", e);
			e.printStackTrace();

		}

		super.setResultSet(rs);

		log.debug("LibraryBean.setResultSet() completed");
	}

	/**
	 * Returns the display value of the library bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		log.debug("LibraryBean.getValue() started");
		// TODO Auto-generated method stub
		return null;
	}

}