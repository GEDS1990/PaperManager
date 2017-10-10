package org.view.teacher;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.util.MyLookAndFeel;

public class TeacherFrameTest {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(MyLookAndFeel.JTATTOO_MINT);
			//JTATTOO_AERO
			//JTATTOO_ALUMINUM黑灰过度
			//JTATTOO_BERNSTEIN橘黄
			//JTATTOO_HIFI纯黑
			//JTATTOO_MCWIN按钮不错，彩色
			//JTATTOO_MINT键入黄色
			//JTATTOO_NOIRE深黑
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new TeacherFrame(1001);
	}
}