package org.service.imp;

import java.util.ArrayList;

import org.dao.IEducationDao;
import org.dao.imp.EducationDao;
import org.model.Education;
import org.service.IEducationService;

public class EducationService implements IEducationService {

	IEducationDao dao=new EducationDao();
	@Override
	public Object[] getInfo(int i) {
		Object a[];
		ArrayList<Education> educationList=new ArrayList<Education>();
		String sql="select * from education where E_no="+i;
		educationList=dao.findSql(sql);	
		a=new Object[3];
		for(Education education:educationList)
		{
			a[0]=education.getEducationName();
			a[1]=education.getEducationTel();
			a[2]=education.getEduPassword();
		}
		return a;
	}
	
	@Override
	public boolean updata_password(Education education) {
		return dao.updata_password(education);
	}

}
