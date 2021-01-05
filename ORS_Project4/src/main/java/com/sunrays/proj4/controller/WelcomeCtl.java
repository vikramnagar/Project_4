package com.sunrays.proj4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sunrays.proj4.util.ServletUtility;

/**
 * Welcome functionality Controller. Performs operation for Show Welcome page
 * 
 * @author Interpreter
 * @version 1.0
 */

@WebServlet(name = "WelcomeCtl", urlPatterns = { "/WelcomeCtl" })
public class WelcomeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(WelcomeCtl.class);
	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("WelcomeCtl Method doGet Started");

		ServletUtility.forward(getView(), request, response);

		log.debug("WelcomeCtl Method doGet Ended");

	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return String
	 */
	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}
}