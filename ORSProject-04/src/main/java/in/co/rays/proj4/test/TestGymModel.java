package in.co.rays.proj4.test;

import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.GymBean;
import in.co.rays.proj4.model.GymModel;

public class TestGymModel {

	public static GymModel model = new GymModel();

	public static void main(String[] args) {

		//testAdd();
		//testUpdate();
		//testSearch();
		//testDelete();
		//testFindByPk();
	}

	private static void testFindByPk() {
    GymBean bean = new GymBean();
    
    bean = model.findByPk(2);
    System.out.println(bean.getId());
    System.out.println(bean.getName());
    System.out.println(bean.getMembershipType());
    System.out.println(bean.getJoiningDate());
    System.out.println(bean.getTrainerName());
		
	}

	private static void testDelete() {
	model.delete(5);
		
	}

	private static void testSearch() {
		GymBean bean = new GymBean();

		List<GymBean> list = model.search(bean, 1, 5);
		Iterator<GymBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println("id = " + bean.getId());
			System.out.println("name = " + bean.getName());
			System.out.println("membership type = " + bean.getMembershipType());
			System.out.println("joining date = " + bean.getJoiningDate());
			System.out.println("trainer name = " + bean.getTrainerName());
			System.out.println("----------------------------------------------------");
		}

	}

	private static void testUpdate() {
		GymBean bean = new GymBean();

		bean.setName("Reena Mehta");
		bean.setMembershipType("Yearly");
		bean.setJoiningDate("2026-03-17");
		bean.setTrainerName("Hemant Singh");
		bean.setId(5);

		model.update(bean);

	}

	private static void testAdd() {
		GymBean bean = new GymBean();

		bean.setName("Reena Mehta");
		bean.setMembershipType("Monthly");
		bean.setJoiningDate("2026-03-27");
		bean.setTrainerName("Pankaj Yadav");

		model.add(bean);
	}

}
