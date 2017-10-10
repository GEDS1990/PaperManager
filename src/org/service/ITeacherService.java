package org.service;

import java.util.ArrayList;

import org.model.Teacher;

public interface ITeacherService {
	public boolean insert(Teacher teacher);
	public boolean delete(Teacher teacher);
	public boolean update(Teacher teacher);
	public boolean updata_password(Teacher teacher);
	public ArrayList<Teacher> findSql(String sql);
	public Object[][] getRecord(String sql);
	public Object[] getInfo(int i);
}
