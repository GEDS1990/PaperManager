package org.view.student;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.util.MyLookAndFeel;

public class StudentFrameTest {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(MyLookAndFeel.JTATTOO_MINT);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new StudentFrame("21406031016").intoFrame();
	}

}
