package org.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExcelTestFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFrame j=new JFrame();
		JPanel panel=new ExcelPanel();
		j.add(panel);
		j.setTitle("��ʦ��������");
		j.setSize(900,620);	
		j.setResizable(false);//��ֹ������ܵĴ�С
		j.setLocationRelativeTo(null);  //����
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
