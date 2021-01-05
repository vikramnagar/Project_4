package com.raystech.proj4.testmodel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.sunrays.proj4.bean.UserBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.exception.RecordNotFoundException;
import com.sunrays.proj4.model.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * User Model Test classes
 * 
 * @author SunilOS
 * @version 1.0
 * 
 */

public class UserModelTest {

	/**
	 * Model object to test
	 */

	public static UserModel model = new UserModel();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws Exception 
	 */

	public static void main(String[] args) throws Exception {
		 testadd();
		 //testFindByPK();
		//testDelete();
		//testUpdate();
		// testAuthenticate();
		// testFindByLogin();
		// testSearch();
		// testList();
		
	
		//testchangePassword();
	 //testforgetPassword();
		// testRegisterUser();
	}

	/**
	 * Tests add a User
	 * @throws Exception 
	 */

	private static void testadd() throws Exception {

		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyyy");

			//bean.setId(5454l);
			bean.setFirstName("abhishek");
			bean.setLastName("sharma");
			bean.setLogin("111absharma@gamil.com");
			bean.setPassword("12346");
			bean.setConfirmPassword("123456");
			bean.setDob(sdf.parse("25-08-1996"));
			bean.setMobileNo("98278");
			bean.setRoleId(1);
			bean.setUnSuccessfulLogin(2);
			bean.setGender("Male");
			bean.setLastLogin(new Timestamp(new Date().getTime()));
			bean.setLock("Yes");

			
			long pk = model.add(bean);
			UserBean addedbean = model.findByPK(pk);
			System.out.println("Test add succ");
			if (addedbean == null) {
				System.out.println("Test add fail");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			UserBean bean = new UserBean();
			long pk = 48;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getLock());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests delete a User
	 */

	public static void testDelete() {

		try {
			UserBean bean = new UserBean();
			long pk = 46l;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete succ" + bean.getId());
			UserBean deletedbean = model.findByPK(pk);
			if (deletedbean == null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a User
	 * @throws DuplicateRecordException 
	 */
	public static void testUpdate() throws DuplicateRecordException {

		try {
			UserBean bean = model.findByPK(10);
			bean.setFirstName("pb");
			bean.setLastName("bhati");
			bean.setLogin("raj@gmail.com");
			bean.setPassword("123456");

			model.update(bean);

			UserBean updatedbean = model.findByPK(1L);
			if (!"pp".equals(updatedbean.getLogin())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests authenticate User.
	 */
	public static void testAuthenticate() {

		try {
			UserBean bean = new UserBean();
			bean.setLogin("viru@gmail.com");
			bean.setPassword("bhati");
			bean = model.authenticate(bean.getLogin(), bean.getPassword());
			if (bean != null) {
				System.out.println("Successfully login");

			} else {
				System.out.println("Invalid login Id & password");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by Login.
	 */
	public static void testFindByLogin() {
		try {
			UserBean bean = new UserBean();
			bean = model.findByLogin("viru@gmail.com");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getLock());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			bean.setFirstName("virendra");
			list = model.search(bean, 0, 0);
			if (list.size() == 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDob());
				System.out.println(bean.getRoleId());
				System.out.println(bean.getUnSuccessfulLogin());
				System.out.println(bean.getGender());
				System.out.println(bean.getLastLogin());
				System.out.println(bean.getLock());
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
			UserBean bean = new UserBean();
			List list = new ArrayList();
			list = model.list(1, 50);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDob());
				System.out.println(bean.getRoleId());
				System.out.println(bean.getUnSuccessfulLogin());
				System.out.println(bean.getGender());
				System.out.println(bean.getLastLogin());
				System.out.println(bean.getLock());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests changepassword
	 * 
	 * @throws DuplicateRecordException
	 
	 */
	public static void testchangePassword() throws DuplicateRecordException {

		try {
			UserBean bean = model.findByLogin("rahulspanwar1989@gmail.com");
			String oldPassword = "rahul";
			long id=52l;
		/*	bean.setId(49l);
			bean.setPassword("pooja");
			bean.setConfirmPassword("pooja");*/
			String newPassword = "ram";
			try {
				model.changePassword(id, oldPassword, newPassword);
				System.out.println("password has been change successfully");
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests forgetPassword

	 */
	public static void testforgetPassword() {
		try {
			boolean b = model.forgetPassword("rahulspanwar1989@gmail.com");

			System.out.println("Suucess : Test Forget Password Success");

		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests add a User register
	 * @throws Exception 
	 */

	public static void testRegisterUser() throws Exception {
		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			// bean.setId(8L);
			bean.setFirstName("rajat");
			bean.setLastName("bhati");
			bean.setLogin("rj@gmail.com");
			bean.setPassword("rj");
			bean.setConfirmPassword("1212");
			bean.setDob(sdf.parse("05/16/2015"));
			bean.setGender("Male");
		bean.setRoleId(21);
			long pk = model.registerUser(bean);
			System.out.println("Successfully register");
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDob());
			UserBean registerbean = model.findByPK(pk);
			if (registerbean != null) {
				System.out.println("Test registation fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

}
