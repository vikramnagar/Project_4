package com.raystech.proj4.testmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sunrays.proj4.bean.TimeTableBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.TimeTableModel;

public class TimeTableModelTest {
	 
	public static void testpk() throws DatabaseException{
		TimeTableModel model=new TimeTableModel();
		int pk=model.nextPK();
		System.out.println(pk);
	}
	public static void testFindByPK() throws ApplicationException, DatabaseException{
		try{TimeTableModel model=new TimeTableModel();
		TimeTableBean bean= new TimeTableBean();
		long pk=10;
		bean=model.findByPK(pk);
		System.out.println("Test FindByPk success");
		if(bean==null){
			System.out.println("Test FindByPk failed");
		}
		System.out.println(bean.getId());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getSubject());
		System.out.println(bean.getSubjectId());
		
		System.out.println(bean.getSubject());
		}catch(ApplicationException e){
		e.printStackTrace();
	}

}
	public static void testAdd() throws ApplicationException, DuplicateRecordException, ParseException{
		TimeTableModel model=new TimeTableModel();
		TimeTableBean bean =new TimeTableBean();
		//bean.setId(15L);
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		bean.setCourseId(5L);
		bean.setSubjectId(2L);
		bean.setSemester("8th");
		//bean.setExamDate(Fri Sep 15 23:50:39 IST 2017);
		bean.setExamDate(sdf.parse("16/05/1995"));
		long pk=model.add(bean);
		if(pk==0){
			System.out.println("NOt ADD");
		}
		else
			System.out.println(" Time Table ADD");
	}
public static void testfindByCourseAndSub() throws ApplicationException{
	TimeTableModel model=new TimeTableModel();
	TimeTableBean bean=model.findByCourseAndSub(1L, 1L);
	System.out.println(bean.getSubject());
}
 
/**
 * Tests get Search
 */
public static void testSearch() {

	try {
		TimeTableBean bean = new TimeTableBean();
		TimeTableModel model = new TimeTableModel();
		List list = new ArrayList();
		bean.setCourseId(10);
		list = model.search(bean, 0, 0);
		if (list.size() == 0) {
			System.out.println("Test Serach fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (TimeTableBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getSubject());
			System.out.println(bean.getSemester());
			System.out.println(bean.getExamDate());
			System.out.println(bean.getExamTime());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
		}

	} catch (ApplicationException e) {
		e.printStackTrace();
	}

}

	
	public static void main(String[] args) throws DatabaseException, ApplicationException, DuplicateRecordException, ParseException {
		
		//testpk();
		//testFindByPK();
		//testAdd();
		testSearch();
		//testfindByCourseSemAndSub();
		/*Date d=new Date();
		System.out.println(d);*/
	}

}
