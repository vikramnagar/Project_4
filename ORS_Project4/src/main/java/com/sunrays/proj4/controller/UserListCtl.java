package com.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.UserBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.model.RoleModel;
import com.sunrays.proj4.model.UserModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

import org.apache.log4j.Logger;

/**
 * User List functionality Controller. Performs operation for list, search and
 * delete operations of User
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebServlet(name = "UserListCtl", urlPatterns = { "/ctl/UserListCtl" })
public class UserListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(UserListCtl.class);
	/**
	 * Loads User And Role list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		UserModel model = new UserModel();
		RoleModel rmodel = new RoleModel();
		try {
			List userList = model.list();
			request.setAttribute("userList", userList);
			
		   List l = rmodel.list();
		   request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Populates Userbean object from request parameters
	 * 
	 * @param request
	 * @return BaseBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
        System.out.println("Populate");
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setRoleId(DataUtility.getInt(request.getParameter("roleId")));
		System.out.println("RoleId "+request.getParameter("roleId"));
		//bean.setRoleId(DataUtility.getInt(request.getParameter("roleId")));
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserListCtl doGet Start");
		
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		UserBean bean = (UserBean) populateBean(request);
	//	System.out.println("my" + bean);
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		//String[] ids = request.getParameterValues("ids");
		UserModel model = new UserModel();
				
		try { 
			list = model.search(bean, pageNo, pageSize);
			
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setBean(bean, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}
		 log.debug("UserListCtl doPOst End");
	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 log.debug("UserListCtl doPost Start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
     
		/*Ternary Operation*/
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		UserBean bean = (UserBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
	 	String[] ids = request.getParameterValues("ids");
		UserModel model = new UserModel();
	try {
 
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) 
		{
				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) 
			{
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) 
			{
				pageNo = 1;
				if (ids != null && ids.length > 0)
				{
					UserBean deletebean = new UserBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
				}
					ServletUtility.setSuccessMessage("Record Successfully Deleted", request);
			} else 
			{
					ServletUtility.setErrorMessage("Select at least one record", request);
			}
			} else if (OP_RESET.equalsIgnoreCase(op))
			{
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			}	
			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
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
		 log.debug("UserListCtl doGet End");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}

}
