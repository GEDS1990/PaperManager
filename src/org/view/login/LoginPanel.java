package org.view.login;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private ImageIcon bg;// ����ͼƬ����
    public LoginPanel() {
        super();
        // ��ȡͼƬ·��
        URL url = getClass().getResource("����.jpg");
        bg = new ImageIcon(url);// ����ͼƬ����
        // ��������뱳����ͬ��С
        setSize(bg.getIconWidth(), bg.getIconHeight());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        super.paintComponent(g2);
        if (bg != null) {// �������ͼƬ�����ʼ�����
            // ����ͼƬ��������
            g2.drawImage(bg.getImage(), 0, 0, this);
        }
    }
}
