package com.raystech.proj4.testmodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sunrays.proj4.bean.CourseBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.CourseModel;
/**
* Course Model Test classes
* 
* @author SunilOS
* @version 1.0
* 
*/

public class CourseModelTest {
	/**
     * Model object to test
     */
    public static CourseModel model = new CourseModel();

    /**
     * Main method to call test methods.
    
     *
     * Tests find a College by PK.
     */
  
	
	 public static void testFindByPK(){
	    	
	    	try {
	    		CourseBean bean = new CourseBean();
	    		long pk = 11;
	    		bean = model.findByPK(pk);
	    		if (bean == null) {
	    			System.out.println("Test findbypk is sucess");
					}
	    		System.out.println(bean.getId());
	    		System.out.println(bean.getName());
	    
	        	System.out.println(bean.getDescription());
	        	System.out.println(bean.getDuration());
	        	System.out.println(bean.getCreatedBy());
	        	System.out.println(bean.getModifiedBy());
	        	System.out.println(bean.getCreatedDatetime());
	        	System.out.println(bean.getModifiedDatetime());
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
	    }
	 /**
	     * Tests find a Course by Name.
	 * @throws DatabaseException 
	      
	     */

	    public static void testFindByName() throws DatabaseException {
	    	CourseBean bean = 
	    			model.findByName("MSC");
	    	if (bean == null) {
	    		System.out.println("Test findbyname is fail");
			}
	    	System.out.println(bean.getId());
	    	System.out.println(bean.getName());
	    	System.out.println(bean.getDescription());
	    	System.out.println(bean.getDuration());
	    	
	   
	    	System.out.println(bean.getCreatedBy());
	    	System.out.println(bean.getModifiedBy());
	    	System.out.println(bean.getCreatedDatetime());
	    	System.out.println(bean.getModifiedDatetime());
	    }
	    public static void testAdd() throws DuplicateRecordException, DatabaseException {

	        try {
	            CourseBean bean = new CourseBean();
	            // bean.setId(2L);
	            bean.setName("BA");
	            bean.setDescription("Bachelor Of Arts");
	            bean.setDuration("3 years");
	            bean.setCreatedBy("Admin");
	            bean.setModifiedBy("Admin");
	            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
	            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	            long pk = model.add(bean);
	           
	            CourseBean addedBean = model.findByPK(pk);
	            if (addedBean == null) {
	                System.out.println("Test add fail");
	            }else {
	            	 System.out.println("Test add succ");
				}
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	    /**
	     * Tests delete a College
	     */
	    
	    public static void testDelete(){
	    	
	    	try {
				CourseBean bean = new CourseBean();
				long pk =11l;
				bean.setId(pk);
				int i=model.delete(bean);
				System.out.println(i);
				System.out.println("Test Delete is Success " + bean.getId());
				CourseBean deletebean = model.findByPK(pk);
				if (deletebean == null) {
					System.out.println("Test Delete is Fail");
					
				}
			} catch (ApplicationException e) {
			 e.printStackTrace();
			}
	    }
	    /**
	     * Tests update a College
	     * @throws DatabaseException 
	     */
	    public static void testUpdate() throws DatabaseException {

	        try {
	            CourseBean bean = model.findByPK(14);
	            bean.setName("M.B.B.S");
	            bean.setDescription("Bachelor of MedicineSurgon");
	            bean.setDuration("5 years");
	            model.update(bean);
	            System.out.println("Test Update succ");
	            CourseBean updateBean = model.findByPK(2);
	            if (!"royal".equals(updateBean.getName())) {
	                System.out.println("Test Update fail");
	            }
	            	
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }

	    }
	    public static void testList() {

	        try {
	            CourseBean bean = new CourseBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CourseBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getDescription());
	                System.out.println(bean.getDuration());
	               
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getCreatedDatetime());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getModifiedDatetime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

  
public static void main(String[] args) {

	
		// TODO Auto-generated method stub
		//testFindByPK();
		//testFindByName();
		//testAdd();
		//testDelete();
		//testUpdate();
		//testList();
		
	
	

}

}
