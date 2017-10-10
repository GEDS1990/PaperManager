package org.service;

import java.util.ArrayList;

import org.model.Paper;

public interface IPaperService {
	public boolean insert(Paper paper);
	public boolean delete(Paper paper);
	public boolean update(Paper paper);	
	public boolean upstate(Paper paper);	
	public boolean upstate_Edu(Paper paper);
	public boolean upstate_ZJ(Paper paper);
	public ArrayList<Paper> findSql(String sql);
	public Object[][] getRecord(String sql);
	public Object[][] getRecord_Unpass(String sql);
	public Object[][] getRecord_Unrelese(String sql);
	public Object[][] getRecord_Relese(String sql);
	public Object[][] getRecord_All(String sql);
	public Object[][] getRecord_Edu(String sql);
	public Object[][] getInfo(int i);
}
