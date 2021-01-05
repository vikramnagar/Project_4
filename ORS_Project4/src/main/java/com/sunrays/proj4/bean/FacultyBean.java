package com.sunrays.proj4.bean;

import java.util.Date;

/**
 * Faculty JavaBean encapsulates Faculty attributes
 * 
 * @author Interpreter
 * @version 1.0
 * 
 */
public class FacultyBean extends BaseBean {
	/**
	 * Non Business Primary Key
	 */
	private long Id;

	/**
	 * ID of College
	 */
	private long CollegeId;
	/**
	 * First Name of Faculty
	 */
	private String FirstName;
	/**
	 * Last Name of Faculty
	 */

	private String LastName;
	/**
	 * Name of College
	 */
	private String CollegeName;
	/**
	 * Date of Birth of Faculty
	 */
	private Date DOB;

	/**
	 * Email Id of Faculty
	 */
	private String EmailId;
	/**
	 * Mobile Number of Faculty
	 */
	private String MobileNo;
	/**
	 * Name of Subject
	 */
	private String Subject;
	/**
	 * Qualification of Facultty
	 */
	private String Qualification;
	/**
	 * Gender Of Faculty
	 */
	private String Gender;
	/**
	 * SubjectId
	 */
	private Long SubjectId;
	/**
	 * CourseId
	 */
	private Long CourseId;
	/**
	 * Name of Course
	 */
	private String CourseName;

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public void setSubjectId(long l) {
		SubjectId = l;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getCollegeId() {
		return CollegeId;
	}

	public void setCollegeId(long collegeId) {
		CollegeId = collegeId;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getCollegeName() {
		return CollegeName;
	}

	public void setCollegeName(String collegeName) {
		CollegeName = collegeName;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public String getSubject() {
		return Subject;
	}

	public Long getSubjectId() {
		return SubjectId;
	}

	public void setSubjectId(Long subjectId) {
		SubjectId = subjectId;
	}

	public Long getCourseId() {
		return CourseId;
	}

	public void setCourseId(Long courseId) {
		CourseId = courseId;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	

	public String getKey() {

		return id + "";
	}

	public String getValue() {

		return FirstName + " " + LastName;
	}

}
