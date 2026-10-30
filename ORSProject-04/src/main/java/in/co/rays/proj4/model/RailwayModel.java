package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.LibraryBean;
import in.co.rays.proj4.bean.RailwayBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class RailwayModel extends BaseModel<RailwayBean>{

	@Override
	public long add(RailwayBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		long pk = 0;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

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
			System.out.println("Record inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(RailwayBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

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
			System.out.println("Record updated successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(RailwayBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getPassengerName() != null && bean.getPassengerName().length() > 0) {
				sql.append(" and passenger_name like '" + bean.getPassengerName() + "%'");
			}
			if (bean.getTrainNo()> 0) {
				sql.append(" and train_no = " + bean.getTrainNo());
			}
			if (bean.getSeatNo() > 0) {
				sql.append(" and seat_no = " + bean.getSeatNo());
			}
			if (bean.getJourneyDate() != null && bean.getJourneyDate().length()>0) {
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
		return sql.toString();

	}

	@Override
	public String getTable() {
		return "railway";
	}

	@Override
	public RailwayBean getBean() {
		return new RailwayBean();
	}

}
