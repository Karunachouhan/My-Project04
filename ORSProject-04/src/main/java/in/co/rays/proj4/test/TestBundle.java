package in.co.rays.proj4.test;

import in.co.rays.proj4.util.MessageSource;

public class TestBundle {
     
	public static void main(String[] args) {
		
		MessageSource ms = MessageSource.getInstance();
		ms.setLocale("hi");
		
		System.out.println(ms.get("login.title"));
	}
}
