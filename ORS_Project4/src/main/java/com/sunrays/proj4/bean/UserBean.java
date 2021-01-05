package com.sunrays.proj4.bean;

import java.util.Date;
import java.sql.Timestamp;

/**
 * UserBean JavaBean encapsulates User attributes
 * 
 * @author Interpreter
 * @version 1.0
 * 
 */

public class UserBean extends BaseBean {

	public static final String ACTIVE = "Active";

	public static final String INACTIVE = "Inactive";
	/**
	 * FirstName of User
	 */

	private String firstName;
	/**
	 * LastName of User
	 */

	private String lastName;
	/**
	 * Login id of User
	 */
	private String login;
	/**
	 * Password
	 */

	private String password;
	/**
	 * Confirm Password
	 */

	private String confirmPassword;
	/**
	 * Date Of Birth
	 */

	private Date dob;
	/**
	 * Mobile No
	 */
	private String mobileNo;
	/**
	 * Role Id
	 */

	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * No.of UnsucessFul Login
	 */
	private String roleName;
	
	private int unSuccessfulLogin;
	/**
	 * Gender
	 */

	private String gender;
	/**
	 * LastLogin
	 */

	private Timestamp lastLogin;
	/**
	 * Lock
	 */

	private String lock = ACTIVE;
	/**
	 * Registered Ip
	 */
	private String registeredIP;
	/**
	 * LastLogin Ip
	 */
	private String lastLoginIP;

	/**
	 * accessor
	 * 
	 */

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	

	public int getUnSuccessfulLogin() {
		return unSuccessfulLogin;
	}

	public void setUnSuccessfulLogin(int unSuccessfulLogin) {
		this.unSuccessfulLogin = unSuccessfulLogin;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getRegisteredIP() {
		return registeredIP;
	}

	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	

	public String getKey() {

		return id + "";
	}

	

	public String getValue() {

		return firstName + " " + lastName ;
		}

}
