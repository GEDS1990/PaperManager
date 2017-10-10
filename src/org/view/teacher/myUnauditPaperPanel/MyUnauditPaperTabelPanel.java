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
	Object columnNames[]={"���ı��","���ı���","�����ʦ","��ѡԺϵ","��ѡ����","���������","���ʱ��","���״̬"};
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
		flowLayout_1.setHgap(20);
		buttonPanel.setLayout(flowLayout_1);

		flushButton=new JButton(" ˢ    ��");
		flushButton.setIcon(new ImageIcon("buttonIma/refresh.png"));
		flushButton.setPreferredSize(new Dimension(100, 34));
		flushButton.addActionListener(this);
		buttonPanel.add(flushButton);
		allButton=new JButton(" ��    ��");
		allButton.setIcon(new ImageIcon("buttonIma/all.png"));
		allButton.setPreferredSize(new Dimension(100, 34));
		allButton.addActionListener(this);
		buttonPanel.add(allButton);
		flushButton.doClick();
		deleteButton=new JButton(" ɾ    ��");
		deleteButton.setIcon(new ImageIcon("buttonIma/delete.png"));
		deleteButton.setPreferredSize(new Dimension(100, 34));
		deleteButton.addActionListener(this);
		buttonPanel.add(deleteButton);
		updateButton=new JButton(" ��    ��");
		updateButton.setIcon(new ImageIcon("buttonIma/update.png"));
		updateButton.setPreferredSize(new Dimension(100, 34));
		updateButton.addActionListener(this);
		buttonPanel.add(updateButton);
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
		//		MakeColor.makeColor(table, Color.red);
	}

	//ɾ����ѡ��
	public void deleteLine()
	{   
		int row=table.getSelectedRow();        
		Paper paper =new Paper();
		paper.setPaperNo((int)tableModel.getValueAt(row, 0));   
		paper.setTeacherNo(teacherNo);
		if(paperService.delete(paper)==true){
			JOptionPane.showMessageDialog(this, "�ɹ�ɾ�����ģ�\n"+tableModel.getValueAt(row, 1), "��ʾ", 2);
		}
		else{
			JOptionPane.showMessageDialog(this, "��ɾ����ʧ��", "��ʾ", 1);
		}
		tableModel.removeRow(row);
	}
	//	������ѡ������
	public void  updateLine(Paper paper)
	{     
		int row=table.getSelectedRow();
		if(row!=-1)
		{
			tableModel.setValueAt(paper.getPaperName(), row, 1);
			tableModel.setValueAt(paper.getMaxNumber(), row, 4);
		}
	}
	//	 ��ȡ��ѡ�е�����
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
					int a=JOptionPane.showConfirmDialog(this, "ȷ��ɾ����"," ɾ������",2,3);
					if(a==0)
					{
						deleteLine();
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "�������Ѿ������״̬\n�ý�����Ȩ�ޡ�ɾ��������", "��ʾ", 2);
				}
			} catch(java.lang.ArrayIndexOutOfBoundsException e1){
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫ��ɾ����������", "��ʾ", 2);
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
					JOptionPane.showMessageDialog(this, "�������Ѿ������״̬\n�ý�����Ȩ�ޡ��޸ġ�����", "��ʾ", 2);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫ���޸ġ�������", "��ʾ", 2);
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
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫ�鿴�����顿������", "��ʾ", 2);
			}
		}
	}
}
