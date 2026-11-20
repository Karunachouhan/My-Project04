
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * MarksheetModel class provides database operations for Marksheet records.
 * It supports adding, updating, searching, and retrieving marksheet records.
 *
 * @author karunaChouhan
 * @version 1.0
 */
public class MarksheetModel extends BaseModel<MarksheetBean> {

	private static final Logger log = Logger.getLogger(MarksheetModel.class);

	/**
	 * Adds a new marksheet record to the database.
	 *
	 * @param bean MarksheetBean object containing marksheet details
	 * @return Primary key of the newly inserted record
	 * @throws ApplicationException if a database operation fails
	 * @throws DuplicateRecordException if the roll number already exists
	 */
	@Override
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("MarksheetModel add method started");

		Connection conn = null;
		long pk = 0;

		MarksheetBean existBean = findByRollno(bean.getRollNo());

		if (existBean != null) {
			log.warn("Marksheet roll number already exists: " + bean.getRollNo());
			throw new DuplicateRecordException("rollno already exist");
		}

		StudentModel smodel = new StudentModel();
		StudentBean sbean = smodel.findByPk(bean.getStudentId());

		try {
			pk = nextPk();

			log.debug("Generated marksheet primary key: " + pk);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing insert query for marksheet");

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, sbean.getFirstName() + " " + sbean.getLastName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Marksheet data inserted successfully. " + i + " row affected");

		} catch (SQLException e) {
			log.error("Exception occurred while inserting marksheet record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing marksheet database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("MarksheetModel add method ended");

		return pk;
	}

	/**
	 * Updates an existing marksheet record in the database.
	 *
	 * @param bean MarksheetBean object containing updated marksheet details
	 * @throws ApplicationException if a database operation fails
	 * @throws DuplicateRecordException if the roll number already exists
	 */
	@Override
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("MarksheetModel update method started");

		Connection conn = null;

		MarksheetBean existBean = findByRollno(bean.getRollNo());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.warn("Marksheet roll number already exists: " + bean.getRollNo());
			throw new DuplicateRecordException("rollno already exist");
		}

		StudentModel smodel = new StudentModel();
		StudentBean sbean = smodel.findByPk(bean.getStudentId());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing update query for marksheet");

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set roll_no=?, student_id=?, name=?, physics=?, chemistry=?, maths=?, created_by=?, modified_by=?, modified_datetime=?, created_datetime=? where id = ?");
			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, sbean.getFirstName() + " " + sbean.getLastName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Marksheet data updated successfully. " + i + " row affected");

		} catch (SQLException e) {
			log.error("Exception occurred while updating marksheet record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing marksheet database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("MarksheetModel update method ended");
	}

	/**
	 * Builds the WHERE clause dynamically based on the provided marksheet details.
	 *
	 * @param bean MarksheetBean object containing search criteria
	 * @return Generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(MarksheetBean bean) {

		log.debug("MarksheetModel getWhereClause method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" and roll_no like '" + bean.getRollNo() + "'");
			}
			if (bean.getStudentId() > 0) {
				sql.append(" and student_id = " + bean.getStudentId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getPhysics() > 0) {
				sql.append(" and physics = " + bean.getPhysics());
			}
			if (bean.getChemistry() > 0) {
				sql.append(" and chemistry = " + bean.getChemistry());
			}
			if (bean.getMaths() > 0) {
				sql.append(" and maths = " + bean.getMaths());
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
		log.debug("MarksheetModel getWhereClause method ended");

		return sql.toString();
	}

	/**
	 * Returns the database table name for marksheet records.
	 *
	 * @return Name of the marksheet table
	 */
	@Override
	public String getTable() {
		log.debug("MarksheetModel getTable method called");
		return "st_marksheet";
	}

	/**
	 * Creates and returns a new MarksheetBean object.
	 *
	 * @return New MarksheetBean instance
	 */
	@Override
	public MarksheetBean getBean() {
		log.debug("MarksheetModel getBean method called");
		return new MarksheetBean();
	}

	/**
	 * Finds a marksheet record using its roll number.
	 *
	 * @param rollNo Roll number of the student
	 * @return MarksheetBean object if found, otherwise null
	 */
	public MarksheetBean findByRollno(String rollNo) {

		log.debug("MarksheetModel findByRollno method started");

		MarksheetBean bean = findByUniqueColumn("roll_no", rollNo);

		if (bean != null) {
			log.info("Marksheet record found for roll number: " + rollNo);
		} else {
			log.warn("Marksheet record not found for roll number: " + rollNo);
		}

		log.debug("MarksheetModel findByRollno method ended");

		return bean;
	}

}
