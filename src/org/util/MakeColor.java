package org.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MakeColor {
	/*
	 * 已知BUG:
	 * 1.同时使用两个类似方法是，只有一个生效
	 * 
	 * 使用方法：
	 * //更改表格模型	
	 * public void changeModel(Object[][] data)
	 * {
	 *     tableModel=new DefaultTableModel(data, columnNames);
	 *     table.setModel(tableModel);
	 *     MakeColor.makeColor(table, Color.red);  //未审核用红色
	 * }
	 * 
	 * 说明：
	 *     根据if(row%2 == 0)
						setBackground(Color.white); //设置奇数行底色
					else if(row%2 == 1)
						setBackground(new Color(206,231,255));  //设置偶数行底色
				修改而得，上述方法可以设置间隔色，很好看
				new Color(206,231,255)可以使用ps获取三原色。
	 */
	public static void makeColor(JTable table,Color color) {
		try
		{
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
			{
				private static final long serialVersionUID = 1L;

				public Component getTableCellRendererComponent
				(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column)
				{
					if(column==6)
						setForeground(color); 
					else
						setForeground(Color.black);
					return super.getTableCellRendererComponent(table, value,
							isSelected, hasFocus, row, column); 
				}
			};
			for(int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

}
