package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.HospitalBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class HospitalModel extends BaseModel<HospitalBean> {

	@Override
	public long add(HospitalBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		long pk = 0;

		HospitalBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("record allready exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

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
			System.out.println("Data inserted succcessfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(HospitalBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		HospitalBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("record allready exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("Update " + getTable()
					+ " set name=? ,age=? ,bloodGroup=? ,disease=? ,created_by=? ,modified_by=? ,created_datetime=? ,modified_datetime=? where id=?");
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
			System.out.println("Data updated successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	@Override
	public String getWhereClause(HospitalBean bean) {
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
		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_hospital";
	}

	@Override
	public HospitalBean getBean() {
		return new HospitalBean();
	}

	public HospitalBean findByName(String name) {
		HospitalBean bean = findByUniqueColumn("name", name);
		return bean;
	}

}
