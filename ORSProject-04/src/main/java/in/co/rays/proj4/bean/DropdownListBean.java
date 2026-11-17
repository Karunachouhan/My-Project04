package in.co.rays.proj4.bean;

/**
 * DropdownListBean is an interface that provides common methods
 * to represent data in an HTML dropdown list.
 * 
 * Implementing classes must provide a key and a display value
 * for each dropdown item.
 * 
 * @author karunaChouhan
 */
public interface DropdownListBean {

	/**
	 * Returns the unique key of the dropdown item.
	 * 
	 * @return key of the dropdown item
	 */
	public String getKey();

	/**
	 * Returns the display value of the dropdown item.
	 * 
	 * @return value to be displayed in the dropdown list
	 */
	public String getValue();

}