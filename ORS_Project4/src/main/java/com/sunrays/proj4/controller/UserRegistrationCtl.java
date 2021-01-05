package com.sunrays.proj4.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.RoleBean;
import com.sunrays.proj4.bean.UserBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.UserModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.DataValidator;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

/**
 * User registration functionality Controller. Performs operation for User
 * Registration
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/UserRegistrationCtl" })
public class UserRegistrationCtl extends BaseCtl {

	public static final String OP_SIGN_UP = "SignUp";

	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

	
	/**
	 * 
	 * Validates User input data entered by User
	 * 
	 * @param request
	 * @return Boolean
	 */
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));

			pass = false;

		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.Name", "First Name"));

			pass = false;
		}
		if (DataValidator.isSpace(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("", "First Name"));
			pass = true;
		}
		if (DataValidator.isWhiteSpace(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.white", "First Name"));
			pass = false;
		}

		if (DataValidator.isWhiteSpace(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.white", "Last Name"));
			pass = false;
		}
		

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.Name", "Last Name"));

			pass = false;
		}
		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.email1", "Login Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.pass", "Password"));

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isLong(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile1", "Mobile No"));

			pass = false;
		} else if (!DataValidator.isMobLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobilelength", "Mobile No"));

			pass = false;
		} else if (!DataValidator.isMob(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.mobile", "Mobile No"));

			pass = false;
		}

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of Birth"));
			pass = false;
		} else
			try {
				if (!DataValidator.isDOB(dob)) {
					request.setAttribute("dob", PropertyReader.getValue("error.age", "Age"));
					pass = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.cpassword", "Confirm Password"));

			pass = false;
		}
		log.debug("UserRegistrationCtl Method validate Ended");
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

		log.debug("UserRegistrationCtl Method populatebean Started");
		InetAddress localhost = null;
		UserBean bean = new UserBean();
		try {
			localhost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		bean.setRegisteredIP(localhost.getHostAddress().trim());
		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoleId(RoleBean.STUDENT);

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		

		populateDTO(bean, request);

		log.debug("UserRegistrationCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Display concept of user registration
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserRegistrationCtl Method doGet Started");
		ServletUtility.forward(getView(), request, response);
		log.debug("UserRegistrationCtl Method doGet Ended");

	}

	/**
	 * Submit concept of user registration
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserRegistrationCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModel model = new UserModel();
	//	long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				long pk = model.registerUser(bean);
				bean.setId(pk);
			//	request.getSession().setAttribute("UserBean", bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Registration is Successfully done",request);
				
				ServletUtility.forward(ORSView.USER_REGISTRATION_VIEW, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				/*ServletUtility.setErrorMessage("Your internet connection is off", request);*/
				ServletUtility.forward(ORSView.ERROR_VIEW, request, response);
				/*ServletUtility.forward(ORSView.USER_REGISTRATION_VIEW, request, response);*/
				return;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
				ServletUtility.forward(getView(), request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.forward(ORSView.USER_REGISTRATION_VIEW, request, response);

		}
		log.debug("UserRegistrationCtl Method doPost Ended");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {

		return ORSView.USER_REGISTRATION_VIEW;
	}

}
