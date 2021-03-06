package org.view.teacher.myUnrelesePaperPanel;

import java.awt.BorderLayout;
import java.awt.Color;
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
import org.service.IPaperService;
import org.service.imp.PaperService;
import org.util.MakeColor;

public class MyUnrelesePaperTabelPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	JScrollPane  scrollPanel;
	JTable table;
	DefaultTableModel tableModel;
	Object data[][];
	Object columnNames[]={"论文编号","论文标题","教务处审核人","教务处审核时间","专家处审核人","专家处审核时间","审核结果"};
	IPaperService paperService;
	JPanel buttonPanel;
	JButton allButton,flushButton,releseButton;
	int teacherNo;

	public MyUnrelesePaperTabelPanel(Teacher teacher)
	{  
		teacherNo=teacher.getTeacherNo();
		this.setLayout(new BorderLayout());

		tableModel=new DefaultTableModel(data, columnNames);
		table=new JTable(tableModel);
		table.setRowHeight(30);	
		table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 27));
		scrollPanel=new JScrollPane();
		scrollPanel.setViewportView(table);
		//设置滚动面板的大小
		scrollPanel.setPreferredSize(new Dimension(1000, 490));
		this.add(scrollPanel,BorderLayout.CENTER);
		buttonPanel=new JPanel();
		FlowLayout flowLayout_1=new FlowLayout();
		flowLayout_1.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(25);
		buttonPanel.setLayout(flowLayout_1);

		flushButton=new JButton(" 刷   新");
		flushButton.setIcon(new ImageIcon("buttonIma/refresh.png"));
		flushButton.setPreferredSize(new Dimension(100, 34));
		flushButton.addActionListener(this);
		buttonPanel.add(flushButton);
		flushButton.doClick();
		allButton=new JButton(" 详   情");
		allButton.setIcon(new ImageIcon("buttonIma/all.png"));
		allButton.setPreferredSize(new Dimension(100, 34));
		allButton.addActionListener(this);
		buttonPanel.add(allButton);
		releseButton=new JButton(" 发   布");
		releseButton.setIcon(new ImageIcon("buttonIma/fabu.png"));
		releseButton.setPreferredSize(new Dimension(100, 34));
		releseButton.addActionListener(this);
		buttonPanel.add(releseButton);
		//设置按钮面板的大小
		buttonPanel.setPreferredSize(new Dimension(500,100));
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
	//更改表格模型	
	public void changeModel(Object[][] data)
	{   
		tableModel=new DefaultTableModel(data, columnNames);
		table.setModel(tableModel);  
//		MakeFace.makeFace(table);
		MakeColor.makeColor(table, Color.blue);
	}
	
	//	 获取所选行的数据
	public Paper selectTableRecord()
	{   
		int row=table.getSelectedRow();
		if(row!=-1)
		{ 
			Paper paper=new Paper();   
			paper.setPaperNo((int)tableModel.getValueAt(row, 0));
			paper.setPaperName((String)tableModel.getValueAt(row, 1));
			paper.setEduName((String)tableModel.getValueAt(row, 2));
			paper.setZjName((String)tableModel.getValueAt(row, 4));
			return paper;
		}
		return null;
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==flushButton)
		{
			paperService= new PaperService();
			data=paperService.getRecord_Unrelese("select * "
					+ "from paper LEFT JOIN teacher ON paper.T_no=teacher.T_no "
					+ "LEFT JOIN department ON paper.D_no=department.D_no "
					+ "LEFT JOIN education ON paper.E_no=education.E_no "
					+ "LEFT JOIN zhuanjia ON paper.Z_no=zhuanjia.Z_no where paper.T_no="+teacherNo +" and paper.P_state=3");
			changeModel(data);
		}

		if(e.getSource()==releseButton)
		{
			Paper paper=selectTableRecord();
			if(paper!=null)
			{
				try {
					int a=JOptionPane.showConfirmDialog(this, "确认发布？"," 发布论文",2,3);
					if(a==0)
					{
						int row=table.getSelectedRow(); 
						paper.setTeacherNo(teacherNo);
						paper.setPaperState(5);
						paper.setChooseNumber(0);
						IPaperService service=new PaperService();
						if(service.upstate(paper))
						{
							JOptionPane.showMessageDialog(this, "成功发布论文：\n"+tableModel.getValueAt(row, 1), "提示", 2);
							tableModel.removeRow(row);
						}
					}
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请选择需要【发布】的数据", "提示", 2);
			}
		}

		if(e.getSource()==allButton)
		{   
			Paper paper=selectTableRecord();
			if(paper!=null)
			{
				paperService= new PaperService();
				int row=table.getSelectedRow(); 
				data=paperService.getInfo((int)tableModel.getValueAt(row, 0));
				String paperContent=(String) data[0][0];
				String eduName=(String) data[0][5];
				String eduTel=(String) data[0][6];
				String zjName=(String) data[0][7];
				String zjTel=(String) data[0][8];
				paper.setPaperContent(paperContent);
				paper.setEduName(eduName);
				paper.setEduTel(eduTel);
				paper.setZjName(zjName);
				paper.setZjTel(zjTel);
				new MyUnrelesePaperInfoDialog(paper);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请选择需要查看【详情】的数据", "提示", 2);
			}
		}
	}
}