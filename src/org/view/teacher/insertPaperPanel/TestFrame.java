package org.view.teacher.insertPaperPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.model.Teacher;

public class TestFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	
	public static void main(String[] args) {
		new TestFrame();
	}
	
	public TestFrame() {

		Teacher teacher=new Teacher();
		teacher.setTeacherNo(1001);
		teacher.setDepartmentNo(1001);
		InsertPaperPanel panel=new InsertPaperPanel(teacher);
		
		mainPanel=new JPanel();
		mainPanel.setBackground(Color.red);
		mainPanel.setBounds(0,0,1000,590);
		mainPanel.setLayout(new BorderLayout());
		setTitle("添加论文的测试");
		setSize(1000,590);	
		setResizable(false);//禁止调整框架的大小
		setLocationRelativeTo(null);  //居中
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(null);
		this.setLayout(null);
		mainPanel.add(panel,BorderLayout.CENTER);
		this.add(mainPanel);
	}
}
