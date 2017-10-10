package org.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExcelPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	public ExcelPanel()
	{
		init();
	}

	private JPanel mainPanel,excelPanel;
	private JLabel chooseLabel,nameLabel,buttonLabel;
	private JTextField fileText,nameText;
	private JButton chooseButton,addButton,quitButton;
	private Font boldF,plainF;
	private JFileChooser filecjoose;  //�ļ�ѡ��
	private FileNameExtensionFilter filte;

	private void init()
	{
		this.setLayout(null);

		boldF=new Font(" ΢���ź�",Font.BOLD,26);
		plainF=new Font(" ΢���ź�",Font.PLAIN,15);
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());

		excelPanel=new JPanel();
		excelPanel.setBackground(new Color(69,137,148));
//		excelPanel.setBackground(Color.lightGray);
		excelPanel.setLayout(null);

		chooseLabel =new JLabel("�ļ�ѡ��");
		chooseLabel.setForeground(Color.white);
		chooseLabel.setFont(boldF);
		chooseLabel.setBounds(300,172,135,60);
		chooseLabel.setToolTipText("��ӵ�Excel�ļ�·��");
		excelPanel.add(chooseLabel);
		fileText=new JTextField();
		fileText.setFont(plainF);
		fileText.setForeground(Color.black);
		//		fileText.setEnabled(false);  //TODO
		fileText.setBounds(430,185,260,32);
		fileText.setToolTipText("�����·����ϴ�����ť�����ļ�");
		excelPanel.add(fileText);

		nameLabel=new JLabel("��ǩѡ��");
		nameLabel.setForeground(Color.white);
		nameLabel.setFont(boldF);
		nameLabel.setBounds(300,251,135,60);
		nameLabel.setToolTipText("��ӵĹ������ǩ");
		excelPanel.add(nameLabel);
		nameText=new JTextField();
		nameText.setFont(plainF);
		nameText.setBounds(430,261,260,32);
		nameText.setToolTipText("ѡ��Ҫ��ӵĹ������ǩ");
		nameText.addMouseListener(new MouseAdapter() {
			private Rectangle sourceRec;

			public void mouseEntered(MouseEvent e)
			{
				JTextField nameText=(JTextField)e.getSource();
				sourceRec=nameText.getBounds();
				nameText.setBounds(sourceRec.x-10,sourceRec.y-10,sourceRec.width+20,sourceRec.height+20);
				super.mouseEntered(e);
			}
			public void mouseExited(MouseEvent e)
			{
				JTextField nameText=(JTextField)e.getSource();
				if(sourceRec!=null)
				{
					nameText.setBounds(sourceRec);
				}
				super.mouseExited(e);
			}
		});
		excelPanel.add(nameText);

		buttonLabel=new JLabel("������");
		buttonLabel.setForeground(Color.white);
		buttonLabel.setFont(boldF);
		buttonLabel.setBounds(300,335,100,60);
		buttonLabel.setToolTipText("������ť��ɲ���");
		excelPanel.add(buttonLabel);

		chooseButton=new JButton("  �ϴ�");
//		chooseButton.setBackground(new Color(138,176,212));
//		chooseButton.setFont(boldF);
//		chooseButton.setForeground(Color.white);
		chooseButton.setBounds(380,345,105,38);
		chooseButton.setIcon(new ImageIcon("buttonIma/excel_shangchuan.png"));
		chooseButton.addActionListener(this);
		chooseButton.addMouseListener(new MouseAdapter() {
			private Rectangle sourceRec;

			public void mouseEntered(MouseEvent e)
			{
				JButton chooseButton=(JButton)e.getSource();
				sourceRec=chooseButton.getBounds();
				chooseButton.setBounds(sourceRec.x-10,sourceRec.y-10,sourceRec.width+20,sourceRec.height+20);
				super.mouseEntered(e);
			}
			public void mouseExited(MouseEvent e)
			{
				JButton chooseButton=(JButton)e.getSource();
				if(sourceRec!=null)
				{
					chooseButton.setBounds(sourceRec);
				}
				super.mouseExited(e);
			}
		});
		
		chooseButton.setToolTipText("����ѡ���ϴ��ļ�");
		excelPanel.add(chooseButton);

		addButton=new JButton("  ���");
