package org.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.dao.DBUtil;
import org.dao.IDepartmentDao;
import org.model.Department;

public class DepartmentDao implements IDepartmentDao {

	@Override
	public boolean insert() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean delete() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean updata() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public ArrayList<Department> findAll() {
		String sql = null;
		sql="select * from department";
		return findSql(sql);
	}

	@Override
	public ArrayList<Department> findSql(String sql) {
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;    
		ArrayList<Department> list=new ArrayList<Department>();
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();	    
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				Department department=new Department();
				department.setDeparNo(rs.getInt("D_no"));
				department.setDeparName(rs.getString("D_name"));
				department.setDeparTel(rs.getString("D_tel"));
				list.add(department);
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
