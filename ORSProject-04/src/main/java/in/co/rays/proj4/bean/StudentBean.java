package in.co.rays.proj4.bean;

import java.util.Date;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 * StudentBean class represents student information. It stores student details
 * such as college, name, date of birth, mobile number, and email.
 *
 * @author karunaChouhan
 */
public class StudentBean extends BaseBean {

	private static Logger log = Logger.getLogger(StudentBean.class);

	/** College ID of the student. */
	private long collegeId;

	/** Name of the college. */
	private String collegeName;

	/** First name of the student. */
	private String firstName;

	/** Last name of the student. */
	private String lastName;

	/** Date of birth of the student. */
	private Date dateOfBirth;

	/** Mobile number of the student. */
	private String mobileNo;

	/** Email address of the student. */
	private String email;

	/**
	 * Returns the college ID.
	 *
	 * @return college ID
	 */
	public long getCollegeId() {
		log.debug("StudentBean.getCollegeId()");
		return collegeId;
	}

	/**
	 * Sets the college ID.
	 *
	 * @param collegeId college ID to set
	 */
	public void setCollegeId(long collegeId) {
		log.debug("StudentBean.setCollegeId()");
		this.collegeId = collegeId;
		log.info("College ID set successfully");
	}

	/**
	 * Returns the college name.
	 *
	 * @return college name
	 */
	public String getCollegeName() {
		log.debug("StudentBean.getCollegeName()");
		return collegeName;
	}

	/**
	 * Sets the college name.
	 *
	 * @param collegeName college name to set
	 */
	public void setCollegeName(String collegeName) {
		log.debug("StudentBean.setCollegeName()");
		this.collegeName = collegeName;
		log.info("College name set successfully");
	}

	/**
	 * Returns the first name of the student.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		log.debug("StudentBean.getFirstName()");
		return firstName;
	}

	/**
	 * Sets the first name of the student.
	 *
	 * @param firstName first name to set
	 */
	public void setFirstName(String firstName) {
		log.debug("StudentBean.setFirstName()");
		this.firstName = firstName;
		log.info("First name set successfully");
	}

	/**
	 * Returns the last name of the student.
	 *
	 * @return last name
	 */
	public String getLastName() {
		log.debug("StudentBean.getLastName()");
		return lastName;
	}

	/**
	 * Sets the last name of the student.
	 *
	 * @param lastName last name to set
	 */
	public void setLastName(String lastName) {
		log.debug("StudentBean.setLastName()");
		this.lastName = lastName;
		log.info("Last name set successfully");
	}

	/**
	 * Returns the date of birth of the student.
	 *
	 * @return date of birth
	 */
	public Date getDateOfBirth() {
		log.debug("StudentBean.getDateOfBirth()");
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the student.
	 *
	 * @param date date of birth to set
	 */
	public void setDateOfBirth(Date date) {
		log.debug("StudentBean.setDateOfBirth()");
		this.dateOfBirth = date;
		log.info("Date of birth set successfully");
	}

	/**
	 * Returns the mobile number of the student.
	 *
	 * @return mobile number
	 */
	public String getMobileNo() {
		log.debug("StudentBean.getMobileNo()");
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the student.
	 *
	 * @param mobileNo mobile number to set
	 */
	public void setMobileNo(String mobileNo) {
		log.debug("StudentBean.setMobileNo()");
		this.mobileNo = mobileNo;
		log.info("Mobile number set successfully");
	}

	/**
	 * Returns the email address of the student.
	 *
	 * @return email address
	 */
	public String getEmail() {
		log.debug("StudentBean.getEmail()");
		return email;
	}

	/**
	 * Sets the email address of the student.
	 *
	 * @param email email address to set
	 */
	public void setEmail(String email) {
		log.debug("StudentBean.setEmail()");
		this.email = email;
		log.info("Email address set successfully");
	}

	/**
	 * Returns the student name as the dropdown value.
	 *
	 * @return student value
	 */
	@Override
	public String getValue() {
		log.debug("StudentBean.getValue()");
		return null;
	}

	/**
	 * Populates student details from the ResultSet.
	 *
	 * @param rs ResultSet containing student records
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		log.debug("StudentBean.setResultSet() started");

		try {
			setCollegeId(rs.getLong("college_id"));
			setCollegeName(rs.getString("college_name"));
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setDateOfBirth(rs.getDate("date_of_birth"));
			setMobileNo(rs.getString("mobile_no"));
			setEmail(rs.getString("email"));

			log.info("StudentBean populated successfully");

		} catch (Exception e) {
			log.error("Error while populating StudentBean", e);
			e.printStackTrace();
		}

		super.setResultSet(rs);
		log.debug("StudentBean.setResultSet() completed");
	}
}