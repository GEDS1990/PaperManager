package org.view;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dao.DBUtil;

public class ExcelInfo {
	public boolean excelToDateBase(String exl,String name)
	{
		String file = exl;
		boolean b = false;
		try {           
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file)); // ������Excel�������ļ�������          
			HSSFSheet sheet = workbook.getSheet(name);  // �����Թ���������á�
			int rows = sheet.getPhysicalNumberOfRows();         //��ȡ��������         
			for (int r = 1; r < rows; r++) {                //ѭ������������
				String value ="";                           //���屣���ȡ���ݵ�String����
				HSSFRow row = sheet.getRow(r);              //��ȡ��Ԫ����ָ�����ж���  
				if (row != null) {
					int  cells = row.getPhysicalNumberOfCells(); //��ȡ��Ԫ����ָ���ж���
					for (short c = 0; c < cells; c++) {      //ѭ��������Ԫ���е���                  
						@SuppressWarnings("deprecation")
						HSSFCell cell = row.getCell((short) c); //��ȡָ����Ԫ���е���                      
						if (cell != null) {
							if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {  //�жϵ�Ԫ���ֵ�Ƿ�Ϊ�ַ�������                                
								value += cell.getStringCellValue()+",";
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {  //�жϵ�Ԫ���ֵ�Ƿ�Ϊ��������                                
								value += cell.getNumericCellValue()+",";
							} else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){    //�жϵ�Ԫ���ֵ�Ƿ�Ϊ��������                      
								value += cell.getStringCellValue()+",";
							}
						}                       
					}                  
				}
				String [] str = value.split(",");           //���ַ������зָ�
								System.out.println(str[0]+"   ---   "+str[1]);
				b=insertExl(str);                    //���������ݿ�������ݷ���
			}   
		} catch (Exception e) {
			e.printStackTrace();            
		}
		if(b==true)
			return true;
		else
			return false;
	}


	public boolean insertExl(String[] str) throws ClassNotFoundException, SQLException {
		DBUtil iteacher = new DBUtil(); // ��������
		int num=0;
		@SuppressWarnings("static-access")
		Connection conn = iteacher.getConnection(); // ���û�ȡ���ݿ����ӷ���
		String sql = "insert into student (S_no,M_no,S_name,S_sex,S_class)  values('"
				+ str[0] + "'," + str[1] + ",'"+str[2]+"','"+str[3]+"','"+str[4]+"')"; // ���������ݿ�������ݵ�SQL���
		try {
			Statement statement = conn.createStatement();
			num=statement.executeUpdate(sql); // ִ�в����sql���
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(num>0)
			return true;
		else
			return false;
	}
}
