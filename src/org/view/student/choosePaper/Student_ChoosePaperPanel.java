package org.view.student.choosePaper;

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

import org.model.Student;
import org.service.IPaperService;
import org.service.imp.PaperService;

public class Student_ChoosePaperPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JButton textButton;
	private JPanel condPanel;
	private int deparmentNo;
	private JTextField textField;
	Student_ChoosePaperTabelPanel tabelPanel;
	public Student_ChoosePaperPanel(Student student)
	{
		deparmentNo=student.getDepartmentNo();
		condPanel=new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setHgap(15);
		condPanel.setLayout(flowLayout);
		condPanel.add(new JLabel("关键字查询："));
		textField=new JTextField(20);
		condPanel.add(textField);
		textButton=new JButton(" 检 索");
		textButton.setIcon(new ImageIcon("buttonIma/search_2.png"));
		textButton.setPreferredSize(new Dimension(80, 30));
		textButton.addActionListener(this);
		condPanel.add(textButton);

		this.add(condPanel,BorderLayout.NORTH);
		tabelPanel=new Student_ChoosePaperTabelPanel(student);
		this.add(tabelPanel,BorderLayout.CENTER);   

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{

		String sql="select * "
				+ "from paper LEFT JOIN teacher ON paper.T_no=teacher.T_no "
				+ "LEFT JOIN department ON paper.D_no=department.D_no "
				+ "LEFT JOIN education ON paper.E_no=education.E_no "
				+ "LEFT JOIN zhuanjia ON paper.Z_no=zhuanjia.Z_no where paper.D_no="+deparmentNo+" and ";


		if(e.getSource()==textButton)
		{
			if(textField.getText().trim().length()!=0){
				String text=textField.getText().trim();
				sql=sql+"(T_name like '%"+text+"%' or P_name like '%"+text+"%' or P_no like '%"+text+"%' or P_maxNumber like '%"+text+"%') order by T_name asc";
				IPaperService paperService= new PaperService();
				if (paperService.findSql(sql)!=null&&paperService.findSql(sql).size()>0) {
					Object data[][]=paperService.getRecord_All(sql);
					tabelPanel.changeModel(data);
				}else{
					JOptionPane.showMessageDialog(this, "关键字【 "+text+" 】\n在数据库中不存在", "空数据", 0);
				}
			}else{
				JOptionPane.showMessageDialog(this, "检索内容为空", "提示", 2);
			}
		}

	}
}
