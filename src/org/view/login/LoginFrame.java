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
		URL url = getClass().getResource("����.png");// ��ȡͼ���ļ�·��
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));// ���ô����ͼ��
		init();
		this.setSize(756, 515);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int a=JOptionPane.showConfirmDialog(jContentPane, "�Ƿ�رմ���"," �رմ���",2,3);
				if(a==0)
				{
					System.exit(0);
				}
			} });
		this.setTitle("������Ŀ����ϵͳ");
		this.setVisible(true);
		this.setResizable(false);//��ֹ������ܵĴ�С
		this.setLocationRelativeTo(null);  //����
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
		Font  boldFont=new Font(" ΢���ź�",Font.BOLD,24);
		Font  plainFont=new Font(" ΢���ź�",Font.PLAIN,16);
		//		jComboBox1 = new JComboBox<String>();
		jContentPane = new JPanel();
		// ���ò��ֹ�����
		jContentPane.setLayout(new BorderLayout());
		loginPanel = new LoginPanel();
		loginPanel.setLayout(null);
		j1=new JLabel("��ݣ�");
		j1.setForeground(Color.white);
		j1.setFont(boldFont);
		j1.setBounds(250,123,80,60);
		j1.setToolTipText("�û����");
		loginPanel.add(j1);
		jComboBox1=new JComboBox<>();
		jComboBox1.setFont(new Font(" ΢���ź�",Font.PLAIN,20));
		jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "��ʦ","ѧ��", "����","ר�Ҵ�" }));
		//		textField.setForeground(Color.black);
		jComboBox1.setBounds(320,136,255,32);
		jComboBox1.setToolTipText("ѡ�����ϵͳ�����");
		loginPanel.add(jComboBox1);
		j2=new JLabel("�˺ţ�");
		j2.setForeground(Color.white);
		j2.setFont(boldFont);
		j2.setBounds(250,176,80,60);
		j2.setToolTipText("��д�˺�");
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

		textField.setToolTipText("�˺�Ϊ�������");
		loginPanel.add(textField);
		j3=new JLabel("���룺");
		j3.setForeground(Color.white);
		j3.setFont(boldFont);
		j3.setBounds(250,235,80,60);
		j3.setToolTipText("��д����");
		loginPanel.add(j3);
		passwordField=new JPasswordField();
		passwordField.setFont(boldFont);
		passwordField.setBounds(320,250,255,32);
		passwordField.setEchoChar('*');  // ���û����ַ�Ϊ*
		passwordField.setFont(plainFont);
		passwordField.setToolTipText("����Ϊ���ѧ����");
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
		j4=new JLabel("��¼��");
		j4.setForeground(Color.white);
		j4.setFont(boldFont);
		j4.setBounds(250,287,80,60);
		j4.setToolTipText("��¼ϵͳ");
		loginPanel.add(j4);
		loginButton = new JButton();
		loginButton.setBounds(410, 293, 68, 68);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		loginButton.setToolTipText("������¼ϵͳ");
		//		 ���ð�ťͼ��
		loginButton.setIcon(new ImageIcon(getClass().getResource(
				"logBut1.png")));
		loginButton.setContentAreaFilled(false);
		// ���ð�ť���¶�����ͼ��
		loginButton.setPressedIcon(new ImageIcon(getClass().getResource(
				"logBut2.png")));
		// ������꾭����ť��ͼ��
		loginButton.setRolloverIcon(new ImageIcon(getClass().getResource(
				"logBut3.png")));
		loginButton.addActionListener(this);
		loginPanel.add(loginButton);
		jContentPane.add(loginPanel, BorderLayout.CENTER);
		this.setContentPane(jContentPane);

		/**
		 * �Զ���ϵͳ����
		 */
		if(SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage("src/image/11.png");

			PopupMenu popupMenu = new PopupMenu();
			MenuItem openItem = new MenuItem("��");
			openItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
				}
			});
			MenuItem exitItem = new MenuItem("�ر�");
			exitItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);                    
				}
			});
			popupMenu.add(openItem);
			popupMenu.add(exitItem);

			trayIcon = new TrayIcon(image, "��ɽѧԺ�������", popupMenu);
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
			JOptionPane.showMessageDialog(this, "����ϵͳ��֧��ϵͳ����", "", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(this, "���벻��Ϊ��","��������ʾ",2);
			}
		}else{
			JOptionPane.showMessageDialog(this, "�˺Ų���Ϊ��","��������ʾ",2);
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginButton)
		{
			if(jComboBox1.getSelectedItem()=="ѧ��")
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
						JOptionPane.showMessageDialog(this, "�������\n������", "��ʾ", 2);
					} 
				}
			} 


			if(jComboBox1.getSelectedItem()=="��ʦ")
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
							JOptionPane.showMessageDialog(this, "�������\n������", "��ʾ", 2);
						} 
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(this, "�˺Ų�����", "��ʾ", 2);
				}
			} 

			if(jComboBox1.getSelectedItem()=="����")
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
						JOptionPane.showMessageDialog(this, "�������\n������", "��ʾ", 2);
					} 
				}
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(this, "�˺Ų�����", "��ʾ", 2);
			}
			}

			if(jComboBox1.getSelectedItem()=="ר�Ҵ�")
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
							JOptionPane.showMessageDialog(this, "�������\n������", "��ʾ", 2);
						} 
					}

				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(this, "�˺Ų�����", "��ʾ", 2);
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
				if(jComboBox1.getSelectedItem()=="ѧ��")
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
							JOptionPane.showMessageDialog(this, "�������\n������", "��ʾ", 2);
						} 
					}
				} 

				if(jComboBox1.getSelectedItem()=="����")
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
								JOptionPane.showMessageDialog(this, "�������\n������", "��ʾ", 2);
							} 
						}

					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(this, "�˺Ų�����", "��ʾ", 2);
					}
				}

				if(jComboBox1.getSelectedItem()=="��ʦ")
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
								JOptionPane.showMessageDialog(this, "�������\n������", "��ʾ", 2);
							} 
						}

					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(this, "�˺Ų�����", "��ʾ", 2);
					}


				}
				
				
				if(jComboBox1.getSelectedItem()=="ר�Ҵ�")
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
								JOptionPane.showMessageDialog(this, "�������\n������", "��ʾ", 2);
							} 
						}

					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(this, "�˺Ų�����", "��ʾ", 2);
					}
				} 
				
				
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}
}
