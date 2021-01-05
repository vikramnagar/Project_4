
package com.sunrays.proj4.bean;

/**
 * Role JavaBean encapsulates Role attributes
 * 
 * @author Interpreter
 * @version 1.0
 * 
 */
public class RoleBean extends BaseBean {

	public static final int ADMIN = 1;

	public static final int STUDENT = 2;

	public static final int COLLAGE_SCHOOL = 3;

	public static final int KIOSK = 4;
	
	public static final int FACULTY=5;
	/**
	 * Name of Role
	 */
	private String name;
	/**
	 * Description of Role
	 */

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getKey() {

		return id + "";
	}

	

	public String getValue() {

		return name;
	}

}
