/**************************************************************
 * 작성자 : 정정훈
 * 작성일 : 2022.12.14
 * 모듈명 : Omok 클래스
 * 기  능 : 오목 게임 메인 클래스
**************************************************************/
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class OMok 
{
	public static void main(String[] args) 
	{
		int mWidth  = 690;  //창 가로크기
		int mHeigth = 540;  //창 세로크기 

		//Window 프레임 생성
		JFrame frame = new JFrame();
		frame.setTitle("오목게임");
		frame.setSize(mWidth, mHeigth);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		//프레임 내 화면 생성
		Toolkit   mToolKit   = Toolkit.getDefaultToolkit();
		Dimension screenSize = mToolKit.getScreenSize();
		frame.setLocation(screenSize.width / 2 - mWidth / 2, screenSize.height / 2 - mHeigth / 2);

		//게임화면 생성 및 실행
		CoreGame mGame = new CoreGame();
		frame.setContentPane(mGame);
		frame.revalidate();
	}
}
