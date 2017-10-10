package org.view.education;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.model.Paper;
import org.service.IPaperService;
import org.service.imp.PaperService;
import org.util.Util;

public class EduAuditPaperDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	Paper paper;
	private int i;
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public  EduAuditPaperDialog(Paper paper)
	{   
		this.setModal(true);
		this.paper=paper;
		init();
		this.setSize(650, 650);
		this.setTitle("审核论文");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.validate();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private JPanel mainPanel;
	private JLabel titleLabel,teacherNameLabel,contentLabel,eduTimeLabel,eduNameLabel,deparLabel;
	private JTextField titleField,teacherNameField,eduTimeField,eduNameField,deparField;
	private JTextArea contentText;
	private JScrollPane scrollPanel;
	private JButton auditButton,UnPassButton,quitButton;

	public void init()
	{    
		mainPanel=new JPanel();
//		mainPanel.setBackground(new Color(253,185,51));
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
		
		deparLabel=new JLabel("限选院系：");
		deparLabel.setBounds(290,80,88,40);
		deparField=new JTextField();
		deparField.setEditable(false);
		deparField.setText(paper.getDepartmentName());
		deparField.setBounds(388,80,200,30);
		
		eduNameLabel=new JLabel("    审核人：");
		eduNameLabel.setBounds(60,140,70,40);
		eduNameField=new JTextField();
		eduNameField.setText(paper.getEduName());
		eduNameField.setBounds(140,140,80,30);
		
		eduTimeLabel=new JLabel("审核日期：");
		eduTimeLabel.setBounds(290,140,88,40);
		eduTimeField=new JTextField();
		String time=String.format("%tY-%<tm-%<td",new Date());
		eduTimeField.setText(time);
		eduTimeField.setBounds(388,140,200,30);

		contentLabel=new JLabel("论题内容：");
		contentLabel.setBounds(60,200,70,40);
		scrollPanel=new JScrollPane();
		scrollPanel.setBounds(140,200,450,345);
		contentText=new JTextArea();
//		contentText.setEditable(false);
		contentText.setLineWrap(true);  //自动换行
		contentText.setText(paper.getPaperContent());
		scrollPanel.setViewportView(contentText);
		
		auditButton=new JButton("  通  过");
		auditButton.setIcon(new ImageIcon("buttonIma/pass_2.png"));
		auditButton.addActionListener(this);
		auditButton.setBounds(160,567,100,40);
		UnPassButton=new JButton("  不通过");
		UnPassButton.setIcon(new ImageIcon("buttonIma/unpass_2.png"));
		UnPassButton.addActionListener(this);
		UnPassButton.setBounds(305,567,100,40);
		quitButton=new JButton("  退  出");
		quitButton.setIcon(new ImageIcon("buttonIma/quit-2.png"));
		quitButton.addActionListener(this);
		quitButton.setBounds(450,567,100,40);

		mainPanel.add(titleLabel);
		mainPanel.add(titleField);
		mainPanel.add(teacherNameLabel);
		mainPanel.add(teacherNameField);
		mainPanel.add(eduNameLabel);
		mainPanel.add(eduNameField);
		mainPanel.add(eduTimeLabel);
		mainPanel.add(eduTimeField);
		mainPanel.add(deparLabel);
		mainPanel.add(deparField);
		mainPanel.add(contentLabel);
		mainPanel.add(scrollPanel);
		mainPanel.add(auditButton);
		mainPanel.add(UnPassButton);
		mainPanel.add(quitButton);
		this.add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int paperNo=paper.getPaperNo();
		int eduNo=paper.getEduNo();
		if(e.getSource()==auditButton)
		{
			Paper paper=new Paper();
			paper.setPaperNo(paperNo);
			paper.setPaperState(1);
			paper.setEduNo(eduNo);
			paper.setEduTime(Util.StrToDate(eduTimeField.getText().trim()));
			IPaperService service=new PaperService();//service.upstate_Edu(paper)
			if(service.upstate_Edu(paper)){
				setI(11);
				JOptionPane.showMessageDialog(this, "  审核操作成功\n论文审核通过", "提示", 1);
			}else{
				JOptionPane.showMessageDialog(this, "审核操作失败", "提示", 0);
			}
			this.setVisible(false);
			this.dispose();
		}
		
		if(e.getSource()==UnPassButton)
		{
			Paper paper=new Paper();
			paper.setPaperNo(paperNo);
			paper.setPaperState(2);
			paper.setEduNo(eduNo);
			paper.setEduTime(Util.StrToDate(eduTimeField.getText().trim()));
			IPaperService service=new PaperService();//service.upstate_Edu(paper)
			if(service.upstate_Edu(paper)){
				setI(00);
				JOptionPane.showMessageDialog(this, "  审核操作成功\n论文审核未通过", "提示", 1);
			}else{
				JOptionPane.showMessageDialog(this, "审核操作失败", "提示", 0);
			}
			this.setVisible(false);
			this.dispose();
		}
		
		if(e.getSource()==quitButton)
		{
			setI(22);
			this.setVisible(false);
			this.dispose();
		}
	} 
}

