package org.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent;

//import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
//import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DataChooser_2 extends JPanel implements ActionListener,

/*
 * ��֪BUG:
 * 1.�������ڵ��㷨�������ڲ�ƥ��
 *        *ò�ƹ�ԪԪ���һ������һ����7ȡģò�ƿ��ԣ��־����鷳
 * 2.���ڡ�����������ɫ���������⣬������ÿ�������춼����ǰ��ɫ
 * 3.��λ�㷨�е����� ����λ��̫���ߣ�������⡣
 *        *����������Լ�����Point getAppropriateLocation(Frame owner, Point position)�еĲ���
 *        * result.x=ĳĳ;
 *		  * result.y=ĳĳ;
 *
 *����ע�����
 *1.ʹ��JFormattedTextField������JText
 *2.����ʱʹ��Point point = text2.getLocationOnScreen();��ȡ���λ��
 *
 *����������
 *1.��ɫ��������Ҫ�Ż�
 *2.����������Ƿ�������ȡJFormattedTextField�����������ø������ȣ�JFormattedTextField��Сʱ��ʹ��Ĭ�ϴ�С��
 */
ChangeListener {
	private static final long serialVersionUID = 1L;

	int startYear = 1980; //Ĭ�ϡ���С����ʾ���

	int lastYear = 2050; //Ĭ�ϡ������ʾ���

	int width = 332; //������

	int height = 260; //����߶�

	Color backGroundColor = Color.gray; //��ɫ

	//���������ɫ----------------//

	Color palletTableColor = Color.white; //�������ɫ
	Color todayBackColor = Color.orange; //���챳��ɫ
	Color weekFontColor = Color.blue; //��������ɫ
	Color dateFontColor = Color.black; //��������ɫ
	Color weekendFontColor = Color.red; //��ĩ����ɫ
	Color controlLineColor = Color.pink; //��������ɫ
	Color controlTextColor = Color.white; //��������ǩ����ɫ
	Color rbFontColor = Color.white; //RoundBox����ɫ
	Color rbBorderColor = Color.red; //RoundBox�߿�ɫ
	Color rbButtonColor = Color.pink; //RoundBox��ťɫ
	Color rbBtFontColor = Color.red; //RoundBox��ť����ɫ

	JDialog dialog; //������ʾ����ѡ��ؼ�

	JSpinner yearSpin; //������ݵ�JSpinner
	JSpinner monthSpin; //�����·�
//	JSpinner hourSpin; //����Сʱ
//	JSpinner minuteSpin;//���ڷ���
	/*
	 * 2016-10-29: ��ѯ
	 * public class JSpinner extends JComponent implements Accessible
	 * ���û���һ������������ѡ��һ�����ֻ���һ������ֵ�ĵ��������ֶΡ�
	 * Spinner ͨ���ṩһ�Դ�С��ͷ�İ�ť�Ա��𲽱�������Ԫ�ء����̵�����/���·����Ҳ��ѭ������Ԫ�ء�
	 * Ҳ�����û��� spinner ��ֱ������Ϸ�ֵ��
	 * ������Ͽ��ṩ�����ƵĹ��ܣ�����Ϊ spinner ��Ҫ���������Ҫ���ݵ������б�������ʱ��Ҳ��Ϊ��Ҫѡ��
	 */
	
	JButton[][] daysButton = new JButton[6][7];//������ʾ��ǰ�·�ÿһ������Ӧ�����ڵİ�ť����

	JFormattedTextField jFormattedTextField;//��ʾ��ǰѡ�����ڵ��и�ʽ�����

	Calendar c = getCalendar();
	Calendar cal = Calendar.getInstance();

	int currentDay=cal.get(Calendar.DAY_OF_MONTH);

	public DataChooser_2(JFormattedTextField jftf) {
		jFormattedTextField = jftf;
		//���ò��ּ��߿�
		setLayout(new BorderLayout());
//		setBorder(new LineBorder(backGroundColor, 2));  
		//��������Χ�߿� (��ѽ���������ÿ�������΢���������һ��������ڻ������Χ�߿�Ĺ��ܣ��ܺÿ����´γ�����ʵ���¡���10-29)
		setBackground(backGroundColor);
		
		//��ʼ������������
		JPanel topYearAndMonth = createYearAndMonthPanal();
		add(topYearAndMonth, BorderLayout.NORTH);
		JPanel centerWeekAndDay = createWeekAndDayPanal();
		add(centerWeekAndDay, BorderLayout.CENTER);

	}
	//��������:���ڴ�����ݼ��·���ʾ���
	//���� return ���ڴ�����ݼ��·���ʾ���JPanel����

	private JPanel createYearAndMonthPanal() {

		int currentYear = c.get(Calendar.YEAR);  //���ظ��������ֶε�ֵ

		int currentMonth = c.get(Calendar.MONTH) + 1;
		
		//�����ʵ�ָ���ϸ��ʱ���ʽ����������ע�ͣ�����ͬ��

//		int currentHour = c.get(Calendar.HOUR_OF_DAY);
//
//		int currentMinute = c.get(Calendar.MINUTE);

		JPanel result = new JPanel();

		result.setLayout(new FlowLayout());

		result.setBackground(controlLineColor);

		yearSpin = new JSpinner(new SpinnerNumberModel(currentYear, startYear,lastYear, 1));  
		//����һ������ָ�� value��minimum/maximum �߽�� stepSize(��������) �� SpinnerNumberModel��
		//�����������е� SpinnerModel�������е����±߽�����Ϊ minimum �� maximum �����Զ���

		yearSpin.setPreferredSize(new Dimension(60, 20));  //���ô��������ѡ��С

		yearSpin.setName("Year");
		
		yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####"));  //.NumberEditor.NumberEditor(JSpinner spinner, String decimalFormatPattern) ���ߣ�ʮ���Ƹ�ʽ
		
		yearSpin.setFont(new Font("΢���ź�", Font.BOLD, 13));

		yearSpin.addChangeListener(this);

		result.add(yearSpin);
		
		JLabel yearLabel = new JLabel("��");

		yearLabel.setForeground(controlTextColor);
		
		yearLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));

		result.add(yearLabel);

		monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1, 12, 1));

		monthSpin.setPreferredSize(new Dimension(60, 20));

		monthSpin.setName("Month");
		
		monthSpin.setFont(new Font("΢���ź�", Font.BOLD, 13));

		monthSpin.addChangeListener(this);

		result.add(monthSpin);

		JLabel monthLabel = new JLabel("��");

		monthLabel.setForeground(controlTextColor);
		
		monthLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));

		result.add(monthLabel);

