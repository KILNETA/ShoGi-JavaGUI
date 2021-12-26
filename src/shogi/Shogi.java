package shogi;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Shogi {

	
/*-------------------------Variable-------------------------*/	

	
	private JFrame Shogi_frame;
	//棋盤上的9x9個棋盤格
	JButton CheckerGrid[][] = new JButton[9][9];
	CheckerGrid_Data CheckerB[][]= new CheckerGrid_Data[9][9];
	//打入盤上的3x3個棋盤格 (各兩個)
	JButton BreakInGrid[][][] = new JButton[2][3][3];
	BreakIn_Data BreakInB[][][]= new BreakIn_Data[2][3][3];
	
	JLabel After = new JLabel();
	JLabel First = new JLabel();
	//兩個玩家 各20個棋子
	Chess PlayerChess[] = new Chess[40];
	Chess choose = null;
	int Now_Player = 0;


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
					Login login = new Login(window.Shogi_frame);
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
		Shogi_frame = new JFrame();//創立主畫面
		Shogi_frame.setResizable(false);//調整窗口大小(停用)
		Shogi_frame.setEnabled(false);//停用窗口 (直到登入窗口登入成功)
		Shogi_frame.setTitle("\u5C07\u68CB\u5927\u5E2B");//窗口標題
		Shogi_frame.setBounds(100, 100, 1046, 819);//畫面大小
		Shogi_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//畫面大小
		Shogi_frame.getContentPane().setLayout(null);//獲取內容窗格 &　設置佈局
		Shogi_frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));//應用程式縮圖
		
		Create_Chessboard();
		Create_BreakInboard();
		

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(822, 510, 198, 240);
		Shogi_frame.getContentPane().add(panel_2);
		
		After = new JLabel("\u25B2\u5148\u624B\u2500\u2500\u2500\u2500\u2500\u2500");
		After.setFont(new Font("標楷體", Font.BOLD, 20));
		After.setHorizontalAlignment(SwingConstants.LEFT);
		After.setBounds(822, 471, 198, 29);
		Shogi_frame.getContentPane().add(After);
		After.setForeground(Color.red);
		
		First = new JLabel("\u2500\u2500\u2500\u2500\u2500\u2500\u5F8C\u624B\u25BD");
		First.setHorizontalAlignment(SwingConstants.RIGHT);
		First.setFont(new Font("標楷體", Font.BOLD, 20));
		First.setBounds(10, 280, 198, 29);
		Shogi_frame.getContentPane().add(First);
		
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
	
	
	
	/**
	 * 布置9x9棋盤格
	 */
	private void Create_Chessboard(){
		//初始化9x9的棋盤格
		for(int j=0;j<9;j++){	for(int i=0;i<9;i++){
			CheckerGrid[j][i] = new JButton();//新建按鈕
			CheckerGrid[j][i].setFocusPainted(false);//去除聚焦線
			CheckerGrid[j][i].setVerticalTextPosition(JButton.CENTER);//設置垂直文本 置中
			CheckerGrid[j][i].setHorizontalTextPosition(JButton.CENTER);//設置水平文本 置中
			CheckerGrid[j][i].setForeground(Color.red);//文本顏色 紅色
			CheckerGrid[j][i].addActionListener(CheckerB[j][i]= new CheckerGrid_Data( (j*10)+i ){//按鈕動作監聽器
				public void actionPerformed(ActionEvent e) {
	/*該格含有棋子*/if(getChess()!=null )
	/*未選取棋子*/		if(choose == null ) {
							if( Pick_up_chess(this) ) 			//拿起棋子
							choose.CanMove(CheckerGrid ,CheckerB); 		//顯示可以走的地方
						}
	/*已選取棋子*/		else {
							if(Pick_back_chess(this)); 		//判斷 + 棋子放回原位
							else Eat_EnemyChess(this); 		//判斷 + 吃掉敵方棋子
						}
					
	/*該格不含有棋*/else 
	/*已選取棋子*/		if(choose != null ) Move_chess(this);	//判斷 + 移動棋子
					
			}	});
			CheckerGrid[j][i].setBounds(746-(66*i), 30+(80*j), 66, 80);//按鈕位置&大小
			Shogi_frame.getContentPane().add(CheckerGrid[j][i]);//於畫面上顯示按鈕
		}	}
		//初始化玩家棋子
		initialize_PlayerChess();
		//初始化棋子擺放
		initialize_CheckerChess();
	}
	
	/**
	 * 布置3x3棋盤格
	 */
	private void Create_BreakInboard(){
		String Chess[]= {null,"Pawn",null,"Bishop","Lance","Rook","Gold general","Knight","Silver general",
				 "Silver general","Knight","Gold general","Rook","Lance","Bishop",null,"Pawn",null};
		//初始化3x3的棋盤格
		for(int P=0;P<2;P++) {for(int j=0;j<3;j++){for(int i=0;i<3;i++){
			BreakInGrid[P][j][i] = new JButton();//新建按鈕
			BreakInGrid[P][j][i].setFocusPainted(false);//去除聚焦線
			BreakInGrid[P][j][i].setVerticalTextPosition(JButton.CENTER);//設置垂直文本 置底
			BreakInGrid[P][j][i].setHorizontalTextPosition(JButton.CENTER);//設置水平文本 置中
			BreakInGrid[P][j][i].setForeground(Color.BLUE);//文本顏色 紅色
			BreakInGrid[P][j][i].setEnabled(false);//禁止使用
			BreakInGrid[P][j][i].addActionListener(BreakInB[P][j][i]= new BreakIn_Data(Chess[(9*P)+(j*3)+i] ,P , (j*10)+i){//按鈕動作監聽器
				public void actionPerformed(ActionEvent e) {
					if(choose == null){
						if( Pick_up_chess(this) )//拿起棋子
						choose.CanMove_BreakIn (CheckerGrid ,CheckerB ,Now_Player!=0?PlayerChess[4]:PlayerChess[24]);//顯示可以走的地方
					}	
					else Pick_back_chess(this);//判斷 + 棋子放回原位
					}
				});
			BreakInGrid[P][j][i].setBounds(822+(66*i)-(812*P), 510+(80*j)-(480*P), 66, 80);//按鈕位置&大小
			Shogi_frame.getContentPane().add(BreakInGrid[P][j][i]);//於畫面上顯示按鈕
		}	}	}
		//初始化打入盤擺放
		initialize_BreakInGrid();
	}
	
	// 初始化玩家棋子
	private void initialize_PlayerChess()
	{
		String Chess[]= {"After_king","Rook","Bishop","Gold general","Silver general","Knight","Lance","Pawn","First_king"};
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
				CheckerB[6-(j*4)][i].setChess(PlayerChess[11+(j*20)+i]);
				PlayerChess[11+(j*20)+i].setPosition(CheckerB[6-(j*4)][i].getGrid_Id());
			}
			for(int i=0;i<2;i++){
				Grid_SetIcon(CheckerGrid[7-(j*6)][1+(i*6)],"Picture\\"+ PlayerChess[9+(j*20)+i].getChess_Class() + direction[PlayerChess[9+(j*20)+i].getChessPlayer()] +".png");
				CheckerB[7-(j*6)][1+(i*6)].setChess(PlayerChess[9+(j*20)+i]);
				PlayerChess[9+(j*20)+i].setPosition(CheckerB[7-(j*6)][(1+(i*6))].getGrid_Id());
			}
			for(int i=0;i<9;i++){
				Grid_SetIcon(CheckerGrid[8-(j*8)][i],"Picture\\"+ PlayerChess[0+(j*20)+i].getChess_Class() + direction[PlayerChess[0+(j*20)+i].getChessPlayer()] +".png");
				CheckerB[8-(j*8)][i].setChess(PlayerChess[0+(j*20)+i]);
				PlayerChess[0+(j*20)+i].setPosition(CheckerB[8-(j*8)][i].getGrid_Id());
	}	}	}
	
	// 初始化打入盤擺放
	private void initialize_BreakInGrid() {
		String direction[]= {" Up"," Down"};
	//------------------------------Function------------------------------//
		for(int P=0;P<2;P++){	for(int j=0;j<3;j++){	for(int i=0;i<3;i++){//匯入圖片
			Grid_SetIcon(BreakInGrid[P][j][i] ,"Picture\\"+ BreakInB[P][j][i].getGrid_Class() + direction[BreakInB[P][j][i].getPlayer()] +".png");
	}	}	}	}
	

	// 拿起棋子
	private boolean Pick_up_chess(CheckerGrid_Data Grid){//主棋盤
		Chess Chess = Grid.getChess();
		int ChessPlayer = Chess.getChessPlayer();
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(ChessPlayer == Now_Player){//判斷選取的棋子 是不是 自己的
			Grid_MarkUp(ThisGrid);
			choose = Chess;//選取變數(choose) 指向 -> 被選取的棋子
			return true;//回傳選取成功
		}
		return false;//回傳選取失敗
	}
	private boolean Pick_up_chess(BreakIn_Data Grid){//打入盤
		Chess Chess = Grid.reduceChess();
		int ChessPlayer = Grid.getPlayer();
		JButton ThisGrid = BreakInGrid[ChessPlayer][Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(ChessPlayer == Now_Player){//判斷選取的棋子 是不是 自己的
			Grid_MarkUp(ThisGrid);
			choose = Chess;//選取變數(choose) 指向 -> 被選取的棋子
			return true;//回傳選取成功
		}
		Grid.addChess(Chess);//放回打入棋盤
		return false;//回傳選取失敗
	}
	
	// 單純移動棋子
	private void Move_chess(CheckerGrid_Data Grid){
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if( ThisGrid.getText() == "●" && choose.Live() ){//判斷欲移動的地方 是否 已被標記為可行走
			choose.EraseCanMove(CheckerGrid ,CheckerB );//擦掉 剛才顯示可以走的地方
			RiseChange_Chess();	//升變棋子
			Pick_down_chess(Grid); //放下棋子
			SwitchPlayer(); //切換玩家
		}
		else if( ThisGrid.getText() == "●" && !choose.Live() ){//判斷欲移動的地方 是否 已被標記為可行走
			choose.EraseCanMove_BreakIn (CheckerGrid ,CheckerB);//擦掉 剛才顯示可以走的地方
			choose.Islive();//復活
			
			for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
				int ChessPlayer = choose.getChessPlayer();
				JButton Grid1 = BreakInGrid[ChessPlayer][j][i];
				BreakIn_Data GridData = BreakInB[ChessPlayer][j][i];
				
				if(GridData.getGrid_Class()==choose.getChess_Class()) { //找到相同類型的打入盤格
					Grid_MarkErase(Grid1);
					BreakInGrid_TryEnable(Grid1, GridData);
			}	}	}

			Pick_down_chess(Grid); //放下棋子
			SwitchPlayer(); //切換玩家
		}
	}
	
	// 棋子放回原位
	private boolean Pick_back_chess(CheckerGrid_Data Grid){//主棋盤
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(choose.getPosition() == Grid.getGrid_Id()){//判斷欲放回的地方 是否 為原起點
			choose.EraseCanMove(CheckerGrid ,CheckerB );//擦掉 剛才顯示可以走的地方
			Grid_MarkErase(ThisGrid);//將原先標記的被選取點 標記擦除
			choose = null;//選取變數(choose) 清除指向 ->null
			return true;//回傳 動作成功
		} 
		return false;//回傳 動作失敗
	}
	private void Pick_back_chess(BreakIn_Data Grid){//打入盤
		JButton ThisGrid = BreakInGrid[choose.getChessPlayer()][Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(Grid.getGrid_Class() == choose.getChess_Class() && !choose.Live()) {
			choose.EraseCanMove_BreakIn (CheckerGrid ,CheckerB);//擦掉 剛才顯示可以走的地方
			Grid_MarkErase(ThisGrid);//將原先標記的被選取點 標記擦除
			Grid.addChess(choose);
			choose = null;
		}
	}
	
	
	// 吃掉敵方棋子
	private boolean Eat_EnemyChess(CheckerGrid_Data Grid){
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
		int Choose_Player = choose.getChessPlayer();
		Chess ThisGrid_Chess = CheckerB[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10].getChess();//設變數(ThisGrid_Chess) 為該棋格上之棋子
	//------------------------------Function------------------------------//
		//判斷該棋是否為敵方棋子 && 判斷欲移動的地方 是否 已被標記為可行走
		if( Choose_Player != ThisGrid_Chess.getChessPlayer()	&& 	ThisGrid.getText() == "●" ){
			
			choose.EraseCanMove(CheckerGrid ,CheckerB );//擦掉 剛才顯示可以走的地方
			ThisGrid_Chess.setChessPlayer(Choose_Player);//該棋擁有者 改為吃掉者
			ThisGrid_Chess.setPosition(0);//坐標系清除
			ThisGrid_Chess.Notlive();//調整為死亡
			ThisGrid_Chess.DeclineChange_Chess();//判斷有無升變 並降變
			
			InputBreakInGrid(ThisGrid_Chess);//將死棋放入 吃掉者之打入盤
			
			RiseChange_Chess();//嘗試升變自己
			Pick_down_chess(Grid);//放下棋子
			SwitchPlayer();//切換玩家
			
			return true;//回傳 動作成功
		} 
		return false;//回傳 動作失敗
	}
	
	// 放下棋子
	private void Pick_down_chess(CheckerGrid_Data Grid){
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
		CheckerGrid_Data ThisGrid_Data = CheckerB[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
		
		JButton ChooseGrid = CheckerGrid[choose.getPosition()/10][choose.getPosition()%10];
		CheckerGrid_Data ChooseGrid_Data = CheckerB[choose.getPosition()/10][choose.getPosition()%10];

		String direction[]= {" Up"," Down"};
	//------------------------------Function------------------------------//
		Grid_EraseMark(ChooseGrid);
		ChooseGrid_Data.setChess(null);//將原先的棋格清空
		Grid_SetIcon(ChooseGrid,"");//將原先的棋格圖示清空
		
		Grid_SetIcon(ThisGrid,"Picture\\"+ choose.getChess_Class() + direction[choose.getChessPlayer()] +".png");//在新的棋格顯示圖示
		ThisGrid_Data.setChess(choose);//在新的棋格寫入新的 選取變數(choose) 棋子
		
		choose.setPosition(Grid.getGrid_Id());//改變棋子自身計入的座標
		choose = null;//選取變數(choose) 清除指向 ->null
	}
	
	// 升變棋子
	private void RiseChange_Chess(){
		int Choose_Y = choose.getPosition()/10;
		int Choose_Player = choose.getChessPlayer();
	//------------------------------Function------------------------------//
		if(Choose_Player==0 && Choose_Y<=2 && Choose_Y>=0) choose.RiseChange_Chess();
		if(Choose_Player==1 && Choose_Y<=8 && Choose_Y>=6) choose.RiseChange_Chess();
	}
	
	// 切換玩家
	private void SwitchPlayer(){
		Now_Player = Now_Player==1?0:1;
		First.setForeground(Color.red);
		if(Now_Player==1){
			After.setForeground(Color.black);
			First.setForeground(Color.red);
		}else {
			After.setForeground(Color.red);
			First.setForeground(Color.black);
		}
	}
	
	// 放至打入盤
	private void InputBreakInGrid(Chess Chess){
		int ChessPlayer = Chess.getChessPlayer();
	//------------------------------Function------------------------------//
		for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
			JButton Grid = BreakInGrid[ChessPlayer][j][i];
			BreakIn_Data GridData = BreakInB[ChessPlayer][j][i];
			
			if(GridData.getGrid_Class()==Chess.getChess_Class()) {//找到相同類型的打入盤格
				GridData.addChess(Chess); //將死棋放入打入盤格堆疊中
				BreakInGrid_TryEnable(Grid, GridData);
	}	}	}	}
	
	
	private void Grid_MarkUp(JButton Grid){//標記格子
		Grid.setBackground(java.awt.Color.red);//被選取點 標記
	}
	private void Grid_EraseMark(JButton Grid){//擦除標記
		Grid.setBackground(java.awt.Color.white);//將原先標記的被選取點 標記擦除
	}
	private void BreakInGrid_TryEnable(JButton Grid , BreakIn_Data GridData) {//依擁有棋子數 啟用打入盤格
		if(GridData.getChess_quantity()!=0) //如果該打入盤格有>1的旗子
			Grid.setEnabled(true); //使按鈕可用
		else
			Grid.setEnabled(false); //使按鈕停用
	}
	private void Grid_MarkErase(JButton Grid){//擦除標記
		Grid.setBackground(java.awt.Color.white);//被選取點 標記擦除
	}
	private void Grid_SetIcon(JButton Grid ,String link) {//設置棋格圖片
		ImageIcon Icon = new ImageIcon(link);
		Grid.setIcon(Icon);
	}
}