package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 * RoleBean class represents the role information of a user. It defines
 * different user roles for authorization.
 *
 * @author karunaChouhan
 */
public class RoleBean extends BaseBean {

	private static Logger log = Logger.getLogger(RoleBean.class);

	/**
	 * Constant representing the Admin role.
	 */
	public static final int ADMIN = 1;

	/**
	 * Constant representing the Student role.
	 */
	public static final int STUDENT = 2;

	/**
	 * Constant representing the Faculty role.
	 */
	public static final int FACULTY = 3;

	/**
	 * Constant representing the College role.
	 */
	public static final int COLLEGE = 4;

	/**
	 * Constant representing the Kiosk role.
	 */
	public static final int KIOSK = 5;

	/** Role name. */
	private String name;

	/** Description of the role. */
	private String description;

	/**
	 * Returns the role name.
	 *
	 * @return name of the role
	 */
	public String getName() {
		log.debug("RoleBean.getName()");
		return name;
	}

	/**
	 * Sets the role name.
	 *
	 * @param name role name to set
	 */
	public void setName(String name) {
		log.debug("RoleBean.setName()");
		this.name = name;
		log.info("Role name set successfully");
	}

	/**
	 * Returns the role description.
	 *
	 * @return description of the role
	 */
	public String getDescription() {
		log.debug("RoleBean.getDescription()");
		return description;
	}

	/**
	 * Sets the role description.
	 *
	 * @param description role description to set
	 */
	public void setDescription(String description) {
		log.debug("RoleBean.setDescription()");
		this.description = description;
		log.info("Role description set successfully");
	}

	/**
	 * Returns the role name as the dropdown value.
	 *
	 * @return role name
	 */
	@Override
	public String getValue() {
		log.debug("RoleBean.getValue()");
		return name;
	}

	/**
	 * Populates role details from the ResultSet.
	 *
	 * @param rs ResultSet containing role records
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		log.debug("RoleBean.setResultSet() started");

		try {
			setName(rs.getString("name"));
			setDescription(rs.getString("description"));

			log.info("RoleBean populated successfully");

		} catch (Exception e) {
			log.error("Error while populating RoleBean", e);
			e.printStackTrace();
		}

		super.setResultSet(rs);
		log.debug("RoleBean.setResultSet() completed");
	}

}