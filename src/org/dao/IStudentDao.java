package org.dao;

import java.util.ArrayList;

import org.model.Student;

public interface IStudentDao {
	public boolean insert(Student student);
	public boolean delete(Student student);
	public boolean update(Student student);
	public boolean upstate(Student student);
	boolean updata_password(Student student);
	public boolean crudSql(String sql);
	public boolean find(int id);
	public ArrayList<Student> findAll();
	public ArrayList<Student> findSql(String sql);
}
