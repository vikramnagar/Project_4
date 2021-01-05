package com.sunrays.proj4.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class validates input data
 * 
 * @author Interpreter
 * @version 1.0
 */

public class DataValidator {

	/**
	 * Checks if value is Null
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is NOT Null
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	/**
	 * Checks if value is an Integer
	 * 
	 * @param val
	 * @return boolean
	 */

	public static boolean isInteger(String val) {

		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}
	
	/**
	 * Checks if value is String
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isString(String val) {

		if (isNotNull(val)) {
			try {
				//int i = Integer.parseInt(val);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value contains White Space
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isWhiteSpace(String val) 
	{
		for (int i = 0; i < val.length(); i++) 
		{
			if (val.charAt(i) == ' ') 
			{
				if (val.matches("^[a-zA-Z  ]")) 
				{
					return true;
				}
			} else if (val.matches(".*\\s+.*")) 
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if value contains isSpace
	 * @param val
	 * @return boolean
	 */
	public static boolean isSpace(String val) {
		if (val.matches("[a-z ]")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Long
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isLong(String val) 
	{
		if (isNotNull(val)) 
		{
			try {
				long i = Long.parseLong(val);
				return true;
			    } catch (NumberFormatException e) 
			    {
				return false;
			    }

		} else 
		{
			return false;
		}
	}

	/**
	 * Checks if value is valid Email ID
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isEmail(String val)
	{

		String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
				          + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
				                      + "(\\.[A-Za-z]{2,})$";

		if (isNotNull(val)) 
		{
		try {
				return val.matches(emailreg);
			} catch (NumberFormatException e) 
			{
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is Date
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	/**
	 * Checks if value is valid isName
	 * @param val
	 * @return boolean
	 */
	public static boolean isName(String val) {
		// System.out.println("fname called");

		String fnamereg = "[a-zA-Z][a-zA-Z ]+";

		if (isNotNull(val)) {
			try {
				return val.matches(fnamereg);
				// System.out.println("matches");
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Subject 
	 * @param val
	 * @return boolean
	 */
	public static boolean isSubject(String val) {
		// System.out.println("fname called");

		String fnamereg = "[a-zA-Z][a-zA-Z0-9!@#+$%^&*]+";

		if (isNotNull(val)) 
		{
		try {
				return val.matches(fnamereg);
				// System.out.println("matches");
			} catch (NumberFormatException e) 
			{
				return false;
			}

		} else 
		{
			return false;
		}
	}

	/**
	 * Checks if values Length is 10 or not
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isMobLength(String val) {

		if (val.length() == 10) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Mobile Number
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isMob(String val) {

		String mo = "^[7-9][0-9]{9}$";

		if (val.matches(mo)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid phone Number
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isPhone(String val) {

		String mo = "^[0/91]+[7-9][0-9]{9}$";

		if (val.matches(mo)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid Password
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isPassword(String val) {

		String pwd = "^[a-zA-Z0-9!@#$%^&*]{6,12}$";

		if (isNotNull(val)) {
			try {
				return val.matches(pwd);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid isSpecial
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isSpecial(String val) {

		String pwd = "^[a-zA-Z0-9_.-]*$";

		if (isNotNull(val)) {
			try {
				return val.matches(pwd);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if lenght of Password is permitted
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isPasslength(String val) {

		if (isNotNull(val)) {
			if (val.length() > 5 && val.length() <= 12) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Checks if value is valid Date of Birth
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isDOB(String val) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date userDate = format.parse(val);
		Date todayDate = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(todayDate);
		cal.add(Calendar.YEAR, -17);

		
		Date beforedate = cal.getTime();
		if (beforedate.compareTo(userDate) == -1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if value is valid Roll Number
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isRollNo(String val) {

		String pwd = "[0-9]+[a-zA-Z_]+[0-9]+";

		if (isNotNull(val)) {
			try {
				return val.matches(pwd);
			} catch (NumberFormatException e) {
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid isExamDate
	 * 
	 * @param val
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean isExamDate(String val) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date userDate = format.parse(val);
		Date todayDate = new Date();

		if (userDate.compareTo(todayDate) == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if value is valid exam date
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean futureDate(String val) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date userDate = format.parse(val);
		Date todayDate = new Date();
		if (userDate.compareTo(todayDate) == -1 || userDate.compareTo(todayDate) == 0) {
			return false;
		} else {
			return true;
		}
	}
	


	
	/**
	 * Checks if value is valid Date
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isValidDate(String val) throws ParseException {
	    SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
		Date date= format.parse(val);
		Date today= new Date();
	    Calendar cal= Calendar.getInstance();
	    cal.setTime(today);
	    cal.add(Calendar.YEAR, -17);
	    Date before= cal.getTime();
	    
		if(before.compareTo(date)==-1) {
			return false;
		}
		else{
			return true;
		}
	}
	
	
	/*public static boolean isExamFutureDate(String val) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date userDate = format.parse(val);
		Date todayDate = new Date();
		System.out.println(userDate.getDay());
		System.out.println(todayDate.getDay() + 3);
		if (userDate.getDay() > (todayDate.getDay() + 3)) {
			return true;
		} else {
			return false;
		}
	}*/

	/**
	 * Test above methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Not Null 2" + isNotNull("ABC"));
		System.out.println("Not Null 3" + isNotNull(null));
		System.out.println("Not Null 4" + isNull("123"));

		System.out.println("Is Int " + isInteger(null));
		System.out.println("Is Int " + isInteger("ABC1"));
		System.out.println("Is Int " + isInteger("123"));
		System.out.println("Is Int " + isNotNull("123"));
	}

}
