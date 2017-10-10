package org.service.imp;

import java.util.ArrayList;

import org.dao.IDepartmentDao;
import org.dao.imp.DepartmentDao;
import org.model.Department;
import org.service.IDepartmentService;

public class DepartmentService implements IDepartmentService {

	IDepartmentDao dao=new DepartmentDao();
	@Override
	public Object[][] getRecord_Depar() {
		Object a[][];
		int m=0;
		ArrayList<Department> list=new ArrayList<Department>();
		list=dao.findAll();	
		int row=list.size();
		a=new Object[row][2];
		for(Department paper:list)
		{
			a[m][0]=paper.getDeparNo();
			a[m][1]=paper.getDeparName();
			m++;
		}
		return a;
	}

}
