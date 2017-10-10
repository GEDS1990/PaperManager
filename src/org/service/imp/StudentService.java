package org.service.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.dao.DBUtil;
import org.dao.IStudentDao;
import org.dao.imp.StudentDao;
import org.model.Student;
import org.service.IStudentService;

public class StudentService implements IStudentService {

	IStudentDao dao=new StudentDao();
	
	@Override
	public boolean updata_password(Student student) {
		return dao.updata_password(student);
	}
	
	@Override
	public Object[][] getRecord_chooseInfo(String sql) {
		Object a[][];
		int m=0;
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;    
		ArrayList<Student> list=new ArrayList<Student>();
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();	    
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{  //TODO
				Student student=new Student();
				student.setChooseNumber(rs.getInt(4)-rs.getInt(5));
				student.setClassNumber(rs.getInt(4));
				student.setStudentClass(rs.getString(3));
				student.setMajorName(rs.getString(2));
				student.setDepartmentName(rs.getString(1));
				list.add(student);
			}
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{    
			DBUtil.closeQuietly(conn, stmt, rs);
		}
		int row=list.size();
		a=new Object[row][6];
		for(Student student:list)
		{
			a[m][0]=m+1;
			a[m][1]=student.getDepartmentName();
			a[m][2]=student.getMajorName();
			a[m][3]=student.getStudentClass();
			a[m][4]=student.getClassNumber();
			a[m][5]=student.getChooseNumber();
			m++;
		}
		return a;
	}

	@Override
	public Object[] getInfo(String i) {
		Object a[];
		ArrayList<Student> studentList=new ArrayList<Student>();
		String sql="select * from student "
				+ "LEFT JOIN major ON student.M_no=major.M_no "
				+ "LEFT JOIN department ON major.D_no=department.D_no  where S_no='"+i+"'";
		studentList=dao.findSql(sql);	
		a=new Object[7];
		for(Student student:studentList)
		{
			a[0]=student.getStudentName();
			a[1]=student.getStudentClass();
			a[2]=student.getDepartmentNo();
			a[3]=student.getDepartmentName();
			a[4]=student.getMajorNo();
			a[5]=student.getMajorName();
			a[6]=student.getStudentPassword();
		}
		return a;
	}

}
