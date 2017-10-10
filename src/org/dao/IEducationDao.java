package org.dao;

import java.util.ArrayList;

import org.model.Education;

public interface IEducationDao {
	public boolean find(int id);
	boolean updata_password(Education education);
	public ArrayList<Education> findAll();
	public ArrayList<Education> findSql(String sql);
	boolean crudSql(String sql);
}
