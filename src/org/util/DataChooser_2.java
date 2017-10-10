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
 * 已知BUG:
 * 1.关于星期的算法出错，星期不匹配
 *        *貌似公元元年第一天是周一，按7取模貌似可以，又觉得麻烦
 * 2.关于【本日日期颜色】出现问题，以至于每个月这天都会上前景色
 * 3.定位算法有点问题 容器位置太靠边，会出问题。
 *        *解决方法：自己定义Point getAppropriateLocation(Frame owner, Point position)中的参数
 *        * result.x=某某;
 *		  * result.y=某某;
 *
 *调用注意事项：
 *1.使用JFormattedTextField而不是JText
 *2.调用时使用Point point = text2.getLocationOnScreen();获取组件位置
 *
 *程序期望：
 *1.配色和字体需要优化
 *2.该组件可以是方法，获取JFormattedTextField组件宽度来觉得该组件宽度（JFormattedTextField过小时，使用默认大小）
 */
ChangeListener {
	private static final long serialVersionUID = 1L;

	int startYear = 1980; //默认【最小】显示年份

	int lastYear = 2050; //默认【最大】显示年份

	int width = 332; //界面宽度

	int height = 260; //界面高度

	Color backGroundColor = Color.gray; //底色

	//月历表格配色----------------//

	Color palletTableColor = Color.white; //日历表底色
	Color todayBackColor = Color.orange; //今天背景色
	Color weekFontColor = Color.blue; //星期文字色
	Color dateFontColor = Color.black; //日期文字色
	Color weekendFontColor = Color.red; //周末文字色
	Color controlLineColor = Color.pink; //控制条底色
	Color controlTextColor = Color.white; //控制条标签文字色
	Color rbFontColor = Color.white; //RoundBox文字色
	Color rbBorderColor = Color.red; //RoundBox边框色
	Color rbButtonColor = Color.pink; //RoundBox按钮色
	Color rbBtFontColor = Color.red; //RoundBox按钮文字色

	JDialog dialog; //用于显示日期选择控件

	JSpinner yearSpin; //调节年份的JSpinner
	JSpinner monthSpin; //调节月份
//	JSpinner hourSpin; //调节小时
//	JSpinner minuteSpin;//调节分钟
	/*
	 * 2016-10-29: 查询
	 * public class JSpinner extends JComponent implements Accessible
	 * 让用户从一个有序序列中选择一个数字或者一个对象值的单行输入字段。
	 * Spinner 通常提供一对带小箭头的按钮以便逐步遍历序列元素。键盘的向上/向下方向键也可循环遍历元素。
	 * 也允许用户在 spinner 中直接输入合法值。
	 * 尽管组合框提供了相似的功能，但因为 spinner 不要求可隐藏重要数据的下拉列表，所以有时它也成为首要选择。
	 */
	
	JButton[][] daysButton = new JButton[6][7];//用于显示当前月份每一天所对应的星期的按钮数组

	JFormattedTextField jFormattedTextField;//显示当前选择日期的有格式输入框

	Calendar c = getCalendar();
	Calendar cal = Calendar.getInstance();

	int currentDay=cal.get(Calendar.DAY_OF_MONTH);

	public DataChooser_2(JFormattedTextField jftf) {
		jFormattedTextField = jftf;
		//设置布局及边框
		setLayout(new BorderLayout());
//		setBorder(new LineBorder(backGroundColor, 2));  
		//设置最外围边框 (哎呀做出来不好看，但是微软的日历有一个点击日期会出现外围边框的功能，很好看，下次尝试是实现下——10-29)
		setBackground(backGroundColor);
		
		//初始化及添加子面板
		JPanel topYearAndMonth = createYearAndMonthPanal();
		add(topYearAndMonth, BorderLayout.NORTH);
		JPanel centerWeekAndDay = createWeekAndDayPanal();
		add(centerWeekAndDay, BorderLayout.CENTER);

	}
	//功能描述:用于创建年份及月份显示面板
	//其中 return 用于创建年份及月份显示面板JPanel对象

	private JPanel createYearAndMonthPanal() {

		int currentYear = c.get(Calendar.YEAR);  //返回给定日历字段的值

		int currentMonth = c.get(Calendar.MONTH) + 1;
		
		//如果想实现更详细的时间格式，请解除下面注释，方法同理

//		int currentHour = c.get(Calendar.HOUR_OF_DAY);
//
//		int currentMinute = c.get(Calendar.MINUTE);

		JPanel result = new JPanel();

		result.setLayout(new FlowLayout());

		result.setBackground(controlLineColor);

		yearSpin = new JSpinner(new SpinnerNumberModel(currentYear, startYear,lastYear, 1));  
		//构造一个具有指定 value、minimum/maximum 边界和 stepSize(跳动幅度) 的 SpinnerNumberModel。
		//用于数字序列的 SpinnerModel。该序列的上下边界由名为 minimum 和 maximum 的属性定义

		yearSpin.setPreferredSize(new Dimension(60, 20));  //设置此组件的首选大小

		yearSpin.setName("Year");
		
		yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####"));  //.NumberEditor.NumberEditor(JSpinner spinner, String decimalFormatPattern) 后者：十进制格式
		
		yearSpin.setFont(new Font("微软雅黑", Font.BOLD, 13));

		yearSpin.addChangeListener(this);

		result.add(yearSpin);
		
		JLabel yearLabel = new JLabel("年");

		yearLabel.setForeground(controlTextColor);
		
		yearLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));

		result.add(yearLabel);

		monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1, 12, 1));

		monthSpin.setPreferredSize(new Dimension(60, 20));

		monthSpin.setName("Month");
		
		monthSpin.setFont(new Font("微软雅黑", Font.BOLD, 13));

		monthSpin.addChangeListener(this);

		result.add(monthSpin);

		JLabel monthLabel = new JLabel("月");

		monthLabel.setForeground(controlTextColor);
		
		monthLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));

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
//		JLabel hourLabel = new JLabel("时");
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
//		JLabel minuteLabel = new JLabel("分");
//
//		minuteLabel.setForeground(controlTextColor);
//
//		result.add(minuteLabel);

		return result;

	}

	//功能描述:用于创建当前月份中每一天按对应星期排列所在面板


	/**
	 * @return
	 */
	private JPanel createWeekAndDayPanal() {



		String colname[] = { "日", "一", "二", "三", "四", "五", "六" };

		JPanel result = new JPanel();

		//设置固定字体，以免调用环境改变影响界面美观

		result.setFont(new Font("微软雅黑", Font.BOLD, 14));

		result.setLayout(new GridLayout(7, 7));

		result.setBackground(Color.gray);

		JLabel cell; //显示星期

		for (int i = 0; i < 7; i++) {

			cell = new JLabel(colname[i]);
			
			cell.setFont(new Font("微软雅黑", Font.BOLD, 14));

			cell.setHorizontalAlignment(JLabel.CENTER);  
			/*
			 * 16-11-30:
			 * setHorizontalAlignment：
			 * 设置标签内容沿 X 轴的对齐方式。 
			 * 这是一个 JavaBeans 绑定属性。
			 * 参数：
			 * alignment - SwingConstants 中定义的以下常量之一：LEFT、CENTER（只显示图像的标签的默认值）、
			    RIGHT、LEADING（只显示文本的标签的默认值）或 TRAILING
			*/

			if (i == 0 || i == 6)

				cell.setForeground(Color.white);

			else

				cell.setForeground(Color.white);//此处本人做了修改，目的是为了让下次应用时方便调用（比如要设置周六周日颜色为红色）

			result.add(cell);

		}

		int actionCommandId = 0;

		for (int i = 0; i < 6; i++)

			for (int j = 0; j < 7; j++) {

				JButton numberButton = new JButton();

				numberButton.setBorder(null);
				//无边框（这个方法借鉴网络，如果不加这一句，42个按钮会同时出现边框，影响框架美观。如果使用标签可能会好一点）

				numberButton.setHorizontalAlignment(SwingConstants.CENTER);
				//SwingConstants：通常用于在屏幕上定位或定向组件的常量的集合（值得学习）
				
				numberButton.setActionCommand(String.valueOf(actionCommandId));
				/*
				 * 16-10-30查询：
				 * setActionCommand(String actionCommand)：设置此按钮的动作命令 
				 * 其中 参数actionCommand - 此按钮的动作命令
				 * */

				numberButton.addActionListener(this);

				numberButton.setBackground(palletTableColor);

				numberButton.setForeground(dateFontColor);//此处存在bug，有时间再修复16-11-6

				if (j == 0 || j == 6)

					numberButton.setForeground(weekendFontColor);

				else

					numberButton.setForeground(dateFontColor);
				numberButton.setFont(new Font("微软雅黑", Font.BOLD, 13));

				daysButton[i][j] = numberButton;
				
				//2016-11-6：此处有非常大的好处在于，不使用MouseListener接口，而是使用匿名内部类，实现循环添加事件。

				numberButton.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {
						/*本人认为此处可以加入的功能
						 * 鼠标进入事件：出现淡灰色矿体
						 */

						if (e.getClickCount() == 2) { //鼠标双击再次时

							closeAndSetDate();  //调用

						}

					}

				});

				result.add(numberButton);

				actionCommandId++;

			}

		return result;

	}



	private JDialog createDialog(Frame owner) {

		JDialog result = new JDialog(owner, "入库时间选择", true);  //此处用于数据入库，使用其他模式，应修改title
		/*
		 * 2016-10-30查询：
		 * JDialog
		 * public JDialog(Dialog owner,String title,boolean modal)创建一个具有指定标题、模式和指定所有者 Dialog 的对话框。 
		 * 此构造方法将该组件的语言环境属性设置为 JComponent.getDefaultLocale 所返回的值。 
		 * 参数：
		 * owner - 显示该对话框的所有者 Dialog；如果此对话框没有所有者，则为 null
		 * title - 该对话框的标题栏中所显示的 String
		 * modal - 指定对话框在显示时是否阻塞用户向其他顶层窗口输入。
		    如果为 true，则模式类型属性被设置为 DEFAULT_MODALITY_TYPE；否则对话框是无模式的。
		 */

		result.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		result.getContentPane().add(this, BorderLayout.CENTER);

		result.pack();
		//调整此窗口的大小，以适合其子组件的首选大小和布局。
		//如果该窗口和/或其所有者还不可显示，则在计算首选大小之前都将变得可显示。在计算首选大小之后，将会验证该窗口

		result.setSize(width, height);

		return result;

	}

	public void showDateChooser(Point position) {

		Object tmpobj=SwingUtilities.getWindowAncestor(jFormattedTextField);
		//getWindowAncestor(Component c) 
		//返回 c 的第一个 Window 祖先；如果 c 未包含在 Window 内，则返回 null

		if(tmpobj.getClass().isInstance(new JDialog())||tmpobj.getClass().getSuperclass().isInstance(new JDialog()))
			//(Object obj) 
	        //判定指定的 Object 是否与此 Class 所表示的对象赋值兼容用于判断出现于窗体还是对话款非
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
		
		//该组件定位，值得学习

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

		Calendar result = Calendar.getInstance();  //getInstance(): 使用默认时区和语言环境获得一个日历

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

		// System.out.println("测试当前日期day:"+c.get(Calendar.DATE));

		int actionCommandId = day - 2 + c.get(Calendar.DAY_OF_WEEK);

		int i = actionCommandId / 7;

		int j = actionCommandId % 7;

		if (isOldDay)

			daysButton[i][j].setForeground(dateFontColor);

		else
			
			daysButton[i][j].setForeground(todayBackColor);  //此处存在bug，有时间再修复16-11-6

	}

	private void flushWeekAndDay() {

		c.set(Calendar.DAY_OF_MONTH, currentDay);

		int maxDayNo = c.getActualMaximum(Calendar.DAY_OF_MONTH);

		int dayNo = 2 - c.get(Calendar.DAY_OF_WEEK);

//		 System.out.println("某月日期值范围："+dayNo+"----"+maxDayNo);

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
	