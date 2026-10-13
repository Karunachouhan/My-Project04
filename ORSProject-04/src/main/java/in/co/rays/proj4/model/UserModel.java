package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class UserModel extends BaseModel<UserBean> {

	@Override
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		long pk = 0;

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null) {
			throw new DuplicateRecordException("Login already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			int i = pstmt.executeUpdate();

			conn.commit();
			System.out.println("Data inserted successfully " + i + " row affected");

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		UserBean existBean = findByLogin(bean.getLogin());

		if (existBean != null && existBean.getId() != bean.getId()) {
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
			System.out.println("Data updated successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
	}

	@Override
	public String getWhereClause(UserBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '" + bean.getLogin() + "'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "'");
			}
			if (bean.getDob() != null) {
				sql.append(" and dob = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 10) {
				sql.append(" and mobie_no like '" + bean.getMobileNo() + "'");
			}
			if (bean.getRoleId() > 0) {
				sql.append(" and role_id = " + bean.getRoleId());
			}
			if (bean.getUnsuccessfullLogin() > 0) {
				sql.append(" and unsuccessfull_login = " + bean.getUnsuccessfullLogin());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "'");
			}
			if (bean.getLastLogin() != null) {
				sql.append(" and last_login = " + bean.getLastLogin());
			}
			if (bean.getUserLock() != null && bean.getUserLock().length() > 0) {
				sql.append(" and user_lock like '" + bean.getUserLock() + "'");
			}
			if (bean.getRegisteredIp() != null && bean.getRegisteredIp().length() > 0) {
				sql.append(" and registered_ip like '" + bean.getRegisteredIp() + "'");
			}
			if (bean.getLastLoginIp() != null && bean.getLastLoginIp().length() > 0) {
				sql.append(" and last_login_ip like '" + bean.getLastLoginIp() + "'");
			}
			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
				sql.append(" and created_by like'" + bean.getCreatedBy() + "'");
			}
			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
				sql.append(" and modified_by like'" + bean.getModifiedBy() + "'");
			}
			if (bean.getCreatedDatetime() != null) {
				sql.append(" and created_datetime like'" + bean.getCreatedDatetime() + "'");
			}
			if (bean.getModifiedDatetime() != null) {
				sql.append(" and modified_datetime like'" + bean.getModifiedDatetime() + "'");
			}
		}

		return sql.toString();
	}

	public UserBean authenticate(String login, String password) {
		UserBean bean = findByLogin(login);

		if (bean != null && bean.getPassword().equals(password)) {
			return bean;
		}
		return null;

	}

	public UserBean findByLogin(String login) {
		UserBean bean = findByUniqueColumn("login", login);
		return bean;
	}

	@Override
	public String getTable() {

		return "st_user";
	}

	@Override
	public UserBean getBean() {

		return new UserBean();
	}

}
