package in.co.rays.proj4.bean;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

/**
 * HospitalBean is a Bean class that stores hospital-related information.
 *
 * It extends BaseBean to inherit common properties and methods.
 *
 * It contains patient details such as ID, name, age, blood group, and disease.
 *
 * @author karunaChouhan
 */
public class HospitalBean extends BaseBean {

	private static Logger log = Logger.getLogger(HospitalBean.class);

	private long id;

	private String name;

	private int age;

	private String bloodGroup;

	private String disease;

	/**
	 * Returns the patient ID.
	 *
	 * @return patient ID
	 */
	public long getId() {
		log.debug("HospitalBean.getId() started");
		return id;
	}

	/**
	 * Sets the patient ID.
	 *
	 * @param id patient ID
	 */
	public void setId(long id) {
		log.debug("HospitalBean.setId() started");
		this.id = id;
		log.info("Patient ID set successfully");
	}

	/**
	 * Returns the name of the patient.
	 *
	 * @return patient name
	 */
	public String getName() {
		log.debug("HospitalBean.getName() started");
		return name;
	}

	/**
	 * Sets the name of the patient.
	 *
	 * @param name patient name
	 */
	public void setName(String name) {
		log.debug("HospitalBean.setName() started");
		this.name = name;
		log.info("Patient name set successfully");
	}

	/**
	 * Returns the age of the patient.
	 *
	 * @return patient age
	 */
	public int getAge() {
		log.debug("HospitalBean.getAge() started");
		return age;
	}

	/**
	 * Sets the age of the patient.
	 *
	 * @param age patient age
	 */
	public void setAge(int age) {
		log.debug("HospitalBean.setAge() started");
		this.age = age;
		log.info("Patient age set successfully");
	}

	/**
	 * Returns the blood group of the patient.
	 *
	 * @return patient blood group
	 */
	public String getBloodGroup() {
		log.debug("HospitalBean.getBloodGroup() started");
		return bloodGroup;
	}

	/**
	 * Sets the blood group of the patient.
	 *
	 * @param bloodGroup patient blood group
	 */
	public void setBloodGroup(String bloodGroup) {
		log.debug("HospitalBean.setBloodGroup() started");
		this.bloodGroup = bloodGroup;
		log.info("Patient blood group set successfully");
	}

	/**
	 * Returns the disease of the patient.
	 *
	 * @return patient disease
	 */
	public String getDisease() {
		log.debug("HospitalBean.getDisease() started");
		return disease;
	}

	/**
	 * Sets the disease of the patient.
	 *
	 * @param disease patient disease
	 */
	public void setDisease(String disease) {
		log.debug("HospitalBean.setDisease() started");
		this.disease = disease;
		log.info("Patient disease set successfully");
	}

	/**
	 * Populates the HospitalBean properties using data from ResultSet. It also
	 * calls the parent class method to set common properties.
	 *
	 * @param rs ResultSet containing patient details
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("HospitalBean.setResultSet() started");

		if (rs == null) {
			log.warn("ResultSet is null in HospitalBean");
		}

		try {

			setName(rs.getString("name"));

			setAge(rs.getInt("age"));

			setBloodGroup(rs.getString("blood_group"));

			setDisease(rs.getString("disease"));

			log.info("HospitalBean populated successfully");

		} catch (Exception e) {

			log.error("Error while populating HospitalBean", e);
			e.printStackTrace();

		}

		super.setResultSet(rs);

		log.debug("HospitalBean.setResultSet() completed");
	}

	/**
	 * Returns the display value of the hospital bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		log.debug("HospitalBean.getValue() started");
		// TODO Auto-generated method stub
		return null;
	}

}