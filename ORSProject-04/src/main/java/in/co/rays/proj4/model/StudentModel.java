
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * StudentModel class provides database operations for Student records. It
 * supports adding, updating, searching, and retrieving student records.
 *
 * @author karunaChouhan
 * @version 1.0
 */
public class StudentModel extends BaseModel<StudentBean> {

	/** Logger for StudentModel class. */
	private static final Logger log = Logger.getLogger(StudentModel.class);

	/**
	 * Adds a new student record to the database.
	 *
	 * @param bean StudentBean object containing student details
	 * @return Primary key of the newly inserted record
	 * @throws ApplicationException     if a database operation fails
	 * @throws DuplicateRecordException if the email already exists
	 */
	@Override
	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("StudentModel add method started");

		Connection conn = null;
		long pk = 0;

		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			log.warn("Student email already exists: " + bean.getEmail());
			throw new DuplicateRecordException("email id already exist");
		}

		try {
			pk = nextPk();
			log.debug("Generated primary key: " + pk);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing insert query for student record");

			PreparedStatement pstmt = conn
					.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setLong(2, bean.getCollegeId());
			pstmt.setString(3, bean.getCollegeName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setDate(6, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getEmail());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Data added successfully " + i + " row affected");
		} catch (SQLException e) {
			log.error("SQL Exception while inserting student record", e);
			JDBCDataSource.trnRollBack(conn);

		} finally {
			log.debug("Closing database connection");
			JDBCDataSource.closeConnection(conn);

		}

		log.debug("StudentModel add method ended");
		return pk;
	}

	/**
	 * Updates an existing student record in the database.
	 *
	 * @param bean StudentBean object containing updated student details
	 * @throws ApplicationException     if a database operation fails
	 * @throws DuplicateRecordException if the email already exists
	 */
	@Override
	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("StudentModel update method started");

		Connection conn = null;

		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.warn("Student email already exists: " + bean.getEmail());
			throw new DuplicateRecordException("email id already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing update query for student record");

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set college_id=?, college_name=?, first_name=?, last_name=?, date_of_birth=?, mobile_no=?, email=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");
			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, bean.getCollegeName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setDate(5, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Data updated successfully " + i + " row affected");
		} catch (SQLException e) {
			log.error("SQL Exception while updating student record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("StudentModel update method ended");
	}

	/**
	 * Builds the WHERE clause dynamically based on the provided student details.
	 *
	 * @param bean StudentBean object containing search criteria
	 * @return Generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(StudentBean bean) {
		log.debug("StudentModel getWhereClause method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getCollegeId() > 0) {
				sql.append(" and college_id = " + bean.getCollegeId());
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" and college_name like '" + bean.getCollegeName() + "'");
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "'");
			}
			if (bean.getDateOfBirth() != null) {
				sql.append(" and date_of_birth = " + bean.getDateOfBirth());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 10) {
				sql.append(" and mobile_no like '" + bean.getMobileNo() + "'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" and email like '" + bean.getEmail() + "'");
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
		log.debug("StudentModel getWhereClause method ended");

		return sql.toString();
	}

	/**
	 * Returns the database table name for student records.
	 *
	 * @return Name of the student table
	 */
	@Override
	public String getTable() {
		log.debug("StudentModel getTable method called");
		return "st_student";
	}

	/**
	 * Creates and returns a new StudentBean object.
	 *
	 * @return New StudentBean instance
	 */
	@Override
	public StudentBean getBean() {
		log.debug("StudentModel getBean method called");
		return new StudentBean();
	}

	/**
	 * Finds a student record using the student's email address.
	 *
	 * @param email Email address of the student
	 * @return StudentBean object if found, otherwise null
	 */
	public StudentBean findByEmail(String email) {
		log.debug("StudentModel findByEmail method started: " + email);

		StudentBean bean = findByUniqueColumn("email", email);

		if (bean != null) {
			log.info("Student found with email: " + email);
		} else {
			log.warn("Student not found with email: " + email);
		}

		log.debug("StudentModel findByEmail method ended");
		return bean;
	}

}
