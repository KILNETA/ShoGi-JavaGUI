package shogi;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Shogi {

	
/*-------------------------Variable-------------------------*/	

	
	private JFrame Shogi_frame;
	
	//棋盤上的9x9個棋盤格
	public static Checker CheckerGrid[][] = new Checker[9][9];
	//打入盤上的3x3個棋盤格 (各兩個)
	public static BreakIn BreakInGrid[][][] = new BreakIn[2][3][3];
	
	public static SeekPeace SeekPeace[] = new SeekPeace[2];
	public static RiseChange RiseChange[] = new RiseChange[2];
	
	public static JLabel After = new JLabel();
	public static JLabel First = new JLabel();
	//兩個玩家 各20個棋子
	public static Chess PlayerChess[] = new Chess[40];
	public static Chess choose = null;
	public static int Now_Player = 0;


/*-------------------------Function-------------------------*/
	
	public JFrame getFrame() {//給予主視窗變數
		return Shogi_frame;
	}

	//啟動應用程序。
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Shogi window = new Shogi();
					Login login = new Login(window.Shogi_frame);
				} 
				catch (Exception e) {
					e.printStackTrace();
	}	}	});	}

	//創建應用程序。
	public Shogi() {
		initialize();
	}

	//初始化框架的內容。
	private void initialize() {
		Shogi_frame = new JFrame();//創立主畫面
		Shogi_frame.setResizable(false);//調整窗口大小(停用)
		Shogi_frame.setEnabled(false);//停用窗口 (直到登入窗口登入成功)
		Shogi_frame.setTitle("\u5C07\u68CB\u5927\u5E2B");//窗口標題
		Shogi_frame.setBounds(100, 100, 1046, 819);//畫面大小
		Shogi_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//畫面大小
		Shogi_frame.getContentPane().setLayout(null);//獲取內容窗格 &　設置佈局
		Shogi_frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));//應用程式縮圖
		Shogi_frame.setLocationRelativeTo(null);
		
		Create_Chessboard();
		Create_BreakInboard();
		Create_SeekPeace();
		Create_RiseChange();
		

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(822, 510, 198, 240);
		Shogi_frame.getContentPane().add(panel_2);
		
		After = new JLabel("\u2500\u2500\u2500\u2500\u2500\u2500\u5F8C\u624B\u25BD");
		After.setFont(new Font("標楷體", Font.BOLD, 20));
		After.setHorizontalAlignment(SwingConstants.RIGHT);
		After.setBounds(10, 280, 198, 25);
		Shogi_frame.getContentPane().add(After);
		
		First = new JLabel("\u25B2\u5148\u624B\u2500\u2500\u2500\u2500\u2500\u2500");
		First.setHorizontalAlignment(SwingConstants.LEFT);
		First.setFont(new Font("標楷體", Font.BOLD, 20));
		First.setBounds(822, 475, 198, 25);
		Shogi_frame.getContentPane().add(First);
		First.setForeground(Color.red);
		
		//標記棋盤 (編輯器用)	----------------------------//
		JPanel panel = new JPanel();				//
		panel.setBounds(218, 30, 594, 720);			//
		Shogi_frame.getContentPane().add(panel);	//
													//
		JPanel panel_1 = new JPanel();				//
		panel_1.setBounds(10, 30, 198, 240);		//
		Shogi_frame.getContentPane().add(panel_1);	//
		//標記棋盤 (編輯器用)	----------------------------//
	}
	
	//布置9x9棋盤格
	private void Create_Chessboard(){
		for(int j=0;j<9;j++) {for(int i=0;i<9;i++) {
			CheckerGrid[j][i]=new Checker(this,j*10+i);
			Shogi_frame.getContentPane().add(CheckerGrid[j][i]);//於畫面上顯示按鈕
		}	}
		//初始化玩家棋子
		initialize_PlayerChess();
		//初始化棋子擺放
		initialize_CheckerChess();
	}
	
	//布置3x3棋盤格
	private void Create_BreakInboard(){
		String Chess[]= {null,"Pawn",null,"Bishop","Lance","Rook","Gold general","Knight","Silver general",
				 "Silver general","Knight","Gold general","Rook","Lance","Bishop",null,"Pawn",null};
		//初始化3x3的棋盤格
		for(int P=0;P<2;P++) {for(int j=0;j<3;j++){for(int i=0;i<3;i++){
			BreakInGrid[P][j][i] = new BreakIn(Chess[(9*P)+(j*3)+i] ,P , (j*10)+i);
			Shogi_frame.getContentPane().add(BreakInGrid[P][j][i]);//於畫面上顯示按鈕
		}	}	}
		//初始化打入盤擺放
		initialize_BreakInGrid();
	}
	
	//布置求和按鈕
	private void Create_SeekPeace() {
		for(int i=0;i<2;i++){
			SeekPeace[i] = new SeekPeace(this,i);//新建按鈕
			Shogi_frame.getContentPane().add(SeekPeace[i]);
		}
	}
	
	//布置升變按鈕
	private void Create_RiseChange() {
		for(int i=0;i<2;i++){
			RiseChange[i] = new RiseChange(i);//新建按鈕
			Shogi_frame.getContentPane().add(RiseChange[i]);
		}
	}
	
	// 初始化玩家棋子
	private void initialize_PlayerChess()
	{
		String Chess[]= {"First_king","Rook","Bishop","Gold general","Silver general","Knight","Lance","Pawn","After_king"};
		PlayerChess[4]=new Chess(Chess[0],0);
		PlayerChess[24]=new Chess(Chess[8],1);
		
		PlayerChess[9]=new Chess(Chess[1],0);
		PlayerChess[10]=new Chess(Chess[2],0);
		PlayerChess[29]=new Chess(Chess[2],1);
		PlayerChess[30]=new Chess(Chess[1],1);
		for(int i=0;i<=1;i++) {	for(int j=0;j<=1;j++) {
				PlayerChess[3+(i*20)+(j*2)]=new Chess(Chess[3],i);
				PlayerChess[2+(i*20)+(j*4)]=new Chess(Chess[4],i);
				PlayerChess[1+(i*20)+(j*6)]=new Chess(Chess[5],i);
				PlayerChess[0+(i*20)+(j*8)]=new Chess(Chess[6],i);
			}
			for(int j=0;j<9;j++) {
				PlayerChess[11+(i*20)+j]=new Chess(Chess[7],i);
	}	}	}
	
	// 初始化棋子擺放
	private void initialize_CheckerChess(){
		String direction[]= {" Up"," Down"};
		for(int j=0;j<=1;j++){
			for(int i=0;i<9;i++){
				Grid_SetIcon(CheckerGrid[6-(j*4)][i],"Picture\\"+ PlayerChess[11+(j*20)+i].getChess_Class() + direction[PlayerChess[11+(j*20)+i].getChessPlayer()] +".png");
				CheckerGrid[6-(j*4)][i].setChess(PlayerChess[11+(j*20)+i]);
				PlayerChess[11+(j*20)+i].setPosition(CheckerGrid[6-(j*4)][i].getGrid_Id());
			}
			for(int i=0;i<2;i++){
				Grid_SetIcon(CheckerGrid[7-(j*6)][1+(i*6)],"Picture\\"+ PlayerChess[9+(j*20)+i].getChess_Class() + direction[PlayerChess[9+(j*20)+i].getChessPlayer()] +".png");
				CheckerGrid[7-(j*6)][1+(i*6)].setChess(PlayerChess[9+(j*20)+i]);
				PlayerChess[9+(j*20)+i].setPosition(CheckerGrid[7-(j*6)][(1+(i*6))].getGrid_Id());
			}
			for(int i=0;i<9;i++){
				Grid_SetIcon(CheckerGrid[8-(j*8)][i],"Picture\\"+ PlayerChess[0+(j*20)+i].getChess_Class() + direction[PlayerChess[0+(j*20)+i].getChessPlayer()] +".png");
				CheckerGrid[8-(j*8)][i].setChess(PlayerChess[0+(j*20)+i]);
				PlayerChess[0+(j*20)+i].setPosition(CheckerGrid[8-(j*8)][i].getGrid_Id());
	}	}	}
	
	// 初始化打入盤擺放
	private void initialize_BreakInGrid() {
		String direction[]= {" Up"," Down"};
	//------------------------------Function------------------------------//
		for(int P=0;P<2;P++){	for(int j=0;j<3;j++){	for(int i=0;i<3;i++){//匯入圖片
			Grid_SetIcon(BreakInGrid[P][j][i] ,"Picture\\"+ BreakInGrid[P][j][i].getGrid_Class() + direction[BreakInGrid[P][j][i].getPlayer()] +".png");
	}	}	}	}
	
	private void Grid_SetIcon(JButton Grid ,String link) {//設置棋格圖片
		ImageIcon Icon = new ImageIcon(link);
		Grid.setIcon(Icon);
	}
	
	public void Create_GameOver(int whoWin) {//創建遊戲結束介面
		GameOver GameOver = new GameOver(this,whoWin);
	}
	public void Reset_allChecker() {//重設棋盤
		Now_Player = 0;
		Shogi.First.setForeground(Color.red);
		Shogi.After.setForeground(Color.black);
		
		for(int p=0;p<2;p++) {for(int y=0;y<3;y++) {for(int x=0;x<3;x++) {
			BreakInGrid[p][y][x].Reset();
		}	}	}
		for(int y=0;y<9;y++) {for(int x=0;x<9;x++) {
			Shogi.CheckerGrid[y][x].Reset();
		}	}
		//初始化玩家棋子
		initialize_PlayerChess();
		//初始化棋子擺放
		initialize_CheckerChess();
		// 初始化打入盤擺放
		initialize_BreakInGrid();
		
		for(int p=0;p<2;p++) {
			SeekPeace[p].Reset();
		}
		
	}
	
}