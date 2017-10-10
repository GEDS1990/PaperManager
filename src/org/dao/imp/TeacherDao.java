package org.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.dao.DBUtil;
import org.dao.ITeacherDao;
import org.model.Teacher;

public class TeacherDao implements ITeacherDao {

	@Override
	public boolean insert(Teacher teacher) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean delete(Teacher teacher) {
		String sql="delete from teacher where T_no="+teacher.getTeacherNo();
		return crudSql(sql);
	}

	@Override
	public boolean update(Teacher teacher) {
		String sql="update teacher set T_name ='"+teacher.getTeacherName()+"',T_tel='"+teacher.getTel()+"',T_content='"+teacher.getContent()+"' where T_no="+teacher.getTeacherNo();	
		System.out.println(sql);
		return crudSql(sql);
	}
	
	@Override
	public boolean updata_password(Teacher teacher) {
		String sql="update teacher set T_password='"+teacher.getTeacherPassword()+"' where T_no="+teacher.getTeacherNo();	
		return crudSql(sql);
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
	public boolean find(int id) {
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		int rowCount=0;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			String sql="select count(*) as rowcount from teacher where T_no="+id; 
			rs=stmt.executeQuery(sql);
			rs.next();
			rowCount = rs.getInt("rowCount");
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally{	
			DBUtil.closeQuietly(conn,stmt,rs);
		}
		if(rowCount>0)
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<Teacher> findAll() {
		String sql = null;
		sql="select * "
				+ "from teacher LEFT JOIN department ON teacher.D_no=department.D_no ";
		return findSql(sql);
	}

	@Override
	public ArrayList<Teacher> findSql(String sql) {
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;    
		ArrayList<Teacher> list=new ArrayList<Teacher>();
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();	    
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{  //TODO
//				System.out.println("111");
				Teacher teacher=new Teacher();
				teacher.setTeacherNo(rs.getInt("T_no"));
				teacher.setTeacherName(rs.getString("T_name"));
				teacher.setTeacherPassword(rs.getString("T_password"));
				teacher.setContent(rs.getString("T_content"));
				teacher.setTel(rs.getString("T_tel")); 
				teacher.setDepartmentNo(rs.getInt("D_no"));
				teacher.setDepartmentName(rs.getString("D_name"));
				teacher.setDepartmentTel(rs.getString("D_tel"));
				teacher.setPaperNumber(rs.getInt("T_paperNum"));
				teacher.setPaperAuNumber(rs.getInt("T_paperAuNum"));
				list.add(teacher);
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
