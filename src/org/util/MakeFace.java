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
						setBackground(Color.white); //���������е�ɫ
					else if(row%2 == 1)
						setBackground(new Color(206,231,255));  //����ż���е�ɫ
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
	//		//�����ѡ��
	//
	//		if(isSelected)
	//
	//			//����ѡ����Ԫ��ı���ɫ����Ԫ����Ⱦ������ʹ�ô���ɫ���ѡ����Ԫ�� 
	//
	//			//����ѡ����Ԫ��ı���ɫ�� 
	//
	//			super.setBackground(table.getSelectionBackground());
	//
	//		else
	//
	//			this.setBackground(table.getBackground());
	//
	//		//���þ���
	//
	//		this.setHorizontalAlignment(JLabel.CENTER);
	//
	//		this.setToolTipText(value.toString());
	//
	//		return this;
	//
	//	}


}
