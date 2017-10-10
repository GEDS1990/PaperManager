package org.view.zj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.model.Paper;
import org.model.ZJ;
import org.service.IPaperService;
import org.service.imp.PaperService;
import org.util.MakeFace;

public class ZJAuditTabelPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	JScrollPane  scrollPanel;
	JTable table;
	DefaultTableModel tableModel;
	Object data[][];
	Object columnNames[]={"论文编号","论文标题","出题教师","限选院系","限选人数"};
	IPaperService paperService;
	JPanel buttonPanel;
	JButton allButton,flushButton,auditButton;


	private JButton firstPageButton;
	private JButton latePageButton;
	private JButton nextPageButton;
	private JButton lastPageButton;
	private int maxPageNumber;
	private int currentPageNumber = 1;
	private double pageSize = 15;
	private int zhuanjiaNo;
	private String zhuanjiaName;

	public ZJAuditTabelPanel(ZJ zhuanjia)
	{  
		zhuanjiaNo=zhuanjia.getZjNo();
		zhuanjiaName=zhuanjia.getZjName();
		this.setLayout(new BorderLayout());

		tableModel=new DefaultTableModel(data, columnNames);
		table=new JTable(tableModel);
		table.setRowHeight(30);	
		table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 30));
		scrollPanel=new JScrollPane();
		scrollPanel.setViewportView(table);
		//设置滚动面板的大小
		scrollPanel.setPreferredSize(new Dimension(1000, 490));
		this.add(scrollPanel,BorderLayout.CENTER);

		/**
		 * 分页实现
		 */

		firstPageButton = new JButton(" 首   页");
		firstPageButton.setIcon(new ImageIcon("buttonIma/shou.png"));
		firstPageButton.setPreferredSize(new Dimension(110, 34));
		firstPageButton.addActionListener(this);
		firstPageButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		latePageButton = new JButton(" 前一页");
		latePageButton.setIcon(new ImageIcon("buttonIma/last.png"));
		latePageButton.setPreferredSize(new Dimension(100, 34));
		latePageButton.addActionListener(this);
		latePageButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));


		nextPageButton = new JButton(" 后一页");
		nextPageButton.setIcon(new ImageIcon("buttonIma/next.png"));
		nextPageButton.setPreferredSize(new Dimension(100, 34));
		nextPageButton.addActionListener(this);
		nextPageButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		lastPageButton = new JButton(" 尾   页");
		lastPageButton.setIcon(new ImageIcon("buttonIma/wei.png"));
		lastPageButton.setPreferredSize(new Dimension(110, 34));
		lastPageButton.addActionListener(this);
		lastPageButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		firstPageButton.setEnabled(false);
		latePageButton.setEnabled(false);
		nextPageButton.setEnabled(true);
		lastPageButton.setEnabled(true);


		buttonPanel=new JPanel();
		FlowLayout flowLayout_1=new FlowLayout();
		flowLayout_1.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(20);
		buttonPanel.setLayout(flowLayout_1);

		flushButton=new JButton(" 刷   新");
		flushButton.setIcon(new ImageIcon("buttonIma/refresh.png"));
		flushButton.setPreferredSize(new Dimension(100, 34));
		flushButton.addActionListener(this);
		buttonPanel.add(flushButton);
		allButton=new JButton(" 详   情");
		allButton.setIcon(new ImageIcon("buttonIma/all.png"));
		allButton.setPreferredSize(new Dimension(100, 34));
		allButton.addActionListener(this);
		buttonPanel.add(allButton);
		auditButton=new JButton(" 审   核");
		auditButton.setIcon(new ImageIcon("buttonIma/audit_2.png"));
		auditButton.setPreferredSize(new Dimension(100, 34));
		auditButton.addActionListener(this);
		buttonPanel.add(auditButton);
		flushButton.doClick();
		//设置按钮面板的大小
		buttonPanel.setPreferredSize(new Dimension(1000,120));
		buttonPanel.add(firstPageButton);
		buttonPanel.add(latePageButton);
		buttonPanel.add(nextPageButton);
		buttonPanel.add(lastPageButton);
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
	//更改表格模型	
	public void changeModel(Object[][] data)
	{   
		tableModel=new DefaultTableModel(data, columnNames);
		table.setModel(tableModel);  
		maxPageNumber = (int) Math.ceil(tableModel.getRowCount() / pageSize);//计算页数
		MakeFace.makeFace(table);
	}
	//	 获取所选行的数据
	public Paper selectTableRecord()
	{   
		int row=table.getSelectedRow();
		if(row!=-1)
		{ 
			Paper paper=new Paper();   
			paper.setPaperName((String)table.getModel().getValueAt(row, 1));
			return paper;
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==flushButton)
		{
			paperService= new PaperService();
			data=paperService.getRecord_Edu("select * "
					+ "from paper LEFT JOIN teacher ON paper.T_no=teacher.T_no "
					+ "LEFT JOIN department ON paper.D_no=department.D_no "
					+ "LEFT JOIN education ON paper.E_no=education.E_no "
					+ "LEFT JOIN zhuanjia ON paper.Z_no=zhuanjia.Z_no where paper.P_state=1  order by T_name asc");
			changeModel(data);
		}


		if(e.getSource()==allButton)
		{   
			Paper paper=selectTableRecord();
			if(paper!=null)
			{
				paperService= new PaperService();
				int row=table.getSelectedRow(); 
				paperService= new PaperService();
				data=paperService.getInfo((int)table.getModel().getValueAt(row, 0));
				String paperContent=(String) data[0][0];
				int maxNumber=(int) data[0][2];
				String teacherTel=(String) data[0][3];
				String deparName=(String) data[0][9];
				paper.setPaperContent(paperContent);
				paper.setTeacherName((String) table.getModel().getValueAt(row, 2));
				paper.setMaxNumber(maxNumber);
				paper.setTeacherTel(teacherTel);
				paper.setDepartmentName(deparName);
				paper.setPaperNo((int)table.getModel().getValueAt(row, 0));
				new ZJAllPaperDialog(paper);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请选择需要查看【详情】的数据", "提示", 2);
			}
		}
		
		if(e.getSource()==auditButton)
		{   
			Paper paper=selectTableRecord();
			if(paper!=null)
			{
				paperService= new PaperService();
				int row=table.getSelectedRow(); 
				paperService= new PaperService();
				data=paperService.getInfo((int)table.getModel().getValueAt(row, 0));
				String paperContent=(String) data[0][0];
				String deparName=(String) data[0][9];
				paper.setPaperContent(paperContent);
				paper.setTeacherName((String) table.getModel().getValueAt(row, 2));
				paper.setDepartmentName(deparName);
				paper.setZjNo(zhuanjiaNo);
				paper.setZjName(zhuanjiaName);
				paper.setPaperNo((int)table.getModel().getValueAt(row, 0));
				int state=new ZJAuditPaperDialog(paper).getI();//判断返回数据
				if(state==11||state==22){
					tableModel.removeRow(row);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "请选择需要【审核】的数据", "提示", 2);
			}
		}

		/**
		 * 分页按钮
		 */
		if(e.getSource()==firstPageButton)
		{
			currentPageNumber = 1;
			Vector dataVector = tableModel.getDataVector();
			DefaultTableModel newModel = new DefaultTableModel();
			newModel.setColumnIdentifiers(new Object[] {"论文编号","论文标题","出题教师","限选院系","限选人数"});
			for (int i = 0; i < pageSize; i++) {
				newModel.addRow((Vector) dataVector.elementAt(i));
			}
			table.setModel(newModel);
			MakeFace.makeFace(table);
			firstPageButton.setEnabled(false);
			latePageButton.setEnabled(false);
			nextPageButton.setEnabled(true);
			lastPageButton.setEnabled(true);
		}

		if(e.getSource()==latePageButton)
		{
			currentPageNumber--;
			Vector dataVector = tableModel.getDataVector();
			DefaultTableModel newModel = new DefaultTableModel();
			newModel.setColumnIdentifiers(new Object[] {"论文编号","论文标题","出题教师","限选院系","限选人数" });
			for (int i = 0; i < pageSize; i++) {
				newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (currentPageNumber - 1) + i)));
			}
			table.setModel(newModel);
			MakeFace.makeFace(table);
			if (currentPageNumber == 1) {
				firstPageButton.setEnabled(false);
				latePageButton.setEnabled(false);
			}
			nextPageButton.setEnabled(true);
			lastPageButton.setEnabled(true);
		}

		if(e.getSource()==nextPageButton)
		{
			currentPageNumber++;
			Vector dataVector = tableModel.getDataVector();
			DefaultTableModel newModel = new DefaultTableModel();
			newModel.setColumnIdentifiers(new Object[] {"论文编号","论文标题","出题教师","限选院系","限选人数"});
			if (currentPageNumber == maxPageNumber) {
				int lastPageSize = (int) (tableModel.getRowCount() - pageSize * (maxPageNumber - 1));
				for (int i = 0; i < lastPageSize; i++) {
					newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
				}
				nextPageButton.setEnabled(false);
				lastPageButton.setEnabled(false);
			} else {
				for (int i = 0; i < pageSize; i++) {
					newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (currentPageNumber - 1) + i)));
				}
			}
	//		table.removeAll();
			table.setModel(newModel);
			MakeFace.makeFace(table);
			firstPageButton.setEnabled(true);
			latePageButton.setEnabled(true);
		}

		if(e.getSource()==lastPageButton)
		{
			 currentPageNumber = maxPageNumber;
		        Vector dataVector = tableModel.getDataVector();
		        DefaultTableModel newModel = new DefaultTableModel();
		        newModel.setColumnIdentifiers(new Object[] {"论文编号","论文标题","出题教师","限选院系","限选人数"});
		        int lastPageSize = (int) (tableModel.getRowCount() - pageSize * (maxPageNumber - 1));
		        
		        if (lastPageSize == 15) {
		            for (int i = 0; i < pageSize; i++) {
		                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
		            }
		        } else {
		            for (int i = 0; i < lastPageSize; i++) {
		                newModel.addRow((Vector) dataVector.elementAt((int) (pageSize * (maxPageNumber - 1) + i)));
		            }
		        }
		        
		        table.setModel(newModel);
		        MakeFace.makeFace(table);
		        firstPageButton.setEnabled(true);
		        latePageButton.setEnabled(true);
		        nextPageButton.setEnabled(false);
		        lastPageButton.setEnabled(false);
		}
	}
}