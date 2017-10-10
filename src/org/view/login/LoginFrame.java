package org.view.login;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.service.IEducationService;
import org.service.IStudentService;
import org.service.ITeacherService;
import org.service.IZhuanJiaService;
import org.service.imp.EducationService;
import org.service.imp.StudentService;
import org.service.imp.TeacherService;
import org.service.imp.ZhuanJiaService;
import org.util.MyLookAndFeel;
import org.view.education.EducationFrame;
import org.view.student.StudentFrame;
import org.view.teacher.TeacherFrame;
import org.view.zj.ZJFrame;

public class LoginFrame extends JFrame implements ActionListener,KeyListener{
	private static final long serialVersionUID = 1L;
	public LoginFrame()
	{
		URL url = getClass().getResource("标题.png");// 获取图标文件路径
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));// 设置窗体的图标
		init();
		this.setSize(756, 515);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int a=JOptionPane.showConfirmDialog(jContentPane, "是否关闭窗口"," 关闭窗口",2,3);
				if(a==0)
				{
					System.exit(0);
				}
			} });
		this.setTitle("论文项目管理系统");
		this.setVisible(true);
		this.setResizable(false);//禁止调整框架的大小
		this.setLocationRelativeTo(null);  //居中
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	private SystemTray tray;
	private TrayIcon trayIcon;

	private JPanel jContentPane = null;
	private LoginPanel loginPanel = null;
	private JLabel j1,j2,j3,j4;
	private JComboBox<String> jComboBox1;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginButton;

	public void init(){
		Font  boldFont=new Font(" 微软雅黑",Font.BOLD,24);
		Font  plainFont=new Font(" 微软雅黑",Font.PLAIN,16);
		//		jComboBox1 = new JComboBox<String>();
		jContentPane = new JPanel();
		// 设置布局管理器
		jContentPane.setLayout(new BorderLayout());
		loginPanel = new LoginPanel();
		loginPanel.setLayout(null);
		j1=new JLabel("身份：");
		j1.setForeground(Color.white);
		j1.setFont(boldFont);
		j1.setBounds(250,123,80,60);
		j1.setToolTipText("用户身份");
		loginPanel.add(j1);
		jComboBox1=new JComboBox<>();
		jComboBox1.setFont(new Font(" 微软雅黑",Font.PLAIN,20));
		jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "教师","学生", "教务处","专家处" }));
		//		textField.setForeground(Color.black);
		jComboBox1.setBounds(320,136,255,32);
		jComboBox1.setToolTipText("选择进入系统的身份");
		loginPanel.add(jComboBox1);
		j2=new JLabel("账号：");
		j2.setForeground(Color.white);
		j2.setFont(boldFont);
		j2.setBounds(250,176,80,60);
		j2.setToolTipText("填写账号");
		loginPanel.add(j2);
		textField=new JTextField(15);
		textField.setFont(plainFont);
		textField.setBounds(320,193,255,32);
		textField.addMouseListener(new MouseAdapter() {
			private Rectangle sourceRec;

			public void mouseEntered(MouseEvent e)
			{
				JTextField textField=(JTextField)e.getSource();
				sourceRec=textField.getBounds();
				textField.setBounds(sourceRec.x-10,sourceRec.y-7,sourceRec.width+20,sourceRec.height+15);
				super.mouseEntered(e);
			}
			public void mouseExited(MouseEvent e)
			{
				JTextField textField=(JTextField)e.getSource();
				if(sourceRec!=null)
				{
					textField.setBounds(sourceRec);
				}
				super.mouseExited(e);
			}
		});

		textField.setToolTipText("账号为你的姓名");
		loginPanel.add(textField);
		j3=new JLabel("密码：");
		j3.setForeground(Color.white);
		j3.setFont(boldFont);
		j3.setBounds(250,235,80,60);
		j3.setToolTipText("填写密码");
		loginPanel.add(j3);
		passwordField=new JPasswordField();
		passwordField.setFont(boldFont);
		passwordField.setBounds(320,250,255,32);
		passwordField.setEchoChar('*');  // 设置回显字符为*
		passwordField.setFont(plainFont);
		passwordField.setToolTipText("密码为你的学工号");
		passwordField.addKeyListener(this);
		passwordField.addMouseListener(new MouseAdapter() {
			private Rectangle sourceRec;

			public void mouseEntered(MouseEvent e)
			{
				JPasswordField passwordField=(JPasswordField)e.getSource();
				sourceRec=passwordField.getBounds();
				passwordField.setBounds(sourceRec.x-10,sourceRec.y-7,sourceRec.width+20,sourceRec.height+15);
				super.mouseEntered(e);
			}
			public void mouseExited(MouseEvent e)
			{
				JPasswordField passwordField=(JPasswordField)e.getSource();
				if(sourceRec!=null)
				{
					passwordField.setBounds(sourceRec);
				}
				super.mouseExited(e);
			}
		});

		loginPanel.add(passwordField);
		j4=new JLabel("登录：");
		j4.setForeground(Color.white);
		j4.setFont(boldFont);
		j4.setBounds(250,287,80,60);
		j4.setToolTipText("登录系统");
		loginPanel.add(j4);
		loginButton = new JButton();
		loginButton.setBounds(410, 293, 68, 68);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		loginButton.setToolTipText("单击登录系统");
		//		 设置按钮图标
		loginButton.setIcon(new ImageIcon(getClass().getResource(
				"logBut1.png")));
		loginButton.setContentAreaFilled(false);
		// 设置按钮按下动作的图标
		loginButton.setPressedIcon(new ImageIcon(getClass().getResource(
				"logBut2.png")));
		// 设置鼠标经过按钮的图标
		loginButton.setRolloverIcon(new ImageIcon(getClass().getResource(
				"logBut3.png")));
		loginButton.addActionListener(this);
		loginPanel.add(loginButton);
		jContentPane.add(loginPanel, BorderLayout.CENTER);
		this.setContentPane(jContentPane);

		/**
		 * 自定义系统托盘
		 */
		if(SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage("src/image/11.png");

			PopupMenu popupMenu = new PopupMenu();
			MenuItem openItem = new MenuItem("打开");
			openItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
				}
			});
			MenuItem exitItem = new MenuItem("关闭");
			exitItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);                    
				}
			});
			popupMenu.add(openItem);
			popupMenu.add(exitItem);

			trayIcon = new TrayIcon(image, "黄山学院打字软件", popupMenu);
			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount()==2) {
						setVisible(true);
					}
				}
			});

			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(this, "您的系统不支持系统托盘", "", JOptionPane.WARNING_MESSAGE);
			return;
		}

	}

	public void frameStyle(){
		try{
			UIManager.setLookAndFeel(MyLookAndFeel.JTATTOO_MINT);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
	}

	public boolean check(JTextField textField,JTextField passwordField){
		if(textField.getText().length()!=0)
		{
			if(passwordField.getText().length()!=0)
			{
				return true;
			}else{
				JOptionPane.showMessageDialog(this, "密码不能为空","空数据提示",2);
			}
		}else{
			JOptionPane.showMessageDialog(this, "账号不能为空","空数据提示",2);
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginButton)
		{
			if(jComboBox1.getSelectedItem()=="学生")
			{
				if(check(textField, passwordField)==true){
					String studentNumber=textField.getText().trim();
					IStudentService studentService=new StudentService();
					Object data[]=studentService.getInfo(studentNumber);
					String studentPassword=(String)data[6];
					String password=passwordField.getText();
					if(password.equals(studentPassword)){
						StudentFrame studentFrame=new StudentFrame(studentNumber);
						frameStyle();
						studentFrame.intoFrame();
						if(studentFrame.getFrameState()==1){
							this.dispose(); 
						}
					}else{
						JOptionPane.showMessageDialog(this, "密码错误\n请重试", "提示", 2);
					} 
				}
			} 


			if(jComboBox1.getSelectedItem()=="教师")
			{
				try{
					if(check(textField, passwordField)==true){
						String teacherNumber=textField.getText().trim();
						int teacherNo=Integer.parseInt(teacherNumber);
						ITeacherService teacherService=new TeacherService();
						Object data[]=teacherService.getInfo(teacherNo);
						String teacherPassword=(String)data[8];
						String password=passwordField.getText();
						if(password.equals(teacherPassword)){
							frameStyle();
							new TeacherFrame(teacherNo);
							this.dispose(); 
						}else{
							JOptionPane.showMessageDialog(this, "密码错误\n请重试", "提示", 2);
						} 
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(this, "账号不存在", "提示", 2);
				}
			} 

			if(jComboBox1.getSelectedItem()=="教务处")
			{try{
				if(check(textField, passwordField)==true){
					String educationNumber=textField.getText().trim();
					int educationNo=Integer.parseInt(educationNumber);
					IEducationService educationService=new EducationService();
					Object data[]=educationService.getInfo(educationNo);
					String educationPassword=(String)data[2];
					String password=passwordField.getText();
					if(password.equals(educationPassword)){
						frameStyle();
						new EducationFrame(educationNo);
						this.dispose(); 
					}else{
						JOptionPane.showMessageDialog(this, "密码错误\n请重试", "提示", 2);
					} 
				}
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(this, "账号不存在", "提示", 2);
			}
			}

			if(jComboBox1.getSelectedItem()=="专家处")
			{
				try{
					if(check(textField, passwordField)==true){
						String zhuanjiaNumber=textField.getText().trim();
						int zhuanjiaNo=Integer.parseInt(zhuanjiaNumber);
						IZhuanJiaService zhuanjiaService=new ZhuanJiaService();
						Object data[]=zhuanjiaService.getInfo(zhuanjiaNo);
						String educationPassword=(String)data[2];
						String password=passwordField.getText();
						if(password.equals(educationPassword)){
							frameStyle();
							new ZJFrame(zhuanjiaNo);
							this.dispose(); 
						}else{
							JOptionPane.showMessageDialog(this, "密码错误\n请重试", "提示", 2);
						} 
					}

				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(this, "账号不存在", "提示", 2);
				}
			} 

		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}
	@SuppressWarnings("deprecation")
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource()==passwordField)
		{
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
				if(jComboBox1.getSelectedItem()=="学生")
				{
					if(check(textField, passwordField)==true){
						String studentNumber=textField.getText().trim();
						IStudentService studentService=new StudentService();
						Object data[]=studentService.getInfo(studentNumber);
						String studentPassword=(String)data[6];
						String password=passwordField.getText();
						if(password.equals(studentPassword)){
							frameStyle();
							StudentFrame studentFrame=new StudentFrame(studentNumber);
							studentFrame.intoFrame();
							if(studentFrame.getFrameState()==1){
								this.dispose(); 
							}
						}else{
							JOptionPane.showMessageDialog(this, "密码错误\n请重试", "提示", 2);
						} 
					}
				} 

				if(jComboBox1.getSelectedItem()=="教务处")
				{
					try{
						if(check(textField, passwordField)==true){
							String educationNumber=textField.getText().trim();
							int educationNo=Integer.parseInt(educationNumber);
							IEducationService educationService=new EducationService();
							Object data[]=educationService.getInfo(educationNo);
							String educationPassword=(String)data[2];
							String password=passwordField.getText();
							if(password.equals(educationPassword)){
								frameStyle();
								new EducationFrame(educationNo);
								this.dispose(); 
							}else{
								JOptionPane.showMessageDialog(this, "密码错误\n请重试", "提示", 2);
							} 
						}

					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(this, "账号不存在", "提示", 2);
					}
				}

				if(jComboBox1.getSelectedItem()=="教师")
				{
					try{
						if(check(textField, passwordField)==true){
							String teacherNumber=textField.getText().trim();
							int teacherNo=Integer.parseInt(teacherNumber);
							ITeacherService teacherService=new TeacherService();
							Object data[]=teacherService.getInfo(teacherNo);
							String teacherPassword=(String)data[8];
							String password=passwordField.getText();
							if(password.equals(teacherPassword)){
								frameStyle();
								new TeacherFrame(teacherNo);
								this.dispose(); 
							}else{
								JOptionPane.showMessageDialog(this, "密码错误\n请重试", "提示", 2);
							} 
						}

					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(this, "账号不存在", "提示", 2);
					}


				}
				
				
				if(jComboBox1.getSelectedItem()=="专家处")
				{
					try{
						if(check(textField, passwordField)==true){
							String zhuanjiaNumber=textField.getText().trim();
							int zhuanjiaNo=Integer.parseInt(zhuanjiaNumber);
							IZhuanJiaService zhuanjiaService=new ZhuanJiaService();
							Object data[]=zhuanjiaService.getInfo(zhuanjiaNo);
							String educationPassword=(String)data[2];
							String password=passwordField.getText();
							if(password.equals(educationPassword)){
								frameStyle();
								new ZJFrame(zhuanjiaNo);
								this.dispose(); 
							}else{
								JOptionPane.showMessageDialog(this, "密码错误\n请重试", "提示", 2);
							} 
						}

					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(this, "账号不存在", "提示", 2);
					}
				} 
				
				
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}
}
