package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;

public class TestMarksheetModel {

	public static MarksheetModel model = new MarksheetModel();

	public static void main(String[] args) {

		 //testAdd();
		testUpdate();
		 //testDelete();
		// testFindByPk();
		//testSearch();

	}

	private static void testSearch() {
		MarksheetBean bean = new MarksheetBean();
		bean.setRollNo("102");

		List<MarksheetBean> list = model.search(bean, 1, 4);
		Iterator<MarksheetBean> it = list.iterator();
		while (it.hasNext()) {
			bean = it.next();
			System.out.println("id- " + bean.getId());
			System.out.println("rollno- " + bean.getRollNo());
			System.out.println("student id- " + bean.getStudentId());
			System.out.println("name- " + bean.getName());
			System.out.println("physics- " + bean.getPhysics());
			System.out.println("chemistry- " + bean.getChemistry());
			System.out.println("maths- " + bean.getMaths());
			System.out.println("created by- " + bean.getCreatedBy());
			System.out.println("modified by- " + bean.getModifiedBy());
			System.out.println("created datetime- " + bean.getCreatedDatetime());
			System.out.println("modified datetime- " + bean.getModifiedDatetime());
			System.out.println("------------------------------------------------------------");

		}

	}

	private static void testFindByPk() {
		MarksheetBean bean = model.findByPk(2);

		System.out.println("id- " + bean.getId());
		System.out.println("rollno- " + bean.getRollNo());
		System.out.println("student id- " + bean.getStudentId());
		System.out.println("name- " + bean.getName());
		System.out.println("physics- " + bean.getPhysics());
		System.out.println("chemistry- " + bean.getChemistry());
		System.out.println("maths- " + bean.getMaths());
		System.out.println("created by- " + bean.getCreatedBy());
		System.out.println("modified by- " + bean.getModifiedBy());
		System.out.println("created datetime- " + bean.getCreatedDatetime());
		System.out.println("modified datetime- " + bean.getModifiedDatetime());

	}

	private static void testDelete() {
		model.delete(6);
	}

	private static void testUpdate() {
		MarksheetBean bean = new MarksheetBean();

		bean.setId(1);
		bean.setRollNo("103");
		bean.setStudentId(4);
		bean.setName("Ram Sharma");
		bean.setPhysics(78);
		bean.setChemistry(97);
		bean.setMaths(80);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	private static void testAdd() {
		MarksheetBean bean = new MarksheetBean();

		// bean.setId(1);
		bean.setRollNo("103");
		bean.setStudentId(4);
		bean.setName("Aaradhiya Chouhan");
		bean.setPhysics(78);
		bean.setChemistry(97);
		bean.setMaths(80);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
