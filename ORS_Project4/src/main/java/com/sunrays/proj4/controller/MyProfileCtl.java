package com.sunrays.proj4.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.UserBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.UserModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.DataValidator;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebServlet(name = "MyProfileCtl", urlPatterns = { "/ctl/MyProfileCtl" })
public class MyProfileCtl extends BaseCtl {

	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";

	private static Logger log = Logger.getLogger(MyProfileCtl.class);

	
	/**
	 * Validates My profile input data entered by User
	 * 
	 * @param request
	 * @return Boolean
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MyProfileCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		
		if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {

			return pass;
		}
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
		} else if (!DataValidator.isMob(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile", "Mobile No"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		} else
			try {
				if (!DataValidator.isDOB(request.getParameter("dob"))) {
					request.setAttribute("dob", PropertyReader.getValue("error.age", "Age"));
					pass = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		log.debug("MyProfileCtl Method validate Ended");

		return pass;

	}
	/**
	 * Populates Userbean object from request parameters
	 * 
	 * @param request
	 * @return BaseBean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("MyProfileCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		populateDTO(bean, request);
		log.debug("MyProfileCtl Method populatebean Ended");
		return bean;
	}

	/**
	   * Display Concept for viewing profile page view
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UserBean UserBean = (UserBean) session.getAttribute("user");
		long id = UserBean.getId();
		
		log.debug("MyprofileCtl Method doGet Started");
	
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();
		if (id > 0 || op != null) 
		{
			UserBean bean;
			try {
				bean = model.findByPK(id);
				//System.out.println(bean.getMobileNo());
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("MyProfileCtl Method doGet Ended");
	}

	/**
	 * Submit Concept
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UserBean UserBean = (UserBean) session.getAttribute("user");
		long id = UserBean.getId();
		
		log.debug("MyprofileCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				if (id > 0) {
					UserBean.setFirstName(bean.getFirstName());
					UserBean.setLastName(bean.getLastName());
					UserBean.setGender(bean.getGender());
					UserBean.setMobileNo(bean.getMobileNo());
					UserBean.setDob(bean.getDob());
					model.update(UserBean);

				}
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Profile has been updated Successfully. ", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("MyProfileCtl Method doPost Ended");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return ORSView.MY_PROFILE_VIEW;
	}

}
