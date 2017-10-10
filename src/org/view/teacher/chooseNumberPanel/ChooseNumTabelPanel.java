package org.view.teacher.chooseNumberPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.model.Paper;
import org.model.Teacher;
import org.service.IStudentService;
import org.service.imp.StudentService;
import org.util.MakeFace;

public class ChooseNumTabelPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	JScrollPane  scrollPanel;
	JTable table;
	DefaultTableModel tableModel;
	Object data[][];
	Object columnNames[]={"ϵͳ���","Ժϵ����","רҵ����","�����༶","������","��ѡ������"};
	JPanel buttonPanel;
	JButton allButton,flushButton;
	int teacherNo,deparmentNo;
	IStudentService studentService;

	public ChooseNumTabelPanel(Teacher teacher)
	{  
		teacherNo=teacher.getTeacherNo();
		deparmentNo=teacher.getDepartmentNo();
		this.setLayout(new BorderLayout());

		tableModel=new DefaultTableModel(data, columnNames);
		table=new JTable(tableModel);
		table.setRowHeight(30);	
		table.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 27));
		scrollPanel=new JScrollPane();
		scrollPanel.setViewportView(table);
		//���ù������Ĵ�С
		scrollPanel.setPreferredSize(new Dimension(1000, 490));
		this.add(scrollPanel,BorderLayout.CENTER);
		buttonPanel=new JPanel();
		FlowLayout flowLayout_1=new FlowLayout();
		flowLayout_1.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(25);
		buttonPanel.setLayout(flowLayout_1);

		flushButton=new JButton(" ˢ    ��");
		flushButton.setIcon(new ImageIcon("buttonIma/refresh.png"));
		flushButton.setPreferredSize(new Dimension(100, 34));
		flushButton.addActionListener(this);
		buttonPanel.add(flushButton);
		flushButton.doClick();
		allButton=new JButton(" ��    ��");
		allButton.setIcon(new ImageIcon("buttonIma/all.png"));
		allButton.setPreferredSize(new Dimension(100, 34));
		allButton.addActionListener(this);
		buttonPanel.add(allButton);
		//���ð�ť���Ĵ�С
		buttonPanel.setPreferredSize(new Dimension(500,100));
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
	//���ı��ģ��	
	public void changeModel(Object[][] data)
	{   
		tableModel=new DefaultTableModel(data, columnNames);
		table.setModel(tableModel);  
		MakeFace.makeFace(table);
	}
	
	//	 ��ȡ��ѡ�е�����
	public Paper selectTableRecord()
	{   
		int row=table.getSelectedRow();
		if(row!=-1)
		{ 
		}
		return null;
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==flushButton)
		{
			studentService= new StudentService();
			String sql ="select  D_name,M_name,S_class,count(DISTINCT student.S_no) as ����,sum(case when P_no is null then 1 else null end) as nan from student "
					+ "LEFT JOIN major ON student.M_no=major.M_no "
					+ "LEFT JOIN department ON major.D_no=department.D_no "
					+ "LEFT JOIN choose ON student.S_no=choose.S_no "
					+ " where major.D_no="+deparmentNo+" Group by S_class order by S_class";
			data=studentService.getRecord_chooseInfo(sql);
			changeModel(data);
		}

		if(e.getSource()==allButton)
		{   
			Paper paper=selectTableRecord();
			if(paper!=null)
			{
				
			}
			else
			{
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫ�鿴�����顿������", "��ʾ", 2);
			}
		}
	}
}
