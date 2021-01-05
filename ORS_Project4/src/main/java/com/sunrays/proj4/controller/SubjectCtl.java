package com.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.SubjectBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.CourseModel;
import com.sunrays.proj4.model.SubjectModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.DataValidator;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

/**
 * Subject functionality Controller. Performs operation for add, update and get
 * Role
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebServlet(name = "SubjectCtl", urlPatterns = { "/ctl/SubjectCtl" })
public class SubjectCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(SubjectCtl.class);
	/**
	 * Loads Course list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("SubjectCtl preload Started");
		CourseModel cmodel = new CourseModel();
		try {
			List li = cmodel.list();
			request.setAttribute("courseList", li);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		log.debug("SubjectCtl preload Ended");
	}
	/**
	 * Validates subject input data entered by User
	 * 
	 * @param request
	 * @return Boolean
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("SubjectCtl Method validate Started");

		boolean pass = true;
		if(DataValidator.isWhiteSpace(request.getParameter("subjectName")))
        {
        	request.setAttribute("subjectName",PropertyReader.getValue("error.white", "Subject Name"));
        	pass=false;
        }

		if (DataValidator.isNull(request.getParameter("subjectName"))) {
			request.setAttribute("subjectName", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		} else if (!DataValidator.isSubject(request.getParameter("subjectName"))) {
			request.setAttribute("subjectName", PropertyReader.getValue("error.Name", "Subject Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("courseList"))) {
			request.setAttribute("courseName", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}

		log.debug("SubjectCtl Method validate Ended");

		return pass;
	}
	/**
	 * Populates Subjectbean object from request parameters
	 * 
	 * @param request
	 * @return BaseBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("SubjectCtl Method populatebean Started");

		SubjectBean bean = new SubjectBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("subjectName")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseList")));

		populateDTO(bean, request);

		log.debug("SubjectCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("SubjectCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		SubjectModel model = new SubjectModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			SubjectBean bean;
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
		log.debug("SubjectCtl Method doGet Ended");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("SubjectCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		SubjectModel model = new SubjectModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			SubjectBean bean = (SubjectBean) populateBean(request);

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
				ServletUtility.setErrorMessage("Subject Name already exists", request);
			} catch (DatabaseException e) {
				e.printStackTrace();
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			SubjectBean bean = (SubjectBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("SubjectCtl Method doPost Ended");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return ORSView.SUBJECT_VIEW;
	}

}
