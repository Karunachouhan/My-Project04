package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * BaseModel is an abstract class that provides common database operations for
 * all model classes. It supports adding, updating, deleting, searching, and
 * retrieving records.
 *
 * @param <T> type of Bean extending BaseBean
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public abstract class BaseModel<T extends BaseBean> {

	private static Logger log = Logger.getLogger(BaseModel.class);

	/**
	 * Adds a new record to the database.
	 *
	 * @param bean object containing record details
	 * @return primary key of the inserted record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if a duplicate record exists
	 */
	public abstract long add(T bean) throws ApplicationException, DuplicateRecordException;

	/**
	 * Updates an existing record in the database.
	 *
	 * @param bean object containing updated record details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if a duplicate record exists
	 */
	public abstract void update(T bean) throws ApplicationException, DuplicateRecordException;

	/**
	 * Generates the WHERE clause based on the provided Bean.
	 *
	 * @param bean object containing search criteria
	 * @return SQL WHERE clause conditions
	 */
	public abstract String getWhereClause(T bean);

	/**
	 * Returns the database table name.
	 *
	 * @return name of the database table
	 */
	public abstract String getTable();

	/**
	 * Creates and returns a new Bean object.
	 *
	 * @return new Bean instance
	 */
	public abstract T getBean();

	/**
	 * Generates the next primary key for the database table. It retrieves the
	 * maximum ID and increments it by one.
	 *
	 * @return next available primary key
	 * @throws DatabaseException if a database error occurs
	 */
	public long nextPk() throws DatabaseException {

		log.debug("BaseModel nextPk method started");

		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from " + getTable());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}

			rs.close();

			log.info("Next primary key generated successfully: " + (pk + 1));

		} catch (SQLException e) {
			log.error("Error while generating next primary key", e);
			throw new DatabaseException("Exception: exception is getting in pk");

		} finally {
			log.debug("Closing connection after nextPk operation");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("BaseModel nextPk method ended");

		return pk + 1;

	}

	/**
	 * Deletes a record from the database using its primary key.
	 *
	 * @param id primary key of the record to be deleted
	 */
	public void delete(long id) {

		log.debug("BaseModel delete method started. ID: " + id);

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from " + getTable() + " where id = ?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Data deleted successfully. " + i + " row affected");

		} catch (SQLException e) {
			log.error("Error while deleting record with ID: " + id, e);
			JDBCDataSource.trnRollBack(conn);

		} finally {
			log.debug("Closing connection after delete operation");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("BaseModel delete method ended");
	}

	/**
	 * Finds a record using its primary key.
	 *
	 * @param id primary key of the record
	 * @return Bean object if the record is found, otherwise null
	 */
	public T findByPk(long id) {

		log.debug("BaseModel findByPk method started. ID: " + id);

		Connection conn = null;
		T bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from " + getTable() + " where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

			if (bean != null) {
				log.info("Record found successfully for ID: " + id);
			} else {
				log.warn("Record not found for ID: " + id);
			}

		} catch (Exception e) {
			log.error("Error while finding record by ID: " + id, e);

		} finally {
			log.debug("Closing connection after findByPk operation");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("BaseModel findByPk method ended");

		return bean;
	}

	/**
	 * Searches records based on the given Bean criteria with pagination.
	 *
	 * @param bean     object containing search criteria
	 * @param pageNo   page number to retrieve
	 * @param pageSize number of records per page
	 * @return list of matching Bean objects
	 */
	public List<T> search(T bean, int pageNo, int pageSize) {

		log.debug("BaseModel search method started");

		Connection conn = null;
		List<T> list = new ArrayList<T>();

		StringBuffer sql = new StringBuffer("select * from " + getTable() + " where 1 = 1");

		sql.append(getWhereClause(bean));

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		log.debug("Search SQL query: " + sql.toString());

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
				list.add(bean);
			}

			log.info("Search completed successfully. Total records found: " + list.size());

			if (list.isEmpty()) {
				log.warn("No records found for the given search criteria");
			}

		} catch (Exception e) {
			log.error("Error while searching records", e);

		} finally {
			log.debug("Closing connection after search operation");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("BaseModel search method ended");

		return list;
	}

	/**
	 * Finds a record using a unique column and its value.
	 *
	 * @param column name of the database column
	 * @param value  value to search for in the specified column
	 * @return Bean object if a matching record is found, otherwise null
	 */
	public T findByUniqueColumn(String column, String value) {

		log.debug("BaseModel findByUniqueColumn method started");
		log.debug("Searching using column: " + column);

		Connection conn = null;
		T bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select * from " + getTable() + " where " + column + " = ?");
			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

			if (bean != null) {
				log.info("Record found using column: " + column);
			} else {
				log.warn("Record not found using column: " + column);
			}

		} catch (Exception e) {
			log.error("Error while finding record using unique column: " + column, e);

		} finally {
			log.debug("Closing connection after findByUniqueColumn operation");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("BaseModel findByUniqueColumn method ended");

		return bean;
	}
}