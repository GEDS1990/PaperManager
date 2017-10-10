package org.view.teacher;

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

import org.model.Teacher;
import org.service.ITeacherService;
import org.service.imp.TeacherService;
import org.view.teacher.allPaperPanel.AllPaperPanel;
import org.view.teacher.chooseNumberPanel.ChooseNumPanel;
import org.view.teacher.insertPaperPanel.InsertPaperPanel;
import org.view.teacher.myInfoPanel.MyInfoPanel;
import org.view.teacher.myInfoPanel.PasswordPanel;
import org.view.teacher.myRelesePaperPanel.MyrelesePaperPanel;
import org.view.teacher.myUnauditPaperPanel.MyUnauditPaperPanel;
import org.view.teacher.myUnpassPaperPanel.MyUnpassPaperPanel;
import org.view.teacher.myUnrelesePaperPanel.MyUnrelesePaperPanel;
import org.view.teacher.schoolChoosePanel.SchoolChooseNumPanel;

public class TeacherFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	public TeacherFrame(int teaNo) {
		URL url = getClass().getResource("标题.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));
		teacherNo=teaNo;
		init();
		this.setTitle("教师操作界面");
		this.setSize(1000,710);	
		this.setResizable(false);//禁止调整框架的大小
		this.setLocationRelativeTo(null);  //居中
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private int teacherNo,departmentNo;
	private String departmentName,teacherName;
	private Teacher teacher;
	private JMenuBar bar;
	private JMenu myPaperMenu,lookMenu,infoMenu;
	private JMenuItem insertPaperItem,myUnpassPaper,myUnauditPaper,myUnrelesePaper,myRelesePaper,
	          allPaperItem,classItem,schoolItem,myInfoItem,passwordItem;
	private CardLayout card;   //卡片式布局.
	private JPanel mainPanel,showPanel,textPanel;
	private FlowLayout flowLayout;
	private JLabel infoLabel,timeLabel;
	private InsertPaperPanel insertPaperPanel;
	private MyUnauditPaperPanel myUnauditPaperPanel;
	private MyUnpassPaperPanel myUnpassPaperPanel;
	private MyUnrelesePaperPanel myUnrelesePaperPanel;
	private MyrelesePaperPanel myrelesePaperPanel;
	private AllPaperPanel allPaperPanel;
	private ChooseNumPanel chooseNumPanel;
	private SchoolChooseNumPanel schoolChooseNumPanel;
	private MyInfoPanel myInfoPanel;
	private PasswordPanel passwordPanel;
	private String str="增加论文 界面";

	public void init()
	{
		/**
		 * 初始化数据
		 */
		ITeacherService teacherService=new TeacherService();
		Object data[]=teacherService.getInfo(teacherNo);
		teacherName=(String) data[0];
		departmentName=(String) data[1];
		departmentNo=(int)data[7];
		
		teacher=new Teacher();
		teacher.setTeacherNo(teacherNo);
		teacher.setDepartmentNo(departmentNo);
		
		/**
		 * 主面板
		 */
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.blue);
		
		/*
		 * 增加子面板
		 */
		insertPaperPanel=new InsertPaperPanel(teacher);
		insertPaperPanel.setBounds(0,0,1000,590);
		myUnauditPaperPanel=new MyUnauditPaperPanel(teacher);
		myUnauditPaperPanel.setBounds(0,0,1000,590);
		myUnpassPaperPanel=new MyUnpassPaperPanel(teacher);
		myUnpassPaperPanel.setBounds(0,0,1000,590);
		myUnrelesePaperPanel=new MyUnrelesePaperPanel(teacher);
		myUnrelesePaperPanel.setBounds(0,0,1000,590);
		myrelesePaperPanel=new MyrelesePaperPanel(teacher);
		myrelesePaperPanel.setBounds(0,0,1000,590);
		allPaperPanel=new AllPaperPanel(teacher);
		allPaperPanel.setBounds(0,0,1000,590);
		chooseNumPanel=new ChooseNumPanel(teacher);
		chooseNumPanel.setBounds(0,0,1000,590);
		schoolChooseNumPanel=new SchoolChooseNumPanel(teacher);
		schoolChooseNumPanel.setBounds(0,0,1000,590);
		myInfoPanel=new MyInfoPanel(teacher);
		myInfoPanel.setBounds(0,0,1000,590);
		passwordPanel=new PasswordPanel(teacher);
		passwordPanel.setBounds(0,0,1000,590);

		card=new CardLayout();
		showPanel=new JPanel();
		showPanel.setLayout(card);
		showPanel.setBackground(Color.lightGray);
		showPanel.setBounds(0,0,1000,590);
		showPanel.add("insert", insertPaperPanel);
		showPanel.add("myUnauditPaperPanel", myUnauditPaperPanel);
		showPanel.add("myUnpassPaperPanel", myUnpassPaperPanel);
		showPanel.add("myUnrelesePaperPanel", myUnrelesePaperPanel);
		showPanel.add("myrelesePaperPanel", myrelesePaperPanel);
		showPanel.add("allPaperPanel", allPaperPanel);
		showPanel.add("chooseNumPanel", chooseNumPanel);
		showPanel.add("schoolChooseNumPanel", schoolChooseNumPanel);
		showPanel.add("myInfoPanel", myInfoPanel);
		showPanel.add("passwordPanel", passwordPanel);
		
		bar=new JMenuBar();
		myPaperMenu=new JMenu("我的论文     ");
		myPaperMenu.setIcon(new ImageIcon("image/myPaper.png"));
		lookMenu=new JMenu("论文选题总览  ");
		lookMenu.setIcon(new ImageIcon("image/look.png"));
		infoMenu=new JMenu("审批与个人信息");
		infoMenu.setIcon(new ImageIcon("image/user_3.png"));

		insertPaperItem=new JMenuItem("增加论文");
		insertPaperItem.setIcon(new ImageIcon("image/add.png"));
		insertPaperItem.addActionListener(this);
		myUnrelesePaper=new JMenuItem("未发布论文");
		myUnrelesePaper.setIcon(new ImageIcon("image/unRelese_2.png"));
		myUnrelesePaper.addActionListener(this);
		myRelesePaper=new JMenuItem("已发布论文");
		myRelesePaper.setIcon(new ImageIcon("image/relese.png"));
		myRelesePaper.addActionListener(this);
		myUnauditPaper=new JMenuItem("审核未完成论文");
		myUnauditPaper.setIcon(new ImageIcon("image/unDo.png"));
		myUnauditPaper.addActionListener(this);
		myUnpassPaper=new JMenuItem("不合格论文");
		myUnpassPaper.setIcon(new ImageIcon("image/no.png"));
		myUnpassPaper.addActionListener(this);
		
		allPaperItem=new JMenuItem("院系所有论文");
		allPaperItem.setIcon(new ImageIcon("image/class.png"));
		allPaperItem.addActionListener(this);
		classItem=new JMenuItem("院系选题情况");
		classItem.setIcon(new ImageIcon("image/docu.png"));
		classItem.addActionListener(this);
		schoolItem=new JMenuItem("全校选题总览");
		schoolItem.setIcon(new ImageIcon("image/school.png"));
		schoolItem.addActionListener(this);
		
		myInfoItem=new JMenuItem("个人资料");
		myInfoItem.setIcon(new ImageIcon("image/user.png"));
		myInfoItem.addActionListener(this);
		passwordItem=new JMenuItem("修改密码");
		passwordItem.setIcon(new ImageIcon("image/password.png"));
		passwordItem.addActionListener(this);

		myPaperMenu.add(insertPaperItem);
		myPaperMenu.addSeparator();
		myPaperMenu.add(myUnrelesePaper);
		myPaperMenu.add(myRelesePaper);
		myPaperMenu.addSeparator();
		myPaperMenu.add(myUnauditPaper);
		myPaperMenu.addSeparator();
		myPaperMenu.add(myUnpassPaper);
		lookMenu.add(allPaperItem);
		lookMenu.add(classItem);
		lookMenu.addSeparator();
		lookMenu.add(schoolItem);
		infoMenu.add(myInfoItem);
		infoMenu.add(passwordItem);
		bar.add(myPaperMenu);
		bar.add(lookMenu);
		bar.add(infoMenu);
		this.setJMenuBar(bar);


		/**
		 * 底部信息页
		 */
		Font  font=new Font(" 微软雅黑",Font.BOLD,18);
		infoLabel=new JLabel();
		infoLabel.setText(teacherName+"老师，您好。            您当前的位置："+str+"               您所在的院系："+departmentName);
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

		textPanel=new JPanel();
		textPanel.setBackground(new Color(69,137,148));
		textPanel.setBounds(0,590,1000,156);
		flowLayout = new FlowLayout();
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

		if(com.equals("增加论文"))
		{
			card.show(showPanel,"insert");
			str="增加论文 界面";
		}
		
		if(com.equals("审核未完成论文"))
		{
			card.show(showPanel,"myUnauditPaperPanel");
			str="审核未完成论文 界面";
		}
		
		if(com.equals("不合格论文"))
		{
			card.show(showPanel,"myUnpassPaperPanel");
			str="不合格论文 界面";
		}
		
		if(com.equals("未发布论文"))
		{
			card.show(showPanel,"myUnrelesePaperPanel");
			str="未发布论文 界面";
		}
		
		
		if(com.equals("已发布论文"))
		{
			card.show(showPanel,"myrelesePaperPanel");
			str="已发布论文 界面";
		}
		
		if(com.equals("院系所有论文"))
		{
			card.show(showPanel,"allPaperPanel");
			str="院系所有论文 界面";
		}
		
		if(com.equals("院系选题情况"))
		{
			card.show(showPanel,"chooseNumPanel");
			str="院系选题情况";
		}
		
		if(com.equals("全校选题总览"))
		{
			card.show(showPanel,"schoolChooseNumPanel");
			str="全校选题总览 界面";
		}
		
		if(com.equals("个人资料"))
		{
			card.show(showPanel,"myInfoPanel");
			str="个人资料 界面";
		}
		
		if(com.equals("修改密码"))
		{
			card.show(showPanel,"passwordPanel");
			str="修改密码 界面";
		}
		infoLabel.setText(teacherName+"老师，您好。            您当前的位置："+str+"               您所在的院系："+departmentName);
	}

}
