package in.co.rays.proj4.bean;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import org.apache.log4j.Logger;

/**
 * UserBean class represents user information. It stores user details such as
 * name, login credentials, date of birth, contact information, role, login
 * status, IP addresses, and photo.
 *
 * @author karunaChouhan
 */
public class UserBean extends BaseBean {

	private static Logger log = Logger.getLogger(UserBean.class);

	/** First name of the user. */
	private String firstName;

	/** Last name of the user. */
	private String lastName;

	/** Login ID of the user. */
	private String login;

	/** Password of the user. */
	private String password;

	/** Confirmation password of the user. */
	private String confirmPassword;

	/** Date of birth of the user. */
	private Date dob;

	/** Mobile number of the user. */
	private String mobileNo;

	/** Number of unsuccessful login attempts. */
	private int unsuccessfullLogin;

	/** Gender of the user. */
	private String gender;

	/** Date and time of the user's last login. */
	private Timestamp lastLogin;

	/** Lock status of the user account. */
	private String userLock;

	/** IP address from which the user registered. */
	private String registeredIp;

	/** IP address from which the user last logged in. */
	private String lastLoginIp;

	/** Role ID assigned to the user. */
	private long roleId;

	/** Photo of the user. */
	private String photo;

	/**
	 * Returns the user's photo.
	 *
	 * @return user photo
	 */
	public String getPhoto() {
		log.debug("UserBean.getPhoto()");
		return photo;
	}

	/**
	 * Sets the user's photo.
	 *
	 * @param photo photo to set
	 */
	public void setPhoto(String photo) {
		log.debug("UserBean.setPhoto()");
		this.photo = photo;
		log.info("User photo set successfully");
	}

	/**
	 * Returns the first name of the user.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		log.debug("UserBean.getFirstName()");
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 *
	 * @param firstName first name to set
	 */
	public void setFirstName(String firstName) {
		log.debug("UserBean.setFirstName()");
		this.firstName = firstName;
		log.info("First name set successfully");
	}

	/**
	 * Returns the last name of the user.
	 *
	 * @return last name
	 */
	public String getLastName() {
		log.debug("UserBean.getLastName()");
		return lastName;
	}

	/**
	 * Sets the last name of the user.
	 *
	 * @param lastName last name to set
	 */
	public void setLastName(String lastName) {
		log.debug("UserBean.setLastName()");
		this.lastName = lastName;
		log.info("Last name set successfully");
	}

	/**
	 * Returns the login ID of the user.
	 *
	 * @return login ID
	 */
	public String getLogin() {
		log.debug("UserBean.getLogin()");
		return login;
	}

	/**
	 * Sets the login ID of the user.
	 *
	 * @param login login ID to set
	 */
	public void setLogin(String login) {
		log.debug("UserBean.setLogin()");
		this.login = login;
		log.info("Login ID set successfully");
	}

	/**
	 * Returns the user's password.
	 *
	 * @return password
	 */
	public String getPassword() {
		log.debug("UserBean.getPassword()");
		return password;
	}

	/**
	 * Sets the user's password.
	 *
	 * @param password password to set
	 */
	public void setPassword(String password) {
		log.debug("UserBean.setPassword()");
		this.password = password;
		log.info("Password set successfully");
	}

	/**
	 * Returns the date of birth of the user.
	 *
	 * @return date of birth
	 */
	public Date getDob() {
		log.debug("UserBean.getDob()");
		return dob;
	}

	/**
	 * Sets the date of birth of the user.
	 *
	 * @param date date of birth to set
	 */
	public void setDob(Date date) {
		log.debug("UserBean.setDob()");
		this.dob = date;
		log.info("Date of birth set successfully");
	}

