package com.raystech.proj4.testmodel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sunrays.proj4.bean.StudentBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.StudentModel;

/**
 * Stident Model Test classes
 * 
 * @author SunilOS
 * @version 1.0
 * 
 */

public class StudentModelTest {

	/**
	 * Model object to test
	 */

	public static StudentModel model = new StudentModel();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws ParseException
	 */

	public static void main(String[] args) throws ParseException {
		// testAdd();
		 testDelete();
		//testUpdate();
		// testFindByPK();
		// testFindByEmailId();
		 //testSearch();
		// testList();

	}

	/**
	 * Tests add a Student
	 * 
	 * @throws ParseException
	 */
	public static void testAdd() throws ParseException {

		try {
			StudentBean bean = new StudentBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			// bean.setId(1L);
			bean.setFirstName("rajat");
			bean.setLastName("singh");
			bean.setDob(sdf.parse("16/05/1995"));
			bean.setMobileNo("98278");
			bean.setEmail("rajatsingh@gmail.com");
			bean.setCollegeId(12);
			bean.setCreatedBy("rajat");
			bean.setModifiedBy("viru");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			StudentBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a Student by Emailid.
	 */
	public static void testFindByEmailId() {
		try {
			StudentBean bean = new StudentBean();
			bean = model.findByEmailId("rajatsingh@gmail.com");
			System.out.println("Test findbyEmailId success");
			if (bean == null) {
				System.out.println("Test Find By EmailId fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCollegeId());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a Student by PK.
	 */
	public static void testFindByPK() {
		try {
			StudentBean bean = new StudentBean();
			long pk = 10;
			bean = model.findByPK(pk);
			System.out.println("Test findByPK Success");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCollegeId());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a Student
	 */
	public static void testDelete() {

		try {
			StudentBean bean = new StudentBean();
			long pk = 15;
			bean.setId(pk);
			model.delete(bean);
			StudentBean deletedbean = model.findByPK(pk);
			if (deletedbean == null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Student
	 */
	public static void testUpdate() {

		try {
			StudentBean bean = model.findByPK(1);
			bean.setCollegeId(1);
			bean.setFirstName("mohit");
			bean.setLastName("mishra");
			model.update(bean);
			System.out.println("Test update Success");
			StudentBean updatedbean = model.findByPK(1);
			if (!"mohit".equals(updatedbean.getFirstName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}
	/**
     * Tests get Search
     */
    public static void testSearch() {

        try {
            StudentBean bean = new StudentBean();
            List list = new ArrayList();
            bean.setFirstName("mohit");
            System.out.println("Test Search Success");
            list = model.search(bean, 0, 0);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (StudentBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getDob());
                System.out.println(bean.getMobileNo());
                System.out.println(bean.getEmail());
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
            StudentBean bean = new StudentBean();
            List list = new ArrayList();
            list = model.list(1, 30);
            System.out.println("Test List Success");
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (StudentBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getFirstName());
                System.out.println(bean.getLastName());
                System.out.println(bean.getDob());
                System.out.println(bean.getMobileNo());
                System.out.println(bean.getEmail());
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
