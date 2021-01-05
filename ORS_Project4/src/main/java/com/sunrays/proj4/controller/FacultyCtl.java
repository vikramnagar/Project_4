package com.sunrays.proj4.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.FacultyBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.CollegeModel;
import com.sunrays.proj4.model.CourseModel;
import com.sunrays.proj4.model.FacultyModel;
import com.sunrays.proj4.model.SubjectModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.DataValidator;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

/**
 * Faculty functionality Controller. Performs operation for add, update, delete
 * and get Faculty
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl" })
public class FacultyCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(FacultyCtl.class);
	
	/**
	 * Loads College,Subject and Course list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("FacultyCtl preload Started");
		CollegeModel model = new CollegeModel();
		try {
			List l = model.list();
			request.setAttribute("collegeList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		SubjectModel smodel = new SubjectModel();
		try {
			List li = smodel.list();
			request.setAttribute("subjectList", li);
		} catch (ApplicationException e) {

			e.printStackTrace();
		}
		CourseModel cmodel = new CourseModel();
		try {
			List lis = cmodel.list();
			request.setAttribute("courseList", lis);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		log.debug("FacultyCtl preload Ended");
	}

	/**
	 * Validates Faculty input data entered by User
	 * 
	 * @param request
	 * @return Boolean
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FacultyCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");

		if(DataValidator.isWhiteSpace(request.getParameter("firstName")))
        {
        	request.setAttribute("firstName",PropertyReader.getValue("error.white", "First Name"));
        	pass=false;
        }
		if(DataValidator.isWhiteSpace(request.getParameter("lastName")))
        {
        	request.setAttribute("lastName",PropertyReader.getValue("error.white", "Last Name"));
        	pass=false;
        }
		if(DataValidator.isWhiteSpace(request.getParameter("qualification")))
        {
        	request.setAttribute("qualification",PropertyReader.getValue("error.white", "Last Name"));
        	pass=false;
        }
		
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.Name", "First Name"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.Name", "Last Name"));

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}else
			if (!DataValidator.isLong(request.getParameter("mobileNo"))) {
				request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile1", "Mobile No"));

				pass = false;
			}
		else if (!DataValidator.isMob(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile", "Mobile No"));

			pass = false;
		}
		
		if (DataValidator.isNull(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "LoginId "));
			pass = false;
		} else if (!DataValidator.isEmail(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.email1", "LoginId "));
			pass = false;
		}

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		}else
			try {
				if (!DataValidator.isDOB(dob)) {
					request.setAttribute("dob", PropertyReader.getValue("error.age", "Age"));
					pass = false;
				}
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification", PropertyReader.getValue("error.require", "Qualification"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("qualification"))) {
			request.setAttribute("qualification", PropertyReader.getValue("Qualification Name must be String"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeName", PropertyReader.getValue("error.require", "College Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("courseList"))) {
			request.setAttribute("courseName", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subjectList"))) {
			request.setAttribute("subjectName", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
		log.debug("FacultyCtl Method validate Ended");

		return pass;
	}
	/**
	 * Populates Facultybean object from request parameters
	 * 
	 * @param request
	 * @return BaseBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("FacultyCtl Method populatebean Started");

		FacultyBean bean = new FacultyBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setDOB(DataUtility.getDate(request.getParameter("dob")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setEmailId(DataUtility.getString(request.getParameter("email")));
		bean.setQualification(DataUtility.getString(request.getParameter("qualification")));

		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));

		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectList")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseList")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		populateDTO(bean, request);

		log.debug("FacultyCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("FacultyCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		FacultyModel model = new FacultyModel();
		if (id > 0 || op != null) {
			FacultyBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DatabaseException e) {

				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("FacultyCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		FacultyModel model = new FacultyModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			System.out.println("save");

			FacultyBean bean = (FacultyBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
					
					ServletUtility.setSuccessMessage("Data is Successfully Updated", request);
				} else {
					long pk = model.add(bean);
					ServletUtility.setSuccessMessage("Data is Successfully Saved", request);	
				}
				ServletUtility.setBean(bean, request);
				//ServletUtility.setSuccessMessage("Data is Successfully Saved", request);

			}  catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Faculty Email Id already exists", request);
			} catch (DatabaseException e) {

				e.printStackTrace();
			}

		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			FacultyBean bean = (FacultyBean) populateBean(request);
			try {
				int i = model.delete(bean);
				if (i > 0) {
					ServletUtility.setSuccessMessage("Record Succefully Deletd", request);
				} else {
					ServletUtility.setErrorMessage("Record Not Deleted", request);
				}
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;

		}
		ServletUtility.forward(getView(), request, response);

		log.debug("FacultyCtl Method doPost Ended");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}

}
