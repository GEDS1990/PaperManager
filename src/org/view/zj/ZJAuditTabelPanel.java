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
	Object columnNames[]={"���ı��","���ı���","�����ʦ","��ѡԺϵ","��ѡ����"};
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
		table.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 30));
		scrollPanel=new JScrollPane();
		scrollPanel.setViewportView(table);
		//���ù������Ĵ�С
		scrollPanel.setPreferredSize(new Dimension(1000, 490));
		this.add(scrollPanel,BorderLayout.CENTER);

		/**
		 * ��ҳʵ��
		 */

		firstPageButton = new JButton(" ��   ҳ");
		firstPageButton.setIcon(new ImageIcon("buttonIma/shou.png"));
		firstPageButton.setPreferredSize(new Dimension(110, 34));
		firstPageButton.addActionListener(this);
		firstPageButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));

		latePageButton = new JButton(" ǰһҳ");
		latePageButton.setIcon(new ImageIcon("buttonIma/last.png"));
		latePageButton.setPreferredSize(new Dimension(100, 34));
		latePageButton.addActionListener(this);
		latePageButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));


		nextPageButton = new JButton(" ��һҳ");
		nextPageButton.setIcon(new ImageIcon("buttonIma/next.png"));
		nextPageButton.setPreferredSize(new Dimension(100, 34));
		nextPageButton.addActionListener(this);
		nextPageButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));

		lastPageButton = new JButton(" β   ҳ");
		lastPageButton.setIcon(new ImageIcon("buttonIma/wei.png"));
		lastPageButton.setPreferredSize(new Dimension(110, 34));
		lastPageButton.addActionListener(this);
		lastPageButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));

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

		flushButton=new JButton(" ˢ   ��");
		flushButton.setIcon(new ImageIcon("buttonIma/refresh.png"));
		flushButton.setPreferredSize(new Dimension(100, 34));
		flushButton.addActionListener(this);
		buttonPanel.add(flushButton);
		allButton=new JButton(" ��   ��");
		allButton.setIcon(new ImageIcon("buttonIma/all.png"));
		allButton.setPreferredSize(new Dimension(100, 34));
		allButton.addActionListener(this);
		buttonPanel.add(allButton);
		auditButton=new JButton(" ��   ��");
		auditButton.setIcon(new ImageIcon("buttonIma/audit_2.png"));
		auditButton.setPreferredSize(new Dimension(100, 34));
		auditButton.addActionListener(this);
		buttonPanel.add(auditButton);
		flushButton.doClick();
		//���ð�ť���Ĵ�С
		buttonPanel.setPreferredSize(new Dimension(1000,120));
		buttonPanel.add(firstPageButton);
		buttonPanel.add(latePageButton);
		buttonPanel.add(nextPageButton);
		buttonPanel.add(lastPageButton);
		this.add(buttonPanel,BorderLayout.SOUTH);
	}
	//���ı��ģ��	
	public void changeModel(Object[][] data)
	{   
		tableModel=new DefaultTableModel(data, columnNames);
		table.setModel(tableModel);  
		maxPageNumber = (int) Math.ceil(tableModel.getRowCount() / pageSize);//����ҳ��
		MakeFace.makeFace(table);
	}
	//	 ��ȡ��ѡ�е�����
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
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫ�鿴�����顿������", "��ʾ", 2);
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
				int state=new ZJAuditPaperDialog(paper).getI();//�жϷ�������
				if(state==11||state==22){
					tableModel.removeRow(row);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫ����ˡ�������", "��ʾ", 2);
			}
		}

		/**
		 * ��ҳ��ť
		 */
		if(e.getSource()==firstPageButton)
		{
			currentPageNumber = 1;
			Vector dataVector = tableModel.getDataVector();
			DefaultTableModel newModel = new DefaultTableModel();
			newModel.setColumnIdentifiers(new Object[] {"���ı��","���ı���","�����ʦ","��ѡԺϵ","��ѡ����"});
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
			newModel.setColumnIdentifiers(new Object[] {"���ı��","���ı���","�����ʦ","��ѡԺϵ","��ѡ����" });
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
			newModel.setColumnIdentifiers(new Object[] {"���ı��","���ı���","�����ʦ","��ѡԺϵ","��ѡ����"});
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
		        newModel.setColumnIdentifiers(new Object[] {"���ı��","���ı���","�����ʦ","��ѡԺϵ","��ѡ����"});
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