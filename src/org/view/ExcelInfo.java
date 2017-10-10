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
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file)); // 创建对Excel工作簿文件的引用          
			HSSFSheet sheet = workbook.getSheet(name);  // 创建对工作表的引用。
			int rows = sheet.getPhysicalNumberOfRows();         //获取表格的行数         
			for (int r = 1; r < rows; r++) {                //循环遍历表格的行
				String value ="";                           //定义保存读取内容的String对象
				HSSFRow row = sheet.getRow(r);              //获取单元格中指定的行对象  
				if (row != null) {
					int  cells = row.getPhysicalNumberOfCells(); //获取单元格中指定列对象
					for (short c = 0; c < cells; c++) {      //循环遍历单元格中的列                  
						@SuppressWarnings("deprecation")
						HSSFCell cell = row.getCell((short) c); //获取指定单元格中的列                      
						if (cell != null) {
							if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {  //判断单元格的值是否为字符串类型                                
								value += cell.getStringCellValue()+",";
							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {  //判断单元格的值是否为数字类型                                
								value += cell.getNumericCellValue()+",";
							} else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){    //判断单元格的值是否为布尔类型                      
								value += cell.getStringCellValue()+",";
							}
						}                       
					}                  
				}
				String [] str = value.split(",");           //将字符串进行分割
								System.out.println(str[0]+"   ---   "+str[1]);
				b=insertExl(str);                    //调用向数据库插入数据方法
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
		DBUtil iteacher = new DBUtil(); // 创建对象
		int num=0;
		@SuppressWarnings("static-access")
		Connection conn = iteacher.getConnection(); // 调用获取数据库连接方法
		String sql = "insert into student (S_no,M_no,S_name,S_sex,S_class)  values('"
				+ str[0] + "'," + str[1] + ",'"+str[2]+"','"+str[3]+"','"+str[4]+"')"; // 定义向数据库插入数据的SQL语句
		try {
			Statement statement = conn.createStatement();
			num=statement.executeUpdate(sql); // 执行插入的sql语句
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(num>0)
			return true;
		else
			return false;
	}
}
