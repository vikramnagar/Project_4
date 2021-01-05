package com.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.CourseBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of CourseModel
 * 
 * @author Interpreter
 * @version 1.0
 */

public class CourseModel {
	private static Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Find next PK of Course
	 * 
	 * @throws DatabaseException
	 */

	public Integer nextPK() throws DatabaseException {
		log.debug("next Pk started");
		Connection conn = null;

		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM ST_COURSE");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				pk = rs.getInt(1);

			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model nextPK End");

		return pk + 1;
	}

	/**
	 * Find User by Course
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */

	public CourseBean findByPK(long pk) throws ApplicationException {

		log.debug("find by PK started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE ID=?");
		CourseBean bean = null;
		Connection conn = null;
System.out.println("IN COURSE MODEL");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));

				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

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
	 * Find Course by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public CourseBean findByName(String name) throws DatabaseException {

		log.debug("find by name started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE NAME=?");
		CourseBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));

				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

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
	 * Add a Course
	 * 
	 * @param bean
	 * @throws DuplicateRecordException
	 * @throws DatabaseException
	 * 
	 */

	public long add(CourseBean bean) throws DuplicateRecordException, DatabaseException {
		log.debug("add started");
		Connection conn = null;
		int pk = 0;
		CourseBean duplicateCourseName = findByName(bean.getName());
		if (duplicateCourseName != null) {
			throw new DuplicateRecordException("Course Name already exists");

		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_COURSE VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDuration());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return pk;
	}

	/**
	 * Delete a Course
	 * 
	 * @param bean
	 * @throws ApplicationException	
	 * 
	 */
	public int delete(CourseBean bean) throws ApplicationException {
		log.debug("Delete method started");
		int i=0;
		System.out.println("in delete");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);// begin transaction
			PreparedStatement ps = conn.prepareStatement("DELETE  FROM ST_COURSE WHERE ID=? ");
		
			ps.setLong(1, bean.getId());
		i=	ps.executeUpdate();
		System.out.println(i);
			conn.commit();
			conn.close(); // End transaction
			ps.close();
		} catch (Exception e) {
			
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
	 * update a Course
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * @throws DuplicateRecordException
	 * @throws ApplicationException
	 * 
	 */

	public void update(CourseBean bean) throws DatabaseException, DuplicateRecordException, ApplicationException {

		log.debug("Model update Started");
		Connection conn = null;

		CourseBean beanExist = findByName(bean.getName());

		// Check if updated College already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {

			throw new DuplicateRecordException("College is already exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_COURSE SET NAME=?,DESCRIPTION=?,DURATION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDuration());

			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
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
	 * Search College
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(CourseBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Course with pagination
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
	public List search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		System.out.println("search call");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
				System.out.println("Query "+sql);
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" AND DURATION like '" + bean.getDuration() + "%'");
				System.out.println(sql);
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
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of College
	 * 
	 * @return list : List of College
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of College with pagination
	 * 
	 * @return list : List of College
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_COURSE");
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
				CourseBean bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
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
