package org.service.imp;

import java.util.ArrayList;

import org.dao.IPaperDao;
import org.dao.imp.PaperDao;
import org.model.Paper;
import org.service.IPaperService;

public class PaperService implements IPaperService {

	IPaperDao dao=new PaperDao();
	@Override
	public boolean insert(Paper paper) {
		return dao.insert(paper);
	}

	@Override
	public boolean delete(Paper paper) {
		if(dao.find(paper.getPaperNo()))	
			return dao.delete(paper);
		else
			return false;
	}

	@Override
	public boolean update(Paper paper) {
		if(dao.find(paper.getPaperNo()))	
			return dao.update(paper);
		else
			return false;
	}

	@Override
	public boolean upstate(Paper paper) {
		if(dao.find(paper.getPaperNo()))	
			return dao.upstate(paper);
		else
			return false;
	}
	
	@Override
	public boolean upstate_Edu(Paper paper) {
		if(dao.find(paper.getPaperNo()))	
			return dao.upstate_Edu(paper);
		else
			return false;
	}
	
	@Override
	public boolean upstate_ZJ(Paper paper) {
		if(dao.find(paper.getPaperNo()))	
			return dao.upstate_ZJ(paper);
		else
			return false;
	}

	@Override
	public ArrayList<Paper> findSql(String sql) {
		return dao.findSql(sql);
	}

	@Override
	public Object[][] getRecord(String sql) {
		Object a[][];
		int m=0;
		ArrayList<Paper> paperList=new ArrayList<Paper>();
		if(sql==null)
			paperList=dao.findAll();
		else
			paperList=dao.findSql(sql);	
		int row=paperList.size();
		a=new Object[row][8];
		for(Paper paper:paperList)
		{
			a[m][0]=paper.getPaperNo();
			a[m][1]=paper.getPaperName();
			a[m][2]=paper.getTeacherName();
			a[m][3]=paper.getDepartmentName();
			a[m][4]=paper.getMaxNumber();
			if(paper.getPaperState()==0){
				a[m][5]="---";
				a[m][6]="---";
				a[m][7]="未审核";
			}
			if(paper.getPaperState()==1){
				a[m][5]=paper.getEduName();
				a[m][6]=paper.getEduTime();
				a[m][7]="教务处审核通过";
			}
			//			a[m][5]=paper.getEduName();
			//			a[m][6]=paper.getEduTime();
			//			a[m][7]=paper.getPaperState();
			m++;
		}
		return a;
	}

	public Object[][] getRecord_Unpass(String sql) {
		Object a[][];
		int m=0;
		ArrayList<Paper> paperList=new ArrayList<Paper>();
		if(sql==null)
			paperList=dao.findAll();
		else
			paperList=dao.findSql(sql);	
		int row=paperList.size();
		a=new Object[row][9];
		for(Paper paper:paperList)
		{
			a[m][0]=paper.getPaperNo();
			a[m][1]=paper.getPaperName();
			a[m][3]=paper.getEduName();
			a[m][4]=paper.getEduTime();
			if(paper.getPaperState()==2){
				a[m][2]="审核未通过";
				a[m][6]="---";
				a[m][7]="---";
				a[m][5]="未审核";
				a[m][8]="---";
			}
			if(paper.getPaperState()==4){
				a[m][2]="审核通过";
				a[m][6]=paper.getZjName();
				a[m][7]=paper.getZjTime();
				a[m][5]="审核未通过";
				a[m][8]=paper.getZjSuggest();
			}
			m++;
		}
		return a;
	}

	@Override
	public Object[][] getRecord_Unrelese(String sql)
	{
		Object a[][];
		int m=0;
		ArrayList<Paper> paperList=new ArrayList<Paper>();
		if(sql==null)
			paperList=dao.findAll();
		else
			paperList=dao.findSql(sql);	
		int row=paperList.size();
		a=new Object[row][7];
		for(Paper paper:paperList)
		{
			a[m][0]=paper.getPaperNo();
			a[m][1]=paper.getPaperName();
			a[m][2]=paper.getEduName();
			a[m][3]=paper.getEduTime();
			a[m][4]=paper.getZjName();
			a[m][5]=paper.getZjTime();
			a[m][6]="审核通过，等待发布";
			m++;
		}
		return a;
	}

	@Override
	public Object[][] getRecord_Relese(String sql)
	{
		Object a[][];
		int m=0;
		ArrayList<Paper> paperList=new ArrayList<Paper>();
		if(sql==null)
			paperList=dao.findAll();
		else
			paperList=dao.findSql(sql);	
		int row=paperList.size();
		a=new Object[row][6];
		for(Paper paper:paperList)
		{
			a[m][0]=paper.getPaperNo();
			a[m][1]=paper.getPaperName();
			a[m][2]=paper.getTeacherName();
			a[m][3]=paper.getDepartmentName();
			a[m][4]=paper.getChooseNumber();
			a[m][5]=paper.getMaxNumber()-paper.getChooseNumber();
			m++;
		}
		return a;
	}


	@Override
	public Object[][] getRecord_All(String sql) {
		Object a[][];
		int m=0;
		ArrayList<Paper> paperList=new ArrayList<Paper>();
		paperList=dao.findSql(sql);	
		int row=paperList.size();
		a=new Object[row][5];
		for(Paper paper:paperList)
		{
			a[m][0]=paper.getPaperNo();
			a[m][1]=paper.getPaperName();
			a[m][2]=paper.getTeacherName();
			a[m][3]=paper.getMaxNumber();
			if(paper.getPaperState()!=5){
				a[m][4]="尚未发布";
			}
			if(paper.getPaperState()==5){
				a[m][4]="已发布";
			}
			m++;
		}
		return a;
	}


	@Override
	public Object[][] getInfo(int i) {
		Object a[][];
		ArrayList<Paper> paperList=new ArrayList<Paper>();
		String sql="select * "
				+ " from paper LEFT JOIN teacher ON paper.T_no=teacher.T_no "
				+ "LEFT JOIN department ON paper.D_no=department.D_no "
				+ "LEFT JOIN education ON paper.E_no=education.E_no "
				+ "LEFT JOIN zhuanjia ON paper.Z_no=zhuanjia.Z_no where P_no="+i;
		paperList=dao.findSql(sql);	
		int row=paperList.size();
		a=new Object[row][10];
		for(Paper paper:paperList)
		{
			a[0][0]=paper.getPaperContent();
			a[0][1]=paper.getPaperState();
			a[0][2]=paper.getMaxNumber();
			a[0][3]=paper.getTeacherTel();
			a[0][4]=paper.getDepartmentTel();
			a[0][5]=paper.getEduName();
			a[0][6]=paper.getEduTel();
			a[0][7]=paper.getZjName();
			a[0][8]=paper.getZjTel();
			a[0][9]=paper.getDepartmentName();
		}
		return a;
	}

	@Override
	public Object[][] getRecord_Edu(String sql) {
		Object a[][];
		int m=0;
		ArrayList<Paper> paperList=new ArrayList<Paper>();
		paperList=dao.findSql(sql);	
		int row=paperList.size();
		a=new Object[row][5];
		for(Paper paper:paperList)
		{
			a[m][0]=paper.getPaperNo();
			a[m][1]=paper.getPaperName();
			a[m][2]=paper.getTeacherName();
			a[m][3]=paper.getDepartmentName();
			a[m][4]=paper.getMaxNumber();
			m++;
		}
		return a;
	}



}
