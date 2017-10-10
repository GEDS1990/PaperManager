package org.view.teacher.schoolChoosePanel;

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

public class SchoolChooseNumPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private SchoolChooseNumTabelPanel tabelPanel;
	private JButton textButton;
	private JPanel condPanel;
	private JTextField textField;
	public SchoolChooseNumPanel(Teacher teacher)
	{
		condPanel=new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setHgap(10);
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
		tabelPanel=new SchoolChooseNumTabelPanel(teacher);
		this.add(tabelPanel,BorderLayout.CENTER);   

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{

		String sql="select  D_name,M_name,S_class,count(DISTINCT student.S_no) as 人数,sum(case when P_no is null then 1 else null end) as nan from student "
				+ "LEFT JOIN major ON student.M_no=major.M_no "
				+ "LEFT JOIN department ON major.D_no=department.D_no "
				+ "LEFT JOIN choose ON student.S_no=choose.S_no ";


		if(e.getSource()==textButton)
		{
			if(textField.getText().trim().length()!=0){
				String text=textField.getText().trim();
				sql=sql+"where (D_name like '%"+text+"%' or S_class like '%"+text+"%' or M_name like '%"+text+"%' ) Group by S_class order by D_name desc";
				//				System.out.println(sql);
				IStudentService studentService= new StudentService();
				Object data[][];
				data=studentService.getRecord_chooseInfo(sql);
				if (data!=null&&data.length>0) {
					//					data=studentService.getRecord_chooseInfo(sql);
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
