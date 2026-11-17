package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * FacultyBean is a Bean class that stores faculty-related information.
 * 
 * It extends BaseBean to inherit common properties such as id, createdBy,
 * modifiedBy, createdDatetime, and modifiedDatetime.
 * 
 * It contains faculty details such as college information, personal
 * information, contact details, gender, and date of birth.
 * 
 * @author karunaChouhan
 */
public class FacultyBean extends BaseBean {

	private static Logger log = Logger.getLogger(FacultyBean.class);

	private long collegeId;
	private String collegeName;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNo;
	private String address;
	private String gender;
	private Date dateOfBirth;

	/**
	 * Returns the college ID.
	 * 
	 * @return collegeId
	 */
	public long getCollegeId() {
		log.debug("FacultyBean.getCollegeId() started");
		return collegeId;
	}

	/**
	 * Sets the college ID.
	 * 
	 * @param collegeId college ID
	 */
	public void setCollegeId(long collegeId) {
		log.debug("FacultyBean.setCollegeId() started");
		this.collegeId = collegeId;
		log.info("College ID set successfully");
	}

	/**
	 * Returns the college name.
	 * 
	 * @return collegeName
	 */
	public String getCollegeName() {
		log.debug("FacultyBean.getCollegeName() started");
		return collegeName;
	}

	/**
	 * Sets the college name.
	 * 
	 * @param collegeName name of the college
	 */
	public void setCollegeName(String collegeName) {
		log.debug("FacultyBean.setCollegeName() started");
		this.collegeName = collegeName;
		log.info("College name set successfully");
	}

	/**
	 * Returns the first name of the faculty.
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		log.debug("FacultyBean.getFirstName() started");
		return firstName;
	}

	/**
	 * Sets the first name of the faculty.
	 * 
	 * @param firstName faculty first name
	 */
	public void setFirstName(String firstName) {
		log.debug("FacultyBean.setFirstName() started");
		this.firstName = firstName;
		log.info("Faculty first name set successfully");
	}

	/**
	 * Returns the last name of the faculty.
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		log.debug("FacultyBean.getLastName() started");
		return lastName;
	}

	/**
	 * Sets the last name of the faculty.
	 * 
	 * @param lastName faculty last name
	 */
	public void setLastName(String lastName) {
		log.debug("FacultyBean.setLastName() started");
		this.lastName = lastName;
		log.info("Faculty last name set successfully");
	}

	/**
	 * Returns the email address of the faculty.
	 * 
	 * @return email address
	 */
	public String getEmail() {
		log.debug("FacultyBean.getEmail() started");
		return email;
	}

	/**
	 * Sets the email address of the faculty.
	 * 
	 * @param email faculty email address
	 */
	public void setEmail(String email) {
		log.debug("FacultyBean.setEmail() started");
		this.email = email;
		log.info("Faculty email set successfully");
	}

	/**
	 * Returns the mobile number of the faculty.
	 * 
	 * @return mobileNo
	 */
	public String getMobileNo() {
		log.debug("FacultyBean.getMobileNo() started");
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the faculty.
	 * 
	 * @param mobileNo faculty mobile number
	 */
	public void setMobileNo(String mobileNo) {
		log.debug("FacultyBean.setMobileNo() started");
		this.mobileNo = mobileNo;
		log.info("Faculty mobile number set successfully");
	}

	/**
	 * Returns the address of the faculty.
	 * 
	 * @return faculty address
	 */
	public String getAddress() {
		log.debug("FacultyBean.getAddress() started");
		return address;
	}

	/**
	 * Sets the address of the faculty.
	 * 
	 * @param address faculty address
	 */
	public void setAddress(String address) {
		log.debug("FacultyBean.setAddress() started");
		this.address = address;
		log.info("Faculty address set successfully");
	}

	/**
	 * Returns the gender of the faculty.
	 * 
	 * @return gender
	 */
	public String getGender() {
		log.debug("FacultyBean.getGender() started");
		return gender;
	}

	/**
	 * Sets the gender of the faculty.
	 * 
	 * @param gender faculty gender
	 */
	public void setGender(String gender) {
		log.debug("FacultyBean.setGender() started");
		this.gender = gender;
		log.info("Faculty gender set successfully");
	}

	/**
	 * Returns the date of birth of the faculty.
	 * 
	 * @return dateOfBirth
	 */
	public Date getDateOfBirth() {
		log.debug("FacultyBean.getDateOfBirth() started");
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the faculty.
	 * 
	 * @param dateOfBirth faculty date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		log.debug("FacultyBean.setDateOfBirth() started");
		this.dateOfBirth = dateOfBirth;
		log.info("Faculty date of birth set successfully");
	}

	/**
	 * Returns the college name as the display value.
	 * 
	 * @return collegeName
	 */
	@Override
	public String getValue() {
		log.debug("FacultyBean.getValue() started");
		return collegeName;
	}

	/**
	 * Populates the FacultyBean properties using data from ResultSet. It also calls
	 * the parent class method to set common properties.
	 * 
	 * @param rs ResultSet containing faculty details
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("FacultyBean.setResultSet() started");

		if (rs == null) {
			log.warn("ResultSet is null in FacultyBean");
		}

		try {
			setCollegeId(rs.getLong("id"));
			setCollegeName(rs.getString("college_name"));
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setEmail(rs.getString("email"));
			setMobileNo(rs.getString("mobile_no"));
			setAddress(rs.getString("address"));
			setGender(rs.getString("gender"));
			setDateOfBirth(rs.getDate("date_of_birth"));

			log.info("FacultyBean populated successfully");

		} catch (Exception e) {
			log.error("Error while populating FacultyBean", e);
			e.printStackTrace();
		}

		super.setResultSet(rs);

		log.debug("FacultyBean.setResultSet() completed");
	}
}