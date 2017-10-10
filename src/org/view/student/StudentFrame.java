package org.view.student;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.model.Student;
import org.service.IStudentService;
import org.service.imp.StudentService;
import org.view.student.choosePaper.Student_ChoosePaperPanel;

public class StudentFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	public String login(String hostIp, int hostPort){
		try {
			 InetAddress  address=InetAddress.getByName(hostIp);
             InetSocketAddress socketAddress=new InetSocketAddress(address,hostPort);
             new Socket().connect(socketAddress); 
			return "true";
		}catch (NumberFormatException e) {
			login_mess = "���ӵķ������˿ں�portΪ����,ȡֵ��ΧΪ��1024<port<65535";
			return login_mess;
		} catch (UnknownHostException e) {
			login_mess = "������ַ����";
			return login_mess;
		} catch (IOException e) {
			login_mess = "���ӷ�����ʧ�ܣ����Ժ�����";
			return login_mess;
		}
	}
	
	public void intoFrame(){
		String login_mess = login("127.0.0.1",2016);
		if(login_mess.equals("true")){
			setFrameState(1);
			init();
			this.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(this, login_mess);
			this.dispose();
		}
	}
	
	public void closeFrame(){
		JOptionPane.showMessageDialog(this, "�������ѱ��رգ����˳�ϵͳ");
		System.exit(0);
	}
	
	
	
	public int getFrameState() {
		return frameState;
	}

	public void setFrameState(int frameState) {
		this.frameState = frameState;
	}

	public StudentFrame(String stuNo) {
		URL url = getClass().getResource("����.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));
		studentNo=stuNo;
		this.setTitle("ѧ����������");
		this.setSize(1000,710);	
		this.setResizable(false);//��ֹ������ܵĴ�С
		this.setLocationRelativeTo(null);  //����
//		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private String login_mess;
	private int frameState;
	
	private int departmentNo;
	private String studentNo;
	private String studentName,studentClass;
	private Student student;
	
	private JPanel mainPanel,showPanel,textPanel;
	private JLabel infoLabel,timeLabel;
	private CardLayout card;
	private String str="ѡ������ ����";
	
	private JMenuBar bar;
	private JMenu myPaperMenu,lookMenu,infoMenu;
	private JMenuItem chooseItem;
	
	private Student_ChoosePaperPanel choosePaperPanel;
	
	private void init() {
		/**
		 * ��ʼ������
		 */
		IStudentService studentService=new StudentService();
		Object data[]=studentService.getInfo(studentNo);
		studentName=(String) data[0];
		studentClass=(String) data[1];
		departmentNo=(int)data[2];
		student=new Student();
		student.setStudentNo(studentNo);
		student.setDepartmentNo(departmentNo);
//		System.out.println(studentNo+studentName+studentClass+departmentNo);
		
		/**
		 * �����
		 */
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.blue);
		
		/**
		 * ���������
		 */
		choosePaperPanel=new Student_ChoosePaperPanel(student);
		choosePaperPanel.setBounds(0,0,1000,590);
		
		/**
		 * ����
		 */
		bar=new JMenuBar();
		myPaperMenu=new JMenu("��������     ");
		myPaperMenu.setIcon(new ImageIcon("image/myPaper_2.png"));
		lookMenu=new JMenu("����ѡ������  ");
		lookMenu.setIcon(new ImageIcon("image/look_2.png"));
		infoMenu=new JMenu("�����������Ϣ");
		infoMenu.setIcon(new ImageIcon("image/user_4.png"));
		
		chooseItem=new JMenuItem("ѡ������");
		chooseItem.setIcon(new ImageIcon("image/choose.png"));
		chooseItem.addActionListener(this);
		myPaperMenu.add(chooseItem);
		
		bar.add(myPaperMenu);
		bar.add(lookMenu);
		bar.add(infoMenu);
		this.setJMenuBar(bar);
		
		/**
		 * �ײ���Ϣҳ
		 */
		Font  font=new Font("΢���ź�",Font.BOLD,18);
		infoLabel=new JLabel();
		infoLabel.setText(studentName+"ͬѧ����á�            ����ǰ��λ�ã�"+str+"               �����ڵİ༶��"+studentClass);
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
							String login_mess = login("127.0.0.1",2016);
							if(login_mess.equals("true")){}
							else{closeFrame();}
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
		showPanel.add("insert", choosePaperPanel);
		
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

		if(com.equals("ѡ������"))
		{
			card.show(showPanel,"choosePaperPanel");
			str="ѡ������ ����";
		}
	}
}
