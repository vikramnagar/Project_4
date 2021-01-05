package com.raystech.proj4.testmodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sunrays.proj4.bean.CollegeBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.CollegeModel;

/**
* College Model Test classes
* 
* @author SunilOS
* @version 1.0
* 
*/

public class CollegeModelTest {

	/**
     * Model object to test
     */
    public static CollegeModel model = new CollegeModel();

    /**
     * Main method to call test methods.
     * 
     * @param args
     * @throws DuplicateRecordException
     */
    public static void main(String[] args) throws DuplicateRecordException, ApplicationException {
         testAdd();
        //testDelete();
        //testUpdate();
         //testFindByName();
       //  testFindByPK();
        // testSearch();
        //testList();

    }

    /**
     * Tests add a College
     * 
     * @throws DuplicateRecordException
     */
    public static void testAdd() throws DuplicateRecordException {

        try {
            CollegeBean bean = new CollegeBean();
            // bean.setId(2L);
            bean.setName("virat");
            bean.setAddress("kholi");
            bean.setState("mp");
            bean.setCity("ratlam");
            bean.setPhoneNo("98278");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
            long pk = model.add(bean);
           
            CollegeBean addedBean = model.findByPK(pk);
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
     * Tests find a College by Name.
      
     */

    public static void testFindByName() {
    	try{
    	CollegeBean bean = model.findByName("virat");
    	if (bean == null) {
    		System.out.println("Test findbyname is fail");
		}
    	System.out.println(bean.getId());
    	System.out.println(bean.getName());
    	System.out.println(bean.getAddress());
    	System.out.println(bean.getState());
    	System.out.println(bean.getCity());
    	System.out.println(bean.getPhoneNo());
    	System.out.println(bean.getCreatedBy());
    	System.out.println(bean.getModifiedBy());
    	System.out.println(bean.getCreatedDatetime());
    	System.out.println(bean.getModifiedDatetime());
    	}catch (ApplicationException e) {
			e.printStackTrace();
		}
    }
    /**
     * Tests find a College by PK.
     */
   
    public static void testFindByPK(){
    	
    	try {
    		CollegeBean bean = new CollegeBean();
    		long pk = 15;
    		bean = model.findByPK(pk);
    		if (bean == null) {
    			System.out.println("Test findbypk is fail");
				}
    		System.out.println(bean.getId());
    		System.out.println(bean.getName());
        	System.out.println(bean.getAddress());
        	System.out.println(bean.getState());
        	System.out.println(bean.getCity());
        	System.out.println(bean.getPhoneNo());
        	System.out.println(bean.getCreatedBy());
        	System.out.println(bean.getModifiedBy());
        	System.out.println(bean.getCreatedDatetime());
        	System.out.println(bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
    }
	
    /**
     * Tests delete a College
     */
    
    public static void testDelete(){
    	
    	try {
			CollegeBean bean = new CollegeBean();
			long pk =6;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete is Success " + bean.getId());
			CollegeBean deletebean = model.findByPK(pk);
			if (deletebean == null) {
				System.out.println("Test Delete is Fail");
				
			}
		} catch (ApplicationException e) {
		 e.printStackTrace();
		}
    }
    

    /**
     * Tests update a College
     */
    public static void testUpdate() {

        try {
            CollegeBean bean = model.findByPK(2);
            bean.setName("defs");
            bean.setAddress("ratlam");
            bean.setCity("ratlam");
            model.update(bean);
            System.out.println("Test Update succ");
            CollegeBean updateBean = model.findByPK(2);
            if (!"royal".equals(updateBean.getName())) {
                System.out.println("Test Update fail");
            }
            	
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests search a College by Name
     */

    public static void testSearch() {
        try {
            CollegeBean bean = new CollegeBean();
            List list = new ArrayList();
			bean.setName("virat");
			list = model.search(bean, 0, 0);
			if (list.size() == 0) {
				System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (CollegeBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getName());
                System.out.println(bean.getAddress());
                System.out.println(bean.getState());
                System.out.println(bean.getCity());
                System.out.println(bean.getPhoneNo());
                System.out.println(bean.getCreatedBy());
                System.out.println(bean.getCreatedDatetime());
                System.out.println(bean.getModifiedBy());
                System.out.println(bean.getModifiedDatetime());
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests get List a College.
     */
    public static void testList() {

        try {
            CollegeBean bean = new CollegeBean();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (CollegeBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getName());
                System.out.println(bean.getAddress());
                System.out.println(bean.getState());
                System.out.println(bean.getCity());
                System.out.println(bean.getPhoneNo());
                System.out.println(bean.getCreatedBy());
                System.out.println(bean.getCreatedDatetime());
                System.out.println(bean.getModifiedBy());
                System.out.println(bean.getModifiedDatetime());
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

}











