package org.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MakeColor {
	/*
	 * ��֪BUG:
	 * 1.ͬʱʹ���������Ʒ����ǣ�ֻ��һ����Ч
	 * 
	 * ʹ�÷�����
	 * //���ı��ģ��	
	 * public void changeModel(Object[][] data)
	 * {
	 *     tableModel=new DefaultTableModel(data, columnNames);
	 *     table.setModel(tableModel);
	 *     MakeColor.makeColor(table, Color.red);  //δ����ú�ɫ
	 * }
	 * 
	 * ˵����
	 *     ����if(row%2 == 0)
						setBackground(Color.white); //���������е�ɫ
					else if(row%2 == 1)
						setBackground(new Color(206,231,255));  //����ż���е�ɫ
				�޸Ķ��ã����������������ü��ɫ���ܺÿ�
				new Color(206,231,255)����ʹ��ps��ȡ��ԭɫ��
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
