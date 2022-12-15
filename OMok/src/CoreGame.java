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
	Stone[][] mStone;     //������ ���� ��ġ�� ����ϱ� ���� ���� 
	Point     mMousePos;  //���콺 ��ġ 
	JButton   mBtnReset;
	int       mWinner;    //0:�º���,1:�鵹��,2:�浹��
	boolean   mPlayOrder; //true:�鵹, false:�浹
	boolean   mIsOver;    //true:���ӳ�,false:����������
	BufferedImage bg = null;	
	
	final int MAX_SIZE   = 10;    //����/���� ��ǥ�� ĭ��
	final int START_POS  = 300;
	final int REC_SIZE   = 40;
	final int GRID_SIZE  = 30;

	public CoreGame() 
	{
		setLayout(null);
		addMouseListener(this);
		
		try
		{
			//��� �̹��� �б�
			File sourceimage = new File("D:\\jhjeong\\study\\OMok\\image\\bg.png");
			bg = ImageIO.read(sourceimage);
		}catch(Exception e)
		{		
			//�̹����� ������ ���� ĥ�ϱ�
			setBackground(new Color(248, 198, 122));
		}
		
		mStone = new Stone[MAX_SIZE][MAX_SIZE];
		mMousePos = new Point();

		mBtnReset = new JButton("���� �����");
		mBtnReset.setBounds(30, 425, 200, 30);
		mBtnReset.addActionListener(this);
		add(mBtnReset);
		
		//���� �ʱ�ȭ
		InitGame();
	}

	//���� �ʱ�ȭ
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

	//���� �Ǵ��ϱ�
	void SetWhoIsWin()
	{
		//����
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

		//����
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

		// ����->������ �밢��
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

		//������->���� �밢��
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
	//ȭ�鿡 ������ �׸���.
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
		
		//ȭ�� �߰��� Ÿ��Ʋ ���
		g.setColor(Color.BLUE);
		g.setFont(new Font("", Font.BOLD, 35));
		g.drawString("�������", 280, 50);
		
		//����/���� ���ڸ� �׸���.
		g.setColor(Color.BLACK);
		for (int i = 0; i < MAX_SIZE; i++) 
		{
			// ����
			g.drawLine(START_POS, 100 + (REC_SIZE * i), START_POS + (REC_SIZE * (MAX_SIZE - 1)), 100 + (REC_SIZE * i));
			// ����
			g.drawLine(START_POS + (REC_SIZE * i), 100, START_POS + (REC_SIZE * i), 100 + (REC_SIZE * (MAX_SIZE - 1)));
		}

		//�������� �浹/�鵹�� �׸���.
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
		
		//���ڰ� �ִ� ��� ���ڸ� ǥ���Ѵ�.
		g.setColor(Color.RED);
		g.setFont(new Font("", Font.BOLD, 30));
		if (mWinner == 1) 
		{
			g.drawString("[�鵹] �¸�!", 50, 100);
			mIsOver = true;
		} else if (mWinner == 2) 
		{
			g.drawString("[�浹] �¸�!", 50, 100);
			mIsOver = true;
		}else
		{
			if (mPlayOrder) 
			{
				g.setColor(Color.WHITE);
				g.drawString("[�鵹] ����", 50, 100);
			} else 
			{
				g.setColor(Color.BLACK);
				g.drawString("[�浹] ����", 50, 100);
			}		
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		InitGame();
	}

	@Override
	//���콺�� Ŭ���� ��ǥ�� ��/�鵹 ����
	public void mousePressed(MouseEvent e) 
	{
		if (mIsOver)
		{
			//������ �������Ƿ� �� ���� �Ұ� 
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
							//�浹 ������
							mStone[y][x].type = 1;
						} else 
						{
							//�鵹 ������
							mStone[y][x].type = 2;
						}
						//���� �Ǵ�
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
