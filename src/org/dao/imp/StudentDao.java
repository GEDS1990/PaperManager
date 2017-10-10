package org.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.dao.DBUtil;
import org.dao.IStudentDao;
import org.model.Student;

public class StudentDao implements IStudentDao {

	@Override
	public boolean insert(Student student) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean delete(Student student) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean update(Student student) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean upstate(Student student) {
		// TODO 自动生成的方法存根
		return false;
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
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean updata_password(Student student) {
		String sql="update student set S_password='"+student.getStudentPassword()+"' where S_no="+student.getStudentNo();	
		return crudSql(sql);
	}
	
	@Override
	public ArrayList<Student> findAll() {
		String sql = null;
		sql="select * from student "
				+ "LEFT JOIN major ON student.M_no=major.M_no "
				+ "LEFT JOIN department ON major.D_no=department.D_no ";
		return findSql(sql);
	}

	@Override
	public ArrayList<Student> findSql(String sql) {
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;    
		ArrayList<Student> list=new ArrayList<Student>();
		try {
//			System.out.println(sql);
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();	    
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{  //TODO
				Student student=new Student();
				student.setStudentNo(rs.getString("S_no"));
				student.setStudentName(rs.getString("S_name"));
				student.setStudentSex(rs.getString("S_sex"));
				student.setStudentClass(rs.getString("S_class"));
				student.setStudentTel(rs.getString("S_tel"));
				student.setMajorNo(rs.getInt("M_no"));
				student.setMajorName(rs.getString("M_name"));
				student.setDepartmentNo(rs.getInt("D_no"));
				student.setDepartmentName(rs.getString("D_name"));
				student.setDepartmentTel(rs.getString("D_tel"));
				student.setStudentPassword(rs.getString("S_password"));
				list.add(student);
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
