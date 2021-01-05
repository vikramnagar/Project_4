package com.sunrays.proj4.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sunrays.proj4.bean.BaseBean;
import com.sunrays.proj4.bean.MarksheetBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.MarksheetModel;
import com.sunrays.proj4.model.StudentModel;
import com.sunrays.proj4.util.DataUtility;
import com.sunrays.proj4.util.DataValidator;
import com.sunrays.proj4.util.PropertyReader;
import com.sunrays.proj4.util.ServletUtility;



/**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 * 
 * @author Interpreter
 * @version 1.0
 */
@ WebServlet(name="MarksheetCtl",urlPatterns={"/ctl/MarksheetCtl"})
public class MarksheetCtl extends BaseCtl {

    private static Logger log = Logger.getLogger(MarksheetCtl.class);
   
    /**
	 * Loads Student list and other data required to display at HTML form
	 * 
	 * @param request
	**/
    @Override
    protected void preload(HttpServletRequest request) {
        StudentModel model = new StudentModel();
        try {
            List l = model.list();
            request.setAttribute("studentList", l);
        } catch (ApplicationException e) {
            log.error(e);
        }

    }

    /**
	 * Validates Marksheet input data entered by User
	 * 
	 * @param request
	 * @return Boolean
	 */
    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("MarksheetCtl Method validate Started");

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("rollNo"))) {
            request.setAttribute("rollNo",PropertyReader.getValue("error.require", "Roll Number"));
            pass = false;
        }
        
        else if (!DataValidator.isRollNo(request.getParameter("rollNo"))) {
            request.setAttribute("rollNo",PropertyReader.getValue("error.roll", "Roll Number"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("physics"))) {
            request.setAttribute("physics",PropertyReader.getValue("error.require", "Marks"));
            pass = false;
        }else if (!DataValidator.isInteger(request.getParameter("physics"))) {
            request.setAttribute("physics",PropertyReader.getValue("error.integer", "Marks"));
            pass = false;
        }

        else if (DataUtility.getInt(request.getParameter("physics")) > 100)  {
            request.setAttribute("physics", "Marks can not be greater than 100");
            pass = false;
        }
        else if(DataUtility.getInt(request.getParameter("physics")) < 0)
        {
        	 request.setAttribute("physics", "Marks can not be negative");
	            pass = false;
        	
        	
        }

        if (DataValidator.isNull(request.getParameter("chemistry"))) {
            request.setAttribute("chemistry",PropertyReader.getValue("error.require", "Marks"));
            pass = false;
        }else if (!DataValidator.isInteger(request.getParameter("chemistry"))) {
            request.setAttribute("chemistry",PropertyReader.getValue("error.integer", "Marks"));
            pass = false;
        }

        else if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
            request.setAttribute("chemistry","Marks can not be greater than 100");
            pass = false;
        }
        
        
        else if(DataUtility.getInt(request.getParameter("chemistry")) < 0)
        {
        	 request.setAttribute("chemistry", "Marks can not be negative");
	            pass = false;
        	
        	
        }

        if (DataValidator.isNull(request.getParameter("maths"))) {
            request.setAttribute("maths",PropertyReader.getValue("error.require", "Marks"));
            pass = false;
        }else if (!DataValidator.isInteger(request.getParameter("maths"))) {
            request.setAttribute("maths",PropertyReader.getValue("error.integer", "Marks"));
            pass = false;
        } 
        /*if (DataValidator.isString(request.getParameter("maths"))) {
            request.setAttribute("maths",PropertyReader.getValue("error.String", "Marks"));
            pass = false;
        }*/


        else if (DataUtility.getInt(request.getParameter("maths")) > 100) {
            request.setAttribute("maths", "Marks can not be greater than 100");
            pass = false;
        }

        else if(DataUtility.getInt(request.getParameter("maths")) < 0)
        {
        	 request.setAttribute("maths", "Marks can not be negative");
	            pass = false;
        	
        	
        }

        if (DataValidator.isNull(request.getParameter("studentId"))) {
            request.setAttribute("studentId",PropertyReader.getValue("error.require", "Student Name"));
            pass = false;
        }

        log.debug("MarksheetCtl Method validate Ended");

        return pass;
    }
    /**
	 * Populates Marksheetbean object from request parameters
	 * 
	 * @param request
	 * @return BaseBean
	 */
    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        log.debug("MarksheetCtl Method populatebean Started");

        String physics=request.getParameter("physics");
        String chemistry=request.getParameter("chemistry");
        String maths=request.getParameter("maths");
        
        MarksheetBean bean = new MarksheetBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));

        //bean.setRollNO(DataUtility.getString(request.getParameter("rollNo")));

        //bean.setName(DataUtility.getString(request.getParameter("name")));
        
        /*bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
        bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
        bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
        bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
        *//*if(request.getParameter("id").equalsIgnoreCase("0")){
        	bean.setPhysics(null);
        bean.setChemistry(null);
        bean.setMaths(null);}
        */
        
        
        
       
        if(DataValidator.isNotNull(physics)){
        	bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
        }
        if(DataValidator.isNotNull(chemistry)){
        	bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
        }
        if(DataValidator.isNotNull(maths)){
        	bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
        }
       /* bean.setMaths1(DataUtility.getString(request.getParameter("maths")));*/
        
        bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));

        populateDTO(bean, request);

        System.out.println("Population done");

        log.debug("MarksheetCtl Method populatebean Ended");

        return bean;
        
    }

    /**
     * Contains Display logics
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("MarksheetCtl Method doGet Started");

        String op = DataUtility.getString(request.getParameter("operation"));
        MarksheetModel model = new MarksheetModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (id > 0) {
            MarksheetBean bean;
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
        log.debug("MarksheetCtl Method doGet Ended");
    }

    /**
     * Contains Submit logics
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        log.debug("MarksheetCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        MarksheetModel model = new MarksheetModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {

            MarksheetBean bean = (MarksheetBean) populateBean(request);
            try {
				if (id > 0) {
					model.update(bean);
					
					ServletUtility.setSuccessMessage("Data is Successfully Updated", request);
				} else {
					long pk = model.add(bean);
					request.setAttribute("pk", pk);
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
                ServletUtility.setErrorMessage("Roll No already exists",
                        request);
            }

        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            MarksheetBean Marksheetbean = (MarksheetBean) populateBean(request);
            System.out.println("in try");
            try {
                model.delete(Marksheetbean);
                ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,response);
                System.out.println("in try");
                return;
            } catch (ApplicationException e) {
                System.out.println("in catch");
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
                    response);
            return;

        }
        ServletUtility.forward(getView(), request, response);

        log.debug("MarksheetCtl Method doPost Ended");
    }
    /**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
    @Override
    protected String getView() {
        return ORSView.MARKSHEET_VIEW;
    }

}

