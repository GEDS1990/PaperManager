package org.view.teacher.myUnauditPaperPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class MyPaperUpdateDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	Paper paper;
	public  MyPaperUpdateDialog(Paper paper)
	{   
		this.setModal(true);
		this.paper=paper;
		init();
		this.setSize(650, 650);
		this.setTitle("修改论文内容");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.validate();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private JPanel mainPanel;
	private JLabel titleLabel,limitNumberLabel,shuomingLabel,contentLabel;
	private JTextField titleField,limitNumberField;
	private JTextArea contentText;
	private JScrollPane scrollPanel;
	private JButton addButton,quitButton;

	public void init()
	{    
		mainPanel=new JPanel();
		mainPanel.setBackground(new Color(227,241,163));
		mainPanel.setBounds(0,0,650,600);
		mainPanel.setLayout(null);

		titleLabel=new JLabel("论文标题：");
		titleLabel.setBounds(60,20,70,40);
		titleField=new JTextField();
		titleField.setText(paper.getPaperName());
		titleField.setBounds(140,20,450,30);

		limitNumberLabel=new JLabel("限选人数：");
		limitNumberLabel.setBounds(60,80,70,40);
		limitNumberField=new JTextField();
		limitNumberField.setText(String.valueOf(paper.getMaxNumber()));
		limitNumberField.setBounds(140,80,80,30);
		shuomingLabel=new JLabel("个人/每个选题");
		shuomingLabel.setBounds(220,80,100,30);

		contentLabel=new JLabel("论题内容：");
		contentLabel.setBounds(60,140,70,40);
		scrollPanel=new JScrollPane();
		scrollPanel.setBounds(140,140,450,410);
		contentText=new JTextArea();
		contentText.setLineWrap(true);  //自动换行
		contentText.setText(paper.getPaperContent());
		scrollPanel.setViewportView(contentText);

		addButton=new JButton("修  改");
		addButton.setIcon(new ImageIcon("buttonIma/xiugai.png"));
		addButton.addActionListener(this);
		addButton.setBounds(160,567,100,40);
		quitButton=new JButton("取  消");
		quitButton.setIcon(new ImageIcon("buttonIma/quit-2.png"));
		quitButton.addActionListener(this);
		quitButton.setBounds(450,567,100,40);

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
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	public void actionPerformed(ActionEvent e) {
		int paperNo=paper.getPaperNo();
		if(e.getSource()==addButton)
		{
			Paper paper=new Paper();
			paper.setPaperNo(paperNo);
			paper.setPaperName(titleField.getText());
			paper.setMaxNumber(Integer.parseInt(limitNumberField.getText().trim()));
			paper.setPaperContent(contentText.getText());
			this.setPaper(paper);
			IPaperService service=new PaperService();
			if(service.update(paper)){
				JOptionPane.showMessageDialog(this, "修改成功", "提示", 2);
			}
			this.setVisible(false);
			this.dispose();
		}
		if(e.getSource()==quitButton)
		{
			this.setVisible(false);
			this.dispose();
		}

	}

}
