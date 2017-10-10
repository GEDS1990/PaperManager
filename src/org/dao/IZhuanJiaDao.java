package org.dao;

import java.util.ArrayList;

import org.model.ZJ;

public interface IZhuanJiaDao {
	boolean updata_password(ZJ zhuanjia);
	public boolean crudSql(String sql);
	public ArrayList<ZJ> findAll();
	public ArrayList<ZJ> findSql(String sql);
}
