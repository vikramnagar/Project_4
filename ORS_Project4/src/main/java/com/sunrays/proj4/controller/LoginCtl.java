package com.sunrays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.RoleBean;
import com.sunrays.proj4.bean.UserBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.model.RoleModel;
import com.sunrays.proj4.model.UserModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.DataValidator;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

/**
 * Login functionality Controller. Performs operation for Login
 * 
 * @author Interpreter
 * @version 1.0
 */
@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {

	public static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "Logout";
   
	private static Logger log = Logger.getLogger(LoginCtl.class);

	
	/**
	 * Validates Login input data entered by User
	 * 
	 * @param request
	 * @return Boolean
	 */
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("LoginCtl Method validate Started");
		//System.out.println("LOGIN CTL VALIDAT");
		boolean pass = true;
		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}
		String login = request.getParameter("login");
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

		}
		log.debug("LoginCtl Method validate Ended");
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

		log.debug("LoginCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		log.debug("LoginCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Display Login form
	 * 
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		      HttpSession session = request.getSession(true);
		
		       Cookie []  cArray= request.getCookies();
	            for (Cookie c: cArray) 
	          {		
		      String name = c.getName();
		      String value = c.getValue();
		       System.out.println(name + "=" + value );
				}
		
		       log.debug("LoginCtl Method doGet Started");
               System.out.println(session.getId());
		        String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_LOG_OUT.equals(op))
		{
			/*session = request.getSession();*/

			   session.invalidate();
			ServletUtility.setSuccessMessage(" Successfully Logout", request);
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);

			return;

		}if(request.getSession().getAttribute("user")!=null)
		{
			ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
		}
		ServletUtility.forward(getView(), request, response);	
		log.debug("LoginCtl Method doPost Ended");

	}

	/**
	 * Submitting or login action performing
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		log.debug("LoginCtl Method doPost Started");
		 //System.out.println(session.getId());
		String op = DataUtility.getString(request.getParameter("operation"));
     
		// get model
		UserModel model = new UserModel();
		RoleModel role = new RoleModel();

		if (OP_SIGN_IN.equalsIgnoreCase(op)) 
		{
			UserBean bean = (UserBean) populateBean(request);
		try {
				bean = model.authenticate(bean.getLogin(), bean.getPassword());

				if (bean != null) 
				{
					session.setAttribute("user", bean);
					int rollId = bean.getRoleId();

					RoleBean roleBean = role.findByPK(rollId);

					if (roleBean != null) 
					{
						session.setAttribute("role", roleBean.getName());

					}
					String str=request.getParameter("uri");
					  // System.out.println(str);
					
					if(str=="null" || "null".equalsIgnoreCase(str)){
						ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);	
				     }else{			
						ServletUtility.redirect(str, request, response);
						//System.out.println("ok sir"+str);
					 }
					  return;
			}
				 
	  else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password ", request);
					//ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
			}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) 
		{
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;
         }   
		ServletUtility.forward(getView(), request, response);
      
		log.debug("LoginCtl Method doPost Ended");
	 
      }
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}
