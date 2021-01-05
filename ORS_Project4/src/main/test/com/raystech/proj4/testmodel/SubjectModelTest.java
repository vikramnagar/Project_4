package com.raystech.proj4.testmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sunrays.proj4.bean.SubjectBean;
import com.sunrays.proj4.exception.ApplicationException;
import com.sunrays.proj4.exception.DatabaseException;
import com.sunrays.proj4.exception.DuplicateRecordException;
import com.sunrays.proj4.model.SubjectModel;

public class SubjectModelTest {
	public static SubjectModel model = new SubjectModel();
public static void testAdd() throws DatabaseException, ApplicationException, DuplicateRecordException{
	SubjectBean bean=new SubjectBean();
	//bean.setId(model.nextPK());
	bean.setCourseId(15L);
	bean.setCourseName("Bnkm");
	bean.setName("Ma");
	bean.setDescription("ctguihfirjfj");
	model.add(bean);
	
}
public static void testFindByName() throws DatabaseException{
	SubjectBean bean=new SubjectBean();
	SubjectBean b=model.findByName("BE");
	if(b==null){
		System.out.println("No Subject Available");
	}else
	System.out.println(b.getName());
}
public static void testDelete() throws ApplicationException{
	SubjectBean bean=new SubjectBean();
	bean.setId(1);
	model.delete(bean);
}
public static void testUpdate() throws DatabaseException, DuplicateRecordException, ApplicationException{
	SubjectBean bean=new SubjectBean();
	bean.setId(1L);
	bean.setName("XYZ");
	bean.setCourseId(2);
	model.update(bean);
}
public static void testList() {

    try {
       SubjectBean bean = new SubjectBean();
        List list = new ArrayList();
        list = model.list(1, 10);
        if (list.size() < 0) {
            System.out.println("Test list fail");
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bean = (SubjectBean) it.next();
            System.out.println(bean.getId());
            System.out.println(bean.getName());
            System.out.println(bean.getDescription());
           
           
            System.out.println(bean.getCreatedBy());
            System.out.println(bean.getCreatedDatetime());
            System.out.println(bean.getModifiedBy());
            System.out.println(bean.getModifiedDatetime());
        }

    } catch (ApplicationException e) {
        e.printStackTrace();
    }}
public static void testfindbypk() throws ApplicationException{
	SubjectModel model=new SubjectModel();
	SubjectBean bean=new SubjectBean();
	bean.setId(1L);
	bean=model.findByPK(1L);
	System.out.println(bean.getName());
}

	public static void main(String[] args) throws DatabaseException, ApplicationException, DuplicateRecordException {
		// long pk=model.nextPK();
		// System.out.println(pk);
		//testAdd();
		//testFindByName();
		//testDelete();
		//testUpdate();
		//testList();
		testfindbypk();
	}
}