	/**
	 * Returns the mobile number of the user.
	 *
	 * @return mobile number
	 */
	public String getMobileNo() {
		log.debug("UserBean.getMobileNo()");
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the user.
	 *
	 * @param mobileNo mobile number to set
	 */
	public void setMobileNo(String mobileNo) {
		log.debug("UserBean.setMobileNo()");
		this.mobileNo = mobileNo;
		log.info("Mobile number set successfully");
	}

	/**
	 * Returns the role ID of the user.
	 *
	 * @return role ID
	 */
	public long getRoleId() {
		log.debug("UserBean.getRoleId()");
		return roleId;
	}

	/**
	 * Sets the role ID of the user.
	 *
	 * @param roleId role ID to set
	 */
	public void setRoleId(long roleId) {
		log.debug("UserBean.setRoleId()");
		this.roleId = roleId;
		log.info("Role ID set successfully");
	}

	/**
	 * Returns the number of unsuccessful login attempts.
	 *
	 * @return unsuccessful login count
	 */
	public int getUnsuccessfullLogin() {
		log.debug("UserBean.getUnsuccessfullLogin()");
		return unsuccessfullLogin;
	}

	/**
	 * Sets the number of unsuccessful login attempts.
	 *
	 * @param unsuccessfullLogin unsuccessful login count to set
	 */
	public void setUnsuccessfullLogin(int unsuccessfullLogin) {
		log.debug("UserBean.setUnsuccessfullLogin()");
		this.unsuccessfullLogin = unsuccessfullLogin;
		log.info("Unsuccessful login count set successfully");
	}

	/**
	 * Returns the gender of the user.
	 *
	 * @return gender
	 */
	public String getGender() {
		log.debug("UserBean.getGender()");
		return gender;
	}

	/**
	 * Sets the gender of the user.
	 *
	 * @param gender gender to set
	 */
	public void setGender(String gender) {
		log.debug("UserBean.setGender()");
		this.gender = gender;
		log.info("Gender set successfully");
	}

	/**
	 * Returns the last login date and time.
	 *
	 * @return last login timestamp
	 */
	public Timestamp getLastLogin() {
		log.debug("UserBean.getLastLogin()");
		return lastLogin;
	}

	/**
	 * Sets the last login date and time.
	 *
	 * @param lastLogin timestamp to set
	 */
	public void setLastLogin(Timestamp lastLogin) {
		log.debug("UserBean.setLastLogin()");
		this.lastLogin = lastLogin;
		log.info("Last login timestamp set successfully");
	}

	/**
	 * Returns the user account lock status.
	 *
	 * @return user lock status
	 */
	public String getUserLock() {
		log.debug("UserBean.getUserLock()");
		return userLock;
	}

	/**
	 * Sets the user account lock status.
	 *
	 * @param userLock lock status to set
	 */
	public void setUserLock(String userLock) {
		log.debug("UserBean.setUserLock()");
		this.userLock = userLock;
		log.info("User lock status set successfully");
	}

	/**
	 * Returns the registered IP address.
	 *
	 * @return registered IP address
	 */
	public String getRegisteredIp() {
		log.debug("UserBean.getRegisteredIp()");
		return registeredIp;
	}

	/**
	 * Sets the registered IP address.
	 *
	 * @param registeredIp IP address to set
	 */
	public void setRegisteredIp(String registeredIp) {
		log.debug("UserBean.setRegisteredIp()");
		this.registeredIp = registeredIp;
		log.info("Registered IP address set successfully");
	}

	/**
	 * Returns the IP address used during the last login.
	 *
	 * @return last login IP address
	 */
	public String getLastLoginIp() {
		log.debug("UserBean.getLastLoginIp()");
		return lastLoginIp;
	}

	/**
	 * Returns the confirmation password.
	 *
	 * @return confirmation password
	 */
	public String getConfirmPassword() {
		log.debug("UserBean.getConfirmPassword()");
		return confirmPassword;
	}

	/**
	 * Sets the confirmation password.
	 *
	 * @param confirmPassword confirmation password to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		log.debug("UserBean.setConfirmPassword()");
		this.confirmPassword = confirmPassword;
		log.info("Confirmation password set successfully");
	}

	/**
	 * Sets the IP address used during the last login.
	 *
	 * @param lastLoginIp last login IP address to set
	 */
	public void setLastLoginIp(String lastLoginIp) {
		log.debug("UserBean.setLastLoginIp()");
		this.lastLoginIp = lastLoginIp;
		log.info("Last login IP address set successfully");
	}

	/**
	 * Returns the user value for dropdown usage.
	 *
	 * @return user value
	 */
	@Override
	public String getValue() {
		log.debug("UserBean.getValue()");
		return null;
	}

	/**
	 * Populates user details from the ResultSet.
	 *
	 * @param rs ResultSet containing user records
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		log.debug("UserBean.setResultSet() started");

		try {
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setLogin(rs.getString("login"));
			setPassword(rs.getString("password"));
			setDob(rs.getDate("dob"));
			setMobileNo(rs.getString("mobile_no"));
			setRoleId(rs.getLong("role_id"));
			setUnsuccessfullLogin(rs.getInt("unsuccessful_login"));
			setGender(rs.getString("gender"));
			setLastLogin(rs.getTimestamp("last_login"));
			setUserLock(rs.getString("user_lock"));
			setRegisteredIp(rs.getString("registered_ip"));
			setLastLoginIp(rs.getString("last_login_ip"));
			setPhoto(rs.getString("photo"));

			log.info("UserBean populated successfully");

		} catch (Exception e) {
			log.error("Error while populating UserBean", e);
			e.printStackTrace();
		}

		super.setResultSet(rs);
		log.debug("UserBean.setResultSet() completed");
	}
}