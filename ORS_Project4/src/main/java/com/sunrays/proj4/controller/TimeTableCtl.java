package com.sunrays.proj4.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.TimeTableBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.CourseModel;
import com.sunrays.proj4.model.SubjectModel;
import com.sunrays.proj4.model.TimeTableModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.DataValidator;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

/**
 * TimeTable functionality Controller. Performs operation for add, update,
 * delete and get TimeTable
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebServlet(name = "TimeTableCtl", urlPatterns = { "/ctl/TimeTableCtl" })
public class TimeTableCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(TimeTableCtl.class);
	/**
	 * Loads Subject and Course list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("TimeTableCtl preload Started");
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
		log.debug("TimeTableCtl preload Ended");

	}
	/**
	 * Validates TimeTable input data entered by User
	 *   * @param request
	 * @return Boolean
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("TimeTableCtl Method validate Started");
		Date examDate2 = DataUtility.getDate(request.getParameter("examDate"));
		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));
		if (DataValidator.isNull(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course"));
			pass = false;
		}
		
		if(DataValidator.isNull(request.getParameter("examDate"))){
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Exam Date"));
			pass= false;
			System.out.println("ExamDate");
		}/*else{
		try {
			if (!DataValidator.futureDate(request.getParameter("examDate"))) {
				request.setAttribute("examDate", "Exam date should be future date");
				pass = false;
			}else if (examDate2.getDay()==0){
				request.setAttribute("examDate", "Exam cant not be scheduled on Sunday");
				pass=false;
			}
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		}
		*/
		if (DataValidator.isNull(request.getParameter("time"))) {
			request.setAttribute("time", PropertyReader.getValue("error.require", "Time"));
			pass = false;
		}
		/*if (DataValidator.isNull(request.getParameter("examDate"))) {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Exam Date"));
			pass = false;
		}else if (examDate2.getDay() == 0) {
			request.setAttribute("examDate", "Exam can not be scheduled on Sunday");
			pass = false;
		} else
			try {
				if (DataValidator.isExamDate(request.getParameter("examDate"))) {
					request.setAttribute("examDate", PropertyReader.getValue("error.examDate", "Exam date"));
					pass = false;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		log.debug("TimeTabletCtl Method validate Ended");

		return pass;
	}
	/**
	 * Populates TimeTablebean object from request parameters
	 * 
	 * @param request
	 * @return BaseBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		System.out.println("populate");

		log.debug("TimeTableCtl Method populatebean Started");

		TimeTableBean bean = new TimeTableBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setExamTime(DataUtility.getString(request.getParameter("time")));
		populateDTO(bean, request);

		log.debug("TimeTableCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("TimeTableCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		TimeTableModel model = new TimeTableModel();
		if (id > 0 || op != null) {
			TimeTableBean bean;
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
		log.debug("TImeTableCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimeTableCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		TimeTableModel model = new TimeTableModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			System.out.println("save");

			TimeTableBean bean = (TimeTableBean) populateBean(request);

			try {
				if (id > 0) {
					System.out.println("Update"+bean.getId());
					System.out.println("Update"+bean.getCourseName());
					System.out.println("Update"+bean.getCourseId());
					System.out.println("Update"+bean.getSubjectId());
                    System.out.println("Update"+bean.getSubject()); 
					model.update(bean);
					
					ServletUtility.setSuccessMessage("Data is Successfully Updated", request);
				} else {
					long pk = model.add(bean);
					ServletUtility.setSuccessMessage("Data is Successfully Saved", request);
				}
				ServletUtility.setBean(bean, request);
				
			}  catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Time Table already exists", request);
			}
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			TimeTableBean bean = (TimeTableBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
			return;

		}
		ServletUtility.forward(getView(), request, response);

		log.debug("TimeTableCtl Method doPost Ended");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return ORSView.TIME_TABLE_VIEW;
	}

}
