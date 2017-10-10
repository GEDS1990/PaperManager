package org.view.teacher.myUnauditPaperPanel;

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
import org.service.IPaperService;
import org.service.imp.PaperService;
import org.util.MakeFace;

public class MyUnauditPaperTabelPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	JScrollPane  scrollPanel;
	JTable table;
	DefaultTableModel tableModel;
	Object data[][];
	Object columnNames[]={"论文编号","论文标题","出题教师","限选院系","限选人数","教务处审核人","审核时间","审核状态"};
	IPaperService paperService;
	JPanel buttonPanel;
	JButton allButton,flushButton,deleteButton,updateButton;
	int teacherNo;

	public MyUnauditPaperTabelPanel(Teacher teacher)
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
		flowLayout_1.setHgap(20);
		buttonPanel.setLayout(flowLayout_1);

		flushButton=new JButton(" 刷    新");
		flushButton.setIcon(new ImageIcon("buttonIma/refresh.png"));
		flushButton.setPreferredSize(new Dimension(100, 34));
		flushButton.addActionListener(this);
		buttonPanel.add(flushButton);
		allButton=new JButton(" 详    情");
		allButton.setIcon(new ImageIcon("buttonIma/all.png"));
		allButton.setPreferredSize(new Dimension(100, 34));
		allButton.addActionListener(this);
		buttonPanel.add(allButton);
		flushButton.doClick();
		deleteButton=new JButton(" 删    除");
		deleteButton.setIcon(new ImageIcon("buttonIma/delete.png"));
		deleteButton.setPreferredSize(new Dimension(100, 34));
		deleteButton.addActionListener(this);
		buttonPanel.add(deleteButton);
		updateButton=new JButton(" 修    改");
		updateButton.setIcon(new ImageIcon("buttonIma/update.png"));
		updateButton.setPreferredSize(new Dimension(100, 34));
		updateButton.addActionListener(this);
		buttonPanel.add(updateButton);
		//设置按钮面板的大小
		buttonPanel.setPreferredSize(new Dimension(500,100));
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
	//更改表格模型	
	public void changeModel(Object[][] data)
	{   
		tableModel=new DefaultTableModel(data, columnNames);
		table.setModel(tableModel);  
		MakeFace.makeFace(table);
		//		MakeColor.makeColor(table, Color.red);
	}

	//删除所选行
	public void deleteLine()
	{   
		int row=table.getSelectedRow();        
		Paper paper =new Paper();
		paper.setPaperNo((int)tableModel.getValueAt(row, 0));   
		paper.setTeacherNo(teacherNo);
		if(paperService.delete(paper)==true){
			JOptionPane.showMessageDialog(this, "成功删除论文：\n"+tableModel.getValueAt(row, 1), "提示", 2);
		}
		else{
			JOptionPane.showMessageDialog(this, "【删除】失败", "提示", 1);
		}
		tableModel.removeRow(row);
	}
	//	更新所选行数据
	public void  updateLine(Paper paper)
	{     
		int row=table.getSelectedRow();
		if(row!=-1)
		{
			tableModel.setValueAt(paper.getPaperName(), row, 1);
			tableModel.setValueAt(paper.getMaxNumber(), row, 4);
		}
	}
	//	 获取所选行的数据
	public Paper selectTableRecord()
	{   
		int row=table.getSelectedRow();
		if(row!=-1)
		{ 
			Paper paper=new Paper();   
			paper.setPaperName((String)tableModel.getValueAt(row, 1));
			return paper;
		}
		return null;
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==flushButton)
		{
			paperService= new PaperService();
			data=paperService.getRecord("select * "
					+ "from paper LEFT JOIN teacher ON paper.T_no=teacher.T_no "
					+ "LEFT JOIN department ON paper.D_no=department.D_no "
					+ "LEFT JOIN education ON paper.E_no=education.E_no "
					+ "LEFT JOIN zhuanjia ON paper.Z_no=zhuanjia.Z_no where paper.T_no="+teacherNo +" and paper.P_state<2");
			changeModel(data);
		}

		if(e.getSource()==deleteButton)
		{
			try {
				paperService= new PaperService();
				int row=table.getSelectedRow(); 
				data=paperService.getInfo((int)tableModel.getValueAt(row, 0));
				int paperState=(int) data[0][1];
				if(paperState==0){
					int a=JOptionPane.showConfirmDialog(this, "确认删除？"," 删除数据",2,3);
					if(a==0)
					{
						deleteLine();
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "该论文已经入审核状态\n该界面无权限【删除】数据", "提示", 2);
				}
			} catch(java.lang.ArrayIndexOutOfBoundsException e1){
				JOptionPane.showMessageDialog(this, "请选择需要【删除】的数据", "提示", 2);
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==updateButton)
		{   
			Paper paper=selectTableRecord();
			if(paper!=null)
			{
				paperService= new PaperService();
				int row=table.getSelectedRow(); 
				data=paperService.getInfo((int)tableModel.getValueAt(row, 0));
				int paperState=(int) data[0][1];
				if(paperState==0){
					paperService= new PaperService();
					data=paperService.getInfo((int)tableModel.getValueAt(row, 0));
					String paperContent=(String) data[0][0];
					int maxNumber=(int) data[0][2];
					paper.setPaperContent(paperContent);
					paper.setMaxNumber(maxNumber);
					paper.setPaperNo((int)tableModel.getValueAt(row, 0));
					//					System.out.println(paperContent);
					//					System.out.println(maxNumber);
					MyPaperUpdateDialog updateDialog=new MyPaperUpdateDialog(paper);	    	 
					paper=updateDialog.getPaper();	    	
					updateLine(paper);  	
				}
				else{
					JOptionPane.showMessageDialog(this, "该论文已经入审核状态\n该界面无权限【修改】数据", "提示", 2);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请选择需要【修改】的数据", "提示", 2);
			}
		}

		if(e.getSource()==allButton)
		{   
			Paper paper=selectTableRecord();
			if(paper!=null)
			{
				paperService= new PaperService();
				int row=table.getSelectedRow(); 
				paperService= new PaperService();
				data=paperService.getInfo((int)tableModel.getValueAt(row, 0));
				String paperContent=(String) data[0][0];
				int maxNumber=(int) data[0][2];
				String eduTel=(String)data[0][6];
				paper.setPaperContent(paperContent);
				paper.setDepartmentName((String) tableModel.getValueAt(row, 3));
				paper.setEduName((String) tableModel.getValueAt(row, 5));
				paper.setEduTel(eduTel);
				paper.setMaxNumber(maxNumber);
				paper.setPaperNo((int)tableModel.getValueAt(row, 0));
				new MyPaperInfoDialog(paper);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请选择需要查看【详情】的数据", "提示", 2);
			}
		}
	}
}
