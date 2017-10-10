package org.dao;

import java.util.ArrayList;

import org.model.Teacher;

public interface ITeacherDao {
	public boolean insert(Teacher teacher);
	public boolean delete(Teacher teacher);
	public boolean update(Teacher teacher);
	boolean updata_password(Teacher teacher);
	public boolean crudSql(String sql);
	public boolean find(int id);
	public ArrayList<Teacher> findAll();
	public ArrayList<Teacher> findSql(String sql);
}
