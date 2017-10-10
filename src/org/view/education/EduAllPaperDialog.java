package org.view.education;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.model.Paper;

public class EduAllPaperDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	Paper paper;
	public  EduAllPaperDialog(Paper paper)
	{   
		this.setModal(true);
		this.paper=paper;
		init();
		this.setSize(650, 650);
		this.setTitle("论文详细内容");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.validate();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private JPanel mainPanel;
	private JLabel titleLabel,teacherNameLabel,contentLabel,maxNumberLabel,teachertelLabel,deparLabel;
	private JTextField titleField,teacherNameField,maxNumberField,teachertelField,deparField;
	private JTextArea contentText;
	private JScrollPane scrollPanel;

	public void init()
	{    
		mainPanel=new JPanel();
		mainPanel.setBackground(new Color(253,185,51));
		mainPanel.setBounds(0,0,650,600);
		mainPanel.setLayout(null);

		titleLabel=new JLabel("论文标题：");
		titleLabel.setBounds(60,20,70,40);
		titleField=new JTextField();
		titleField.setEditable(false);
		titleField.setText(paper.getPaperName());
		titleField.setBounds(140,20,450,30);

		teacherNameLabel=new JLabel("出题教师：");
		teacherNameLabel.setBounds(60,80,70,40);
		teacherNameField=new JTextField();
		teacherNameField.setEditable(false);
		teacherNameField.setText(paper.getTeacherName());
		teacherNameField.setBounds(140,80,80,30);
		
		teachertelLabel=new JLabel("联系电话：");
		teachertelLabel.setBounds(290,80,88,40);
		teachertelField=new JTextField();
		teachertelField.setEditable(false);
		teachertelField.setText(paper.getTeacherTel());
		teachertelField.setBounds(388,80,200,30);
		
		maxNumberLabel=new JLabel("限选人数：");
		maxNumberLabel.setBounds(60,140,70,40);
		maxNumberField=new JTextField();
		maxNumberField.setEditable(false);
		maxNumberField.setText(String.valueOf(paper.getMaxNumber()));
		maxNumberField.setBounds(140,140,80,30);
		
		deparLabel=new JLabel("限选院系：");
		deparLabel.setBounds(290,140,88,40);
		deparField=new JTextField();
		deparField.setEditable(false);
		deparField.setText(paper.getDepartmentName());
		deparField.setBounds(388,140,200,30);

		contentLabel=new JLabel("论题内容：");
		contentLabel.setBounds(60,200,70,40);
		scrollPanel=new JScrollPane();
		scrollPanel.setBounds(140,200,450,400);
		contentText=new JTextArea();
//		contentText.setEditable(false);
		contentText.setLineWrap(true);  //自动换行
		contentText.setText(paper.getPaperContent());
		scrollPanel.setViewportView(contentText);

		mainPanel.add(titleLabel);
		mainPanel.add(titleField);
		mainPanel.add(teacherNameLabel);
		mainPanel.add(teacherNameField);
		mainPanel.add(teachertelLabel);
		mainPanel.add(teachertelField);
		mainPanel.add(maxNumberLabel);
		mainPanel.add(maxNumberField);
		mainPanel.add(deparLabel);
		mainPanel.add(deparField);
		mainPanel.add(contentLabel);
		mainPanel.add(scrollPanel);
		this.add(mainPanel);
	} 
}
