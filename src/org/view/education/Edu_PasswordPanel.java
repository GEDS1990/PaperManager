package org.view.education;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.model.Education;
import org.service.IEducationService;
import org.service.imp.EducationService;


public class Edu_PasswordPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	public Edu_PasswordPanel(Education education) {
		educationNo=education.getEducationNo();
		init();
	}

	private int educationNo;
	private JLabel oldLabel,newLabel_1,newLabel_2;
	private JPasswordField oldField,newField_1,newField_2;
	private JButton submitButton,cancelButton;

	public void init(){
		this.setLayout(null);
		this.setBackground(new Color(108,162,207));
		
		Font  boldFont=new Font(" 微软雅黑",Font.BOLD,24);
		Font  plainFont=new Font(" 微软雅黑",Font.PLAIN,16);

		oldLabel=new JLabel("旧密码：");
		oldLabel.setFont(boldFont);
		oldLabel.setForeground(Color.white);
		oldLabel.setBounds(310,140,200,36);
		oldField=new JPasswordField();
		//		oldField.setEchoChar('*');  // 设置回显字符为*
		oldField.setFont(plainFont);
		oldField.setBounds(400,140,280,36);

		newLabel_1=new JLabel("新密码：");
		newLabel_1.setFont(boldFont);
		newLabel_1.setForeground(Color.white);
		newLabel_1.setBounds(310,220,200,36);
		newField_1=new JPasswordField();
		//		newField_1.setEchoChar('*');  // 设置回显字符为*
		newField_1.setFont(plainFont);
		newField_1.setBounds(400,220,280,36);

		newLabel_2=new JLabel("确认密码：");
		newLabel_2.setFont(boldFont);
		newLabel_2.setForeground(Color.white);
		newLabel_2.setBounds(280,300,200,36);
		newField_2=new JPasswordField();
		//		newField_2.setEchoChar('*');  // 设置回显字符为*
		newField_2.setFont(plainFont);
		newField_2.setBounds(400,300,280,36);

		cancelButton=new JButton("  清空");
		cancelButton.setIcon(new ImageIcon("buttonIma/shanchu.png"));
		cancelButton.addActionListener(this);
		cancelButton.setBounds(290,400,105,38);
		submitButton=new JButton("  提交");
		submitButton.setIcon(new ImageIcon("buttonIma/tijiao.png"));
		submitButton.addActionListener(this);
		submitButton.setBounds(590,400,105,38);

		this.add(oldLabel);
		this.add(oldField);
		this.add(newLabel_1);
		this.add(newField_1);
		this.add(newLabel_2);
		this.add(newField_2);
		this.add(submitButton);
		this.add(cancelButton);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submitButton){
			String oldPassword=oldField.getText();
			String newPassword_1=newField_1.getText();
			String newPassword_2=newField_2.getText();
			if(oldPassword.length()!=0&&newPassword_1.length()!=0&&newPassword_2.length()!=0){
				if(newPassword_1.equals(newPassword_2)){
					IEducationService educationService=new EducationService();
					Object data[]=educationService.getInfo(educationNo);
					String educationPassword=(String)data[2];
					if(educationPassword.equals(oldPassword)){
						Education education=new Education();
						education.setEduPassword(newPassword_2);
						education.setEducationNo(educationNo);
						if(educationService.updata_password(education)==true){
							JOptionPane.showMessageDialog(this, "密码修改成功", "提示", 1);
							oldField.setText(null);
							newField_1.setText(null);
							newField_2.setText(null);
						}else{
//							System.out.println(educationPassword+"---"+oldPassword+"---"+newPassword_2);
							JOptionPane.showMessageDialog(this, "密码修改失败", "提示", 0);
						}
					}else{
						JOptionPane.showMessageDialog(this, "密码错误\n请重试", "提示", 2);
					}
				}else if(!(newPassword_1.equals(newPassword_2))){
					JOptionPane.showMessageDialog(this, "两次密码输入的不\n请重试", "提示", 2);
				}
			}else if(oldPassword.length()==0){
				JOptionPane.showMessageDialog(this, "旧密码不能为空", "提示", 0);
			}else if(newPassword_1.length()==0){
				JOptionPane.showMessageDialog(this, "新密码不能为空不能为空", "提示", 0);
			}else if(newPassword_2.length()==0){
				JOptionPane.showMessageDialog(this, "请确认新密码", "提示", 0);
			}
		}else if(e.getSource()==cancelButton){
			oldField.setText(null);
			newField_1.setText(null);
			newField_2.setText(null);
		}
	}
}
