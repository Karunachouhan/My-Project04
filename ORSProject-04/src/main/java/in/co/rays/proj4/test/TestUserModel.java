package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {

	public static UserModel model = new UserModel();

	public static void main(String[] args) throws Exception {

		//testAdd();
		testUpdate();
		// testFindByPk();
		//testSearch();
		//testDelete();
	}

	private static void testDelete() {
	
	model.delete(5);
		
	}

	private static void testSearch() {
		UserBean bean = new UserBean();
		
		bean.setLastName("Verma");
		List<UserBean> list = model.search(bean, 1, 2);

		Iterator<UserBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println("Id:- " + bean.getId());
			System.out.println("Firstname:- " + bean.getFirstName());
			System.out.println("Lastname:- " + bean.getLastName());
			System.out.println("Login id:- " + bean.getLogin());
			System.out.println("Password:- " + bean.getPassword());
			System.out.println("Dob:- " + bean.getDob());
			System.out.println("Mobile number:- " + bean.getMobileNo());
			System.out.println("Role id:- " + bean.getRoleId());
			System.out.println("Unsuccessfull login:- " + bean.getUnsuccessfullLogin());
			System.out.println("Gender:- " + bean.getGender());
			System.out.println("Last login:- " + bean.getLastLogin());
			System.out.println("User lock:- " + bean.getUserLock());
			System.out.println("Registered ip:- " + bean.getRegisteredIp());
			System.out.println("Last login ip:- " + bean.getLastLoginIp());
			System.out.println("Created by:- " + bean.getCreatedBy());
			System.out.println("Modified by:- " + bean.getModifiedBy());
			System.out.println("Created date time:- " + bean.getCreatedDatetime());
			System.out.println("Modified date time:- " + bean.getModifiedDatetime());
			
			System.out.println("-------------------------------------------------------");
		}

	}

	private static void testFindByPk() {
		UserBean bean = model.findByPk(2);

		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getLogin());
		System.out.println(bean.getPassword());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getRoleId());
		System.out.println(bean.getLastLogin());
		System.out.println(bean.getLastLoginIp());
		System.out.println(bean.getRegisteredIp());
		System.out.println(bean.getUnsuccessfullLogin());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getGender());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
		System.out.println(bean.getUserLock());
		System.out.println("--------------------------------------------------");

	}

	private static void testUpdate() throws ParseException {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setFirstName("Rahul");
		bean.setLastName("Sharma");
		bean.setLogin("rahul.sharma@gmail.com");
		bean.setPassword("rahul@123");
		bean.setDob(sdf.parse("2000-02-17"));
		bean.setMobileNo("9876543251");
		bean.setUnsuccessfullLogin(1);
		bean.setGender("male");
		bean.setLastLogin(new Timestamp(new Date().getTime()));
		bean.setUserLock("N");
		bean.setRegisteredIp("192.168.1.14");
		bean.setLastLoginIp("192.168.1.14");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setRoleId(5);
		bean.setId(5);

		model.update(bean);

	}

	private static void testAdd() throws ParseException {
		UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		// bean.setId(1);
		bean.setFirstName("Nikunj");
		bean.setLastName("Lowanshi");
		bean.setLogin("nikunj@gmail.com");
		bean.setPassword("nikunj@123");
		bean.setDob(sdf.parse("2000-06-07"));
		bean.setMobileNo("9238790874");
		bean.setRoleId(2);
		bean.setUnsuccessfullLogin(3);
		bean.setGender("male");
		bean.setLastLogin(new Timestamp(new Date().getTime()));
		bean.setUserLock("Y");
		bean.setRegisteredIp("192.168.1.15");
		bean.setLastLoginIp("192.168.1.15");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}
}
