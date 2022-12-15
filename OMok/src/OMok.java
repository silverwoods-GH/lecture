/**************************************************************
 * �ۼ��� : ������
 * �ۼ��� : 2022.12.14
 * ���� : Omok Ŭ����
 * ��  �� : ���� ���� ���� Ŭ����
**************************************************************/
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class OMok 
{
	public static void main(String[] args) 
	{
		int mWidth  = 690;  //â ����ũ��
		int mHeigth = 540;  //â ����ũ�� 

		//Window ������ ����
		JFrame frame = new JFrame();
		frame.setTitle("�������");
		frame.setSize(mWidth, mHeigth);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		//������ �� ȭ�� ����
		Toolkit   mToolKit   = Toolkit.getDefaultToolkit();
		Dimension screenSize = mToolKit.getScreenSize();
		frame.setLocation(screenSize.width / 2 - mWidth / 2, screenSize.height / 2 - mHeigth / 2);

		//����ȭ�� ���� �� ����
		CoreGame mGame = new CoreGame();
		frame.setContentPane(mGame);
		frame.revalidate();
	}
}
