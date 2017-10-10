package org.view.zj;

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

import org.model.ZJ;
import org.service.IZhuanJiaService;
import org.service.imp.ZhuanJiaService;


public class ZJFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	public ZJFrame(int zjNo) {
		URL url = getClass().getResource("标题.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));
		zhuanjiaNo=zjNo;
		init();
		this.setTitle("专家处操作界面");
		this.setSize(1000,710);	
		this.setResizable(false);//禁止调整框架的大小
		this.setLocationRelativeTo(null);  //居中
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private int zhuanjiaNo;
	private String zhuanjiaName;
	private ZJ zhuanjia;
	
	private JPanel mainPanel,showPanel,textPanel;
	private JLabel infoLabel,timeLabel;
	private CardLayout card;
	private String str="审核论文 界面";
	
	private JMenuBar bar;
	private JMenu auditPaperMenu,infoMenu;
	private JMenuItem chooseItem,myInfoItem;
	
	
	private void init() {
		/**
		 * 初始化数据
		 */
		IZhuanJiaService service=new ZhuanJiaService();
		Object data[]=service.getInfo(zhuanjiaNo);
		zhuanjiaName=(String)data[0];
		
		zhuanjia=new ZJ();
		zhuanjia.setZjNo(zhuanjiaNo);
		zhuanjia.setZjName(zhuanjiaName);
		
		/**
		 * 主面板
		 */
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.blue);
		
		/**
		 * 增加子面板
		 */
		ZJAuditPanel zhuanjiaAuditPanel=new ZJAuditPanel(zhuanjia);
		zhuanjiaAuditPanel.setBounds(0,0,1000,590);
		ZJ_PasswordPanel passwordPanel=new ZJ_PasswordPanel(zhuanjia);
		passwordPanel.setBounds(0,0,1000,590);
		
		/**
		 * 顶部
		 */
		bar=new JMenuBar();
		auditPaperMenu=new JMenu("审核论文   ");
		auditPaperMenu.setIcon(new ImageIcon("image/audit.png"));
		infoMenu=new JMenu("个人信息   ");
		infoMenu.setIcon(new ImageIcon("image/user_3.png"));
		
		chooseItem=new JMenuItem("审核论文");
		chooseItem.setIcon(new ImageIcon("image/audit_2.png"));
		chooseItem.addActionListener(this);
		auditPaperMenu.add(chooseItem);
		
		myInfoItem=new JMenuItem("个人资料");
		myInfoItem.setIcon(new ImageIcon("image/user.png"));
		myInfoItem.addActionListener(this);
		JMenuItem passwordItem=new JMenuItem("修改密码");
		passwordItem.setIcon(new ImageIcon("image/password.png"));
		passwordItem.addActionListener(this);
		
		infoMenu.add(myInfoItem);
		infoMenu.add(passwordItem);
		
		bar.add(auditPaperMenu);
		bar.add(infoMenu);
		this.setJMenuBar(bar);
		
		/**
		 * 底部信息页
		 */
		Font  font=new Font("微软雅黑",Font.BOLD,18);
		infoLabel=new JLabel();
		infoLabel.setText("                               审核人："+zhuanjiaName+"  您好。            您当前的位置："+str+"                               ");
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
		showPanel.add("zhuanjiaAuditPanel", zhuanjiaAuditPanel);
		showPanel.add("passwordPanel", passwordPanel);
		
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
			card.show(showPanel,"zhuanjiaAuditPanel");
			str="审核论文 界面";
		}
		if(com.equals("修改密码"))
		{
			card.show(showPanel,"passwordPanel");
			str="修改密码 界面";
		}
		
		infoLabel.setText("                               审核人："+zhuanjiaName+"  您好。            您当前的位置："+str+"                               ");
	}
}