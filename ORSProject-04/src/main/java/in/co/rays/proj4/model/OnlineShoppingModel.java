
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * OnlineShoppingModel class performs database operations for online shopping
 * products, including adding, updating, searching, and retrieving product data.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class OnlineShoppingModel extends BaseModel<OnlineShoppingBean> {

	/** Logger to log OnlineShoppingModel activities. */
	private static final Logger log = Logger.getLogger(OnlineShoppingModel.class);

	/**
	 * Adds a new product record to the database.
	 *
	 * @param bean OnlineShoppingBean object containing product details
	 * @return primary key of the inserted record
	 * @throws ApplicationException if a database error occurs
	 * @throws DuplicateRecordException if product name already exists
	 */
	@Override
	public long add(OnlineShoppingBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("OnlineShoppingModel add method started");

		Connection conn = null;
		long pk = 0;

		OnlineShoppingBean existBean = findByName(bean.getName());

		if (existBean != null) {
			log.warn("Product name already exists: " + bean.getName());
			throw new DuplicateRecordException("product name already exist");
		}

		try {
			pk = nextPk();
			log.debug("Generated primary key: " + pk);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing insert query for online shopping product");

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setString(4, bean.getCategory());
			pstmt.setInt(5, bean.getStock());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Product record inserted successfully. " + i + " row affected");
		} catch (SQLException e) {
			log.error("SQL Exception while inserting product record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("OnlineShoppingModel add method ended");
		return pk;
	}

	/**
	 * Updates an existing product record in the database.
	 *
	 * @param bean OnlineShoppingBean object containing updated product details
	 * @throws ApplicationException if a database error occurs
	 * @throws DuplicateRecordException if another product has the same name
	 */
	@Override
	public void update(OnlineShoppingBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("OnlineShoppingModel update method started");

		Connection conn = null;

		OnlineShoppingBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.warn("Product name already exists: " + bean.getName());
			throw new DuplicateRecordException("product name already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing update query for online shopping product");

			PreparedStatement pstmt = conn.prepareStatement("Update " + getTable()
					+ " set name = ? , price = ? , category = ? , stock = ? ,created_by = ? ,modified_by = ? ,created_datetime = ? ,modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setDouble(2, bean.getPrice());
			pstmt.setString(3, bean.getCategory());
			pstmt.setInt(4, bean.getStock());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Product data updated successfully. " + i + " row affected");
		} catch (SQLException e) {
			log.error("SQL Exception while updating product record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("OnlineShoppingModel update method ended");
	}

	/**
	 * Generates the WHERE clause dynamically based on product details.
	 *
	 * @param bean OnlineShoppingBean object containing search criteria
	 * @return dynamically generated SQL WHERE clause
	 */
	@Override
	public String getWhereClause(OnlineShoppingBean bean) {
		log.debug("OnlineShoppingModel getWhereClause method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getPrice() > 0) {
				sql.append(" and price = " + bean.getPrice());
			}
			if (bean.getCategory() != null && bean.getCategory().length() > 0) {
				sql.append(" and category like '" + bean.getCategory() + "%'");
			}
			if (bean.getStock() > 0) {
				sql.append(" and stock = " + bean.getStock());
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

		log.debug("Generated WHERE clause: " + sql.toString());
		log.debug("OnlineShoppingModel getWhereClause method ended");

		return sql.toString();
	}

	/**
	 * Returns the database table name.
	 *
	 * @return online_shopping table name
	 */
	@Override
	public String getTable() {
		log.debug("OnlineShoppingModel getTable method called");
		return "online_shopping";
	}

	/**
	 * Creates and returns a new OnlineShoppingBean object.
	 *
	 * @return new OnlineShoppingBean object
	 */
	@Override
	public OnlineShoppingBean getBean() {
		log.debug("OnlineShoppingModel getBean method called");
		return new OnlineShoppingBean();
	}

	/**
	 * Finds a product record using its name.
	 *
	 * @param name product name to search
	 * @return OnlineShoppingBean if found, otherwise null
	 */
	public OnlineShoppingBean findByName(String name) {
		log.debug("OnlineShoppingModel findByName method started: " + name);

		OnlineShoppingBean bean = findByUniqueColumn("name", name);

		if (bean != null) {
			log.info("Product found with name: " + name);
		} else {
			log.warn("Product not found with name: " + name);
		}

		log.debug("OnlineShoppingModel findByName method ended");
		return bean;
	}

}
