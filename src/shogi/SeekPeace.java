package shogi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class SeekPeace extends JButton {
	
	
/*-------------------------Variable-------------------------*/
	
	
	private Shogi Main_frame;
	private int GridPlayer; //所屬玩家
	private boolean Surrender = false; //所屬玩家


/*-------------------------Function-------------------------*/
	
	//建構者
	public SeekPeace(Shogi Main_frame,int GridPlayer){
		this.Main_frame = Main_frame;
		this.GridPlayer = GridPlayer;
		
		//初始化3x3的棋盤格
		new JButton();//新建按鈕
		setText("求和");
		setForeground(Color.RED);
		setBackground(Color.RED);
		setFont(new Font("標楷體", Font.BOLD, 14));
		setBounds(955-(945*GridPlayer), 440-(125*GridPlayer), 65, 25);
		setFocusPainted(false);//去除聚焦線
		addActionListener(new ActionListener(){//按鈕動作監聽器
			public void actionPerformed(ActionEvent e) {
				set_PressSurrender();
				PeaceRound();
			}	});
	}
	
	private void set_PressSurrender() {
		Surrender = Surrender ? false: true;
		SeekPeace_setColor();
		
		if(haveOnePeace()) {
			UnEnabled_allCheckers();
			haveChoose();
		}else{
			Enabled_allCheckers();
		}
	}
	
	private void haveChoose() {
		if(Shogi.choose!=null) {
			if(Shogi.choose.Live())
				PickBack_chess_Checker();
			else if(!Shogi.choose.Live())
				PickBack_chess_BreakIn();
		}
	}
	//設置所有棋盤 (不可用)
	private void UnEnabled_allCheckers(){
		for(int p=0;p<2;p++) {for(int y=0;y<3;y++) {for(int x=0;x<3;x++) {
			Shogi.BreakInGrid[p][y][x].setEnabled(false);
		}	}	}
		for(int y=0;y<9;y++) {for(int x=0;x<9;x++) {
			Shogi.CheckerGrid[y][x].setEnabled(false);
	}	}	}
	
	//設置所有棋盤 (可用)
	private void Enabled_allCheckers(){
		for(int p=0;p<2;p++) {for(int y=0;y<3;y++) {for(int x=0;x<3;x++) {
			BreakInGrid_TryEnable(Shogi.BreakInGrid[p][y][x]);
		}	}	}
		for(int y=0;y<9;y++) {for(int x=0;x<9;x++) {
			Shogi.CheckerGrid[y][x].setEnabled(true);
	}	}	}
	
	// 放至打入盤
	private void InputBreakInGrid(Chess Chess){
		int ChessPlayer = Chess.getChessPlayer();
	//------------------------------Function------------------------------//
		for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
			BreakIn BreakIn = Shogi.BreakInGrid[ChessPlayer][j][i];
							
			if(BreakIn.getGrid_Class()==Chess.getChess_Class()) {//找到相同類型的打入盤格
				BreakIn.addChess(Chess); //將死棋放入打入盤格堆疊中
				BreakInGrid_TryEnable(BreakIn);
				Shogi.choose.EraseCanMove_BreakIn (Shogi.CheckerGrid );//擦掉 剛才顯示可以走的地方
				BreakIn.setBackground(Color.white);//被選取點 標記擦除;//將原先標記的被選取點 標記擦除
				Shogi.choose = null;
				break;
	}	}	}	}
	
	//依擁有棋子數 啟用打入盤格
	private void BreakInGrid_TryEnable(BreakIn BreakIn) {
		if(BreakIn.getChess_quantity()!=0) //如果該打入盤格有>1的旗子
			BreakIn.setEnabled(true); //使按鈕可用
		else
			BreakIn.setEnabled(false); //使按鈕停用
	}
	
	// 棋子放回原位 //打入盤
	private void PickBack_chess_BreakIn(){
		InputBreakInGrid(Shogi.choose);
	}
	
	// 棋子放回原位//主棋盤
	private void PickBack_chess_Checker(){
		Checker ThisGrid = Shogi.CheckerGrid[Shogi.choose.getPosition()/10][Shogi.choose.getPosition()%10];
	//------------------------------Function------------------------------//
		if(Shogi.choose.getPosition() == Shogi.choose.getPosition()){//判斷欲放回的地方 是否 為原起點
			Shogi.choose.EraseCanMove(Shogi.CheckerGrid);//擦掉 剛才顯示可以走的地方
			ThisGrid.setBackground(Color.white);//將原先標記的被選取點 標記擦除
			Button_RiseChess_lose();
			Shogi.choose = null;//選取變數(choose) 清除指向 ->null
	} 	}
	
	// 升變棋子按鈕停用
	private void Button_RiseChess_lose(){
		int Choose_Player = Shogi.choose.getChessPlayer();
	//------------------------------Function------------------------------//
		Shogi.RiseChange[Choose_Player].RiseCntUse();
		Shogi.RiseChange[Choose_Player].NoUseRise();
	}
	
	//有其中一個人求和
	private boolean haveOnePeace() {
		boolean After_Surrender = Shogi.SeekPeace[0].Surrender();
		boolean First_Surrender = Shogi.SeekPeace[1].Surrender();
		
		if(After_Surrender || First_Surrender)
			return true;
		else
			return false;
	}
	
	//此局和局 呼叫結束介面
	private void PeaceRound() {
		boolean After_Surrender = Shogi.SeekPeace[0].Surrender();
		boolean First_Surrender = Shogi.SeekPeace[1].Surrender();
		
		if(After_Surrender && First_Surrender){
			Main_frame.Create_GameOver(2);
		}
	}
	
	//回傳 是否要求求和
	private boolean Surrender() {
		return Surrender;
	}
	
	//設置選擇顏色改變
	private void SeekPeace_setColor() {
		
		if(!Surrender) {
			setForeground(Color.RED);
			setBackground(Color.RED);
		}else {
			setForeground(Color.BLUE);
			setBackground(Color.BLUE);
		}
	}
	
	//重置按鈕
	public void Reset(){
		setForeground(Color.RED);
		setBackground(Color.RED);
		Surrender = false;
		Enabled_allCheckers();
	}
	
}