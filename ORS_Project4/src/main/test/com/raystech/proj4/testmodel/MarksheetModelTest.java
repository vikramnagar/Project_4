package com.raystech.proj4.testmodel;

import com.sunrays.proj4.bean.MarksheetBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.MarksheetModel;

/**
 * Marksheet Model Test classes
 * 
 * @author SunilOS
 * @version 1.0
 * 
 */

public class MarksheetModelTest {

	/**
     * Model object to test
     */

    public static MarksheetModel model = new MarksheetModel();

    /**
     * Main method to call test methods.
     * 
     * @param args
     */
    public static void main(String[] args) {
         testAdd();
        // testDelete();
        // testUpdate();
        //testFindByRollNo();
        // testFindByPK();
        // testSearch();
        // testMeritList();
       // testList();

    }

    /**
     * Tests add a Marksheet
     */
    public static void testAdd() {

        try {
            MarksheetBean bean = new MarksheetBean();
            // bean.setId(1L);
            bean.setRollNO("1212");;
            bean.setPhysics(88);
            bean.setChemistry(77);
            bean.setMaths(99);
            bean.setStudentId(1);
            long pk = model.add(bean);
            System.out.println("Test add success");
            MarksheetBean addedbean = model.findByPK(pk);
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
     * Tests find a marksheet by Roll No.
     */

    public static void testFindByRollNo() {

        try {
            MarksheetBean bean = model.findByRollNo("1");
            System.out.println("Test findByRollNo success");
            if (bean == null) {
                System.out.println("Test Find By RollNo fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getRollNO());
            System.out.println(bean.getName());
            System.out.println(bean.getPhysics());
            System.out.println(bean.getChemistry());
            System.out.println(bean.getMaths());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
    /**
     * Tests find a marksheet by PK.
     */
    public static void testFindByPK() {
        try {
            MarksheetBean bean = new MarksheetBean();
            long pk = 1;
            bean = model.findByPK(pk);
            System.out.println("Test findByPK success");
            if (bean == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(bean.getId());
            System.out.println(bean.getRollNO());
            System.out.println(bean.getName());
            System.out.println(bean.getPhysics());
            System.out.println(bean.getChemistry());
            System.out.println(bean.getMaths());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }


}









