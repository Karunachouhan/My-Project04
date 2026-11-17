package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 * SubjectBean class represents subject information.
 *
 * It stores subject name, description, and associated course ID.
 *
 * @author karunaChouhan
 */
public class SubjectBean extends BaseBean {

	private static Logger log = Logger.getLogger(SubjectBean.class);

	/** Name of the subject. */
	private String name;

	/** Description of the subject. */
	private String description;

	/** ID of the course associated with the subject. */
	private long courseId;

	/**
	 * Returns the subject name.
	 *
	 * @return subject name
	 */
	public String getName() {
		log.debug("SubjectBean.getName()");
		return name;
	}

	/**
	 * Sets the subject name.
	 *
	 * @param name subject name to set
	 */
	public void setName(String name) {
		log.debug("SubjectBean.setName()");
		this.name = name;
		log.info("Subject name set successfully");
	}

	/**
	 * Returns the subject description.
	 *
	 * @return subject description
	 */
	public String getDescription() {
		log.debug("SubjectBean.getDescription()");
		return description;
	}

	/**
	 * Sets the subject description.
	 *
	 * @param description subject description to set
	 */
	public void setDescription(String description) {
		log.debug("SubjectBean.setDescription()");
		this.description = description;
		log.info("Subject description set successfully");
	}

	/**
	 * Returns the course ID.
	 *
	 * @return course ID
	 */
	public long getCourseId() {
		log.debug("SubjectBean.getCourseId()");
		return courseId;
	}

	/**
	 * Sets the course ID.
	 *
	 * @param courseId course ID to set
	 */
	public void setCourseId(long courseId) {
		log.debug("SubjectBean.setCourseId()");
		this.courseId = courseId;
		log.info("Course ID set successfully");
	}

	/**
	 * Returns the subject value for dropdown usage.
	 *
	 * @return subject value
	 */
	@Override
	public String getValue() {
		log.debug("SubjectBean.getValue()");
		return null;
	}

	/**
	 * Populates subject details from the ResultSet.
	 *
	 * @param rs ResultSet containing subject records
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		log.debug("SubjectBean.setResultSet() started");

		try {
			setName(rs.getString("name"));
			setDescription(rs.getString("description"));
			setCourseId(rs.getLong("course_id"));

			log.info("SubjectBean populated successfully");

		} catch (Exception e) {
			log.error("Error while populating SubjectBean", e);
			e.printStackTrace();
		}

		super.setResultSet(rs);
		log.debug("SubjectBean.setResultSet() completed");
	}

}