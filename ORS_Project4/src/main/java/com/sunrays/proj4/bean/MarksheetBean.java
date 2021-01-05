
package com.sunrays.proj4.bean;

/**
 * Marksheet JavaBean encapsulates Marksheet attributes
 * 
 * @author Interpreter
 * @version 1.0
 * 
 */
public class MarksheetBean extends BaseBean {
	/**
	 * Rollno of Student
	 */
	private String rollNo;
	/**
	 * ID of Student
	 */
	private long studentId;
	/**
	 * Name of Student
	 */

	private String name;

	/**
	 * Physics marks of Student
	 */
	private Integer physics;
	/**
	 * Chemistry marks of Student
	 */

	private Integer chemistry;
	/**
	 * Maths marks of Student
	 */

	/**
	 * Maths marks of Student
	 */
	private Integer maths;

	public String getRollNO() {
		return rollNo;
	}

	public void setRollNO(String rollNO) {
		this.rollNo = rollNO;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	
	public String getKey() {

		return id + "";
	}

	
	public String getValue() {

		return rollNo;
	}

}
