package org.view.teacher.chooseNumberPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.model.Teacher;
import org.service.IStudentService;
import org.service.imp.StudentService;

public class ChooseNumPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	ChooseNumTabelPanel tabelPanel;
	private JButton textButton;
	private JPanel condPanel;
	private JTextField textField;
	private int deparmentNo;
	public ChooseNumPanel(Teacher teacher) {
		deparmentNo=teacher.getDepartmentNo();
		condPanel=new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setHgap(10);
		condPanel.setLayout(flowLayout);
		condPanel.add(new JLabel("�ؼ��ֲ�ѯ��"));
		textField=new JTextField(20);
		condPanel.add(textField);
		textButton=new JButton(" �� ��");
		textButton.setIcon(new ImageIcon("buttonIma/search_2.png"));
		textButton.setPreferredSize(new Dimension(80, 30));
		textButton.addActionListener(this);
		condPanel.add(textButton);

		this.add(condPanel,BorderLayout.NORTH);
		tabelPanel=new ChooseNumTabelPanel(teacher);
		this.add(tabelPanel,BorderLayout.CENTER);   
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String sql ="select  D_name,M_name,S_class,count(DISTINCT student.S_no) as ����,sum(case when P_no is null then 1 else null end) as nan from student "
				+ "LEFT JOIN major ON student.M_no=major.M_no "
				+ "LEFT JOIN department ON major.D_no=department.D_no "
				+ "LEFT JOIN choose ON student.S_no=choose.S_no "
				+ " where major.D_no="+deparmentNo;
		if(e.getSource()==textButton)
		{

			if(textField.getText().trim().length()!=0){
				String text=textField.getText().trim();
				sql=sql+" and (D_name like '%"+text+"%' or S_class like '%"+text+"%' or M_name like '%"+text+"%' )  Group by S_class order by S_class";
				IStudentService studentService= new StudentService();
				Object data[][];;
				data=studentService.getRecord_chooseInfo(sql);
				if (data!=null&&data.length>0) {
					tabelPanel.changeModel(data);
				}else{
					JOptionPane.showMessageDialog(this, "�ؼ��֡� "+text+" ��\n�����ݿ��в�����", "������", 0);
				}
			}else{
				JOptionPane.showMessageDialog(this, "��������Ϊ��", "��ʾ", 2);
			}
		}

	}
}
