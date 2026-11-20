
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RailwayBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * RailwayModel class provides database operations for Railway records. It
 * supports adding, updating, searching, and retrieving railway records.
 *
 * @author karunaChouhan
 * @version 1.0
 */
public class RailwayModel extends BaseModel<RailwayBean> {

	/** Logger for RailwayModel class. */
	private static final Logger log = Logger.getLogger(RailwayModel.class);

	/**
	 * Adds a new railway record to the database.
	 *
	 * @param bean RailwayBean object containing railway details
	 * @return Primary key of the newly inserted record
	 * @throws ApplicationException     if a database operation fails
	 * @throws DuplicateRecordException if a duplicate record exists
	 */
	@Override
	public long add(RailwayBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("RailwayModel add method started");

		Connection conn = null;
		long pk = 0;

		try {
			pk = nextPk();
			log.debug("Generated primary key: " + pk);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing insert query for railway record");

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getPassengerName());
			pstmt.setInt(3, bean.getTrainNo());
			pstmt.setInt(4, bean.getSeatNo());
			pstmt.setString(5, bean.getJourneyDate());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Record inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			log.error("SQL Exception while inserting railway record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("RailwayModel add method ended");
		return pk;
	}

	/**
	 * Updates an existing railway record in the database.
	 *
	 * @param bean RailwayBean object containing updated railway details
	 * @throws ApplicationException     if a database operation fails
	 * @throws DuplicateRecordException if a duplicate record exists
	 */
	@Override
	public void update(RailwayBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("RailwayModel update method started");

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing update query for railway record");

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set passengerName = ?, trainNo = ?, seatNo = ?, journeyDate = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getPassengerName());
			pstmt.setInt(2, bean.getTrainNo());
			pstmt.setInt(3, bean.getSeatNo());
			pstmt.setString(4, bean.getJourneyDate());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Record updated successfully " + i + " row affected");
		} catch (SQLException e) {
			log.error("SQL Exception while updating railway record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("RailwayModel update method ended");
	}

	/**
	 * Builds the WHERE clause dynamically based on the provided railway details.
	 *
	 * @param bean RailwayBean object containing search criteria
	 * @return Generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(RailwayBean bean) {
		log.debug("RailwayModel getWhereClause method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getPassengerName() != null && bean.getPassengerName().length() > 0) {
				sql.append(" and passenger_name like '" + bean.getPassengerName() + "%'");
			}
			if (bean.getTrainNo() > 0) {
				sql.append(" and train_no = " + bean.getTrainNo());
			}
			if (bean.getSeatNo() > 0) {
				sql.append(" and seat_no = " + bean.getSeatNo());
			}
			if (bean.getJourneyDate() != null && bean.getJourneyDate().length() > 0) {
				sql.append(" and journey_date like '" + bean.getJourneyDate() + "%'");
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
		log.debug("RailwayModel getWhereClause method ended");

		return sql.toString();

	}

	/**
	 * Returns the database table name for railway records.
	 *
	 * @return Name of the railway table
	 */
	@Override
	public String getTable() {
		log.debug("RailwayModel getTable method called");
		return "railway";
	}

	/**
	 * Creates and returns a new RailwayBean object.
	 *
	 * @return New RailwayBean instance
	 */
	@Override
	public RailwayBean getBean() {
		log.debug("RailwayModel getBean method called");
		return new RailwayBean();
	}

}
