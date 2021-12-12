package shogi;

import java.awt.EventQueue;
import javax.swing.UIManager;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class Shogi {

	
/*-------------------------Variable-------------------------*/	

	
	private JFrame frame;
	//棋盤上的9x9個棋盤格
	JButton CheckerGrid[][] = new JButton[9][9];
	CheckerGrid_Data CheckerB[][]= new CheckerGrid_Data[9][9];
	//兩個玩家 各20個棋子
	Chess Player[] = new Chess[40];
	Chess choose = null;


/*-------------------------Function-------------------------*/


	/**
	 * 啟動應用程序。
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Shogi window = new Shogi();
					window.frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
	}	}	});	}

	/**
	 * 創建應用程序。
	 */
	public Shogi() {
		initialize();
	}

	/**
	 * 初始化框架的內容。
	 */
	private void initialize() {
		//創立主畫面
		frame = new JFrame();
		frame.setTitle("\u5C07\u68CB\u5927\u5E2B");
		//畫面大小
		frame.setBounds(100, 100, 900, 900);
		//設置默認關閉操作
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//獲取內容窗格 &　設置佈局
		frame.getContentPane().setLayout(null);
		//應用程式縮圖
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));
		
		//初始化9x9的棋盤格
		for(int j=0;j<9;j++){
			for(int i=0;i<9;i++){
				//新建按鈕
				CheckerGrid[j][i] = new JButton();
				//按鈕動作監聽器
				CheckerGrid[j][i].addActionListener(CheckerB[j][i]= new CheckerGrid_Data( ((j+1)*10)+i+1){
					
					public void actionPerformed(ActionEvent e) {
		/*該格含有棋子*/	if(getChess()!=null )
			/*未選取棋子*/		if(choose == null ) {
								Pick_up_chess(this.getGrid_Id());				//拿起棋子
								choose.CanMove(CheckerGrid ,CheckerB);			//顯示可以走的地方
							}
			/*已選取棋子*/		else {
								if(Pick_back_chess(this.getGrid_Id())); 		//判斷 + 棋子放回原位
								else Eat_EnemyChess(this.getGrid_Id()); 		//判斷 + 吃掉敵方棋子
							}
			
		/*該格不含有棋*/	else 
			/*已選取棋子*/		if(choose != null ) Move_chess(this.getGrid_Id());	//判斷 + 移動棋子
			
					}	});
				//按鈕位置&大小
				CheckerGrid[j][i].setBounds(558-(66*i), 30+(80*j), 66, 80);
				//於畫面上顯示按鈕
				frame.getContentPane().add(CheckerGrid[j][i]);
				//去除聚焦線
				CheckerGrid[j][i].setFocusPainted(false);
				//設置垂直文本 置中
				CheckerGrid[j][i].setVerticalTextPosition(JButton.CENTER);
				//設置水平文本 置中
				CheckerGrid[j][i].setHorizontalTextPosition(JButton.CENTER);
				//文本顏色 紅色
				CheckerGrid[j][i].setForeground(Color.red);
				
				//CheckerGrid[j][i].setText("●");
		}	}
		//初始化玩家棋子
		initialize_PlayerChess();
		//初始化棋子擺放
		initialize_CheckerChess();
		
		//標記棋盤 (編輯器用)	--------------------//
		JPanel panel = new JPanel();		//
		panel.setBounds(30, 30, 594, 720);	//
		frame.getContentPane().add(panel);	//
		//標記棋盤 (編輯器用)	--------------------//

	}
	
	/**
	 * 初始化玩家棋子
	 */
	private void initialize_PlayerChess()
	{
		String Chess[]= {"After_king","Rook","Bishop","Gold general","Silver general","Knight","Lance","Pawn","First_king"};
		Player[4]=new Chess(Chess[0],0);
		Player[24]=new Chess(Chess[8],1);
		
		Player[9]=new Chess(Chess[1],0);
		Player[10]=new Chess(Chess[2],0);
		Player[29]=new Chess(Chess[2],1);
		Player[30]=new Chess(Chess[1],1);
		for(int i=0;i<=1;i++) {
			for(int j=0;j<=1;j++) {
				Player[3+(i*20)+(j*2)]=new Chess(Chess[3],i);
				Player[2+(i*20)+(j*4)]=new Chess(Chess[4],i);
				Player[1+(i*20)+(j*6)]=new Chess(Chess[5],i);
				Player[0+(i*20)+(j*8)]=new Chess(Chess[6],i);
			}
			for(int j=0;j<9;j++) {
				Player[11+(i*20)+j]=new Chess(Chess[7],i);
	}	}	}
	
	/**
	 * 初始化棋子擺放
	 */
	private void initialize_CheckerChess(){
		String direction[]= {" Up"," Down"};
		for(int j=0;j<=1;j++){
			for(int i=0;i<9;i++){
				CheckerGrid[6-(j*4)][i].setIcon(new ImageIcon("Picture\\"+ Player[11+(j*20)+i].getChess() + direction[Player[11+(j*20)+i].getChessPlayer()] +".png"));
				CheckerB[6-(j*4)][i].setChess(Player[11+(j*20)+i]);
				Player[11+(j*20)+i].setPosition(CheckerB[6-(j*4)][i].getGrid_Id());
			}
			for(int i=0;i<2;i++){
				CheckerGrid[7-(j*6)][1+(i*6)].setIcon(new ImageIcon("Picture\\"+ Player[9+(j*20)+i].getChess() + direction[Player[9+(j*20)+i].getChessPlayer()] +".png"));
				CheckerB[7-(j*6)][1+(i*6)].setChess(Player[9+(j*20)+i]);
				Player[9+(j*20)+i].setPosition(CheckerB[7-(j*6)][(1+(i*6))].getGrid_Id());
			}
			for(int i=0;i<9;i++){
				CheckerGrid[8-(j*8)][i].setIcon(new ImageIcon("Picture\\"+ Player[0+(j*20)+i].getChess() + direction[Player[0+(j*20)+i].getChessPlayer()] +".png"));
				CheckerB[8-(j*8)][i].setChess(Player[0+(j*20)+i]);
				Player[0+(j*20)+i].setPosition(CheckerB[8-(j*8)][i].getGrid_Id());
	}	}	}
	
	/**
	 * 拿起棋子
	 */
	private void Pick_up_chess(int Grid_Number){
		CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].setBackground(java.awt.Color.red);
		choose = CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].getChess();
	}
	
	/**
	 * 單純移動棋子
	 */
	private void Move_chess(int Grid_Number){

		if( "●" == CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].getText()){
			choose.ResetCanMove(CheckerGrid ,CheckerB );		//擦掉 剛才顯示可以走的地方
			RiseChange_Chess();
			Pick_down_chess(Grid_Number);
	}	}
	
	/**
	 * 棋子放回原位
	 */
	private boolean Pick_back_chess(int Grid_Number){

		if(choose.getPosition() == Grid_Number){
			choose.ResetCanMove(CheckerGrid ,CheckerB );		//擦掉 剛才顯示可以走的地方
			CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].setBackground(java.awt.Color.white);
			choose = null;
			
			return true;
	} return false;	}
	
	/*
	 * 吃掉敵方棋子
	 */
	private boolean Eat_EnemyChess(int Grid_Number){
		Chess ThisGrid_Chess = CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].getChess();
		if( choose.getChessPlayer() != ThisGrid_Chess.getChessPlayer() 
		&& 	"●" == CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].getText()){
			choose.ResetCanMove(CheckerGrid ,CheckerB );		//擦掉 剛才顯示可以走的地方
			ThisGrid_Chess.setChessPlayer(choose.getChessPlayer());
			ThisGrid_Chess.setPosition(0);
			ThisGrid_Chess.Notlive();
			ThisGrid_Chess.DeclineChange_Chess();
			
			RiseChange_Chess();
			Pick_down_chess(Grid_Number); //放下棋子
			return true;
	} return false;	}
	
	/*
	 * 放下棋子
	 */
	private void Pick_down_chess(int Grid_Number){
		String direction[]= {" Up"," Down"};
		
		CheckerGrid[(choose.getPosition()/10)-1][(choose.getPosition()%10)-1].setBackground(java.awt.Color.white);
		CheckerB[(choose.getPosition()/10)-1][(choose.getPosition()%10)-1].setChess(null);
		CheckerGrid[(choose.getPosition()/10)-1][(choose.getPosition()%10)-1].setIcon(new ImageIcon());
		
		CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].setIcon(new ImageIcon("Picture\\"+ choose.getChess() + direction[choose.getChessPlayer()] +".png"));
		CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].setChess(choose);
		choose.setPosition(Grid_Number);
		choose = null;
	}
	
	/*
	 * 升變棋子
	 */
	private void RiseChange_Chess(){
		if(choose.getChessPlayer()==0 && choose.getPosition()/10<=3 && choose.getPosition()/10>=1) {
			choose.RiseChange_Chess();
		}
		if(choose.getChessPlayer()==1 && choose.getPosition()/10<=9 && choose.getPosition()/10>=7) {
			choose.RiseChange_Chess();
		}
	}
}