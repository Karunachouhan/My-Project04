package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class HospitalBean extends BaseBean{

	private long id;
	private String name;
	private int age;
	private String bloodGroup;
	private String disease;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	@Override
	public void setResultSet(ResultSet rs) {
		try {
			setName(rs.getString("name"));
			setAge(rs.getInt("age"));
			setBloodGroup(rs.getString("blood_group"));
			setDisease(rs.getString("disease"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