//		hourSpin = new JSpinner(new SpinnerNumberModel(currentHour, 0, 23, 1));
//
//		hourSpin.setPreferredSize(new Dimension(35, 20));
//
//		hourSpin.setName("Hour");
//
//		hourSpin.addChangeListener(this);
//
//
//
//		result.add(hourSpin);
//
//		JLabel hourLabel = new JLabel("ʱ");
//
//		hourLabel.setForeground(controlTextColor);
//
//		result.add(hourLabel);
//
//		minuteSpin = new JSpinner(new SpinnerNumberModel(currentMinute, 0, 59,1));
//
//		minuteSpin.setPreferredSize(new Dimension(35, 20));
//
//		minuteSpin.setName("Minute");
//
//		minuteSpin.addChangeListener(this);
//
//		result.add(minuteSpin);
//
//		JLabel minuteLabel = new JLabel("��");
//
//		minuteLabel.setForeground(controlTextColor);
//
//		result.add(minuteLabel);

		return result;

	}

	//��������:���ڴ�����ǰ�·���ÿһ�찴��Ӧ���������������


	/**
	 * @return
	 */
	private JPanel createWeekAndDayPanal() {



		String colname[] = { "��", "һ", "��", "��", "��", "��", "��" };

		JPanel result = new JPanel();

		//���ù̶����壬������û����ı�Ӱ���������

		result.setFont(new Font("΢���ź�", Font.BOLD, 14));

		result.setLayout(new GridLayout(7, 7));

		result.setBackground(Color.gray);

		JLabel cell; //��ʾ����

		for (int i = 0; i < 7; i++) {

			cell = new JLabel(colname[i]);
			
			cell.setFont(new Font("΢���ź�", Font.BOLD, 14));

			cell.setHorizontalAlignment(JLabel.CENTER);  
			/*
			 * 16-11-30:
			 * setHorizontalAlignment��
			 * ���ñ�ǩ������ X ��Ķ��뷽ʽ�� 
			 * ����һ�� JavaBeans �����ԡ�
			 * ������
			 * alignment - SwingConstants �ж�������³���֮һ��LEFT��CENTER��ֻ��ʾͼ��ı�ǩ��Ĭ��ֵ����
			    RIGHT��LEADING��ֻ��ʾ�ı��ı�ǩ��Ĭ��ֵ���� TRAILING
			*/

			if (i == 0 || i == 6)

				cell.setForeground(Color.white);

			else

				cell.setForeground(Color.white);//�˴����������޸ģ�Ŀ����Ϊ�����´�Ӧ��ʱ������ã�����Ҫ��������������ɫΪ��ɫ��

			result.add(cell);

		}

		int actionCommandId = 0;

		for (int i = 0; i < 6; i++)

			for (int j = 0; j < 7; j++) {

				JButton numberButton = new JButton();

				numberButton.setBorder(null);
				//�ޱ߿��������������磬���������һ�䣬42����ť��ͬʱ���ֱ߿�Ӱ�������ۡ����ʹ�ñ�ǩ���ܻ��һ�㣩

				numberButton.setHorizontalAlignment(SwingConstants.CENTER);
				//SwingConstants��ͨ����������Ļ�϶�λ��������ĳ����ļ��ϣ�ֵ��ѧϰ��
				
				numberButton.setActionCommand(String.valueOf(actionCommandId));
				/*
				 * 16-10-30��ѯ��
				 * setActionCommand(String actionCommand)�����ô˰�ť�Ķ������� 
				 * ���� ����actionCommand - �˰�ť�Ķ�������
				 * */

				numberButton.addActionListener(this);

				numberButton.setBackground(palletTableColor);

				numberButton.setForeground(dateFontColor);//�˴�����bug����ʱ�����޸�16-11-6

				if (j == 0 || j == 6)

					numberButton.setForeground(weekendFontColor);

				else

					numberButton.setForeground(dateFontColor);
				numberButton.setFont(new Font("΢���ź�", Font.BOLD, 13));

				daysButton[i][j] = numberButton;
				
				//2016-11-6���˴��зǳ���ĺô����ڣ���ʹ��MouseListener�ӿڣ�����ʹ�������ڲ��࣬ʵ��ѭ������¼���

				numberButton.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {
						/*������Ϊ�˴����Լ���Ĺ���
						 * �������¼������ֵ���ɫ����
						 */

						if (e.getClickCount() == 2) { //���˫���ٴ�ʱ

							closeAndSetDate();  //����

						}

					}

				});

				result.add(numberButton);

				actionCommandId++;

			}

		return result;

	}



	private JDialog createDialog(Frame owner) {

		JDialog result = new JDialog(owner, "���ʱ��ѡ��", true);  //�˴�����������⣬ʹ������ģʽ��Ӧ�޸�title
		/*
		 * 2016-10-30��ѯ��
		 * JDialog
		 * public JDialog(Dialog owner,String title,boolean modal)����һ������ָ�����⡢ģʽ��ָ�������� Dialog �ĶԻ��� 
		 * �˹��췽��������������Ի�����������Ϊ JComponent.getDefaultLocale �����ص�ֵ�� 
		 * ������
		 * owner - ��ʾ�öԻ���������� Dialog������˶Ի���û�������ߣ���Ϊ null
		 * title - �öԻ���ı�����������ʾ�� String
		 * modal - ָ���Ի�������ʾʱ�Ƿ������û����������㴰�����롣
		    ���Ϊ true����ģʽ�������Ա�����Ϊ DEFAULT_MODALITY_TYPE������Ի�������ģʽ�ġ�
		 */

		result.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		result.getContentPane().add(this, BorderLayout.CENTER);

		result.pack();
		//�����˴��ڵĴ�С�����ʺ������������ѡ��С�Ͳ��֡�
		//����ô��ں�/���������߻�������ʾ�����ڼ�����ѡ��С֮ǰ������ÿ���ʾ���ڼ�����ѡ��С֮�󣬽�����֤�ô���

		result.setSize(width, height);

		return result;

	}

	public void showDateChooser(Point position) {

		Object tmpobj=SwingUtilities.getWindowAncestor(jFormattedTextField);
		//getWindowAncestor(Component c) 
		//���� c �ĵ�һ�� Window ���ȣ���� c δ������ Window �ڣ��򷵻� null

		if(tmpobj.getClass().isInstance(new JDialog())||tmpobj.getClass().getSuperclass().isInstance(new JDialog()))
			//(Object obj) 
	        //�ж�ָ���� Object �Ƿ���� Class ����ʾ�Ķ���ֵ���������жϳ����ڴ��廹�ǶԻ����
		{

			JDialog ownerdialog = (JDialog) SwingUtilities.getWindowAncestor(jFormattedTextField);

			Frame owner = (Frame) SwingUtilities.getWindowAncestor(ownerdialog);

			if (dialog == null || dialog.getOwner() != owner) {

				dialog = createDialog(owner);

			}

			dialog.setLocation(getAppropriateLocation(owner, position));

		}

		else if(tmpobj.getClass().isInstance(new JFrame())||tmpobj.getClass().getSuperclass().isInstance(new JFrame())) {

			JFrame ownerFrame = (JFrame) SwingUtilities.getWindowAncestor(jFormattedTextField);

			if (dialog == null || dialog.getOwner() != ownerFrame) {

				dialog = createDialog(ownerFrame);

			}

			dialog.setLocation(getAppropriateLocation(ownerFrame, position));

		}

		flushWeekAndDay();

		dialog.setVisible(true);

	}

	Point getAppropriateLocation(Frame owner, Point position) {
		
		//�������λ��ֵ��ѧϰ

		Point result = new Point(position);

		Point p = owner.getLocation();
		
		int offsetX = (position.x + width) - (p.x + owner.getWidth());

		int offsetY = (position.y + height) - (p.y + owner.getHeight());

		if (offsetX > 0) {
			
			result.x -= offsetX;

		}

		if (offsetY > 0) {

			result.y -= offsetY;

		}
//		System.out.println(result);
		return result;

	}
	

	public void closeAndSetDate() {

		setDate(c.getTime());

		dialog.dispose();

	}

	private Calendar getCalendar() {

		Calendar result = Calendar.getInstance();  //getInstance(): ʹ��Ĭ��ʱ�������Ի������һ������

//		result.setTime(getDate());

		return result;

	}

	private int getSelectedYear() {

		return ((Integer) yearSpin.getValue()).intValue();

	}



	private int getSelectedMonth() {

		return ((Integer) monthSpin.getValue()).intValue();

	}

