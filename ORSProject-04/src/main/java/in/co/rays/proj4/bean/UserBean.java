package in.co.rays.proj4.bean;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class UserBean extends BaseBean {

	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private String confirmPassword;
	private Date dob;
	private String mobileNo;
	private int unsuccessfullLogin;
	private String gender;
	private Timestamp lastLogin;
	private String userLock;
	private String registeredIp;
	private String lastLoginIp;
	private long roleId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date date) {
		this.dob = date;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getUnsuccessfullLogin() {
		return unsuccessfullLogin;
	}

	public void setUnsuccessfullLogin(int unsuccessfullLogin) {
		this.unsuccessfullLogin = unsuccessfullLogin;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getUserLock() {
		return userLock;
	}

	public void setUserLock(String userLock) {
		this.userLock = userLock;
	}

	public String getRegisteredIp() {
		return registeredIp;
	}

	public void setRegisteredIp(String registeredIp) {
		this.registeredIp = registeredIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setResultSet(ResultSet rs) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}
}
