package org.view.zj;

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

import org.model.ZJ;
import org.service.IZhuanJiaService;
import org.service.imp.ZhuanJiaService;


public class ZJ_PasswordPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	public ZJ_PasswordPanel(ZJ zhuanjia) {
		zhuanjiaNo=zhuanjia.getZjNo();
		init();
	}

	private int zhuanjiaNo;
	private JLabel oldLabel,newLabel_1,newLabel_2;
	private JPasswordField oldField,newField_1,newField_2;
	private JButton submitButton,cancelButton;

	public void init(){
		this.setLayout(null);
		this.setBackground(new Color(108,162,207));
		
		Font  boldFont=new Font(" ΢���ź�",Font.BOLD,24);
		Font  plainFont=new Font(" ΢���ź�",Font.PLAIN,16);

		oldLabel=new JLabel("�����룺");
		oldLabel.setFont(boldFont);
		oldLabel.setForeground(Color.white);
		oldLabel.setBounds(310,140,200,36);
		oldField=new JPasswordField();
		//		oldField.setEchoChar('*');  // ���û����ַ�Ϊ*
		oldField.setFont(plainFont);
		oldField.setBounds(400,140,280,36);

		newLabel_1=new JLabel("�����룺");
		newLabel_1.setFont(boldFont);
		newLabel_1.setForeground(Color.white);
		newLabel_1.setBounds(310,220,200,36);
		newField_1=new JPasswordField();
		//		newField_1.setEchoChar('*');  // ���û����ַ�Ϊ*
		newField_1.setFont(plainFont);
		newField_1.setBounds(400,220,280,36);

		newLabel_2=new JLabel("ȷ�����룺");
		newLabel_2.setFont(boldFont);
		newLabel_2.setForeground(Color.white);
		newLabel_2.setBounds(280,300,200,36);
		newField_2=new JPasswordField();
		//		newField_2.setEchoChar('*');  // ���û����ַ�Ϊ*
		newField_2.setFont(plainFont);
		newField_2.setBounds(400,300,280,36);

		cancelButton=new JButton("  ���");
		cancelButton.setIcon(new ImageIcon("buttonIma/shanchu.png"));
		cancelButton.addActionListener(this);
		cancelButton.setBounds(290,400,105,38);
		submitButton=new JButton("  �ύ");
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
					IZhuanJiaService zhuanjiaService=new ZhuanJiaService();
					Object data[]=zhuanjiaService.getInfo(zhuanjiaNo);
					String zhuanjiaPassword=(String)data[2];
					if(zhuanjiaPassword.equals(oldPassword)){
						ZJ zhuanjia=new ZJ();
						zhuanjia.setZjPassword(newPassword_2);
						zhuanjia.setZjNo(zhuanjiaNo);
						if(zhuanjiaService.updata_password(zhuanjia)==true){
							JOptionPane.showMessageDialog(this, "�����޸ĳɹ�", "��ʾ", 1);
							oldField.setText(null);
							newField_1.setText(null);
							newField_2.setText(null);
						}else{
//							System.out.println(zhuanjiaPassword+"---"+oldPassword+"---"+newPassword_2);
							JOptionPane.showMessageDialog(this, "�����޸�ʧ��", "��ʾ", 0);
						}
					}else{
						JOptionPane.showMessageDialog(this, "�������\n������", "��ʾ", 2);
					}
				}else if(!(newPassword_1.equals(newPassword_2))){
					JOptionPane.showMessageDialog(this, "������������Ĳ�\n������", "��ʾ", 2);
				}
			}else if(oldPassword.length()==0){
				JOptionPane.showMessageDialog(this, "�����벻��Ϊ��", "��ʾ", 0);
			}else if(newPassword_1.length()==0){
				JOptionPane.showMessageDialog(this, "�����벻��Ϊ�ղ���Ϊ��", "��ʾ", 0);
			}else if(newPassword_2.length()==0){
				JOptionPane.showMessageDialog(this, "��ȷ��������", "��ʾ", 0);
			}
		}else if(e.getSource()==cancelButton){
			oldField.setText(null);
			newField_1.setText(null);
			newField_2.setText(null);
		}
	}
}
