package org.view.teacher;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.util.MyLookAndFeel;

public class TeacherFrameTest {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(MyLookAndFeel.JTATTOO_MINT);
			//JTATTOO_AERO
			//JTATTOO_ALUMINUM�ڻҹ���
			//JTATTOO_BERNSTEIN�ٻ�
			//JTATTOO_HIFI����
			//JTATTOO_MCWIN��ť������ɫ
			//JTATTOO_MINT�����ɫ
			//JTATTOO_NOIRE���
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