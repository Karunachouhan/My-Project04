
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.LibraryBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * LibraryModel class provides database operations for Library records. It
 * supports adding, updating, searching, and retrieving library records.
 *
 * @author karunaChouhan
 * @version 1.0
 */
public class LibraryModel extends BaseModel<LibraryBean> {

	private static final Logger log = Logger.getLogger(LibraryModel.class);

	/**
	 * Adds a new library record to the database.
	 *
	 * @param bean LibraryBean object containing library details
	 * @return Primary key of the newly inserted record
	 * @throws ApplicationException     if a database operation fails
	 * @throws DuplicateRecordException if the library title already exists
	 */
	@Override
	public long add(LibraryBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("LibraryModel add method started");

		Connection conn = null;
		long pk = 0;

		LibraryBean existBean = findByTitle(bean.getTitle());

		if (existBean != null) {
			log.warn("Library title already exists: " + bean.getTitle());
			throw new DuplicateRecordException("title allready exist ");
		}

		try {
			pk = nextPk();

			log.debug("Generated primary key: " + pk);

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing insert query for library");

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getAuthor());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setBoolean(5, bean.isAvailability());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Record inserted successfully. " + i + " row affected");

		} catch (SQLException e) {
			log.error("Exception occurred while inserting library record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing library database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("LibraryModel add method ended");

		return pk;
	}

	/**
	 * Updates an existing library record in the database.
	 *
	 * @param bean LibraryBean object containing updated library details
	 * @throws ApplicationException     if a database operation fails
	 * @throws DuplicateRecordException if the library title already exists
	 */
	@Override
	public void update(LibraryBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("LibraryModel update method started");

		Connection conn = null;

		LibraryBean existBean = findByTitle(bean.getTitle());

		if (existBean != null && existBean.getId() != bean.getId()) {
			log.warn("Library title already exists: " + bean.getTitle());
			throw new DuplicateRecordException("title allready exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			log.debug("Preparing update query for library");

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set title = ?, author = ?, price = ?, availability = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getAuthor());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setBoolean(4, bean.isAvailability());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			log.info("Record updated successfully. " + i + " row affected");

		} catch (SQLException e) {
			log.error("Exception occurred while updating library record", e);
			JDBCDataSource.trnRollBack(conn);
		} finally {
			log.debug("Closing library database connection");
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("LibraryModel update method ended");
	}

	/**
	 * Builds the WHERE clause dynamically based on the provided library details.
	 *
	 * @param bean LibraryBean object containing search criteria
	 * @return Generated SQL WHERE clause conditions
	 */
	@Override
	public String getWhereClause(LibraryBean bean) {

		log.debug("LibraryModel getWhereClause method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getTitle() != null && bean.getTitle().length() > 0) {
				sql.append(" and title like '" + bean.getTitle() + "%'");
			}
			if (bean.getAuthor() != null && bean.getAuthor().length() > 0) {
				sql.append(" and author like '" + bean.getAuthor() + "%'");
			}
			if (bean.getPrice() > 0) {
				sql.append(" and price = " + bean.getPrice());
			}
			if (bean.isAvailability()) {
				sql.append(" and availability = " + bean.isAvailability());
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
		log.debug("LibraryModel getWhereClause method ended");

		return sql.toString();
	}

	/**
	 * Returns the database table name for library records.
	 *
	 * @return Name of the library table
	 */
	@Override
	public String getTable() {
		log.debug("LibraryModel getTable method called");
		return "st_library";
	}

	/**
	 * Creates and returns a new LibraryBean object.
	 *
	 * @return New LibraryBean instance
	 */
	@Override
	public LibraryBean getBean() {
		log.debug("LibraryModel getBean method called");
		return new LibraryBean();
	}

	/**
	 * Finds a library record using its title.
	 *
	 * @param title Title of the library record
	 * @return LibraryBean object if found, otherwise null
	 */
	public LibraryBean findByTitle(String title) {

		log.debug("LibraryModel findByTitle method started");

		LibraryBean bean = findByUniqueColumn("title", title);

		if (bean != null) {
			log.info("Library record found: " + title);
		} else {
			log.warn("Library record not found: " + title);
		}

		log.debug("LibraryModel findByTitle method ended");

		return bean;
	}
}
