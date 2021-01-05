package com.sunrays.proj4.exception;
/**
 * DuplicateRecordException is propogate by DAO classes when an duplicate database exception occured
 * 
 * @author Interpreter
 * @version 1.0
 */
public class DuplicateRecordException extends Exception{
	
	public DuplicateRecordException(String msg){
		super(msg);
	}
	

}
