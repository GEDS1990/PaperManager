package org.service.imp;

import java.util.ArrayList;

import org.dao.ITeacherDao;
import org.dao.imp.TeacherDao;
import org.model.Teacher;
import org.service.ITeacherService;

public class TeacherService implements ITeacherService {

	ITeacherDao dao=new TeacherDao();

	@Override
	public boolean insert(Teacher teacher) {
		return dao.insert(teacher);
	}

	@Override
	public boolean delete(Teacher teacher) {
		if(dao.find(teacher.getTeacherNo()))
			return dao.delete(teacher);
		else
			return false;
	}

	@Override
	public boolean update(Teacher teacher) {
		if(dao.find(teacher.getTeacherNo()))
			return dao.update(teacher);
		else
			return false;
	}

	@Override
	public boolean updata_password(Teacher teacher) {
		return dao.updata_password(teacher);
	}

	@Override
	public ArrayList<Teacher> findSql(String sql) {
		return dao.findSql(sql);
	}

	@Override
	public Object[][] getRecord(String sql) {
		return null;//TODO
		
	}
	
	@Override
	public Object[] getInfo(int i) {
		Object a[];
		ArrayList<Teacher> teacherList=new ArrayList<Teacher>();
		String sql="select * "
				+ "from teacher LEFT JOIN department ON teacher.D_no=department.D_no where T_no="+i;
		teacherList=dao.findSql(sql);
		a=new Object[9];
		for(Teacher teacher:teacherList)
		{
			a[0]=teacher.getTeacherName();
			a[1]=teacher.getDepartmentName();
			a[2]=teacher.getDepartmentTel();
			a[3]=teacher.getTel();
			a[4]=teacher.getContent();
			a[5]=teacher.getPaperNumber();
			a[6]=teacher.getPaperAuNumber();
			a[7]=teacher.getDepartmentNo();
			a[8]=teacher.getTeacherPassword();
		}
		return a;
	}

}
