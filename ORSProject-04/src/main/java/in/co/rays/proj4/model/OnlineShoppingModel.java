package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class OnlineShoppingModel extends BaseModel<OnlineShoppingBean> {

	@Override
	public long add(OnlineShoppingBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		long pk = 0;
		
		OnlineShoppingBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("product name already exist");
		}


		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

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
			System.out.println("record inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

	@Override
	public void update(OnlineShoppingBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		
		OnlineShoppingBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("product name already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

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
			System.out.println("Data updated successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(OnlineShoppingBean bean) {
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
		return sql.toString();
	}

	@Override
	public String getTable() {
		return "online_shopping";
	}

	@Override
	public OnlineShoppingBean getBean() {
		return new OnlineShoppingBean();
	}
	
	public OnlineShoppingBean findByName(String name) {
		OnlineShoppingBean bean = findByUniqueColumn("name", name);
		return bean;
	}

}
