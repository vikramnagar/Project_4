package com.sunrays.proj4.bean;

import java.util.Date;

/**
 * Time Table JavaBean encapsulates Time Table attributes
 * 
 * @author Interpreter
 * @version 1.0
 * 
 */
public class TimeTableBean extends BaseBean {

	/**
	 * Course Id of Course
	 */
	private long courseId;
	/**
	 * Name of Course
	 */
	private String courseName;
	/**
	 * Name of Semester
	 */
	private String semester;
	/**
	 * Exam Time of Subject
	 */
	private String examTime;
	/**
	 * Name of Subject
	 */
	private String subject;
	/**
	 * Date of exam
	 */
	private Date examDate;
	/**
	 * SubjectId of Subject
	 */
	private long subjectId;

	/*
	 * accessor
	 */
	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
	public String getKey() {

		return null;
	}

	
	public String getValue() {

		return null;
	}

}