//		addButton.setBackground(new Color(138,176,212));
//		addButton.setFont(boldF);
//		addButton.setForeground(Color.white);
		addButton.setBounds(495,345,105,38);
		addButton.setIcon(new ImageIcon("buttonIma/excel_add.png"));
		addButton.addActionListener(this);
		addButton.addMouseListener(new MouseAdapter() {
			private Rectangle sourceRec;

			public void mouseEntered(MouseEvent e)
			{
				JButton chooseButton=(JButton)e.getSource();
				sourceRec=chooseButton.getBounds();
				chooseButton.setBounds(sourceRec.x-10,sourceRec.y-10,sourceRec.width+20,sourceRec.height+20);
				super.mouseEntered(e);
			}
			public void mouseExited(MouseEvent e)
			{
				JButton chooseButton=(JButton)e.getSource();
				if(sourceRec!=null)
				{
					chooseButton.setBounds(sourceRec);
				}
				super.mouseExited(e);
			}
		});
		addButton.setToolTipText("����ȷ���ϴ��ļ�");
		excelPanel.add(addButton);

		quitButton=new JButton("  ȡ��");
//		quitButton.setBackground(new Color(138,176,212));
//		quitButton.setFont(boldF);
//		quitButton.setForeground(Color.white);
		quitButton.setBounds(610,345,105,38);
		quitButton.setIcon(new ImageIcon("buttonIma/shanchu.png"));
		quitButton.addActionListener(this);
		quitButton.addMouseListener(new MouseAdapter() {
			private Rectangle sourceRec;

			public void mouseEntered(MouseEvent e)
			{
				JButton chooseButton=(JButton)e.getSource();
				sourceRec=chooseButton.getBounds();
				chooseButton.setBounds(sourceRec.x-10,sourceRec.y-10,sourceRec.width+20,sourceRec.height+20);
				super.mouseEntered(e);
			}
			public void mouseExited(MouseEvent e)
			{
				JButton chooseButton=(JButton)e.getSource();
				if(sourceRec!=null)
				{
					chooseButton.setBounds(sourceRec);
				}
				super.mouseExited(e);
			}
		});
		quitButton.setToolTipText("����ȡ���ϴ��ļ�");
		excelPanel.add(quitButton);

//		MouseAdapter adapter=new MouseAdapter() {
//			private Rectangle sourceRec;
//
//			public void mouseEntered(MouseEvent e)
//			{
//				JButton chooseButton=(JButton)e.getSource();
//				sourceRec=chooseButton.getBounds();
//				chooseButton.setBounds(sourceRec.x-10,sourceRec.y-10,sourceRec.width+20,sourceRec.height+20);
//				super.mouseEntered(e);
//			}
//		};

		mainPanel.add(excelPanel,BorderLayout.CENTER);
		mainPanel.setBounds(0, 0, 1000,590);
		this.add(mainPanel);

		filecjoose=new JFileChooser();
		filte=new FileNameExtensionFilter("Excle��ʽ(*.xls;*.xlsx)", "xls","xlsx");
		filecjoose.setFileFilter(filte);
		filecjoose.setAcceptAllFileFilterUsed(false);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==chooseButton)
		{
			int state=filecjoose.showOpenDialog(null);
			if(state==JFileChooser.APPROVE_OPTION)
			{
				String file=filecjoose.getSelectedFile().getAbsolutePath();
				fileText.setText(file);
			}
		}

		if(e.getSource()==addButton)
		{
			if (fileText.getText().length()!=0&&nameText.getText().length()!=0) 
			{
				String file = fileText.getText();
				String name = nameText.getText().trim();
				ExcelInfo info=new ExcelInfo();
				if (info.excelToDateBase(file, name) == true) 
				{
					JOptionPane.showMessageDialog(this, "��ϲ������ӳɹ�", "�ɹ���ʾ", 1);
					fileText.setText(null);
					nameText.setText(null);
				} 
				else 
				{
					JOptionPane.showMessageDialog(this, "��Ǹ����ӳ���", "������ʾ", 0);
				} 
			}
			else
			{
				JOptionPane.showMessageDialog(this, "ע�⣬������Ϊ��","��������ʾ",2);
			}
		}

		if(e.getSource()==quitButton)
		{
			fileText.setText(null);
			nameText.setText(null);
		}
	}


}

