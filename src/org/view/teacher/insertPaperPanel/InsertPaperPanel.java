package org.view.teacher.insertPaperPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.model.Paper;
import org.model.Teacher;
import org.service.IPaperService;
import org.service.imp.PaperService;

public class InsertPaperPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	public InsertPaperPanel(Teacher teacher) {
		this.teaNo=teacher.getTeacherNo();
		this.deNo=teacher.getDepartmentNo();
		init();
	}

	private int teaNo,deNo;
	private JPanel mainPanel;
	private JLabel titleLabel,limitNumberLabel,shuomingLabel,contentLabel;
	private JTextField titleField,limitNumberField;
	private JTextArea contentText;
	private JScrollPane scrollPanel;
	private JButton addButton,quitButton;
	
	private void init() {
		this.setLayout(null);
		mainPanel=new JPanel();
		mainPanel.setBounds(0,0,1000,590);
		mainPanel.setLayout(null);
		
		titleLabel=new JLabel("论文标题：");
		titleLabel.setBounds(180,40,70,40);
		titleField=new JTextField();
		titleField.setBounds(250,40,590,30);
		
		limitNumberLabel=new JLabel("限选人数：");
		limitNumberLabel.setBounds(180,100,70,40);
		limitNumberField=new JTextField();
		limitNumberField.setBounds(250,100,80,30);
		shuomingLabel=new JLabel("个人/每个选题");
		shuomingLabel.setBounds(330,100,100,30);
		
		contentLabel=new JLabel("论题内容：");
		contentLabel.setBounds(180,160,70,40);
		scrollPanel=new JScrollPane();
		scrollPanel.setBounds(250,160,590,320);
		contentText=new JTextArea();
		contentText.setLineWrap(true);  //自动换行
		contentText.setBackground(new Color(204,238,160)); 
//		contentText.setBounds(250,160,580,300);
		scrollPanel.setViewportView(contentText);
		
		addButton=new JButton(" 添 加");
		addButton.setIcon(new ImageIcon("buttonIma/add.png"));
		addButton.addActionListener(this);
		addButton.setBounds(300,518,100,40);
		quitButton=new JButton("   重 置");
		quitButton.setIcon(new ImageIcon("buttonIma/quxiao_2.png"));
		quitButton.addActionListener(this);
		quitButton.setBounds(670,518,100,40);
		
		mainPanel.add(titleLabel);
		mainPanel.add(titleField);
		mainPanel.add(limitNumberLabel);
		mainPanel.add(limitNumberField);
		mainPanel.add(shuomingLabel);
		mainPanel.add(contentLabel);
		mainPanel.add(scrollPanel);
		mainPanel.add(addButton);
		mainPanel.add(quitButton);
		this.add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String paperName=titleField.getText();
		String maxNumber=limitNumberField.getText().trim();
		String paperCuntent=contentText.getText();
		
		/**
		 * 添加论文
		 */
		if(e.getSource()==addButton)
		{
			if(paperName.length()!=0&&maxNumber.length()!=0&&paperCuntent.length()!=0)
			{
				try{
					Paper paper=new Paper();
					paper.setPaperName(paperName);
					paper.setPaperContent(paperCuntent);
					paper.setTeacherNo(teaNo);
					paper.setDepartmentNo(deNo);
					paper.setMaxNumber(Integer.parseInt(maxNumber));
					IPaperService service=new PaperService();
					if (service.insert(paper)==true) {
						JOptionPane.showMessageDialog(this, "添加成功", "提示", 1);
						titleField.setText(null);
						limitNumberField.setText(null);
						contentText.setText(null);
					}
				}catch (Exception e1) {
					System.out.println(e);
					JOptionPane.showMessageDialog(this, "添加失败", "提示", 0);
				} 
				
			}
			
			else if(paperName.length()==0){
				JOptionPane.showMessageDialog(this, "论文标题不能为空", "提示", 0);
			}
			else if(maxNumber.length()==0){
				JOptionPane.showMessageDialog(this, "限制人数不能为空", "提示", 0);
			}
			else if(paperCuntent.length()==0){
				JOptionPane.showMessageDialog(this, "论题内容不能为空", "提示", 0);
			}
		}
		
		/**
		 * 重置
		 */
		if(e.getSource()==quitButton)
		{
			titleField.setText(null);
			limitNumberField.setText(null);
			contentText.setText(null);
		}
		
	}
}
