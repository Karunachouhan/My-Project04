package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

/**
 * BaseBean is an abstract parent class that contains common properties and
 * methods shared by all Bean classes.
 * 
 * It implements DropdownListBean to provide a key for dropdown lists. It also
 * provides common audit fields such as createdBy, modifiedBy, createdDatetime,
 * and modifiedDatetime.
 * 
 * @author karunaChouhan
 */
public abstract class BaseBean implements DropdownListBean {

	private static Logger log = Logger.getLogger(BaseBean.class);
	
	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createdDatetime;
	protected Timestamp modifiedDatetime;

	/**
	 * Returns the ID of the bean.
	 * 
	 * @return id
	 */
	public long getId() {
		log.debug("BaseBean.getId() started");
		log.debug("ID = " + id);
		return id;
	}

	/**
	 * Sets the ID of the bean.
	 * 
	 * @param id the ID to set
	 */
	public void setId(long id) {
		log.debug("BaseBean.setId() started");
		this.id = id;
		log.info("ID set successfully: " + id);
	}

	public String getCreatedBy() {
		log.debug("BaseBean.getCreatedBy() started");
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		log.debug("BaseBean.setCreatedBy() started");
		this.createdBy = createdBy;
		log.info("CreatedBy set successfully");
	}

	public String getModifiedBy() {
		log.debug("BaseBean.getModifiedBy() started");
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		log.debug("BaseBean.setModifiedBy() started");
		this.modifiedBy = modifiedBy;
		log.info("ModifiedBy set successfully");
	}

	public Timestamp getCreatedDatetime() {
		log.debug("BaseBean.getCreatedDatetime() started");
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		log.debug("BaseBean.setCreatedDatetime() started");
		this.createdDatetime = createdDatetime;
		log.info("CreatedDatetime set successfully");
	}

	public Timestamp getModifiedDatetime() {
		log.debug("BaseBean.getModifiedDatetime() started");
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		log.debug("BaseBean.setModifiedDatetime() started");
		this.modifiedDatetime = modifiedDatetime;
		log.info("ModifiedDatetime set successfully");
	}

	@Override
	public String getKey() {
		log.debug("BaseBean.getKey() started");
		log.info("Dropdown key generated successfully");
		return id + "";
	}

	public void setResultSet(ResultSet rs) {

		log.debug("BaseBean.setResultSet() started");

		try {
			if (rs == null) {
				log.warn("ResultSet is null");
			}

			setId(rs.getLong("id"));
			setCreatedBy(rs.getString("created_by"));
			setModifiedBy(rs.getString("modified_by"));
			setCreatedDatetime(rs.getTimestamp("created_datetime"));
			setModifiedDatetime(rs.getTimestamp("modified_datetime"));

			log.info("BaseBean populated successfully from ResultSet");

		} catch (Exception e) {
			log.error("Error while populating BaseBean from ResultSet", e);
			e.printStackTrace();
		}
	}
}