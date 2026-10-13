package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {

	public static CourseModel model = new CourseModel();

	public static void main(String[] args) {

		// testAdd();
		 testUpdate();
		// testDelete();
		// testFindByPk();
		//testSearch();

	}

	private static void testSearch() {
		CourseBean bean = new CourseBean();
		
		bean.setName("BCA");
		List<CourseBean> list = model.search(bean, 1, 4);

		Iterator<CourseBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();

			System.out.println("Course id- " + bean.getId());
			System.out.println("Course Name- " + bean.getName());
			System.out.println("Course Description- " + bean.getDescription());
			System.out.println("Course Duration- " + bean.getDuration());
			System.out.println("Course createdby- " + bean.getCreatedBy());
			System.out.println("Course modifiedby- " + bean.getModifiedBy());
			System.out.println("Course created datetime- " + bean.getCreatedDatetime());
			System.out.println("Course modified datetime- " + bean.getModifiedDatetime());

			System.out.println("-------------------------------------------------------------------------");
		}
	}

	private static void testFindByPk() {
		CourseBean bean = model.findByPk(3);

		System.out.println("Course id- " + bean.getId());
		System.out.println("Course Name- " + bean.getName());
		System.out.println("Course Description- " + bean.getDescription());
		System.out.println("Course Duration- " + bean.getDuration());
		System.out.println("Course createdby- " + bean.getCreatedBy());
		System.out.println("Course modifiedby- " + bean.getModifiedBy());
		System.out.println("Course created datetime- " + bean.getCreatedDatetime());
		System.out.println("Course modified datetime- " + bean.getModifiedDatetime());
	}

	private static void testDelete() {
		model.delete(8);

	}

	private static void testUpdate() {
		CourseBean bean = new CourseBean();

		bean.setName("MTech");
		bean.setDescription("Masters in engineering");
		bean.setDuration("2 years");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(3);

		model.update(bean);

	}

	private static void testAdd() {

		CourseBean bean = new CourseBean();

		// bean.setId(1);
		bean.setName("Btech");
		bean.setDescription("Engineering");
		bean.setDuration("5 years");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
