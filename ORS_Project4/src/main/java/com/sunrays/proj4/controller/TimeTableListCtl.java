package com.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.TimeTableBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.model.CourseModel;
import com.sunrays.proj4.model.SubjectModel;
import com.sunrays.proj4.model.TimeTableModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

/**
 * Time Table List functionality Controller. Performs operation for list, search
 * and delete operations of User
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebServlet(name = "TimeTableListCtl", urlPatterns = { "/ctl/TimeTableListCtl" })
public class TimeTableListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(TimeTableListCtl.class);
	/**
	 * Loads Subject Course list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("TimeTableListCtl preload start");
		SubjectModel subjectModel = new SubjectModel();
		try {
			List subjectList = subjectModel.list();
			request.setAttribute("subjectList", subjectList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		CourseModel courseModel = new CourseModel();
		try {
			List courseList = courseModel.list();
			request.setAttribute("courseList", courseList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		log.debug("TimeTableListCtl preload End");
	}
	/**
	 * Populates TimeTablebean object from request parameters
	 * 
	 * @param request
	 * @return BaseBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		TimeTableBean bean = new TimeTableBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
       	
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimeTableListCtl doGet Start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		
		TimeTableBean bean = (TimeTableBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");
		TimeTableModel model = new TimeTableModel();
		try {
			list = model.search(bean, pageNo, pageSize);
			//ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("TimeTableListCtl doPOst End");
	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimeTableListCtl doPost Start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
     
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		TimeTableBean bean = (TimeTableBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");
		TimeTableModel model = new TimeTableModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.TIME_TABLE_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) 
			{
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					TimeTableBean deletebean = new TimeTableBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						int i=model.delete(deletebean);
						if(i==0){
							ServletUtility.setErrorMessage("Data Not Deleted", request);
						}
						else
							{ServletUtility.setSuccessMessage("Data Successfully Deleted ", request);
							}
					}
				} else 
				{
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) 
			{
				ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
				return;
			}/*else if (OP_BACK.equalsIgnoreCase(op)) {
				list = model.search(bean, pageNo, pageSize);
				ServletUtility.setList(list, request);
				ServletUtility.setPageNo(pageNo, request);
				ServletUtility.setPageSize(pageSize, request);
				ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);

				return;
			}
*/
		System.out.println("=======  "+bean.getExamDate());
			list = model.search(bean, pageNo, pageSize);
			//ServletUtility.setList(list, request);
			
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("TimeTableListCtl doGet End");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return ORSView.TIME_TABLE_LIST_VIEW;
	}

}
