package org.view.teacher.myUnauditPaperPanel;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.model.Paper;

public class MyPaperInfoDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	Paper paper;
	public  MyPaperInfoDialog(Paper paper)
	{   
		this.setModal(true);
		this.paper=paper;
		init();
		this.setSize(650, 650);
		this.setTitle("详细论文内容");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.validate();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private JPanel mainPanel;
	private JLabel titleLabel,limitNumberLabel,contentLabel,eduNameLabel,deparNameLabel,eduTelLabel;
	private JTextField titleField,limitNumberField,eduNameField,deparNameField,eduTelField;
	private JTextArea contentText;
	private JScrollPane scrollPanel;

	public void init()
	{    
		mainPanel=new JPanel();
		mainPanel.setBackground(new Color(227,241,163));
		mainPanel.setBounds(0,0,650,600);
		mainPanel.setLayout(null);

		titleLabel=new JLabel("论文标题：");
		titleLabel.setBounds(60,20,70,40);
		titleField=new JTextField();
		titleField.setEditable(false);
		titleField.setText(paper.getPaperName());
		titleField.setBounds(140,20,450,30);

		limitNumberLabel=new JLabel("限选人数：");
		limitNumberLabel.setBounds(60,80,70,40);
		limitNumberField=new JTextField();
		limitNumberField.setEditable(false);
		limitNumberField.setText(String.valueOf(paper.getMaxNumber()));
		limitNumberField.setBounds(140,80,80,30);
		
		deparNameLabel=new JLabel("限选专业：");
		deparNameLabel.setBounds(290,80,88,40);
		deparNameField=new JTextField();
		deparNameField.setEditable(false);
		deparNameField.setText(paper.getDepartmentName());
		deparNameField.setBounds(388,80,200,30);
		
		eduNameLabel=new JLabel("审核人：");
		eduNameLabel.setBounds(60,140,70,40);
		eduNameField=new JTextField();
		eduNameField.setEditable(false);
		eduNameField.setText(paper.getEduName());
		eduNameField.setBounds(140,140,80,30);
		
		eduTelLabel=new JLabel("审核人电话：");
		eduTelLabel.setBounds(290,140,88,40);
		eduTelField=new JTextField();
		eduTelField.setEditable(false);
		eduTelField.setText(paper.getEduTel());
		eduTelField.setBounds(388,140,200,30);

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
		mainPanel.add(limitNumberLabel);
		mainPanel.add(limitNumberField);
		mainPanel.add(deparNameLabel);
		mainPanel.add(deparNameField);
		mainPanel.add(eduNameLabel);
		mainPanel.add(eduNameField);
		mainPanel.add(eduTelLabel);
		mainPanel.add(eduTelField);
		mainPanel.add(contentLabel);
		mainPanel.add(scrollPanel);
		this.add(mainPanel);
	} 
}
