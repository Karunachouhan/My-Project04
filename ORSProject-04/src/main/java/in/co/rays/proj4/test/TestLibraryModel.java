package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.LibraryBean;
import in.co.rays.proj4.model.LibraryModel;

public class TestLibraryModel {

	public static LibraryModel model = new LibraryModel();

	public static void main(String[] args) {

		//testAdd();
		testUpdate();
		// testDelete();
		//testSearch();
	    //testFindByPk();

	}

	private static void testFindByPk() {
	LibraryBean bean = new LibraryBean();
	bean = model.findByPk(1);
	
	System.out.println(bean.getId());
	System.out.println(bean.getTitle());
	System.out.println(bean.getAuthor());
	System.out.println(bean.getPrice());
	System.out.println(bean.isAvailability());
	System.out.println(bean.getCreatedBy());
	System.out.println(bean.getModifiedBy());
	System.out.println(bean.getCreatedDatetime());
	System.out.println(bean.getModifiedDatetime());

	System.out.println("-----------------------------------");
		
	}

	private static void testSearch() {
		LibraryBean bean = new LibraryBean();
		List<LibraryBean> list = model.search(bean, 1, 5);
		Iterator<LibraryBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getAuthor());
			System.out.println(bean.getPrice());
			System.out.println(bean.isAvailability());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

			System.out.println("-----------------------------------");
		}

	}

	private static void testDelete() {
		model.delete(2);
	}

	private static void testUpdate() {
		LibraryBean bean = new LibraryBean();

		bean.setTitle("Java");
		bean.setAuthor("Jon duckett");
		bean.setPrice(550);
		bean.setAvailability(true);
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(3);

		model.update(bean);

	}

	private static void testAdd() {
		LibraryBean bean = new LibraryBean();

		bean.setId(1);
		bean.setTitle("Java");
		bean.setAuthor("Mark lutz");
		bean.setPrice(350);
		bean.setAvailability(true);
		bean.setCreatedBy("karuna");
		bean.setModifiedBy("karuna");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
	}
}
