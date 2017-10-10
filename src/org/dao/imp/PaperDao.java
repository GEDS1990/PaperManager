package org.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.dao.DBUtil;
import org.dao.IPaperDao;
import org.model.Paper;
import org.util.Util;

public class PaperDao implements IPaperDao {
	@Override
	public boolean insert(Paper paper) {
		String paperName=paper.getPaperName();
		String paperConent=paper.getPaperContent();
		int teacherNo=paper.getTeacherNo();
		int departmentNo=paper.getDepartmentNo();
		int maxNumber=paper.getMaxNumber();
		int paperState=0;  //未审核
		int num1=0;
		int num2=0;
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			stmt=conn.createStatement();		
			rs=stmt.executeQuery("select * from teacher where T_no ="+teacherNo);
			rs.next();
			int teacherPaperNumber=rs.getInt("T_paperNum");
//			System.out.println("之前："+teacherPaperNumber);
			teacherPaperNumber=teacherPaperNumber+1;
			num1=stmt.executeUpdate("insert into paper(P_name,P_content,T_no,D_no,P_maxNumber,P_state) "
					+ "values('"+paperName+"','"+paperConent+"','"+teacherNo+"','"+departmentNo+"',"+maxNumber+","+paperState+")");
			num2=stmt.executeUpdate("update teacher set T_paperNum ="+teacherPaperNumber+" where T_no ="+teacherNo);
			conn.commit();
			conn.setAutoCommit(true);
//			System.out.println("之后："+teacherPaperNumber);
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {		
			try{
				conn.rollback();
				System.out.println(e);
			}catch(SQLException ee){
				ee.printStackTrace();
			}
		} finally{	
			DBUtil.closeQuietly(conn, stmt,rs);
		}
		if(num1>0&&num2>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Paper paper) {
		int teacherNo=paper.getTeacherNo();
		int num1=0,num2=0;
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			stmt=conn.createStatement();	
			rs=stmt.executeQuery("select * from teacher where T_no ="+teacherNo);
			rs.next();
			int teacherPaperNumber=rs.getInt("T_paperNum");
			teacherPaperNumber=teacherPaperNumber-1;
			num1=stmt.executeUpdate("delete from paper where P_no="+paper.getPaperNo());
			num2=stmt.executeUpdate("update teacher set T_paperNum ="+teacherPaperNumber+" where T_no ="+teacherNo);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {		
			try{
				conn.rollback();
				System.out.println(e);
			}catch(SQLException ee){
				ee.printStackTrace();
			}
		} finally{	
			DBUtil.closeQuietly(conn, stmt,rs);
		}
		if(num1>0&&num2>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean update(Paper paper) {
		String sql="update paper set "+
				"P_name='"+paper.getPaperName()+
				"',P_content='"+paper.getPaperContent()+
				"',P_maxNumber="+paper.getMaxNumber()+",P_state=0 "+
				" where P_no="+paper.getPaperNo();	
		return crudSql(sql);
	}
	
	@Override
	public boolean upstate(Paper paper) {
//		String sql="update paper set "+
//				"P_state="+paper.getPaperState()+" ,P_chooNumber="+paper.getChooseNumber()+
//				" where P_no="+paper.getPaperNo();	
//		return crudSql(sql);
		int teacherNo=paper.getTeacherNo();
		int num1=0,num2=0;
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			stmt=conn.createStatement();	
			rs=stmt.executeQuery("select * from teacher where T_no ="+teacherNo);
			rs.next();
			int paperAuNumber=rs.getInt("T_paperAuNum");
			paperAuNumber=paperAuNumber+1;
			String sql="update paper set "+
					"P_state="+paper.getPaperState()+" ,P_chooNumber="+paper.getChooseNumber()+
					" where P_no="+paper.getPaperNo();	
			num1=stmt.executeUpdate(sql);
			num2=stmt.executeUpdate("update teacher set T_paperAuNum ="+paperAuNumber+" where T_no ="+teacherNo);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {		
			try{
				conn.rollback();
				System.out.println(e);
			}catch(SQLException ee){
				ee.printStackTrace();
			}
		} finally{	
			DBUtil.closeQuietly(conn, stmt,rs);
		}
		if(num1>0&&num2>0)
			return true;
		else
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
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;
		int rowCount=0;
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();
			String sql="select count(*) as rowcount from paper where P_no="+id; 
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
	public ArrayList<Paper> findAll() {
		String sql = null;
//		sql="select paper.*,teacher.T_name,department.D_name,E_name,Z_name from paper,teacher,department,education,zhuanjia"
//				+ " where paper.T_no=teacher.T_no and paper.D_no=department.D_no and paper.E_no=education.E_no and paper.Z_no=zhuanjia.Z_no";
		sql="select * "
				+ "from paper LEFT JOIN teacher ON paper.T_no=teacher.T_no "
				+ "LEFT JOIN department ON paper.D_no=department.D_no "
				+ "LEFT JOIN education ON paper.E_no=education.E_no "
				+ "LEFT JOIN zhuanjia ON paper.Z_no=zhuanjia.Z_no";
		//paper.*,teacher.T_name,department.D_name,education.E_name,zhuanjia.Z_name
		return findSql(sql);
	}

	@Override
	public ArrayList<Paper> findSql(String sql) {
		Connection conn=null;
		Statement  stmt=null;
		ResultSet rs=null;    
		ArrayList<Paper> list=new ArrayList<Paper>();
		try {
			conn=DBUtil.getConnection();
			stmt=conn.createStatement();	    
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
//				System.out.println("111");
				Paper paper=new Paper();
				paper.setPaperNo(rs.getInt("P_no"));
				paper.setPaperName(rs.getString("P_name"));
				paper.setPaperContent(rs.getString("P_content"));
				paper.setTeacherNo(rs.getInt("T_no"));
				paper.setTeacherName(rs.getString("T_name"));
				paper.setTeacherTel(rs.getString("T_tel")); 
				paper.setDepartmentNo(rs.getInt("D_no"));
				paper.setDepartmentName(rs.getString("D_name"));
				paper.setDepartmentTel(rs.getString("D_tel"));
				paper.setMaxNumber(rs.getInt("P_maxNumber"));
				paper.setChooseNumber(rs.getInt("P_chooNumber"));
				paper.setPaperState(rs.getInt("P_state"));
				paper.setEduNo(rs.getInt("E_no"));
				paper.setEduName(rs.getString("E_name"));
				paper.setEduTel(rs.getString("E_tel"));
				paper.setEduTime(rs.getDate("P_eduTime"));
				paper.setZjNo(rs.getInt("Z_no"));
				paper.setZjName(rs.getString("Z_name"));
				paper.setZjTel(rs.getString("Z_tel"));
				paper.setZjTime(rs.getDate("P_auTime"));
				paper.setZjSuggest(rs.getString("P_zhSuggest"));
				list.add(paper);
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

	@Override
	public boolean upstate_Edu(Paper paper) {
		String sql="update paper set "+
				"P_state="+paper.getPaperState()+" ,E_no="+paper.getEduNo()+" ,P_eduTime ='"+Util.DateToStr(paper.getEduTime())+
				"' where P_no="+paper.getPaperNo();	
//		System.out.println(sql);
		return crudSql(sql);
	}

	@Override
	public boolean upstate_ZJ(Paper paper) {
		String sql="update paper set "+
				"P_state="+paper.getPaperState()+" ,Z_no="+paper.getZjNo()+" ,P_auTime ='"+Util.DateToStr(paper.getZjTime())+"',P_zhSuggest='"+paper.getZjSuggest()+"' where P_no="+paper.getPaperNo();	
//		System.out.println(sql);
		return crudSql(sql);
	}

}
