package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 * RailwayBean is a Bean class that stores railway reservation-related
 * information.
 *
 * It extends BaseBean to inherit common properties such as id, createdBy,
 * modifiedBy, createdDatetime, and modifiedDatetime.
 *
 * It contains passenger details such as passenger name, train number, seat
 * number, and journey date.
 *
 * @author karunaChouhan
 */
public class RailwayBean extends BaseBean {

	private static Logger log = Logger.getLogger(RailwayBean.class);

	private String passengerName;

	private int trainNo;

	private int seatNo;

	private String journeyDate;

	/**
	 * Returns the name of the passenger.
	 *
	 * @return passenger name
	 */
	public String getPassengerName() {
		log.debug("RailwayBean.getPassengerName()");
		return passengerName;
	}

	/**
	 * Sets the name of the passenger.
	 *
	 * @param passengerName passenger name
	 */
	public void setPassengerName(String passengerName) {
		log.debug("RailwayBean.setPassengerName()");
		this.passengerName = passengerName;
		log.info("Passenger name set successfully");
	}

	/**
	 * Returns the train number.
	 *
	 * @return train number
	 */
	public int getTrainNo() {
		log.debug("RailwayBean.getTrainNo()");
		return trainNo;
	}

	/**
	 * Sets the train number.
	 *
	 * @param trainNo train number
	 */
	public void setTrainNo(int trainNo) {
		log.debug("RailwayBean.setTrainNo()");
		this.trainNo = trainNo;
		log.info("Train number set successfully");
	}

	/**
	 * Returns the seat number.
	 *
	 * @return seat number
	 */
	public int getSeatNo() {
		log.debug("RailwayBean.getSeatNo()");
		return seatNo;
	}

	/**
	 * Sets the seat number.
	 *
	 * @param seatNo seat number
	 */
	public void setSeatNo(int seatNo) {
		log.debug("RailwayBean.setSeatNo()");
		this.seatNo = seatNo;
		log.info("Seat number set successfully");
	}

	/**
	 * Returns the journey date.
	 *
	 * @return journey date
	 */
	public String getJourneyDate() {
		log.debug("RailwayBean.getJourneyDate()");
		return journeyDate;
	}

	/**
	 * Sets the journey date.
	 *
	 * @param journeyDate journey date
	 */
	public void setJourneyDate(String journeyDate) {
		log.debug("RailwayBean.setJourneyDate()");
		this.journeyDate = journeyDate;
		log.info("Journey date set successfully");
	}

	/**
	 * Returns the display value of the railway bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		log.debug("RailwayBean.getValue()");
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Populates the RailwayBean properties using data from ResultSet. It also calls
	 * the parent class method to set common properties.
	 *
	 * @param rs ResultSet containing railway reservation details
	 */
	@Override
	public void setResultSet(ResultSet rs) {
		log.debug("RailwayBean.setResultSet() started");

		try {
			setPassengerName(rs.getString("passenger_name"));
			setTrainNo(rs.getInt("train_no"));
			setSeatNo(rs.getInt("seat_no"));
			setJourneyDate(rs.getString("journey_date"));

			log.info("RailwayBean populated successfully");

		} catch (Exception e) {
			log.error("Error while populating RailwayBean", e);
			e.printStackTrace();
		}

		super.setResultSet(rs);
		log.debug("RailwayBean.setResultSet() completed");
	}

}