package org.view.teacher.myInfoPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.model.Teacher;
import org.service.ITeacherService;
import org.service.imp.TeacherService;

public class MyInfoPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public MyInfoPanel(Teacher teacher){
		teacherNo=teacher.getTeacherNo();
		init();
	}
	
	private int teacherNo;
	private String teacherName,departmentName;
	private JPanel mainPanel;
	private JScrollPane scrollPanel;
	private JLabel nameLabel,numberLabel,deparNameLabel,deparNoLabel,telLabel,paperNumberLabel,paperAuNumberLabel,contentLabel;
	private JTextField nameField,numberField,deparNameField,deparNoField,telField,paperNumberField,paperAuNumberField;
	private JTextArea contentText;
	private JButton upButton,flushButton;
	
	private void init() {
		ITeacherService teacherService=new TeacherService();
		Object data[]=teacherService.getInfo(teacherNo);
		teacherName=(String) data[0];
		departmentName=(String) data[1];
		String tel=(String)data[3];
		String content=(String)data[4];
		int paperNumber=(int)data[5];
		int paperAuNumber=(int)data[6];
		int departmentNo=(int)data[7];
		
		Font  boldFont=new Font(" 微软雅黑",Font.BOLD,24);
		Font  plainFont=new Font(" 微软雅黑",Font.PLAIN,16);
		
		this.setLayout(null);
		mainPanel=new JPanel();
		mainPanel.setBackground(new Color(108,162,207));
		mainPanel.setBounds(0,0,1000,590);
		mainPanel.setLayout(null);
		
		nameLabel=new JLabel("姓名：");
		nameLabel.setFont(boldFont);
		nameLabel.setForeground(Color.white);
		nameLabel.setBounds(60,40,200,36);
		nameField=new JTextField();
		nameField.setFont(plainFont);
		nameField.setText(teacherName);
		nameField.setEditable(false);
		nameField.setBounds(210,40,200,36);
		
		numberLabel=new JLabel("职工号：");
		numberLabel.setFont(boldFont);
		numberLabel.setForeground(Color.white);
		numberLabel.setBounds(60,110,200,36);
		numberField=new JTextField();
		numberField.setFont(plainFont);
		numberField.setText(String.valueOf(teacherNo));
		numberField.setEditable(false);
		numberField.setBounds(210,110,200,36);
		
		telLabel=new JLabel("联系方式：");
		telLabel.setFont(boldFont);
		telLabel.setForeground(Color.white);
		telLabel.setBounds(60,180,200,36);
		telField=new JTextField();
		telField.setFont(plainFont);
		telField.setText(tel);
		telField.setEditable(false);
		telField.setBounds(210,180,200,36);
		
		deparNameLabel=new JLabel("所属院系：");
		deparNameLabel.setFont(boldFont);
		deparNameLabel.setForeground(Color.white);
		deparNameLabel.setBounds(60,250,200,36);
		deparNameField=new JTextField();
		deparNameField.setFont(plainFont);
		deparNameField.setText(departmentName);
		deparNameField.setEditable(false);
		deparNameField.setBounds(210,250,200,36);
		
		deparNoLabel=new JLabel("院系编号：");
		deparNoLabel.setFont(boldFont);
		deparNoLabel.setForeground(Color.white);
		deparNoLabel.setBounds(60,320,200,36);
		deparNoField=new JTextField();
		deparNoField.setFont(plainFont);
		deparNoField.setText(String.valueOf(departmentNo));
		deparNoField.setEditable(false);
		deparNoField.setBounds(210,320,200,36);
		
		paperNumberLabel=new JLabel("所有论文数：");
		paperNumberLabel.setFont(boldFont);
		paperNumberLabel.setForeground(Color.white);
		paperNumberLabel.setBounds(60,390,200,36);
		paperNumberField=new JTextField();
		paperNumberField.setFont(plainFont);
		paperNumberField.setText(String.valueOf(paperNumber));
		paperNumberField.setEditable(false);
		paperNumberField.setBounds(210,390,200,36);
		
		paperAuNumberLabel=new JLabel("发布论文数：");
		paperAuNumberLabel.setFont(boldFont);
		paperAuNumberLabel.setForeground(Color.white);
		paperAuNumberLabel.setBounds(60,460,200,36);
		paperAuNumberField=new JTextField();
		paperAuNumberField.setFont(plainFont);
		paperAuNumberField.setText(String.valueOf(paperAuNumber));
		paperAuNumberField.setEditable(false);
		paperAuNumberField.setBounds(210,460,200,36);
		
		contentLabel=new JLabel("个人简介：");
		contentLabel.setFont(boldFont);
		contentLabel.setForeground(Color.white);
		contentLabel.setBounds(500,40,200,36);
		scrollPanel=new JScrollPane();
		scrollPanel.setBounds(500,80,440,416);
		contentText=new JTextArea();
		contentText.setText(content);
		contentText.setFont(new Font(" 微软雅黑",Font.PLAIN,13));
		contentText.setLineWrap(true);  //自动换行
		contentText.setBackground(new Color(234,237,203)); 
		scrollPanel.setViewportView(contentText);
		
		flushButton=new JButton(" 刷   新");
		flushButton.setIcon(new ImageIcon("buttonIma/refresh_2.png"));
		flushButton.addActionListener(this);
//		flushButton.setFont(new Font("微软雅黑",Font.BOLD,15));
//		upButton.setForeground(Color.black);
		flushButton.setBackground(new Color(228,176,141));
		flushButton.setBounds(500,527,100,38);
		
		upButton=new JButton(" 修   改");
		upButton.setIcon(new ImageIcon("buttonIma/update_2.png"));
		upButton.addActionListener(this);
//		upButton.setFont(new Font("微软雅黑",Font.BOLD,15));
//		upButton.setForeground(Color.black);
		upButton.setBackground(new Color(228,176,141));
		upButton.setBounds(825,527,105,38);
		
		mainPanel.add(nameLabel);
		mainPanel.add(nameField);
		mainPanel.add(numberLabel);
		mainPanel.add(numberField);
		mainPanel.add(telLabel);
		mainPanel.add(telField);
		mainPanel.add(deparNameLabel);
		mainPanel.add(deparNameField);
		mainPanel.add(deparNoLabel);
		mainPanel.add(deparNoField);
		mainPanel.add(paperNumberLabel);
		mainPanel.add(paperNumberField);
		mainPanel.add(paperAuNumberLabel);
		mainPanel.add(paperAuNumberField);
		mainPanel.add(contentLabel);
		mainPanel.add(scrollPanel);
		mainPanel.add(upButton);
		mainPanel.add(flushButton);
		this.add(mainPanel);
	}

	public void update(){
		ITeacherService teacherService=new TeacherService();
		Object data[]=teacherService.getInfo(teacherNo);
		teacherName=(String) data[0];
		String tel=(String)data[3];
		String content=(String)data[4];
		int paperNumber=(int)data[5];
		int paperAuNumber=(int)data[6];
		
		nameField.setText(teacherName);
		telField.setText(tel);
		paperNumberField.setText(String.valueOf(paperNumber));
		paperAuNumberField.setText(String.valueOf(paperAuNumber));
		contentText.setText(content);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==flushButton)
		{
//			System.out.println("刷新");
			update();
		}
		
		if(e.getSource()==upButton)
		{
			System.out.println(1111);
		}
	}

}
