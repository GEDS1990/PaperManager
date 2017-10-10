package org.service;

import org.model.Student;

public interface IStudentService {
	public boolean updata_password(Student student);
	public Object[] getInfo(String i);
	public Object[][] getRecord_chooseInfo(String sql);
}
