package com.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.CourseBean;
import com.sunrays.proj4.bean.MarksheetBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.CourseModel;
import com.sunrays.proj4.model.MarksheetModel;
import com.sunrays.proj4.model.StudentModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.DataValidator;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

/**
 * Course functionality Controller. Performs operation for add, update, delete
 * and get Course
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl" })
public class CourseCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(MarksheetCtl.class);
	/**
	 * Loads Course list and other data required to display at HTML form
	 * 
	 * @param request
	 */
		@Override
	protected void preload(HttpServletRequest request) {
		log.debug("CourseCtl preload Started");
		CourseModel model = new CourseModel();
		try {
			List l = model.list();
			request.setAttribute("courseDurationList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}
		/**
		 * Validates Course input data entered by User
		 * 
		 * @param request
		 * @return Boolean
		 */

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CourseCtl Method validate Started");

		boolean pass = true;

		 if(DataValidator.isWhiteSpace(request.getParameter("courseName")))
	        {
	        	request.setAttribute("courseName",PropertyReader.getValue("error.white", "Course Name"));
	        	pass=false;
	        }
		
		if (DataValidator.isNull(request.getParameter("courseName"))) {
			request.setAttribute("courseName", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}else if(!DataValidator.isName(request.getParameter("courseName"))){
			request.setAttribute("courseName", PropertyReader.getValue("error.Name","Course Name"));
			pass=false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		else if(!DataValidator.isName(request.getParameter("description"))){
			request.setAttribute("description", PropertyReader.getValue("error.Name","Description"));
			pass=false;
		}
		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", PropertyReader.getValue("error.require", "Duration"));
			pass = false;
		}

		log.debug("CourseCtl Method validate Ended");

		return pass;
	}
	/**
	 * Populates Coursebean object from request parameters
	 * 
	 * @param request
	 * @return BaseBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("CourseCtl Method populatebean Started");

		CourseBean bean = new CourseBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("courseName")));

		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));
		populateDTO(bean, request);

		System.out.println("Population done");

		log.debug("CourseCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("CourseCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		CourseModel model = new CourseModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			CourseBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("CourseCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CourseCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		CourseModel model = new CourseModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			CourseBean bean = (CourseBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
					
					ServletUtility.setSuccessMessage("Data is Successfully Updated", request);
				} else {
					long pk = model.add(bean);
					ServletUtility.setSuccessMessage("Data is Successfully Saved", request);
				}
				ServletUtility.setBean(bean, request);
			//	ServletUtility.setSuccessMessage("Data is Successfully Saved", request);

			}  catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Course Name already exists", request);
			} catch (DatabaseException e) {

				e.printStackTrace();
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			CourseBean bean = (CourseBean) populateBean(request);

			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);

				return;
			} catch (ApplicationException e) {

				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;

		}
		ServletUtility.forward(getView(), request, response);

		log.debug("CourseCtl Method doPost Ended");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

}
