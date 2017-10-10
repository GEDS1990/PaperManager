package org.dao;

import java.util.ArrayList;
import org.model.Department;

public interface IDepartmentDao {
	public boolean insert();
	public boolean delete();
	public boolean updata();
	public ArrayList<Department> findAll();
	public ArrayList<Department> findSql(String sql);
}
