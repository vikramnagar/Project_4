package com.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.CourseBean;
import com.sunrays.proj4.bean.SubjectBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.util.JDBCDataSource;
/**
 * JDBC Implementation of Subject Model
 * 
 * @author Interpreter
 * @version 1.0
 */
public class SubjectModel {
	Logger log=Logger.getLogger(SubjectModel.class);
	/**
	 * Find next PK of Subject
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		 log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM SUBJECT");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
				System.out.println(pk);
			}
			rs.close();

		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a Subject
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException, DatabaseException {
		 log.debug("Model add Started");
		Connection conn = null;

		// get College Name
		System.err.println(bean.getCourseId());
		CourseModel cModel = new CourseModel();
		CourseBean courseBean = cModel.findByPK(bean.getCourseId());
		bean.setCourseName(courseBean.getName());
		
		System.err.println(courseBean.getName());

		SubjectBean duplicateName = findByName(bean.getName());
		int pk = 0;

		if (duplicateName != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SUBJECT VALUES(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, bean.getCourseName());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
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
	 * Find Subject by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public SubjectBean findByName(String name) throws DatabaseException {

		 log.debug("find by name started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SUBJECT WHERE NAME=?");
		SubjectBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				// bean.setDuration(rs.getString(4));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

			}
			rs.close();
		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in getting findbyPK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	/**
	 * Delete a Subject
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * 
	 */

	public int delete(SubjectBean bean) throws ApplicationException {
		 log.debug("Delete method started");
		Connection conn = null;
		int i = 0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);// begin transaction
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM SUBJECT WHERE ID=? ");
			System.out.println(bean.getId());
			ps.setLong(1, bean.getId());
			 i = ps.executeUpdate();
			conn.commit();
			conn.close(); // End transaction
			ps.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		 log.debug("delete ends");
		}
              return i;
	}

	/**
	 * update a Subject
	 * 
	 * @param bean
	 
	 * @throws DatabaseException
	 * @throws DuplicateRecordException
	 * @throws ApplicationException
	 * 
	 */
	public void update(SubjectBean bean) throws DatabaseException, DuplicateRecordException, ApplicationException {

		// log.debug("Model update Started");
		CourseModel cModel = new CourseModel();
		CourseBean courseBean = cModel.findByPK(bean.getCourseId());
		bean.setCourseName(courseBean.getName());
		Connection conn = null;
		SubjectBean beanExist = findByName(bean.getName());
		// Check if updated College already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {

			throw new DuplicateRecordException("Subject Name is already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE SUBJECT SET NAME=?,DESCRIPTION=?,COURSE_ID=?,COURSE_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCourseName());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		}

		catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating College ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model update End");

	}

	/**
	 * Search Subject
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(SubjectBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Subject with pagination
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
	public List search(SubjectBean bean, int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SUBJECT WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND ADDRESS like '" + bean.getCourseName() + "%'");
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
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model search End");
		return list;
	}

	public SubjectBean findByPK(long pk) throws ApplicationException {

		// log.debug("find by PK started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SUBJECT WHERE ID=?");
		SubjectBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

			}
			rs.close();
		} catch (Exception e) {
		

			throw new ApplicationException("Exception : Exception in getting findbyPK");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		System.out.println(bean.getName());
		return bean;
	
	}

	/**
	 * Get List of Subject
	 * 
	 * @return list : List of College
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Subject with pagination
	 * 
	 * @return list : List of College
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from SUBJECT");
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
				SubjectBean bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

}
