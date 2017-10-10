package org.view.teacher.myUnauditPaperPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.model.Teacher;
import org.service.IPaperService;
import org.service.imp.PaperService;

public class MyUnauditPaperPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JComboBox<String> findCombox;
	private JButton findButton;
	private JPanel condPanel;
	private int teacherNo;
	MyUnauditPaperTabelPanel tabelPanel;
	public MyUnauditPaperPanel(Teacher teacher)
	{
		teacherNo=teacher.getTeacherNo();
		condPanel=new JPanel();
		condPanel.add(new JLabel("审核状态："));
		String a[]={"尚未审核","教务处审核通过"};
		findCombox=new JComboBox<String>(a);
		condPanel.add(findCombox);
		findButton=new JButton(" 查 找");
		findButton.setIcon(new ImageIcon("buttonIma/search_3.png"));
		findButton.setPreferredSize(new Dimension(80, 30));
		findButton.addActionListener(this);
		condPanel.add(findButton);
		this.add(condPanel,BorderLayout.NORTH);
		tabelPanel=new MyUnauditPaperTabelPanel(teacher);
		this.add(tabelPanel,BorderLayout.CENTER);   

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String sql="select * "
				+ "from paper LEFT JOIN teacher ON paper.T_no=teacher.T_no "
				+ "LEFT JOIN department ON paper.D_no=department.D_no "
				+ "LEFT JOIN education ON paper.E_no=education.E_no "
				+ "LEFT JOIN zhuanjia ON paper.Z_no=zhuanjia.Z_no where paper.T_no="+teacherNo+" and ";
		if(e.getSource()==findButton)
		{
			String text = (String) findCombox.getSelectedItem();
			if (text.equals("尚未审核"))
				sql = sql + "P_state=0";
			if (text.equals("教务处审核通过"))
				sql = sql + "P_state=1";
			IPaperService paperService= new PaperService();
			if (paperService.findSql(sql)!=null&&paperService.findSql(sql).size()>0) {
				Object data[][]=paperService.getRecord(sql);
				tabelPanel.changeModel(data);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "【 "+(String) findCombox.getSelectedItem()+" 】论文\n在数据库中不存在", "空数据", 0);
			}
		}
	}
}
