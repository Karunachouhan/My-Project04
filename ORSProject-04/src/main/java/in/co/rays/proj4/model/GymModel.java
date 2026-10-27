package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.GymBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class GymModel extends BaseModel<GymBean> {

	@Override
	public long add(GymBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		long pk = 0;

		GymBean existBean = findByName(bean.getName());
		if (existBean != null) {
			throw new DuplicateRecordException("name already exist");
		}
		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getMembershipType());
			pstmt.setString(4, bean.getJoiningDate());
			pstmt.setString(5, bean.getTrainerName());

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
	public void update(GymBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		GymBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("name already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name = ?, membership_type = ?, joining_date = ? , trainer_name = ? where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMembershipType());
			pstmt.setString(3, bean.getJoiningDate());
			pstmt.setString(4, bean.getTrainerName());
			pstmt.setLong(5, bean.getId());
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
	public String getWhereClause(GymBean bean) {
		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getMembershipType() != null && bean.getMembershipType().length() > 0) {
				sql.append(" and membership_type like '" + bean.getMembershipType() + "%'");
			}
			if (bean.getJoiningDate() != null && bean.getJoiningDate().length() > 0) {
				sql.append(" and joining_date like '" + bean.getJoiningDate() + "%'");
			}
			if (bean.getTrainerName() != null && bean.getTrainerName().length() > 0) {
				sql.append(" and trainer_name like '" + bean.getTrainerName() + "%'");
			}
		}
		return sql.toString();

	}

	@Override
	public String getTable() {
		return "st_gym";
	}

	@Override
	public GymBean getBean() {
		return new GymBean();
	}

	public GymBean findByName(String name) {
		GymBean bean = findByUniqueColumn("name", name);
		return bean;
	}
}
