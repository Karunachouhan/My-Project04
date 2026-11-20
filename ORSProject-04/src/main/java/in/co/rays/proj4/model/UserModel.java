
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * UserModel class provides database operations for UserBean. It handles user
 * registration, authentication, password management, photo updates, and other
 * user-related operations.
 *
 * @author karunaChouhan
 * @version 1.0
 */
public class UserModel extends BaseModel<UserBean> {

	private static final Logger log = Logger.getLogger(UserModel.class);

	/**
	 * Adds a new user record to the database.
	 *
	 * @param bean UserBean object containing user details
	 * @return primary key of the inserted record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if the login already exists
	 */
	@Override
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("UserModel add method started");

		Connection conn = null;
		long pk = 0;

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null) {
			log.warn("User already exists with login: " + bean.getLogin());
			throw new DuplicateRecordException("Login already exist");
		}

		try {
			log.debug("Getting database connection");

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setInt(9, bean.getUnsuccessfullLogin());
			pstmt.setString(10, bean.getGender());
			pstmt.setTimestamp(11, bean.getLastLogin());
			pstmt.setString(12, bean.getUserLock());
			pstmt.setString(13, bean.getRegisteredIp());
			pstmt.setString(14, bean.getLastLoginIp());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.setString(19, bean.getPhoto());

			int i = pstmt.executeUpdate();

			conn.commit();
			log.info("User data inserted successfully. Rows affected: " + i);

		} catch (SQLException e) {
			log.error("SQL exception occurred while adding user", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed");
		}

		log.debug("UserModel add method completed");
		return pk;
	}

	/**
	 * Updates an existing user record in the database.
	 *
	 * @param bean UserBean object containing updated user details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if the login belongs to another user
	 */
	@Override
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("UserModel update method started");

		Connection conn = null;

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.warn("Login already exists: " + bean.getLogin());
			throw new DuplicateRecordException("login already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set first_name=? , last_name=? , login=? , password=? , dob=? ,mobile_no=? ,unsuccessful_login=?, gender=?, last_login=?, user_lock=?,registered_ip=?, last_login_ip=?, created_by = ? ,modified_by = ? , created_datetime = ? , modified_datetime = ? , role_id=? where id = ?  ");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setInt(7, bean.getUnsuccessfullLogin());
			pstmt.setString(8, bean.getGender());
			pstmt.setTimestamp(9, bean.getLastLogin());
			pstmt.setString(10, bean.getUserLock());
			pstmt.setString(11, bean.getRegisteredIp());
			pstmt.setString(12, bean.getLastLoginIp());
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
			pstmt.setLong(17, bean.getRoleId());
			pstmt.setLong(18, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("User data updated successfully. Rows affected: " + i);

		} catch (SQLException e) {
			log.error("SQL exception occurred while updating user", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed");
		}

		log.debug("UserModel update method completed");
	}

	/**
	 * Generates a WHERE clause based on the supplied user bean fields.
	 *
	 * @param bean UserBean object containing search criteria
	 * @return generated WHERE clause as a String
	 */
	@Override
	public String getWhereClause(UserBean bean) {

		log.debug("UserModel getWhereClause method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "%'");
			}
			if (bean.getDob() != null) {
				sql.append(" and dob = '" + new java.sql.Date(bean.getDob().getTime()) + "'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 10) {
				sql.append(" and mobie_no like '" + bean.getMobileNo() + "%'");
			}
			if (bean.getRoleId() > 0) {
				sql.append(" and role_id = " + bean.getRoleId());
			}
			if (bean.getUnsuccessfullLogin() > 0) {
				sql.append(" and unsuccessfull_login = " + bean.getUnsuccessfullLogin());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}
			if (bean.getLastLogin() != null) {
				sql.append(" and last_login = " + bean.getLastLogin());
			}
			if (bean.getUserLock() != null && bean.getUserLock().length() > 0) {
				sql.append(" and user_lock like '" + bean.getUserLock() + "%'");
			}
			if (bean.getRegisteredIp() != null && bean.getRegisteredIp().length() > 0) {
				sql.append(" and registered_ip like '" + bean.getRegisteredIp() + "%'");
			}
			if (bean.getLastLoginIp() != null && bean.getLastLoginIp().length() > 0) {
				sql.append(" and last_login_ip like '" + bean.getLastLoginIp() + "%'");
			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
				sql.append(" and created_by like'" + bean.getCreatedBy() + "%'");
			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
				sql.append(" and modified_by like'" + bean.getModifiedBy() + "%'");
			}
			if (bean.getCreatedDatetime() != null) {
				sql.append(" and created_datetime like'" + bean.getCreatedDatetime() + "%'");
			}
			if (bean.getModifiedDatetime() != null) {
				sql.append(" and modified_datetime like'" + bean.getModifiedDatetime() + "%'");
			}
		}

		log.debug("Generated user WHERE clause");
		return sql.toString();
	}

	/**
	 * Authenticates a user using login and password.
	 *
	 * @param login    user's login ID
	 * @param password user's password
	 * @return UserBean if authentication is successful; otherwise null
	 */
	public UserBean authenticate(String login, String password) {

		log.debug("UserModel authenticate method started");

		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(password)) {
			log.info("User authentication successful for login: " + login);
			return bean;
		}

		log.warn("User authentication failed for login: " + login);
		return null;
	}

	/**
	 * Finds a user record using the login ID.
	 *
	 * @param login user's login ID
	 * @return matching UserBean object
	 */
	public UserBean findByLogin(String login) {

		log.debug("UserModel findByLogin method started");

		UserBean bean = findByUniqueColumn("login", login);

		if (bean != null) {
			log.info("User found with login: " + login);
		} else {
			log.warn("No user found with login: " + login);
		}

		return bean;
	}

	/**
	 * Changes the user's password after validating the old password and sends a
	 * password-change email.
	 *
	 * @param newPassword new password
	 * @param oldPassword current password
	 * @param login       user's login ID
	 * @return updated UserBean if the password is changed; otherwise null
	 */
	public UserBean changePassword(String newPassword, String oldPassword, String login) {

		log.debug("UserModel changePassword method started");

		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(oldPassword)) {

			bean.setPassword(newPassword);
			update(bean);

			HashMap<String, String> map = new HashMap<String, String>();
			EmailMessage msg = new EmailMessage();

			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());

			msg.setTo(map.get("login"));
			msg.setSubject("Password Changed");
			msg.setMessage(EmailBuilder.getChangePasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			log.info("Password changed and email sent for login: " + login);
			return bean;
		}

		log.warn("Password change failed for login: " + login);
		return null;
	}

	/**
	 * Sends the user's password information to the registered login email.
	 *
	 * @param login user's login ID
	 * @return UserBean if the user is found; otherwise null
	 */
	public UserBean forgetPassword(String login) {

		log.debug("UserModel forgetPassword method started");

		UserBean bean = findByLogin(login);

		if (bean != null) {

			HashMap<String, String> map = new HashMap<String, String>();
			EmailMessage msg = new EmailMessage();

			map.put("login", bean.getLogin());
			map.put("password", bean.getPassword());
			map.put("firstName", bean.getFirstName());
			map.put("lastName", bean.getLastName());

			msg.setTo(map.get("login"));
			msg.setSubject("Password Changed");
			msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
			msg.setMessageType(EmailMessage.HTML_MSG);

			EmailUtility.sendMail(msg);

			log.info("Forget password email sent for login: " + login);
			return bean;
		}

		log.warn("Forget password failed. User not found for login: " + login);
		return null;
	}

	/**
	 * Registers a new user and sends registration information by email.
	 *
	 * @param bean UserBean object containing registration details
	 * @return primary key returned by the add method
	 */
	public long register(UserBean bean) {

		log.debug("UserModel register method started");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();

		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		msg.setTo(map.get("login"));
		msg.setSubject("User Registration Information");
		msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		log.info("Registration email sent successfully for login: " + bean.getLogin());

		return pk;
	}

	/**
	 * Updates the photo path of a user in the database.
	 *
	 * @param id    user ID
	 * @param photo photo path or file name
	 */
	public void updatePhoto(long id, String photo) {

		log.debug("UserModel updatePhoto method started for user ID: " + id);

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set photo = ? where id = ?");
			pstmt.setString(1, photo);
			pstmt.setLong(2, id);

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

			log.info("User photo updated successfully for user ID: " + id);

		} catch (Exception e) {
			log.error("Exception occurred while updating user photo", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed");
		}
	}

	/**
	 * Updates user profile details.
	 *
	 * @param bean UserBean containing profile information
	 */
	public void updateProfile(UserBean bean) {

		log.debug("UserModel updateProfile method started");

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE " + getTable() + " SET first_name=?, last_name=?, gender=?, dob=? WHERE id=?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getGender());
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("User profile updated successfully. Rows affected: " + i);

		} catch (SQLException e) {
			log.error("SQL exception occurred while updating user profile", e);
			JDBCDataSource.trnRollBack(conn);

		} finally {
			JDBCDataSource.closeConnection(conn);
			log.debug("Database connection closed");
		}
	}

	/**
	 * Returns the name of the user database table.
	 *
	 * @return table name
	 */
	@Override
	public String getTable() {

		log.debug("UserModel getTable method called");
		return "st_user";
	}

	/**
	 * Creates and returns a new UserBean object.
	 *
	 * @return new UserBean instance
	 */
	@Override
	public UserBean getBean() {

		log.debug("UserModel getBean method called");
		return new UserBean();
	}

}
