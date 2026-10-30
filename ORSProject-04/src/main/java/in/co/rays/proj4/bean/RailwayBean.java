package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class RailwayBean extends BaseBean {
	private String passengerName;
	private int trainNo;
	private int seatNo;
	private String journeyDate;

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResultSet(ResultSet rs) {
		try {
			setPassengerName(rs.getString("passenger_name"));
			setTrainNo(rs.getInt("train_no"));
			setSeatNo(rs.getInt("seat_no"));
			setJourneyDate(rs.getString("journey_date"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}
