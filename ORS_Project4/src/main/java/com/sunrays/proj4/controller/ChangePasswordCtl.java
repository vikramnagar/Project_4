package com.sunrays.proj4.controller;

import java.io.IOException;

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
import com.sunrays.proj4.exception.RecordNotFoundException;
import com.sunrays.proj4.model.UserModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.DataValidator;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;

/**
 * Change Password functionality Controller. Performs operation for Change
 * Password
 * 
 * @author Interpreter
 * @version 1.0
 */
@ WebServlet(name="ChangePasswordCtl",urlPatterns={"/ctl/ChangePasswordCtl"})
public class ChangePasswordCtl extends BaseCtl {

    public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";

    private static Logger log = Logger.getLogger(ChangePasswordCtl.class);

    /**
	 * Validates Change password input data entered by User
	 * 
	 * @param request
	 * @return Boolean
	 */
    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("ChangePasswordCtl Method validate Started");

        boolean pass = true;

        String op = request.getParameter("operation");

        if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {

            return pass;
        }
        if (DataValidator.isNull(request.getParameter("oldPassword"))) {
        	request.setAttribute("oldPassword",PropertyReader.getValue("error.require", "Old Password"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", PropertyReader.getValue("error.require", "New Password"));
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", PropertyReader.getValue("error.pass", "New Password"));

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}

		if (!request.getParameter("newPassword").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.cpassword", "Confirm Password"));

			pass = false;
		}
		if (request.getParameter("oldPassword").equals(request.getParameter("newPassword"))
				&& !"".equals(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", PropertyReader.getValue("error.new", "New Password"));
			pass = false;
		}
		
        log.debug("ChangePasswordCtl Method validate Ended");

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
        log.debug("ChangePasswordCtl Method populatebean Started");

        UserBean bean = new UserBean();

        bean.setPassword(DataUtility.getString(request
                .getParameter("oldPassword")));
      
        bean.setConfirmPassword(DataUtility.getString(request
                .getParameter("confirmPassword")));

        populateDTO(bean, request);

        log.debug("ChangePasswordCtl Method populatebean Ended");

        return bean;
    }

    /**
     * Display Logics inside this method
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Submit logic inside it 
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
         UserBean UserBean = (UserBean) session.getAttribute("user");
         long id = UserBean.getId();

        log.debug("ChangePasswordCtl Method doGet Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        UserModel model = new UserModel();

        UserBean bean = (UserBean) populateBean(request);

        String newPassword = (String) request.getParameter("newPassword");       

        if (OP_SAVE.equalsIgnoreCase(op)) {
            try {
                boolean flag = true;
               
				try {
					flag = model.changePassword(id,bean.getPassword(), newPassword);
				} catch (DuplicateRecordException e) {
					
					e.printStackTrace();
				}
			
                if (flag == true) {
                    bean = model.findByLogin(UserBean.getLogin());
                    session.setAttribute("user", bean);
                    ServletUtility.setBean(bean, request);
                    ServletUtility.setSuccessMessage("Password has been changed Successfully.", request);
                }
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.setErrorMessage("your internet connection is off", request);
				ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
                return;

            } catch (RecordNotFoundException e) {
                ServletUtility.setErrorMessage("Old PassWord is Invalid",
                        request);
            }
        } else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
            return;
        }

        ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
        log.debug("ChangePasswordCtl Method doGet Ended");
    }
    /**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
    @Override
    protected String getView() {
        return ORSView.CHANGE_PASSWORD_VIEW;
    }
}