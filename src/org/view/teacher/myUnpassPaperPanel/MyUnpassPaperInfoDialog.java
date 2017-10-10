package org.view.teacher.myUnpassPaperPanel;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.model.Paper;

public class MyUnpassPaperInfoDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	Paper paper;
	public  MyUnpassPaperInfoDialog(Paper paper)
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
	private JLabel titleLabel,zjNameLabel,contentLabel,eduNameLabel,zjTelLabel,eduTelLabel;
	private JTextField titleField,zjNameField,eduNameField,zjTelField,eduTelField;
	private JTextArea contentText;
	private JScrollPane scrollPanel;

	public void init()
	{    
		mainPanel=new JPanel();
		mainPanel.setBackground(new Color(227,241,163));
		mainPanel.setBounds(0,0,650,600);
		mainPanel.setLayout(null);

		titleLabel=new JLabel("论文标题：");
		titleLabel.setBounds(50,20,70,40);
		titleField=new JTextField();
		titleField.setEditable(false);
		titleField.setText(paper.getPaperName());
		titleField.setBounds(140,20,450,30);

		eduNameLabel=new JLabel("教务处审核人：");
		eduNameLabel.setBounds(50,80,100,40);
		eduNameField=new JTextField();
		eduNameField.setEditable(false);
		eduNameField.setText(paper.getEduName());
		eduNameField.setBounds(140,80,80,30);

		eduTelLabel=new JLabel("审核人电话：");
		eduTelLabel.setBounds(290,80,88,40);
		eduTelField=new JTextField();
		eduTelField.setEditable(false);
		eduTelField.setText(paper.getEduTel());
		eduTelField.setBounds(388,80,200,30);

		zjNameLabel=new JLabel("专家处审核人：");
		zjNameLabel.setBounds(50,140,100,40);
		zjNameField=new JTextField();
		zjNameField.setEditable(false);
		if(paper.getZjName()==null)
			zjNameField.setText("---");
		else
			zjNameField.setText(String.valueOf(paper.getZjName()));
		zjNameField.setBounds(140,140,80,30);

		zjTelLabel=new JLabel("审核人电话：");
		zjTelLabel.setBounds(290,140,88,40);
		zjTelField=new JTextField();
		zjTelField.setEditable(false);
		zjTelField.setText(paper.getZjTel());
		zjTelField.setBounds(388,140,200,30);

		contentLabel=new JLabel("论题内容：");
		contentLabel.setBounds(50,200,70,40);
		scrollPanel=new JScrollPane();
		scrollPanel.setBounds(140,200,450,400);
		contentText=new JTextArea();
		//		contentText.setEditable(false);
		contentText.setLineWrap(true);  //自动换行
		contentText.setText(paper.getPaperContent());
		scrollPanel.setViewportView(contentText);

		mainPanel.add(titleLabel);
		mainPanel.add(titleField);
		mainPanel.add(zjNameLabel);
		mainPanel.add(zjNameField);
		mainPanel.add(zjTelLabel);
		mainPanel.add(zjTelField);
		mainPanel.add(eduNameLabel);
		mainPanel.add(eduNameField);
		mainPanel.add(eduTelLabel);
		mainPanel.add(eduTelField);
		mainPanel.add(contentLabel);
		mainPanel.add(scrollPanel);
		this.add(mainPanel);
	} 
}
