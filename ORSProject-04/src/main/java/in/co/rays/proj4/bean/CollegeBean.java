package in.co.rays.proj4.bean;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

/**
 * CollegeBean is a Bean class that stores college-related information.
 * 
 * It extends BaseBean to inherit common properties such as id, createdBy,
 * modifiedBy, createdDatetime, and modifiedDatetime.
 * 
 * It also provides methods to get and set college details.
 * 
 * @author karunaChouhan
 */
public class CollegeBean extends BaseBean {

	private static Logger log = Logger.getLogger(CollegeBean.class);

	private String name;
	private String address;
	private String state;
	private String city;
	private String phoneNo;

	/**
	 * Returns the name of the college.
	 * 
	 * @return name of the college
	 */
	public String getName() {
		log.debug("CollegeBean.getName() started");
		log.debug("College name = " + name);
		return name;
	}

	/**
	 * Sets the name of the college.
	 * 
	 * @param name college name
	 */
	public void setName(String name) {
		log.debug("CollegeBean.setName() started");
		this.name = name;
		log.info("College name set successfully");
	}

	/**
	 * Returns the address of the college.
	 * 
	 * @return college address
	 */
	public String getAddress() {
		log.debug("CollegeBean.getAddress() started");
		return address;
	}

	/**
	 * Sets the address of the college.
	 * 
	 * @param address college address
	 */
	public void setAddress(String address) {
		log.debug("CollegeBean.setAddress() started");
		this.address = address;
		log.info("College address set successfully");
	}

	/**
	 * Returns the state in which the college is located.
	 * 
	 * @return state name
	 */
	public String getState() {
		log.debug("CollegeBean.getState() started");
		return state;
	}

	/**
	 * Sets the state of the college.
	 * 
	 * @param state state name
	 */
	public void setState(String state) {
		log.debug("CollegeBean.setState() started");
		this.state = state;
		log.info("College state set successfully");
	}

	/**
	 * Returns the city in which the college is located.
	 * 
	 * @return city name
	 */
	public String getCity() {
		log.debug("CollegeBean.getCity() started");
		return city;
	}

	/**
	 * Sets the city of the college.
	 * 
	 * @param city city name
	 */
	public void setCity(String city) {
		log.debug("CollegeBean.setCity() started");
		this.city = city;
		log.info("College city set successfully");
	}

	/**
	 * Returns the contact number of the college.
	 * 
	 * @return phone number
	 */
	public String getPhoneNo() {
		log.debug("CollegeBean.getPhoneNo() started");
		return phoneNo;
	}

	/**
	 * Sets the contact number of the college.
	 * 
	 * @param phoneNo college contact number
	 */
	public void setPhoneNo(String phoneNo) {
		log.debug("CollegeBean.setPhoneNo() started");
		this.phoneNo = phoneNo;
		log.info("College phone number set successfully");
	}

	/**
	 * Returns the college name as the display value.
	 * 
	 * @return college name
	 */
	@Override
	public String getValue() {
		log.debug("CollegeBean.getValue() started");
		return name;
	}

	/**
	 * Populates the CollegeBean properties using data from ResultSet. It also calls
	 * the parent class method to set common properties.
	 * 
	 * @param rs ResultSet containing college details
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("CollegeBean.setResultSet() started");

		if (rs == null) {
			log.warn("ResultSet is null in CollegeBean");
		}

		try {

			setName(rs.getString("name"));
			setAddress(rs.getString("address"));
			setState(rs.getString("state"));
			setCity(rs.getString("city"));
			setPhoneNo(rs.getString("phone_no"));

			log.info("CollegeBean populated successfully");

		} catch (Exception e) {

			log.error("Error while populating CollegeBean", e);
			e.printStackTrace();

		}

		super.setResultSet(rs);

		log.debug("CollegeBean.setResultSet() completed");

	}

}