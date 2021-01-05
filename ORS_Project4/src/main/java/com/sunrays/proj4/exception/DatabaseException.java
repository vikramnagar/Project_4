package com.sunrays.proj4.exception;
/**
 * DatabaseException is propogate by DAO classes when an database exception occured
 * 
* @author Interpreter
 * @version 1.0
 */public class DatabaseException extends Exception {
	
	public DatabaseException(String msg) {
		super(msg);
	}

}
