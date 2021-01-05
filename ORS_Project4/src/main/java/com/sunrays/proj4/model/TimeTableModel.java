package com.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

import com.sunrays.proj4.bean.CourseBean;
import com.sunrays.proj4.bean.SubjectBean;
import com.sunrays.proj4.bean.TimeTableBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of TimeTable Model
 * 
 * @author Interpreter
 * @version 1.0
 */
public class TimeTableModel {

	private static Logger log = Logger.getLogger(TimeTableModel.class);

	/**
	 * Find next PK of TimeTable
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_TIME_TABLE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a TimeTable
	 *
	 * @param bean
	 * @throws DuplicateRecordException
	 * @throws ApplicationException
	 */
	public long add(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		System.out.println("ADD CALLED");
		CourseModel cModel = new CourseModel();
		CourseBean courseBean = cModel.findByPK(bean.getCourseId());
		// System.out.println(bean.getCourseId());
		// System.out.println(courseBean.getName());
		bean.setCourseName(courseBean.getName());
		// System.out.println(courseBean.getName());
		SubjectModel sModel = new SubjectModel();
		SubjectBean subjectBean = sModel.findByPK(bean.getSubjectId());
		// System.out.println(bean.getSubjectId());
		// System.out.println(subjectBean.getName());
		bean.setSubject(subjectBean.getName());
		bean.setSubjectId(subjectBean.getId());
		// System.out.println(subjectBean.getName());
		// System.out.println(bean.getId());

		TimeTableBean duplicateTimeTable = findByPK(bean.getId());
		if (duplicateTimeTable != null) {
			throw new DuplicateRecordException("Time Table already exists!");
		}
		TimeTableBean existDate = findByCourseAndSub(bean.getCourseId(), bean.getSubjectId());
		if (existDate != null) {
			System.out.println("exitdate");
			throw new DuplicateRecordException("Timetable is already exists on " + existDate.getExamDate());

		}

		TimeTableBean existsem = findByDateAndCourseId(new java.sql.Date(bean.getExamDate().getTime()),
				bean.getCourseId());
		if (existsem != null) {
			System.out.println("exitsem");
			throw new DuplicateRecordException("Exam date already schedule for this course!");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			System.out.println("Primary Key" + pk);
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer("INSERT INTO ST_TIME_TABLE VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setLong(4, bean.getSubjectId());
			pstmt.setString(5, bean.getSubject());
			pstmt.setString(6, bean.getSemester());
			pstmt.setDate(7, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(8, bean.getExamTime());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			System.out.println(sql);
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Deletes a TimeTable
	 * 
	 * @param bean
	 * @throws ApplicationException
	 */
	public int delete(TimeTableBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		int i;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIME_TABLE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			i = pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback Exception");
			}
			throw new ApplicationException("Exception: Exception in delete Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete End");
		return i;
	}

	/**
	 * Finds TimeTable by Course, Semester and Subject
	 * 
	 * @param courseId
	 *            : get Course Id
	 * @param subjectId
	 *            : get ID of Subject
	 * @return bean
	 * @throws ApplicationException
	 */
	public TimeTableBean findByCourseAndSub(Long courseId, Long subjectId) throws ApplicationException {
		log.debug("Model findBySubjectID Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIME_TABLE WHERE COURSE_ID=? And SUBJECT_ID=?");

		TimeTableBean bean = null;
		Connection conn = null;
		try {/*
				 * System.out.println(courseId); System.out.println(subjectId);
				 * System.out.println(semester);
				 */

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, courseId);
			pstmt.setLong(2, subjectId);

			System.out.println("After Prepered Statement");
			System.out.println(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubject(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting TimeTable by SubjectID");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBySubjectID End");
		System.out.println("findByCourseSemAndSub called");
		return bean;
	}

	/**
	 * Finds TimeTable by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */
	public TimeTableBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("select * from st_Time_Table where ID=?");
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			System.out.println("IN FIND BY PK");
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				System.out.println("Modell C Name" + rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubject(rs.getString(5));
				System.out.println("Modell S Name" + rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			System.out.println("Find By Pk Call");
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Finds TimeTable by Date and Course
	 * 
	 * @param examDate
	 *            : get Exam Date
	 * @param courseId
	 *            : get ID of Course
	 * @return bean
	 * @throws ApplicationException
	 */
	public TimeTableBean findByDateAndCourseId(Date examDate, Long courseId) throws ApplicationException {
		log.debug("Model findByDate Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIME_TABLE WHERE EXAM_DATE= ? And COURSE_ID= ?");
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setDate(1, (java.sql.Date) examDate);
			pstmt.setLong(2, courseId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubject(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception", e);
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting User by Date");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByDate End");
		System.out.println("findByDateAndCourseId called");
		return bean;
	}

	/**
	 * Updates a TimeTable
	 * 
	 * @param bean
	 * @throws ApplicationException
	 */
	public void update(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model update Started");
		Connection conn = null;

		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPK(bean.getCourseId());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPK(bean.getSubjectId());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer("UPDATE ST_TIME_TABLE SET COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,SEMESTER=?,EXAM_DATE=?,EXAM_TIME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			System.out.println("Update Called");
			pstmt.setLong(1, bean.getCourseId());
			pstmt.setString(2, courseBean.getName());
			pstmt.setLong(3, bean.getSubjectId());
			pstmt.setString(4, subjectBean.getName());
			pstmt.setString(5, bean.getSemester());
			pstmt.setDate(6, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(7, bean.getExamTime());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());
			pstmt.executeUpdate();
			System.out.println(sql);
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				e.printStackTrace();
				throw new ApplicationException("Exception : Delete Rollback exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating TimeTable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Update End");

	}

	/**
	 * Searches TimeTable
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List<TimeTableBean> search(TimeTableBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Searches TimeTable with pagination
	 * 
	 * @return list : List of TimeTable
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */

	public List<TimeTableBean> search(TimeTableBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model Search start");
		//System.out.println("Model Sear ch start");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIME_TABLE WHERE 1=1");
		//System.out.println("serch start");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id=" + bean.getId());
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND COURSE_ID= " + bean.getCourseId());
			}
			if (bean.getExamDate() != null) {
				sql.append(" AND Exam_date = '" + new java.sql.Date(bean.getExamDate().getTime()) + "'");
			}
			/*if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME like '" + bean.getCourseName() + "%'");
			}*/
			System.out.println("Course Name :---- " + sql);
			if (bean.getSubjectId() > 0) {
				sql.append(" AND SUBJECT_ID=" + bean.getSubjectId());
			} // System.out.println("Subject Id :--------" + sql);
			if (bean.getSubject() != null && bean.getSubject().length() > 0) {
				sql.append(" AND SUBJECT_NAME like '" + bean.getSubject() + "%'");
			}
		}
		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
		}
		System.out.println(sql);
		ArrayList<TimeTableBean> list = new ArrayList<TimeTableBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubject(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		System.out.println("Model Search End");
		log.debug("Model search End");
		return list;
	}

	/**
	 * Gets List of TimeTable
	 * 
	 * @return list : List of TimeTable
	 * @throws ApplicationException
	 */

	public List<TimeTableBean> list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * get List of Marksheet with pagination
	 * 
	 * @return list : List of Marksheets
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */
	public List<TimeTableBean> list(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model List Started");

		ArrayList<TimeTableBean> list = new ArrayList<TimeTableBean>();
		StringBuffer sql = new StringBuffer("select * from st_time_table");
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TimeTableBean bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubject(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

}
