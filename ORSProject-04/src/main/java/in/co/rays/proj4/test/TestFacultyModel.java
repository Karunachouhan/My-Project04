package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {
	public static FacultyModel model = new FacultyModel();

	public static void main(String[] args) throws Exception {

		//testAdd();
		testUpdate();
		// testFindByPk();
		//testSearch();
		//testDelete();
	}

	private static void testDelete() {
	 model.delete(3);
		
	}

	private static void testSearch() {
		FacultyBean bean = new FacultyBean();
        //bean.setCollegeId(2);
		bean.setFirstName("Aman");
		List<FacultyBean> list = model.search(bean, 1, 2);

		Iterator<FacultyBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println("Id- " + bean.getId());
			System.out.println("College id- " + bean.getCollegeId());
			System.out.println("College name- " + bean.getCollegeName());
			System.out.println("First name- " + bean.getFirstName());
			System.out.println("Last name- " + bean.getLastName());
			System.out.println("Email- " + bean.getEmail());
			System.out.println("Mobile- " + bean.getMobileNo());
			System.out.println("Address- " + bean.getAddress());
			System.out.println("Gender- " + bean.getGender());
			System.out.println("Dob- " + bean.getDateOfBirth());
			System.out.println("Created by- " + bean.getCreatedBy());
			System.out.println("Modified by- " + bean.getModifiedDatetime());
			System.out.println("--------------------------------------------------");
		}

	}

	private static void testFindByPk() {
		FacultyBean bean = model.findByPk(2);

		System.out.println("Id- " + bean.getId());
		System.out.println("College id- " + bean.getCollegeId());
		System.out.println("College name- " + bean.getCollegeName());
		System.out.println("First name- " + bean.getFirstName());
		System.out.println("Last name- " + bean.getLastName());
		System.out.println("Email- " + bean.getEmail());
		System.out.println("Mobile- " + bean.getMobileNo());
		System.out.println("Address- " + bean.getAddress());
		System.out.println("Gender- " + bean.getGender());
		System.out.println("Dob- " + bean.getDateOfBirth());
		System.out.println("Created by- " + bean.getCreatedBy());
		System.out.println("Modified by- " + bean.getModifiedDatetime());

	}

	private static void testUpdate() throws ParseException {
		FacultyBean bean = new FacultyBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// bean.setId(1);
		bean.setCollegeId(2);
		bean.setCollegeName("IPS Academy");
		bean.setFirstName("Aman");
		bean.setLastName("Meena");
		bean.setEmail("Aman@email.com");
		bean.setMobileNo("9856754321");
		bean.setAddress("Dewas");
		bean.setGender("Female");
		bean.setDateOfBirth(sdf.parse("2000-06-26"));
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);
		model.update(bean);

	}

	private static void testAdd() throws ParseException {
		FacultyBean bean = new FacultyBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setCollegeId(1);
		bean.setCollegeName("Chameli Devi College");
		bean.setFirstName("Meera");
		bean.setLastName("Singh");
		bean.setEmail("Meera@email.com");
		bean.setMobileNo("9876675435");
		bean.setAddress("Indore");
		bean.setGender("Male");
		bean.setDateOfBirth(sdf.parse("2002-01-16"));
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		// bean.setId(1);
		model.add(bean);
	}
}
