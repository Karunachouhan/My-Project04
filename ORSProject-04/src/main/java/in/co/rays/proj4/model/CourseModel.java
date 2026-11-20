package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CourseModel class provides database operations for Course records. It
 * supports adding, updating, searching and retrieving course details.
 *
 * @author karunaChouhan
 * @version 1.0
 */
public class CourseModel extends BaseModel<CourseBean> {

	private static Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Adds a new course record into the database.
	 *
	 * @param bean CourseBean object containing course details
	 * @return primary key of the inserted course
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if course name already exists
	 */
	@Override
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("CourseModel add method started");

		Connection conn = null;
		long pk = 0;

		CourseBean existBean = findByName(bean.getName());

		if (existBean != null) {
			log.warn("Course name already exists: " + bean.getName());
			throw new DuplicateRecordException("course name already exist");
		}

		try {
			pk = nextPk();
			log.debug("Generated course primary key: " + pk);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDuration());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Course data inserted successfully. " + i + " row affected");
			log.debug("Course record inserted with ID: " + pk);

		} catch (SQLException e) {
			log.error("Error while inserting course record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing course database connection");
			JDBCDataSource.closeConnection(conn);

		}

		log.debug("CourseModel add method ended");

		return pk;
	}

	/**
	 * Updates an existing course record in the database.
	 *
	 * @param bean CourseBean object containing updated course details
	 * @throws ApplicationException     if application error occurs
	 * @throws DuplicateRecordException if course name already exists
	 */
	@Override
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("CourseModel update method started");

		Connection conn = null;

		CourseBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.warn("Duplicate course name found: " + bean.getName());
			throw new DuplicateRecordException("course name already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set name=? , description=? , duration=? , created_by=? , modified_by=? , created_datetime=? , modified_datetime=? where id=? ");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Course data updated successfully. " + i + " row affected");
			log.debug("Course record updated with ID: " + bean.getId());

		} catch (SQLException e) {
			log.error("Error while updating course record with ID: " + bean.getId(), e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing course database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("CourseModel update method ended");

	}

	/**
	 * Generates a dynamic WHERE clause based on CourseBean search criteria.
	 *
	 * @param bean CourseBean object containing search criteria
	 * @return dynamically generated WHERE clause
	 */
	@Override
	public String getWhereClause(CourseBean bean) {

		log.debug("CourseModel getWhereClause method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" and duration like '" + bean.getDuration() + "%'");
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

		log.debug("CourseModel getWhereClause method ended");

		return sql.toString();

	}

	/**
	 * Returns the name of the database table used by this model.
	 *
	 * @return database table name
	 */
	@Override
	public String getTable() {

		log.debug("CourseModel getTable method called");

		return "st_course";
	}

	/**
	 * Creates and returns a new CourseBean object.
	 *
	 * @return new CourseBean instance
	 */
	@Override
	public CourseBean getBean() {

		log.debug("CourseModel getBean method called");

		return new CourseBean();
	}

	/**
	 * Finds a course record using its unique name.
	 *
	 * @param name course name to search
	 * @return CourseBean object if found
	 */
	public CourseBean findByName(String name) {

		log.debug("CourseModel findByName method started. Course name: " + name);

		CourseBean bean = findByUniqueColumn("name", name);

		if (bean != null) {
			log.info("Course record found: " + name);
		} else {
			log.warn("Course record not found: " + name);
		}

		log.debug("CourseModel findByName method ended");

		return bean;
	}
}