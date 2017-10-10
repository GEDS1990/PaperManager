package org.view.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dao.DBUtil;

public class Test {
	public static void main(String[] args) {
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();		
//			rs=stmt.executeQuery("select * from student LEFT JOIN major ON student.M_no=major.M_no "
//					+ "LEFT JOIN choose ON student.S_no=choose.S_no "
//					+ "LEFT JOIN department ON major.D_no=department.D_no");
//			rs=stmt.executeQuery("select D_name,M_name,S_class,count(*) as 人数 from student LEFT JOIN major ON student.M_no=major.M_no "
//					+ "LEFT JOIN department ON major.D_no=department.D_no where major.D_no=1001 Group by S_class order by count(*) desc");
			
			rs=stmt.executeQuery("select  D_name,M_name,S_class,count(DISTINCT student.S_no) as 人数,sum(case when P_no is null then 1 else null end) as nan from student "
					+ "LEFT JOIN major ON student.M_no=major.M_no "
					+ "LEFT JOIN department ON major.D_no=department.D_no "
					+ "LEFT JOIN choose ON student.S_no=choose.S_no "
					+ " Group by S_class order by D_name");//where major.D_no=101
			
			/*
			 * ,count (case when S_sex='男' then 1 else null end) as 男生
			 */
			while(rs.next())
			{
//				String studentNo=rs.getString(1);
//				String name=rs.getString(3);
//				String sex=rs.getString(4);
//				String sClass=rs.getString(5);
//				String tel=rs.getString(6);
//				String major=rs.getString(9);
//				String depar=rs.getString(11);
//				System.out.println("  "+studentNo+"       "+name+"          "+sex+"       "+sClass+"       "+major+"       "+depar);	
			System.out.println(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getInt(4)+"   "+(rs.getInt(4)-rs.getInt(5)));
			}
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally{	
			DBUtil.closeQuietly(conn, stmt,rs                        );
		}
		
	}
}
