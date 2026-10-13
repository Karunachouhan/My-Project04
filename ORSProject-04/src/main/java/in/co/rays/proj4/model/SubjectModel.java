package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class SubjectModel extends BaseModel<SubjectBean> {

	@Override
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		long pk = 0;

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("college name already exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

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
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("college name already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name=?, description=?, course_id=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	@Override
	public String getWhereClause(SubjectBean bean) {
		StringBuffer sql = new StringBuffer();

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" and course_id = " + bean.getCourseId());
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
		return "st_subject";
	}

	@Override
	public SubjectBean getBean() {
		return new SubjectBean();
	}

	public SubjectBean findByName(String name) {
		SubjectBean bean = findByUniqueColumn("name", name);
		return bean;
	}

}
