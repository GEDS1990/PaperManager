package org.view.education;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.model.Education;
import org.service.IEducationService;
import org.service.imp.EducationService;
import org.view.ExcelPanel;

public class EducationFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	public EducationFrame(int eduNo) {
		URL url = getClass().getResource("标题.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));
		educationNo=eduNo;
		init();
		this.setTitle("教务处操作界面");
		this.setSize(1000,710);	
		this.setResizable(false);//禁止调整框架的大小
		this.setLocationRelativeTo(null);  //居中
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private int educationNo;
	private String educationName;
	private Education education;
	
	private JPanel mainPanel,showPanel,textPanel;
	private JLabel infoLabel,timeLabel;
	private CardLayout card;
	private String str="审核论文 界面";
	
	private JMenuBar bar;
	private JMenu auditPaperMenu,infoMenu,addStudentMenu;
	private JMenuItem chooseItem,myInfoItem,passwordItem,addStudentItem,openItem;
	
	
	private void init() {
		/**
		 * 初始化数据
		 */
		IEducationService service=new EducationService();
		Object data[]=service.getInfo(educationNo);
		educationName=(String)data[0];
		
		education=new Education();
		education.setEducationNo(educationNo);
		education.setEducationName(educationName);
		
		/**
		 * 主面板
		 */
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.blue);
		
		/**
		 * 增加子面板
		 */
		EducationAuditPanel educationAuditPanel=new EducationAuditPanel(education);
		educationAuditPanel.setBounds(0,0,1000,590);
		Edu_PasswordPanel passwordPanel=new Edu_PasswordPanel(education);
		passwordPanel.setBounds(0,0,1000,590);
		ExcelPanel excelPanel=new ExcelPanel();
		excelPanel.setBounds(0,0,1000,590);
		OpenSystenPanel openSystenPanel=new OpenSystenPanel();
		openSystenPanel.setBounds(0,0,1000,590);
		
		/**
		 * 顶部
		 */
		bar=new JMenuBar();
		auditPaperMenu=new JMenu("审核论文   ");
		auditPaperMenu.setIcon(new ImageIcon("image/audit.png"));
		infoMenu=new JMenu("个人信息   ");
		infoMenu.setIcon(new ImageIcon("image/user_3.png"));
		addStudentMenu=new JMenu("教务操作 ");
		addStudentMenu.setIcon(new ImageIcon("image/addstudent.png"));
		
		chooseItem=new JMenuItem("审核论文");
		chooseItem.setIcon(new ImageIcon("image/audit_2.png"));
		chooseItem.addActionListener(this);
		auditPaperMenu.add(chooseItem);
		
		myInfoItem=new JMenuItem("个人资料");
		myInfoItem.setIcon(new ImageIcon("image/user.png"));
		myInfoItem.addActionListener(this);
		passwordItem=new JMenuItem("修改密码");
		passwordItem.setIcon(new ImageIcon("image/password.png"));
		passwordItem.addActionListener(this);
		
		infoMenu.add(myInfoItem);
		infoMenu.add(passwordItem);
		
		addStudentItem=new JMenuItem("导入学生");
		addStudentItem.setIcon(new ImageIcon("image/addstudent_2.png"));
		addStudentItem.addActionListener(this);
		openItem=new JMenuItem("管理系统");
		openItem.setIcon(new ImageIcon("image/open_1.png"));
		openItem.addActionListener(this);
		
		addStudentMenu.add(addStudentItem);
		addStudentMenu.add(openItem);
		
		bar.add(auditPaperMenu);
		bar.add(infoMenu);
		bar.add(addStudentMenu);
		this.setJMenuBar(bar);
		
		/**
		 * 底部信息页
		 */
		Font  font=new Font("微软雅黑",Font.BOLD,18);
		infoLabel=new JLabel();
		infoLabel.setText("                               "+educationName+"教师，您好。            您当前的位置："+str+"                               ");
		infoLabel.setForeground(Color.white);
		infoLabel.setFont(font);
		timeLabel=new JLabel();
		timeLabel.setForeground(Color.white);
		timeLabel.setFont(font);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					int delay=1000;
					ActionListener lister=new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String s=String.format("%tY年%<tm月%<td日  %<tA  %<tT",new Date());
							timeLabel.setText("当前时间："+s);
						}
					};
					new Timer(delay,lister).start();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});


		card=new CardLayout();
		showPanel=new JPanel();
		showPanel.setLayout(card);
		showPanel.setBackground(new Color(131,175,155));
		showPanel.setBounds(0,0,1000,590);
		showPanel.add("educationAuditPanel", educationAuditPanel);
		showPanel.add("passwordPanel", passwordPanel);
		showPanel.add("excelPanel",excelPanel);
		showPanel.add("openSystenPanel",openSystenPanel);
		
		textPanel=new JPanel();
		textPanel.setBackground(new Color(69,137,148));
		textPanel.setBounds(0,590,1000,156);
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setHgap(15);
		textPanel.setLayout(flowLayout);
		textPanel.add(infoLabel);	
		textPanel.add(timeLabel);
		mainPanel.add(textPanel);
		mainPanel.add(showPanel);
		this.add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String com=e.getActionCommand();

		if(com.equals("审核论文"))
		{
			card.show(showPanel,"educationAuditPanel");
			str="审核论文 界面";
		}
		
		if(com.equals("修改密码"))
		{
			card.show(showPanel,"passwordPanel");
			str="修改密码 界面";
		}
		
		if(com.equals("导入学生"))
		{
			card.show(showPanel,"excelPanel");
			str="导入学生 界面";
		}
		
		if(com.equals("管理系统"))
		{
			card.show(showPanel,"openSystenPanel");
			str="管理系统 界面";
		}
		
		infoLabel.setText("                               "+educationName+"教师，您好。            您当前的位置："+str+"                               ");
		
	}
}

