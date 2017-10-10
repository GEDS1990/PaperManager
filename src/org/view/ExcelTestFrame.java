package org.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExcelTestFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFrame j=new JFrame();
		JPanel panel=new ExcelPanel();
		j.add(panel);
		j.setTitle("教师操作界面");
		j.setSize(900,620);	
		j.setResizable(false);//禁止调整框架的大小
		j.setLocationRelativeTo(null);  //居中
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
