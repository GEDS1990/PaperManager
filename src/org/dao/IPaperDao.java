package org.dao;

import java.util.ArrayList;

import org.model.Paper;

public interface IPaperDao {
	public boolean insert(Paper paper);
	public boolean delete(Paper paper);
	public boolean update(Paper paper);
	public boolean upstate(Paper paper);
	public boolean upstate_Edu(Paper paper);
	public boolean upstate_ZJ(Paper paper);
	public boolean crudSql(String sql);
	public boolean find(int id);
	public ArrayList<Paper> findAll();
	public ArrayList<Paper> findSql(String sql);
}
