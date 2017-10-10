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
		URL url = getClass().getResource("����.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));
		teacherNo=teaNo;
		init();
		this.setTitle("��ʦ��������");
		this.setSize(1000,710);	
		this.setResizable(false);//��ֹ������ܵĴ�С
		this.setLocationRelativeTo(null);  //����
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
	private CardLayout card;   //��Ƭʽ����.
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
	private String str="�������� ����";

	public void init()
	{
		/**
		 * ��ʼ������
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
		 * �����
		 */
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.blue);
		
		/*
		 * ���������
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
		myPaperMenu=new JMenu("�ҵ�����     ");
		myPaperMenu.setIcon(new ImageIcon("image/myPaper.png"));
		lookMenu=new JMenu("����ѡ������  ");
		lookMenu.setIcon(new ImageIcon("image/look.png"));
		infoMenu=new JMenu("�����������Ϣ");
		infoMenu.setIcon(new ImageIcon("image/user_3.png"));

		insertPaperItem=new JMenuItem("��������");
		insertPaperItem.setIcon(new ImageIcon("image/add.png"));
		insertPaperItem.addActionListener(this);
		myUnrelesePaper=new JMenuItem("δ��������");
		myUnrelesePaper.setIcon(new ImageIcon("image/unRelese_2.png"));
		myUnrelesePaper.addActionListener(this);
		myRelesePaper=new JMenuItem("�ѷ�������");
		myRelesePaper.setIcon(new ImageIcon("image/relese.png"));
		myRelesePaper.addActionListener(this);
		myUnauditPaper=new JMenuItem("���δ�������");
		myUnauditPaper.setIcon(new ImageIcon("image/unDo.png"));
		myUnauditPaper.addActionListener(this);
		myUnpassPaper=new JMenuItem("���ϸ�����");
		myUnpassPaper.setIcon(new ImageIcon("image/no.png"));
		myUnpassPaper.addActionListener(this);
		
		allPaperItem=new JMenuItem("Ժϵ��������");
		allPaperItem.setIcon(new ImageIcon("image/class.png"));
		allPaperItem.addActionListener(this);
		classItem=new JMenuItem("Ժϵѡ�����");
		classItem.setIcon(new ImageIcon("image/docu.png"));
		classItem.addActionListener(this);
		schoolItem=new JMenuItem("ȫУѡ������");
		schoolItem.setIcon(new ImageIcon("image/school.png"));
		schoolItem.addActionListener(this);
		
		myInfoItem=new JMenuItem("��������");
		myInfoItem.setIcon(new ImageIcon("image/user.png"));
		myInfoItem.addActionListener(this);
		passwordItem=new JMenuItem("�޸�����");
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
		 * �ײ���Ϣҳ
		 */
		Font  font=new Font(" ΢���ź�",Font.BOLD,18);
		infoLabel=new JLabel();
		infoLabel.setText(teacherName+"��ʦ�����á�            ����ǰ��λ�ã�"+str+"               �����ڵ�Ժϵ��"+departmentName);
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
							String s=String.format("%tY��%<tm��%<td��  %<tA  %<tT",new Date());
							timeLabel.setText("��ǰʱ�䣺"+s);
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

		if(com.equals("��������"))
		{
			card.show(showPanel,"insert");
			str="�������� ����";
		}
		
		if(com.equals("���δ�������"))
		{
			card.show(showPanel,"myUnauditPaperPanel");
			str="���δ������� ����";
		}
		
		if(com.equals("���ϸ�����"))
		{
			card.show(showPanel,"myUnpassPaperPanel");
			str="���ϸ����� ����";
		}
		
		if(com.equals("δ��������"))
		{
			card.show(showPanel,"myUnrelesePaperPanel");
			str="δ�������� ����";
		}
		
		
		if(com.equals("�ѷ�������"))
		{
			card.show(showPanel,"myrelesePaperPanel");
			str="�ѷ������� ����";
		}
		
		if(com.equals("Ժϵ��������"))
		{
			card.show(showPanel,"allPaperPanel");
			str="Ժϵ�������� ����";
		}
		
		if(com.equals("Ժϵѡ�����"))
		{
			card.show(showPanel,"chooseNumPanel");
			str="Ժϵѡ�����";
		}
		
		if(com.equals("ȫУѡ������"))
		{
			card.show(showPanel,"schoolChooseNumPanel");
			str="ȫУѡ������ ����";
		}
		
		if(com.equals("��������"))
		{
			card.show(showPanel,"myInfoPanel");
			str="�������� ����";
		}
		
		if(com.equals("�޸�����"))
		{
			card.show(showPanel,"passwordPanel");
			str="�޸����� ����";
		}
		infoLabel.setText(teacherName+"��ʦ�����á�            ����ǰ��λ�ã�"+str+"               �����ڵ�Ժϵ��"+departmentName);
	}

}
