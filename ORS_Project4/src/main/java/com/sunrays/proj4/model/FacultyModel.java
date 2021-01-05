package com.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.CollegeBean;
import com.sunrays.proj4.bean.CourseBean;
import com.sunrays.proj4.bean.FacultyBean;
import com.sunrays.proj4.bean.SubjectBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.util.JDBCDataSource;
/**
 * JDBC Implementation of Faculty Model
 * 
 * @author Interpreter
 * @version 1.0
 */
public class FacultyModel {
	private static Logger log = Logger.getLogger(FacultyModel.class);
	/**
	 * Find next PK of Course
	 * 
	 * @throws DatabaseException
	 */

	public Integer nextPK() throws DatabaseException {
		log.debug("nextPK method started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("Select Max(ID)from st_faculty");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			throw new DatabaseException("Exception: Exception in getting PK");

		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		log.debug("nextPK method ended");
		return pk + 1;

	}
	/**
	 * Find User by Course
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public FacultyBean findByPK(long pk) throws ApplicationException, DatabaseException {
		log.debug("findBYPK started");
		StringBuffer sql = new StringBuffer("Select* from st_faculty where id=?");
		Connection conn = null;
		FacultyBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setDOB(rs.getDate(5));
				bean.setEmailId(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setQualification(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCollegeName(rs.getString(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setSubject(rs.getString(12));
				bean.setCourseId(rs.getLong(13));
				bean.setCourseName(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

			}
			rs.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception: Exception in getting PK");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Method findByPK ended");
		return bean;

	}

	/**
	 * Find Faculty by Email
	 * 
	 * @param EmailId
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */
	public FacultyBean findByEmailId(String EmailId) throws ApplicationException {
		log.debug("findByEmailId started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE EMAIL_ID=?");

		FacultyBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, EmailId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setDOB(rs.getDate(5));
				bean.setEmailId(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setQualification(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCollegeName(rs.getString(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setSubject(rs.getString(12));
				bean.setCourseId(rs.getLong(13));
				bean.setCourseName(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

			}
			rs.close();

		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in getting EmailId");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Method findByEmailId ended");
		return bean;

	}
	/**
	 * Add a Faculty
	 * 
	 * @param bean
	 * @throws DuplicateRecordException
	 * @throws DatabaseException
	 * 
	 */

	public long add(FacultyBean bean) throws DuplicateRecordException, DatabaseException, ApplicationException {

		log.debug("Model add Started");
		Connection conn = null;

		// get College Name
		CollegeModel cModel = new CollegeModel();
		CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel csModel = new CourseModel();
		CourseBean courseBean = csModel.findByPK(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectModel subModel = new SubjectModel();
		SubjectBean subjectBean = subModel.findByPK(bean.getSubjectId());
		bean.setSubject(subjectBean.getName());
		FacultyBean duplicateName = findByEmailId(bean.getEmailId());
		int pk = 0;

		if (duplicateName != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_FACULTY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getGender());
			pstmt.setDate(5, new java.sql.Date(bean.getDOB().getTime()));

			pstmt.setString(6, bean.getEmailId());
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getQualification());
			pstmt.setLong(9, bean.getCollegeId());
			pstmt.setString(10, bean.getCollegeName());
			pstmt.setLong(11, bean.getSubjectId());
			pstmt.setString(12, bean.getSubject());
			pstmt.setLong(13, bean.getCourseId());
			pstmt.setString(14, bean.getCourseName());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	/**
	 * Delete Faculty 
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * 
	 */

	public int delete(FacultyBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		int i;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_FACULTY WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			i=pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
		return i;

	}
	/**
	 * Update Faculty
	 * 
	 * @param bean
	 * @throws DuplicateRecordException
	 * @throws DatabaseException
	 * 
	 */

	public int update(FacultyBean bean) throws DatabaseException, ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		int i;
		Connection conn = null;

		FacultyBean beanExist = findByEmailId(bean.getEmailId());

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Email Id is already exist");
		}

		// get College Name
				CollegeModel cModel = new CollegeModel();
				CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
				bean.setCollegeName(collegeBean.getName());

				CourseModel csModel = new CourseModel();
				CourseBean courseBean = csModel.findByPK(bean.getCourseId());
				bean.setCourseName(courseBean.getName());

				SubjectModel subModel = new SubjectModel();
				SubjectBean subjectBean = subModel.findByPK(bean.getSubjectId());
				bean.setSubject(subjectBean.getCourseName());

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ST_FACULTY SET FIRST_NAME=?,LAST_NAME=?,GENDER=?,DOB=?,EMAIL_ID=?,MOBILE_NO=?,QUALIFICATION=?,COLLEGE_ID=?,COLLEGE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,COURSE_ID=?,COURSE_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getGender());
			pstmt.setDate(4, new java.sql.Date(bean.getDOB().getTime()));
			pstmt.setString(5, bean.getEmailId());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getQualification());
			pstmt.setLong(8, bean.getCollegeId());
			pstmt.setString(9, bean.getCollegeName());
			pstmt.setLong(10, bean.getSubjectId());
			pstmt.setString(11, bean.getSubject());
			pstmt.setLong(12, bean.getCourseId());
			pstmt.setString(13, bean.getCourseName());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());
			pstmt.setLong(18, bean.getId());
			i=pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Faculty " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
		return i;
	}
	/**
	 * Search Faculty
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(FacultyBean bean) throws ApplicationException {
		return search(bean, 0, 0);

	}
	/**
	 * Search Faculty with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */


	public List search(FacultyBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}if (bean.getCollegeId() > 0) {
				sql.append(" AND COLLEGE_ID =" + bean.getCollegeId());
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGE_NAME like '" + bean.getCollegeName() + "%'");
			}
			if (bean.getDOB() != null && bean.getDOB().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDOB());
			}

			if (bean.getEmailId() != null && bean.getEmailId().length() > 0) {
				sql.append(" AND EMAIL_ID like '" + bean.getEmailId() + "%'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO like '" + bean.getMobileNo() + "%'");
			}if (bean.getSubject() != null && bean.getSubject().length() > 0) {
				sql.append(" AND SUBJECT_NAME like '" + bean.getSubject() + "%'");
			}if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME like '" + bean.getCourseName() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setDOB(rs.getDate(5));
				bean.setEmailId(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setQualification(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCollegeName(rs.getString(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setSubject(rs.getString(12));
				bean.setCourseId(rs.getLong(13));
				bean.setCourseName(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model search End");
		return list;
	}
	/**
	 * Get List of Faculty
	 * 
	 * @return list : List of Faculty
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);

	}
	/**
	 * Get List of Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */



	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_faculty");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FacultyBean bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setDOB(rs.getDate(5));
				bean.setEmailId(rs.getString(6));
				bean.setMobileNo(rs.getString(7));
				bean.setQualification(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCollegeName(rs.getString(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setSubject(rs.getString(12));
				bean.setCourseId(rs.getLong(13));
				bean.setCourseName(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");

		return list;

	}
}
