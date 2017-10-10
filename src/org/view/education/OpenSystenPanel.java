package org.view.education;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OpenSystenPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	public OpenSystenPanel() {
		init();
	}

	private JButton openButton,exitButton,quitButton;
	private ServerThread serverThread;

	public void init(){
		this.setLayout(null);
		this.setBackground(new Color(108,162,207));
		
//		Font  boldFont=new Font(" 微软雅黑",Font.BOLD,24);
//		Font  plainFont=new Font(" 微软雅黑",Font.PLAIN,16);

		openButton=new JButton("开启服务器");
		openButton.setIcon(new ImageIcon("buttonIma/shanchu.png"));
		openButton.addActionListener(this);
		openButton.setBounds(190,400,120,38);
		exitButton=new JButton("关闭服务器");
		exitButton.setEnabled(false);
		exitButton.setIcon(new ImageIcon("buttonIma/tijiao.png"));
		exitButton.addActionListener(this);
		exitButton.setBounds(490,400,120,38);
		quitButton=new JButton("退出服务器");
		quitButton.setIcon(new ImageIcon("buttonIma/shanchu.png"));
		quitButton.addActionListener(this);
		quitButton.setBounds(790,400,120,38);

		this.add(exitButton);
		this.add(openButton);
		this.add(quitButton);
	}
	
	public void success(){
		JOptionPane.showMessageDialog(this, "服务器开启成功", "提示", 1);
	}
	
	public void setStartAndStopUnable() {
		JOptionPane.showMessageDialog(this, "不能同时开启两个服务器");
		openButton.setEnabled(false);
		exitButton.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==openButton){
			try{
				serverThread = new ServerThread(this);
			}catch(Exception e1){
				JOptionPane.showMessageDialog(this, "服务器开启失败", "提示", 0);
			}
			serverThread.setFlag_exit(true);
			serverThread.start();
			openButton.setEnabled(false);
			exitButton.setEnabled(true);
		}
		
		if(e.getSource()==exitButton){
			int flag = JOptionPane.showConfirmDialog(this, "是否要停止服务器？", "", 
            		JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(flag == JOptionPane.OK_OPTION){
            	serverThread.stopServer();
            	openButton.setEnabled(true);
            	exitButton.setEnabled(false);
            }
		}
		
		
		
		if(e.getSource()==quitButton){
			System.exit(0);
		}
		
	}
}
