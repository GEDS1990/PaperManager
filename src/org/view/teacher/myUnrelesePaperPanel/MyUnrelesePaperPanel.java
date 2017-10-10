package org.view.teacher.myUnrelesePaperPanel;

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
import org.service.IPaperService;
import org.service.imp.PaperService;

public class MyUnrelesePaperPanel  extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	MyUnrelesePaperTabelPanel tabelPanel;
	private JButton textButton;
	private JPanel condPanel;
	private JTextField textField;
	private int teacherNo;

	public MyUnrelesePaperPanel(Teacher teacher)
	{
		teacherNo=teacher.getTeacherNo();
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

		tabelPanel=new MyUnrelesePaperTabelPanel(teacher);
		this.add(tabelPanel,BorderLayout.CENTER);   
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sql="select * "
				+ "from paper LEFT JOIN teacher ON paper.T_no=teacher.T_no "
				+ "LEFT JOIN department ON paper.D_no=department.D_no "
				+ "LEFT JOIN education ON paper.E_no=education.E_no "
				+ "LEFT JOIN zhuanjia ON paper.Z_no=zhuanjia.Z_no where paper.T_no="+teacherNo +" and paper.P_state=3 and";
		if(textField.getText().trim().length()!=0){
			String text=textField.getText().trim();
			sql=sql+"( P_no like '%"+text+"%' or P_name like '%"+text+"%' or E_name like '%"+text+"%' or Z_name like '%"+text+"%')";
			IPaperService paperService= new PaperService();
			Object data[][];
			data=paperService.getRecord_Unrelese(sql);
			if (data!=null&&data.length>0) {
				tabelPanel.changeModel(data);
			}else
			{
				JOptionPane.showMessageDialog(this, "关键字【 "+text+" 】\n在数据库中不存在", "空数据", 0);
			}
		}else{
			JOptionPane.showMessageDialog(this, "检索内容为空", "提示", 2);
		}

	}
}
