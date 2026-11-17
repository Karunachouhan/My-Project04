package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * GymBean is a Bean class that stores gym-related information.
 *
 * It extends BaseBean to inherit common properties such as id,
 * createdBy, modifiedBy, createdDatetime, and modifiedDatetime.
 *
 * It contains gym details such as name, membership type,
 * joining date, and trainer name.
 *
 * @author karunaChouhan
 */
public class GymBean extends BaseBean {

	private String name;
	private String membershipType;
	private String joiningDate;
	private String trainerName;

	/**
	 * Returns the name of the gym.
	 *
	 * @return gym name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the gym.
	 *
	 * @param name gym name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the membership type.
	 *
	 * @return membership type
	 */
	public String getMembershipType() {
		return membershipType;
	}

	/**
	 * Sets the membership type.
	 *
	 * @param membershipType type of gym membership
	 */
	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	/**
	 * Returns the joining date of the gym member.
	 *
	 * @return joining date
	 */
	public String getJoiningDate() {
		return joiningDate;
	}

	/**
	 * Sets the joining date of the gym member.
	 *
	 * @param joiningDate gym joining date
	 */
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	/**
	 * Returns the name of the trainer.
	 *
	 * @return trainer name
	 */
	public String getTrainerName() {
		return trainerName;
	}

	/**
	 * Sets the name of the trainer.
	 *
	 * @param trainerName name of the trainer
	 */
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	/**
	 * Returns the display value of the gym.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Populates the GymBean properties using data from ResultSet.
	 * It also calls the parent class method to set common properties.
	 *
	 * @param rs ResultSet containing gym details
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		try {
			setName(rs.getString("name"));
			setMembershipType(rs.getString("membership_type"));
			setJoiningDate(rs.getString("joining_date"));
			setTrainerName(rs.getString("trainer_name"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}
}