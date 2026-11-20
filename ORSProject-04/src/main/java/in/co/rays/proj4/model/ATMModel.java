package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * ATMModel handles database operations related to ATM records. It provides
 * functionality to add, update, search, and retrieve ATM records.
 *
 * @author karunaChouhan
 * @version 1.0
 * @Copyright (c) Rays Technologies
 */
public class ATMModel extends BaseModel<ATMBean> {

	private static Logger log = Logger.getLogger(ATMModel.class);

	/**
	 * Adds a new ATM record to the database. Checks whether the bank name already
	 * exists before inserting the record.
	 *
	 * @param bean ATMBean containing ATM details
	 * @return primary key of the inserted ATM record
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if the bank name already exists
	 */
	@Override
	public long add(ATMBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("ATMModel add method started");

		Connection conn = null;
		long pk = 0;

		ATMBean existBean = findByName(bean.getBankName());

		if (existBean != null) {
			log.warn("ATM bank name already exists: " + bean.getBankName());
			throw new DuplicateRecordException("bank name allready exist");
		}

		try {
			pk = nextPk();
			log.debug("Generated ATM primary key: " + pk);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getLocation());
			pstmt.setString(3, bean.getBankName());
			pstmt.setDouble(4, bean.getCashAvailable());
			pstmt.setString(5, bean.getStatus());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("ATM data inserted successfully. " + i + " row affected");
			log.debug("ATM record inserted with ID: " + pk);

		} catch (SQLException e) {
			log.error("Error while inserting ATM record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing ATM database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("ATMModel add method ended");

		return pk;
	}

	/**
	 * Updates an existing ATM record in the database. Checks for duplicate bank
	 * names before updating the record.
	 *
	 * @param bean ATMBean containing updated ATM details
	 * @throws ApplicationException     if an application error occurs
	 * @throws DuplicateRecordException if another record has the same bank name
	 */
	@Override
	public void update(ATMBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("ATMModel update method started");

		Connection conn = null;

		ATMBean existBean = findByName(bean.getBankName());

		if (existBean != null && bean.getId() != existBean.getId()) {
			log.warn("Duplicate ATM bank name found: " + bean.getBankName());
			throw new DuplicateRecordException("bank name allready exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("Update " + getTable()
					+ " set location=? ,bank_name=? ,cash_available=? ,status=? ,created_by=? ,modified_by=? ,created_datetime=? ,modified_datetime=? where id=?");
			pstmt.setString(1, bean.getLocation());
			pstmt.setString(2, bean.getBankName());
			pstmt.setDouble(3, bean.getCashAvailable());
			pstmt.setString(4, bean.getStatus());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("ATM data updated successfully. " + i + " row affected");
			log.debug("ATM record updated with ID: " + bean.getId());

		} catch (SQLException e) {
			log.error("Error while updating ATM record with ID: " + bean.getId(), e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing ATM database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("ATMModel update method ended");
	}

	/**
	 * Creates a dynamic WHERE clause based on the provided ATMBean fields.
	 *
	 * @param bean ATMBean containing search criteria
	 * @return SQL WHERE clause conditions as a String
	 */
	@Override
	public String getWhereClause(ATMBean bean) {

		log.debug("ATMModel getWhereClause method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getLocation() != null && bean.getLocation().length() > 0) {
				sql.append(" and location like '" + bean.getLocation() + "%'");
			}
			if (bean.getBankName() != null && bean.getBankName().length() > 0) {
				sql.append(" and bank_name like '" + bean.getBankName() + "%'");
			}
			if (bean.getCashAvailable() > 0) {
				sql.append(" and cash_available = " + bean.getCashAvailable());
			}
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like'" + bean.getStatus() + "%'");
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

		log.debug("ATMModel getWhereClause method ended");

		return sql.toString();
	}

	/**
	 * Returns the name of the database table.
	 *
	 * @return ATM table name
	 */
	@Override
	public String getTable() {

		log.debug("ATMModel getTable method called");

		return "atm";
	}

	/**
	 * Creates and returns a new ATMBean object.
	 *
	 * @return new ATMBean instance
	 */
	@Override
	public ATMBean getBean() {

		log.debug("ATMModel getBean method called");

		return new ATMBean();
	}

	/**
	 * Finds an ATM record using its bank name.
	 *
	 * @param bank_name name of the bank to search
	 * @return ATMBean if a matching record is found, otherwise null
	 */
	public ATMBean findByName(String bank_name) {

		log.debug("ATMModel findByName method started. Bank name: " + bank_name);

		ATMBean bean = findByUniqueColumn("bank_name", bank_name);

		if (bean != null) {
			log.info("ATM record found for bank: " + bank_name);
		} else {
			log.warn("ATM record not found for bank: " + bank_name);
		}

		log.debug("ATMModel findByName method ended");

		return bean;
	}

}