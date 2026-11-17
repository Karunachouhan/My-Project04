package in.co.rays.proj4.bean;

import java.sql.ResultSet;

/**
 * ATMBean is a Bean class that stores ATM-related information.
 *
 * It extends BaseBean to inherit common properties such as id,
 * createdBy, modifiedBy, createdDatetime, and modifiedDatetime.
 *
 * It contains ATM details such as location, bank name,
 * available cash, and ATM status.
 *
 * @author karunaChouhan
 */
public class ATMBean extends BaseBean {

	private String location;

	private String bankName;

	private double cashAvailable;

	private String status;

	/**
	 * Returns the location of the ATM.
	 *
	 * @return ATM location
	 */
	public String getLocation() {

		return location;

	}

	/**
	 * Sets the location of the ATM.
	 *
	 * @param location ATM location
	 */
	public void setLocation(String location) {

		this.location = location;

	}

	/**
	 * Returns the name of the bank.
	 *
	 * @return bank name
	 */
	public String getBankName() {

		return bankName;

	}

	/**
	 * Sets the name of the bank.
	 *
	 * @param bankName bank name
	 */
	public void setBankName(String bankName) {

		this.bankName = bankName;

	}

	/**
	 * Returns the available cash in the ATM.
	 *
	 * @return available cash
	 */
	public double getCashAvailable() {

		return cashAvailable;

	}

	/**
	 * Sets the available cash in the ATM.
	 *
	 * @param cashAvailable available cash amount
	 */
	public void setCashAvailable(double cashAvailable) {

		this.cashAvailable = cashAvailable;

	}

	/**
	 * Returns the current status of the ATM.
	 *
	 * @return ATM status
	 */
	public String getStatus() {

		return status;

	}

	/**
	 * Sets the current status of the ATM.
	 *
	 * @param status ATM status
	 */
	public void setStatus(String status) {

		this.status = status;

	}

	/**
	 * Returns the display value of the ATM.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {

		// TODO Auto-generated method stub

		return null;

	}

	/**
	 * Populates the ATMBean properties using data from ResultSet.
	 * It also calls the parent class method to set common properties.
	 *
	 * @param rs ResultSet containing ATM details
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		try {

			setLocation(rs.getString("location"));

			setBankName(rs.getString("bank_name"));

			setCashAvailable(rs.getDouble("cash_available"));

			setStatus(rs.getString("status"));

		} catch (Exception e) {

			e.printStackTrace();

		}

		super.setResultSet(rs);

	}

}