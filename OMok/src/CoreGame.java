import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CoreGame extends JPanel implements MouseListener, ActionListener 
{
	Stone[][] mStone;     //놓여진 돌의 위치를 기억하기 위한 변수 
	Point     mMousePos;  //마우스 위치 
	JButton   mBtnReset;
	int       mWinner;    //0:승부중,1:백돌승,2:흑돌승
	boolean   mPlayOrder; //true:백돌, false:흑돌
	boolean   mIsOver;    //true:게임끝,false:게임진행중
	BufferedImage bg = null;	
	
	final int MAX_SIZE   = 10;    //가로/세로 좌표의 칸수
	final int START_POS  = 300;
	final int REC_SIZE   = 40;
	final int GRID_SIZE  = 30;

	public CoreGame() 
	{
		setLayout(null);
		addMouseListener(this);
		
		try
		{
			//배경 이미지 읽기
			File sourceimage = new File("D:\\jhjeong\\study\\OMok\\image\\bg.png");
			bg = ImageIO.read(sourceimage);
		}catch(Exception e)
		{		
			//이미지가 없으면 배경색 칠하기
			setBackground(new Color(248, 198, 122));
		}
		
		mStone = new Stone[MAX_SIZE][MAX_SIZE];
		mMousePos = new Point();

		mBtnReset = new JButton("게임 재시작");
		mBtnReset.setBounds(30, 425, 200, 30);
		mBtnReset.addActionListener(this);
		add(mBtnReset);
		
		//게임 초기화
		InitGame();
	}

	//게임 초기화
	void InitGame() 
	{
		mPlayOrder = false;
		mWinner    = 0;
		mIsOver    = false;

		for (int y = 0; y < MAX_SIZE; y++) 
		{
			for (int x = 0; x < MAX_SIZE; x++) 
			{
				Stone stone  = new Stone();
				stone.x      = START_POS - 15 + (x * REC_SIZE);
				stone.y      = 100 - 15 + (y * REC_SIZE);
				stone.size   = GRID_SIZE;
				stone.type   = 0;
				mStone[y][x] = stone;
			}
		}
	}

	//승자 판단하기
	void SetWhoIsWin()
	{
		//가로
		for (int y = 0; y < MAX_SIZE; y++) 
		{
			for (int x = 0; x < MAX_SIZE - 4; x++) 
			{
				if (mStone[y][x].type == 1 && mStone[y][x + 1].type == 1 && mStone[y][x + 2].type == 1 && mStone[y][x + 3].type == 1 && mStone[y][x + 4].type == 1) 
				{
					mWinner = 1;

				}
				if (mStone[y][x].type == 2 && mStone[y][x + 1].type == 2 && mStone[y][x + 2].type == 2 && mStone[y][x + 3].type == 2 && mStone[y][x + 4].type == 2) 
				{
					mWinner = 2;
				}
			}
		}

		//세로
		for (int y = 0; y < MAX_SIZE - 4; y++) 
		{
			for (int x = 0; x < MAX_SIZE; x++) 
			{
				if (mStone[y][x].type == 1 && mStone[y + 1][x].type == 1 && mStone[y + 2][x].type == 1 && mStone[y + 3][x].type == 1 && mStone[y + 4][x].type == 1) 
				{
					mWinner = 1;

				}
				if (mStone[y][x].type == 2 && mStone[y + 1][x].type == 2 && mStone[y + 2][x].type == 2 && mStone[y + 3][x].type == 2 && mStone[y + 4][x].type == 2) {
					mWinner = 2;
				}
			}
		}

		// 왼쪽->오른쪽 대각선
		for (int y = 0; y < MAX_SIZE - 4; y++) 
		{
			for (int x = 0; x < MAX_SIZE - 4; x++) 
			{
				if (mStone[y][x].type == 1 && mStone[y + 1][x + 1].type == 1 && mStone[y + 2][x + 2].type == 1 && mStone[y + 3][x + 3].type == 1 && mStone[y + 4][x + 4].type == 1) {
					mWinner = 1;

				}
				if (mStone[y][x].type == 2 && mStone[y + 1][x + 1].type == 2 && mStone[y + 2][x + 2].type == 2 && mStone[y + 3][x + 3].type == 2 && mStone[y + 4][x + 4].type == 2) {

					mWinner = 2;
				}
			}
		}

		//오른쪽->왼쪽 대각선
		for (int y = MAX_SIZE - 1; y > MAX_SIZE - 7; y--) 
		{
			for (int x = 0; x < MAX_SIZE - 4; x++) 
			{
				if (mStone[y][x].type == 1 && mStone[y - 1][x + 1].type == 1 && mStone[y - 2][x + 2].type == 1 && mStone[y - 3][x + 3].type == 1 && mStone[y - 4][x + 4].type == 1) 
				{
					mWinner = 1;
				}
				if (mStone[y][x].type == 2 && mStone[y - 1][x + 1].type == 2 && mStone[y - 2][x + 2].type == 2 && mStone[y - 3][x + 3].type == 2 && mStone[y - 4][x + 4].type == 2) 
				{
					mWinner = 2;
				}
			}
		}
	}

	@Override
	//화면에 게임을 그린다.
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		if( bg != null ) 
		{
			g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		}
		
		try 
		{
			Thread.sleep(10);
			repaint();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		//화면 중간에 타이틀 출력
		g.setColor(Color.BLUE);
		g.setFont(new Font("", Font.BOLD, 35));
		g.drawString("오목게임", 280, 50);
		
		//가로/세로 격자를 그린다.
		g.setColor(Color.BLACK);
		for (int i = 0; i < MAX_SIZE; i++) 
		{
			// 가로
			g.drawLine(START_POS, 100 + (REC_SIZE * i), START_POS + (REC_SIZE * (MAX_SIZE - 1)), 100 + (REC_SIZE * i));
			// 세로
			g.drawLine(START_POS + (REC_SIZE * i), 100, START_POS + (REC_SIZE * i), 100 + (REC_SIZE * (MAX_SIZE - 1)));
		}

		//격자위에 흑돌/백돌을 그린다.
		for (int y = 0; y < MAX_SIZE; y++) 
		{
			for (int x = 0; x < MAX_SIZE; x++) 
			{
				if (mStone[y][x].type == 1) 
				{
					g.setColor(Color.WHITE);
					g.fillOval(mStone[y][x].x, mStone[y][x].y, mStone[y][x].size, mStone[y][x].size);
				}

				else if (mStone[y][x].type == 2) 
				{
					g.setColor(Color.BLACK);
					g.fillOval(mStone[y][x].x, mStone[y][x].y, mStone[y][x].size, mStone[y][x].size);
				}
			}
		}
		
		//승자가 있는 경우 승자를 표시한다.
		g.setColor(Color.RED);
		g.setFont(new Font("", Font.BOLD, 30));
		if (mWinner == 1) 
		{
			g.drawString("[백돌] 승리!", 50, 100);
			mIsOver = true;
		} else if (mWinner == 2) 
		{
			g.drawString("[흑돌] 승리!", 50, 100);
			mIsOver = true;
		}else
		{
			if (mPlayOrder) 
			{
				g.setColor(Color.WHITE);
				g.drawString("[백돌] 차례", 50, 100);
			} else 
			{
				g.setColor(Color.BLACK);
				g.drawString("[흑돌] 차례", 50, 100);
			}		
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		InitGame();
	}

	@Override
	//마우스를 클릭시 좌표에 흑/백돌 놓기
	public void mousePressed(MouseEvent e) 
	{
		if (mIsOver)
		{
			//게임이 끝났으므로 돌 놓기 불가 
			return;
		}

		mMousePos = e.getPoint();

		for (int y = 0; y < MAX_SIZE; y++) 
		{
			for (int x = 0; x < MAX_SIZE; x++) 
			{
				int minX = mStone[y][x].x;
				int maxX = mStone[y][x].x + mStone[y][x].size;
				int minY = mStone[y][x].y;
				int maxY = mStone[y][x].y + mStone[y][x].size;
				if (minX <= mMousePos.x && mMousePos.x <= maxX && minY <= mMousePos.y && mMousePos.y <= maxY) 
				{
					if (mStone[y][x].type == 0) 
					{
						if (mPlayOrder) 
						{
							//흑돌 놓여짐
							mStone[y][x].type = 1;
						} else 
						{
							//백돌 놓여짐
							mStone[y][x].type = 2;
						}
						//승자 판단
						SetWhoIsWin();
						mPlayOrder = !mPlayOrder;
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
