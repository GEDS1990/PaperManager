package org.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.dao.DBUtil;
import org.dao.IEducationDao;
import org.model.Education;

public class EducationDao implements IEducationDao {

	@Override
	public boolean find(int id) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public ArrayList<Education> findAll() {
		String sql = null;
		sql="select * from education";
		return findSql(sql);
	}

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
	public boolean updata_password(Education education) {
		String sql="update education set E_password='"+education.getEduPassword()+"' where E_no="+education.getEducationNo();	
		return crudSql(sql);
	}
	
	@Override
	public ArrayList<Education> findSql(String sql) {
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;    
		ArrayList<Education> list=new ArrayList<Education>();
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();	    
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				Education education=new Education();
				education.setEducationNo(rs.getInt("E_no"));
				education.setEducationName(rs.getString("E_name"));
				education.setEducationTel(rs.getString("E_tel"));
				education.setEduPassword(rs.getString("E_password"));
				list.add(education);
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
