package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 * MarksheetBean is a Bean class that stores student marksheet information.
 *
 * It extends BaseBean to inherit common properties such as id, createdBy,
 * modifiedBy, createdDatetime, and modifiedDatetime.
 *
 * It contains student details such as roll number, student ID, name, and marks
 * in Physics, Chemistry, and Maths.
 *
 * @author karunaChouhan
 */
public class MarksheetBean extends BaseBean {

	private static Logger log = Logger.getLogger(MarksheetBean.class);

	private String rollNo;
	private long studentId;
	private String name;
	private int physics;
	private int chemistry;
	private int maths;

	/**
	 * Returns the roll number of the student.
	 *
	 * @return student roll number
	 */
	public String getRollNo() {
		log.debug("MarksheetBean.getRollNo()");
		return rollNo;
	}

	/**
	 * Sets the roll number of the student.
	 *
	 * @param rollNo student roll number
	 */
	public void setRollNo(String rollNo) {
		log.debug("MarksheetBean.setRollNo()");
		this.rollNo = rollNo;
		log.info("Student roll number set successfully");
	}

	/**
	 * Returns the student ID.
	 *
	 * @return student ID
	 */
	public long getStudentId() {
		log.debug("MarksheetBean.getStudentId()");
		return studentId;
	}

	/**
	 * Sets the student ID.
	 *
	 * @param studentId student ID
	 */
	public void setStudentId(long studentId) {
		log.debug("MarksheetBean.setStudentId()");
		this.studentId = studentId;
		log.info("Student ID set successfully");
	}

	/**
	 * Returns the name of the student.
	 *
	 * @return student name
	 */
	public String getName() {
		log.debug("MarksheetBean.getName()");
		return name;
	}

	/**
	 * Sets the name of the student.
	 *
	 * @param name student name
	 */
	public void setName(String name) {
		log.debug("MarksheetBean.setName()");
		this.name = name;
		log.info("Student name set successfully");
	}

	/**
	 * Returns the marks obtained in Physics.
	 *
	 * @return Physics marks
	 */
	public int getPhysics() {
		log.debug("MarksheetBean.getPhysics()");
		return physics;
	}

	/**
	 * Sets the marks obtained in Physics.
	 *
	 * @param physics Physics marks
	 */
	public void setPhysics(int physics) {
		log.debug("MarksheetBean.setPhysics()");
		this.physics = physics;
		log.info("Physics marks set successfully");
	}

	/**
	 * Returns the marks obtained in Chemistry.
	 *
	 * @return Chemistry marks
	 */
	public int getChemistry() {
		log.debug("MarksheetBean.getChemistry()");
		return chemistry;
	}

	/**
	 * Sets the marks obtained in Chemistry.
	 *
	 * @param chemistry Chemistry marks
	 */
	public void setChemistry(int chemistry) {
		log.debug("MarksheetBean.setChemistry()");
		this.chemistry = chemistry;
		log.info("Chemistry marks set successfully");
	}

	/**
	 * Returns the marks obtained in Maths.
	 *
	 * @return Maths marks
	 */
	public int getMaths() {
		log.debug("MarksheetBean.getMaths()");
		return maths;
	}

	/**
	 * Sets the marks obtained in Maths.
	 *
	 * @param maths Maths marks
	 */
	public void setMaths(int maths) {
		log.debug("MarksheetBean.setMaths()");
		this.maths = maths;
		log.info("Maths marks set successfully");
	}

	/**
	 * Returns the student name as the display value.
	 *
	 * @return student name
	 */
	@Override
	public String getValue() {
		log.debug("MarksheetBean.getValue()");
		return name;
	}

	/**
	 * Populates the MarksheetBean properties using data from ResultSet. It also
	 * calls the parent class method to set common properties.
	 *
	 * @param rs ResultSet containing marksheet details
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		log.debug("MarksheetBean.setResultSet() started");

		try {
			setRollNo(rs.getString("roll_no"));
			setStudentId(rs.getLong("student_id"));
			setName(rs.getString("name"));
			setPhysics(rs.getInt("physics"));
			setChemistry(rs.getInt("chemistry"));
			setMaths(rs.getInt("maths"));

			log.info("MarksheetBean populated successfully");

		} catch (Exception e) {
			log.error("Error while populating MarksheetBean", e);
			e.printStackTrace();
		}

		super.setResultSet(rs);
		log.debug("MarksheetBean.setResultSet() completed");
	}

}