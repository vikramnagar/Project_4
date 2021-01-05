
package com.sunrays.proj4.exception;
/**
 * ApplicationException is propogate from service classes when business logic exception occured
 * 
 * @author Interpreter
 * @version 1.0
 */
public class ApplicationException extends Exception {
	
	public ApplicationException(String msg){
		super(msg);
		
	}

}
