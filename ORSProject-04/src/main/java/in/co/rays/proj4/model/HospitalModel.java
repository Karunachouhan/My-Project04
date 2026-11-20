
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * HospitalModel class provides database operations for Hospital records. It
 * performs insert, update, search and retrieval operations on the st_hospital
 * table.
 *
 * @author karunaChouhan
 * @version 1.0
 */
public class HospitalModel extends BaseModel<HospitalBean> {

	private static final Logger log = Logger.getLogger(HospitalModel.class);

	/**
	 * Adds a new hospital record into the database.
	 *
	 * @param bean HospitalBean object containing hospital details
	 * @return primary key of the inserted hospital record
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if hospital name already exists
	 */
	@Override
	public long add(HospitalBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("HospitalModel add method started");

		Connection conn = null;
		long pk = 0;

		HospitalBean existBean = findByName(bean.getName());

		if (existBean != null) {
			log.warn("Hospital record already exists: " + bean.getName());
			throw new DuplicateRecordException("record allready exist");
		}

		try {
			pk = nextPk();

			log.debug("Generated primary key: " + pk);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing insert query for hospital");

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setInt(3, bean.getAge());
			pstmt.setString(4, bean.getBloodGroup());
			pstmt.setString(5, bean.getDisease());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Data inserted successfully. " + i + " row affected");

		} catch (SQLException e) {
			log.error("Exception occurred while inserting hospital record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing hospital database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("HospitalModel add method ended");

		return pk;
	}

	/**
	 * Updates an existing hospital record in the database.
	 *
	 * @param bean HospitalBean object containing updated hospital details
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if hospital name already exists for another
	 *                                  record
	 */
	@Override
	public void update(HospitalBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("HospitalModel update method started");

		Connection conn = null;

		HospitalBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.warn("Hospital record already exists: " + bean.getName());
			throw new DuplicateRecordException("record allready exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing update query for hospital");

			PreparedStatement pstmt = conn.prepareStatement("Update " + getTable()
					+ " set name=? ,age=? ,blood_group=? ,disease=? ,created_by=? ,modified_by=? ,created_datetime=? ,modified_datetime=? where id=?");
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getAge());
			pstmt.setString(3, bean.getBloodGroup());
			pstmt.setString(4, bean.getDisease());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Data updated successfully. " + i + " row affected");

		} catch (SQLException e) {
			log.error("Exception occurred while updating hospital record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing hospital database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("HospitalModel update method ended");
	}

	/**
	 * Generates a dynamic WHERE clause based on HospitalBean search criteria.
	 *
	 * @param bean HospitalBean object containing search criteria
	 * @return dynamically generated WHERE clause
	 */
	@Override
	public String getWhereClause(HospitalBean bean) {

		log.debug("HospitalModel getWhereClause method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getAge() > 0) {
				sql.append(" and age = " + bean.getAge());
			}
			if (bean.getBloodGroup() != null && bean.getBloodGroup().length() > 0) {
				sql.append(" and bloodGroup like '" + bean.getBloodGroup() + "%'");
			}
			if (bean.getDisease() != null && bean.getDisease().length() > 0) {
				sql.append(" and disease like '" + bean.getDisease() + "%'");
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
		log.debug("HospitalModel getWhereClause method ended");

		return sql.toString();
	}

	/**
	 * Returns the database table name used by this model.
	 *
	 * @return database table name
	 */
	@Override
	public String getTable() {
		log.debug("HospitalModel getTable method called");
		return "st_hospital";
	}

	/**
	 * Creates and returns a new HospitalBean object.
	 *
	 * @return new HospitalBean instance
	 */
	@Override
	public HospitalBean getBean() {
		log.debug("HospitalModel getBean method called");
		return new HospitalBean();
	}

	/**
	 * Finds a hospital record using its unique name.
	 *
	 * @param name hospital name to search
	 * @return HospitalBean object if found
	 */
	public HospitalBean findByName(String name) {

		log.debug("HospitalModel findByName method started");

		HospitalBean bean = findByUniqueColumn("name", name);

		if (bean != null) {
			log.info("Hospital record found: " + name);
		} else {
			log.warn("Hospital record not found: " + name);
		}

		log.debug("HospitalModel findByName method ended");

		return bean;
	}

}
