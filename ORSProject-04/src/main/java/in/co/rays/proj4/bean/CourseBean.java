package in.co.rays.proj4.bean;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

/**
 * CourseBean is a Bean class that stores course-related information.
 * 
 * It extends BaseBean to inherit common properties such as id, createdBy,
 * modifiedBy, createdDatetime, and modifiedDatetime.
 * 
 * It provides methods to get and set course details.
 * 
 * @author karunaChouhan
 */
public class CourseBean extends BaseBean {

	private static Logger log = Logger.getLogger(CourseBean.class);

	private String name;
	private String description;
	private String duration;

	/**
	 * Returns the name of the course.
	 * 
	 * @return course name
	 */
	public String getName() {
		log.debug("CourseBean.getName() started");
		log.debug("Course name = " + name);
		return name;
	}

	/**
	 * Sets the name of the course.
	 * 
	 * @param name course name
	 */
	public void setName(String name) {
		log.debug("CourseBean.setName() started");
		this.name = name;
		log.info("Course name set successfully");
	}

	/**
	 * Returns the description of the course.
	 * 
	 * @return course description
	 */
	public String getDescription() {
		log.debug("CourseBean.getDescription() started");
		return description;
	}

	/**
	 * Sets the description of the course.
	 * 
	 * @param description course description
	 */
	public void setDescription(String description) {
		log.debug("CourseBean.setDescription() started");
		this.description = description;
		log.info("Course description set successfully");
	}

	/**
	 * Returns the duration of the course.
	 * 
	 * @return course duration
	 */
	public String getDuration() {
		log.debug("CourseBean.getDuration() started");
		return duration;
	}

	/**
	 * Sets the duration of the course.
	 * 
	 * @param duration course duration
	 */
	public void setDuration(String duration) {
		log.debug("CourseBean.setDuration() started");
		this.duration = duration;
		log.info("Course duration set successfully");
	}

	/**
	 * Returns the course name as the display value.
	 * 
	 * @return course name
	 */
	@Override
	public String getValue() {
		log.debug("CourseBean.getValue() started");
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * Populates the CourseBean properties using data from ResultSet. It also calls
	 * the parent class method to set common properties.
	 * 
	 * @param rs ResultSet containing course details
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("CourseBean.setResultSet() started");

		if (rs == null) {
			log.warn("ResultSet is null in CourseBean");
		}

		try {

			setName(rs.getString("name"));
			setDescription(rs.getString("description"));
			setDuration(rs.getString("duration"));

			log.info("CourseBean populated successfully");

		} catch (Exception e) {

			log.error("Error while populating CourseBean", e);
			e.printStackTrace();

		}

		super.setResultSet(rs);

		log.debug("CourseBean.setResultSet() completed");
	}

}