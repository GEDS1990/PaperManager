package org.view.teacher.myUnauditPaperPanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	
	public static void main(String[] args) {
		new TestFrame();
	}
	
	public TestFrame() {
//		MyUnauditPaperPanel panel=new MyUnauditPaperPanel();
		
		mainPanel=new JPanel();
//		mainPanel.setBackground(Color.red);
		mainPanel.setBounds(0,0,1000,590);
		mainPanel.setLayout(new BorderLayout());
		setTitle("δ������Ľ���Ĳ���");
		setSize(1000,590);	
		setResizable(false);//��ֹ������ܵĴ�С
		setLocationRelativeTo(null);  //����
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
//		mainPanel.add(panel,BorderLayout.CENTER);
		this.add(mainPanel);
	}
}
