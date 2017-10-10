package org.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.dao.DBUtil;
import org.dao.IZhuanJiaDao;
import org.model.ZJ;

public class ZhuanJiaDao implements IZhuanJiaDao {
	@Override
	public boolean crudSql(String sql) {
		int num=0;
		Connection conn=null;
		Statement  stmt=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();		
			num=stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally{	
			DBUtil.closeQuietly(conn, stmt);
		}
		if(num>0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean updata_password(ZJ zhuanjia) {
		String sql="update zhuanjia set Z_password='"+zhuanjia.getZjPassword()+"' where Z_no="+zhuanjia.getZjNo();
		return crudSql(sql);
	}
	
	@Override
	public ArrayList<ZJ> findAll() {
		String sql = null;
		sql="select * from zhuanjia";
		return findSql(sql);
	}

	@Override
	public ArrayList<ZJ> findSql(String sql) {
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;    
		ArrayList<ZJ> list=new ArrayList<ZJ>();
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();	    
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				ZJ zhuanjia=new ZJ();
				zhuanjia.setZjNo(rs.getInt("Z_no"));
				zhuanjia.setZjName(rs.getString("Z_name"));
				zhuanjia.setZjTel(rs.getString("Z_tel"));
				zhuanjia.setZjPassword(rs.getString("Z_password"));
				list.add(zhuanjia);
			}
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{    
			DBUtil.closeQuietly(conn, stmt, rs);
		}
		return list;
	}

}
