package com.raystech.proj4.testmodel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sunrays.proj4.bean.RoleBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.RoleModel;

/**
 * Role Model Test classes
 * 
 * @author SunilOS
 * @version 1.0
 * 
 */

public class RoleModelTest {

	/**
	 * Model object to test
	 */

	public static RoleModel model = new RoleModel();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testAdd();
		// testDelete();
		// testUpdate();
		// testFindByPK();
		// testFindByName();
		// testSearch();
		// testList();

	}

	/**
	 * Tests add a Role
	 */
	public static void testAdd() {

		try {
			RoleBean bean = new RoleBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// bean.setId(1L);
			bean.setName("rudra");
			bean.setDescription("singh");
			bean.setCreatedBy("virendra");
			bean.setModifiedBy("viru");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			RoleBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			} else {
				System.out.println("Test add success");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			RoleBean bean = new RoleBean();
			long pk = 5L;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Name.
	 */
	public static void testFindByName() {
		try {
			RoleBean bean = new RoleBean();
			bean = model.findByName("shiva");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests delete a Role
	 */
	public static void testDelete() {

		try {
			RoleBean bean = new RoleBean();
			long pk = 6L;
			bean.setId(pk);
			model.delete(bean);
			RoleBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			} else {
				System.out.println("Test Delete success");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Role
	 */
	public static void testUpdate() {

		try {
			RoleBean bean = model.findByPK(7L);
			bean.setName("rajat");
			bean.setDescription("bhati");
			model.update(bean);

			RoleBean updatedbean = model.findByPK(7L);
			if (!"rajat".equals(updatedbean.getName())) {
				System.out.println("Test Update fail");
			} else {
				System.out.println("Test Update success");
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
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			bean.setName("sonu");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			} else {
				System.out.println("Test search success");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
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
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			} else {
				System.out.println("Test list success");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
