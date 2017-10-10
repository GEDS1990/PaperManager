package org.view.zj;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.model.ZJ;
import org.service.IDepartmentService;
import org.service.IPaperService;
import org.service.imp.DepartmentService;
import org.service.imp.PaperService;

public class ZJAuditPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JComboBox<Object> findCombox;
	private JButton findButton,textButton;
	private JPanel condPanel;
	private JTextField textField;
	private ZJAuditTabelPanel tabelPanel;
	

	Object b[] = null;
	Object c[] = null;
	
	public ZJAuditPanel(ZJ zhuanjia)
	{
		condPanel=new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setHgap(15);
		condPanel.setLayout(flowLayout);
		condPanel.add(new JLabel("关键字查询："));
		textField=new JTextField(20);
		condPanel.add(textField);
		textButton=new JButton(" 检 索  ");
		textButton.setIcon(new ImageIcon("buttonIma/search_2.png"));
		textButton.setPreferredSize(new Dimension(80, 30));
		textButton.addActionListener(this);
		condPanel.add(textButton);

		condPanel.add(new JLabel("                       "));

		condPanel.add(new JLabel(" 院系查询："));
		Object a[][]=null;
		IDepartmentService service=new DepartmentService();
		a=service.getRecord_Depar();
		int len=a.length;
		b=new Object[len];
		c=new Object[len];
		for(int i=0;i<len;i++){
			b[i]=a[i][1];
			c[i]=a[i][0];
		}
		findCombox=new JComboBox<Object>(b);
		condPanel.add(findCombox);
		findButton=new JButton(" 查 找");
		findButton.setIcon(new ImageIcon("buttonIma/search_3.png"));
		findButton.setPreferredSize(new Dimension(80, 30));
		findButton.addActionListener(this);
		condPanel.add(findButton);
		this.add(condPanel,BorderLayout.NORTH);
		tabelPanel=new ZJAuditTabelPanel(zhuanjia);
		this.add(tabelPanel,BorderLayout.CENTER);   

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String sql="select * "
				+ "from paper LEFT JOIN teacher ON paper.T_no=teacher.T_no "
				+ "LEFT JOIN department ON paper.D_no=department.D_no "
				+ "LEFT JOIN education ON paper.E_no=education.E_no "
				+ "LEFT JOIN zhuanjia ON paper.Z_no=zhuanjia.Z_no where paper.P_state=1  and ";

		if(e.getSource()==textButton)
		{
			if(textField.getText().trim().length()!=0){
				String text=textField.getText().trim();
				sql=sql+"(T_name like '%"+text+"%' or P_name like '%"+text+"%' or P_no like '%"+text+"%' or D_name like '%"+text+"%') order by T_name asc";
				IPaperService paperService= new PaperService();
				if (paperService.findSql(sql)!=null&&paperService.findSql(sql).size()>0) {
					Object data[][]=paperService.getRecord_Edu(sql);
					tabelPanel.changeModel(data);
				}else{
					JOptionPane.showMessageDialog(this, "关键字【 "+text+" 】\n在数据库中不存在", "空数据", 0);
				}
			}else{
				JOptionPane.showMessageDialog(this, "检索内容为空", "提示", 2);
			}
		}

		if(e.getSource()==findButton)
		{

			String text = (String) findCombox.getSelectedItem();
			for(int j=0;j<b.length;j++){
				if (text.equals(b[j]))
					sql = sql + "paper.D_no= "+c[j]+" order by T_name asc";}

			IPaperService paperService= new PaperService();
			if (paperService.findSql(sql)!=null&&paperService.findSql(sql).size()>0) {
				Object data[][]=paperService.getRecord_Edu(sql);
				tabelPanel.changeModel(data);}else{
				JOptionPane.showMessageDialog(this, "【 "+text+" 】论文\n在数据库中不存在", "空数据", 0);
			}
		}
	}
}
