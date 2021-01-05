package com.raystech.proj4.testmodel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sunrays.proj4.bean.FacultyBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.FacultyModel;

public class FacultyModelTest {
	public static FacultyModel model= new FacultyModel();
	
	public static void main (String[]args)throws ParseException, ApplicationException, DatabaseException{
		//testAdd();
		testUpdate();
		//testDelete();
		//testFindByEmailId();
		//testFindByPK();
		//testSearch();
		//testList();
		
	}
	public static void testFindByPK() throws ApplicationException, DatabaseException{
		try{
		FacultyBean bean= new FacultyBean();
		long pk=10;
		bean=model.findByPK(pk);
		System.out.println("Test FindByPk success");
		if(bean==null){
			System.out.println("Test FindByPk failed");
		}
		System.out.println(bean.getId());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getDOB());
		System.out.println(bean.getEmailId());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getSubject());
		}catch(ApplicationException e){
		e.printStackTrace();
	}

}
	public static void testFindByEmailId() throws ApplicationException{
		try{
		FacultyBean bean= new FacultyBean();
		bean= model.findByEmailId("ajay@gmail.com");
		System.out.println("Test findByEmailId success");
		if(bean==null){
			System.out.println("Test findByEmailId fail");
		}
		System.out.println(bean.getId());
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getDOB());
		System.out.println(bean.getEmailId());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getSubject());
		}catch(ApplicationException e){
		e.printStackTrace();
		
		
		
	}
}
	public static void testAdd()throws ParseException, DatabaseException{
		try{
    FacultyBean bean= new FacultyBean();
    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
            bean.setId(16L);
            bean.setCollegeName("TRUBA");;
 			bean.setFirstName("Rajat");
 			bean.setLastName("singh");
 			//bean.setCollegeName("MIT");
 			bean.setDOB(sdf.parse("16/05/1995"));
 			bean.setEmailId("rjjjj@gmail.com");
 			bean.setMobileNo("982788478");
 			bean.setCourseName("ME");
 			bean.setSubject("Maths");
 			bean.setCreatedBy("manojpatidar");
 			bean.setModifiedBy("manojpatidar");
 			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
 			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
 			long pk = model.add(bean);
 			FacultyBean addedbean = model.findByPK(pk);
 			if (addedbean == null) {
 				System.out.println("Test add fail");
 			}
 		} catch (ApplicationException e) {
 			e.printStackTrace();
 		} catch (DuplicateRecordException e) {
 			e.printStackTrace();
 		}
	}
	public static void testDelete() throws DatabaseException {

		try {
			FacultyBean bean = new FacultyBean();
			long pk = 16L;
			bean.setId(pk);
			model.delete(bean);
			FacultyBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	public static  void testUpdate() throws DatabaseException {

		try {
			FacultyBean bean = model.findByPK(2);
			bean.setCollegeId(2);
			bean.setFirstName("mohit");
			bean.setLastName("mishra");
			model.update(bean);
			System.out.println("Test update Success");
			FacultyBean updatedbean = model.findByPK(3);
			if (!"mohit".equals(updatedbean.getFirstName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}
	public static void testSearch() {

        try {
            FacultyBean bean = new FacultyBean();
            List list = new ArrayList();
            bean.setFirstName("mohit");
            System.out.println("Test Search Success");
            list = model.search(bean, 0, 0);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (FacultyBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getCollegeId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getDOB());
                System.out.println(bean.getMobileNo());
                System.out.println(bean.getEmailId());
                System.out.println(bean.getCollegeId());
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests get List.
     */
    public static void testList() {

        try {
        	FacultyBean bean = new FacultyBean();
            List list = new ArrayList();
            list = model.list(1, 30);
            System.out.println("Test List Success");
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (FacultyBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getDOB());
                System.out.println(bean.getMobileNo());
                System.out.println(bean.getEmailId());
                System.out.println(bean.getCollegeId());
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