//	private int getSelectedHour() {
//
//		return ((Integer) hourSpin.getValue()).intValue();
//
//	}
//
//	private int getSelectedMinute() {
//
//		return ((Integer) minuteSpin.getValue()).intValue();
//
//	}

	private void dayColorUpdate(boolean isOldDay) {

		int day = c.get(Calendar.DAY_OF_MONTH);

		// System.out.println(day+"-----day-----");

		c.set(Calendar.DAY_OF_MONTH, currentDay);

		// System.out.println("���Ե�ǰ����day:"+c.get(Calendar.DATE));

		int actionCommandId = day - 2 + c.get(Calendar.DAY_OF_WEEK);

		int i = actionCommandId / 7;

		int j = actionCommandId % 7;

		if (isOldDay)

			daysButton[i][j].setForeground(dateFontColor);

		else
			
			daysButton[i][j].setForeground(todayBackColor);  //�˴�����bug����ʱ�����޸�16-11-6

	}

	private void flushWeekAndDay() {

		c.set(Calendar.DAY_OF_MONTH, currentDay);

		int maxDayNo = c.getActualMaximum(Calendar.DAY_OF_MONTH);

		int dayNo = 2 - c.get(Calendar.DAY_OF_WEEK);

//		 System.out.println("ĳ������ֵ��Χ��"+dayNo+"----"+maxDayNo);

		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 7; j++) {

				String s = "";

				if (dayNo >= 1 && dayNo <= maxDayNo)

					s = String.valueOf(dayNo);

				daysButton[i][j].setText(s);

				dayNo++;

			}

		}

		dayColorUpdate(false);

	}

	public void stateChanged(ChangeEvent e) {
//
		JSpinner source = (JSpinner) e.getSource();
//		if (source.getName().equals("Minute")) {
//
//			c.set(Calendar.MINUTE, getSelectedMinute());
//
//			return;
//
//		}
//
//		if (source.getName().equals("Hour")) {
//
//			c.set(Calendar.HOUR_OF_DAY, getSelectedHour());
//
//			return;
//
//		}

		dayColorUpdate(true);

		if (source.getName().equals("Year")) {

			c.set(Calendar.YEAR, getSelectedYear());

		}

		if (source.getName().equals("Month")) {

			c.set(Calendar.MONTH, getSelectedMonth() - 1);

		}

		flushWeekAndDay();

	}

	public void actionPerformed(ActionEvent e) {

		JButton source = (JButton) e.getSource();

		if (source.getText().length() == 0)
			
			return;

		dayColorUpdate(true);

		source.setForeground(todayBackColor);

		int newDay = Integer.parseInt(source.getText());

		c.set(Calendar.DAY_OF_MONTH, newDay);

	}

	public void setDate(Date date) {

		jFormattedTextField.setText(getDefaultDateFormat().format(date));

	}
	
		private static SimpleDateFormat getDefaultDateFormat() {

			return new SimpleDateFormat("yyyy-MM-dd");

		}

	}
	