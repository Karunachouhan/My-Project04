package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CollegeModel handles database operations related to college records. It
 * provides functionality to add, update, search, and find college records.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class CollegeModel extends BaseModel<CollegeBean> {

	/**
	 * Adds a new college record to the database. Checks whether the college name
	 * already exists before inserting.
	 *
	 * @param bean CollegeBean containing college details
	 * @return primary key of the inserted college record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if the college name already exists
	 */
	@Override
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		long pk = 0;

		CollegeBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("college name already exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data inserted succcessfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	/**
	 * Updates an existing college record in the database. Checks for duplicate
	 * college names before updating.
	 *
	 * @param bean CollegeBean containing updated college details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if another record has the same college name
	 */
	@Override
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		CollegeBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("college name already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("Update " + getTable()
					+ " set name=? ,address=? ,state=? ,city=? ,phone_no=? ,created_by=? ,modified_by=? ,created_datetime=? ,modified_datetime=? where id=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());
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

	/**
	 * Creates a dynamic WHERE clause based on the provided CollegeBean fields.
	 *
	 * @param bean CollegeBean containing search criteria
	 * @return SQL WHERE clause conditions as a String
	 */
	@Override
	public String getWhereClause(CollegeBean bean) {

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address = '" + bean.getAddress() + "%'");
			}
			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" and city like '" + bean.getCity() + "%'");
			}
			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append(" and state like'" + bean.getState() + "%'");
			}
			if (bean.getPhoneNo() != null && bean.getPhoneNo().length() > 10) {
				sql.append(" and phone_no like'" + bean.getPhoneNo() + "%'");
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

	/**
	 * Returns the name of the college database table.
	 *
	 * @return college table name
	 */
	@Override
	public String getTable() {

		return "st_college";
	}

	/**
	 * Creates and returns a new CollegeBean object.
	 *
	 * @return new CollegeBean instance
	 */
	@Override
	public CollegeBean getBean() {
		return new CollegeBean();
	}

	/**
	 * Finds a college record using its name.
	 *
	 * @param name name of the college to search
	 * @return CollegeBean if a matching record is found, otherwise null
	 */
	public CollegeBean findByName(String name) {
		CollegeBean bean = findByUniqueColumn("name", name);
		return bean;
	}
}