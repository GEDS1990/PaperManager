package org.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MakeFace {
	public static void makeFace(JTable table) {
		try
		{
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
			{
				private static final long serialVersionUID = 1L;

				public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column)
				{
					if(row%2 == 0)
						setBackground(Color.white); //设置奇数行底色
					else if(row%2 == 1)
						setBackground(new Color(206,231,255));  //设置偶数行底色
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
	
	

	//	public Component getTableCellRendererComponent(JTable table,Object value,
	//
	//			boolean isSelected,boolean hasFocus,int row,int column)
	//
	//	{
	//		//如果被选中
	//
	//		if(isSelected)
	//
	//			//设置选定单元格的背景色。单元格渲染器可以使用此颜色填充选定单元格。 
	//
	//			//返回选定单元格的背景色。 
	//
	//			super.setBackground(table.getSelectionBackground());
	//
	//		else
	//
	//			this.setBackground(table.getBackground());
	//
	//		//设置居中
	//
	//		this.setHorizontalAlignment(JLabel.CENTER);
	//
	//		this.setToolTipText(value.toString());
	//
	//		return this;
	//
	//	}


}
