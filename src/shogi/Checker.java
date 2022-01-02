package shogi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.JButton;

public class Checker extends JButton {
	
	
/*-------------------------Variable-------------------------*/
	
	
	private Shogi Main_frame;
	private int Grid_Id = 0; //格子的編號
	private Chess Chessman = null; //在該格的棋子


/*-------------------------Function-------------------------*/
	
	// 建構者
	public Checker(Shogi Main_frame,int i){
		this.Main_frame = Main_frame;
		this.Grid_Id = i;
		
		new JButton();//新建按鈕
		setFocusPainted(false);//去除聚焦線
		setVerticalTextPosition(JButton.CENTER);//設置垂直文本 置中
		setHorizontalTextPosition(JButton.CENTER);//設置水平文本 置中
		setForeground(Color.red);//文本顏色 紅色
		addActionListener(new ActionListener(){//按鈕動作監聽器
			public void actionPerformed(ActionEvent e) {
/*該格含有棋子*/if(getChess()!=null )
/*未選取棋子*/		if(Shogi.choose == null ) {
						if( Pick_up_chess(this) ) { 				//拿起棋子
							Button_RiseChess_Try();					//嘗試啟用升變功能
							Shogi.choose.CanMove(Shogi.CheckerGrid);//顯示可以走的地方
						}
					}
/*已選取棋子*/		else {
						if(Pick_back_chess(this)); 		//判斷 + 棋子放回原位
						else Eat_EnemyChess(this); 		//判斷 + 吃掉敵方棋子
					}
									
/*該格不含有棋*/else 
/*已選取棋子*/		if(Shogi.choose != null ) Move_chess(this);	//判斷 + 移動棋子
		}	});
		setBounds(746-(66*(i%10)), 30+(80*(i/10)), 66, 80);//按鈕位置&大小
	}
	
	// 回傳 格子編號
	public int getGrid_Id(){
		return this.Grid_Id;
	}
	
	// 回傳 棋子
	public Chess getChess(){
		return this.Chessman;
	}
	
	// 改變 棋子
	public void setChess(Chess Chessman){
		this.Chessman = Chessman;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	
	// 拿起棋子
	private boolean Pick_up_chess(ActionListener actionListener){//主棋盤
		Chess Chess = this.getChess();
		int ChessPlayer = Chess.getChessPlayer();
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(ChessPlayer == Shogi.Now_Player){//判斷選取的棋子 是不是 自己的
			Grid_MarkUp(ThisGrid);
			Shogi.choose = Chess;//選取變數(choose) 指向 -> 被選取的棋子
			return true;//回傳選取成功
		}
		return false;//回傳選取失敗
	}
		
	// 單純移動棋子
	private void Move_chess(ActionListener actionListener){
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if( ThisGrid.getText() == "●" && Shogi.choose.Live() ){//判斷欲移動的地方 是否 已被標記為可行走
			Shogi.choose.EraseCanMove(Shogi.CheckerGrid);//擦掉 剛才顯示可以走的地方
			RiseChess_Try();
			Pick_down_chess(actionListener); //放下棋子
			SwitchPlayer(); //切換玩家
		}
		else if( ThisGrid.getText() == "●" && !Shogi.choose.Live() ){//判斷欲移動的地方 是否 已被標記為可行走
			Shogi.choose.EraseCanMove_BreakIn (Shogi.CheckerGrid);//擦掉 剛才顯示可以走的地方
						
			for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
				int ChessPlayer = Shogi.choose.getChessPlayer();
				BreakIn BreakIn = Shogi.BreakInGrid[ChessPlayer][j][i];
							
				if(BreakIn.getGrid_Class()==Shogi.choose.getChess_Class()) { //找到相同類型的打入盤格
					Grid_MarkErase(BreakIn);
					BreakInGrid_TryEnable(BreakIn);
			}	}	}

			Pick_down_chess(actionListener); //放下棋子
			SwitchPlayer(); //切換玩家
		}
	}
		
	// 棋子放回原位
	private boolean Pick_back_chess(ActionListener actionListener){//主棋盤
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(Shogi.choose.getPosition() == this.getGrid_Id()){//判斷欲放回的地方 是否 為原起點
			Shogi.choose.EraseCanMove(Shogi.CheckerGrid);//擦掉 剛才顯示可以走的地方
			Grid_MarkErase(ThisGrid);//將原先標記的被選取點 標記擦除
			Button_RiseChess_lose();
			Shogi.choose = null;//選取變數(choose) 清除指向 ->null
			return true;//回傳 動作成功
		} 
		return false;//回傳 動作失敗
	}
				
	// 吃掉敵方棋子
	private boolean Eat_EnemyChess(ActionListener actionListener){
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
		int Choose_Player = Shogi.choose.getChessPlayer();
		Chess ThisGrid_Chess = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10].getChess();//設變數(ThisGrid_Chess) 為該棋格上之棋子
	//------------------------------Function------------------------------//
		//判斷該棋是否為敵方棋子 && 判斷欲移動的地方 是否 已被標記為可行走
		if( Choose_Player != ThisGrid_Chess.getChessPlayer()	&& 	ThisGrid.getText() == "●" ){
						
			Shogi.choose.EraseCanMove(Shogi.CheckerGrid);//擦掉 剛才顯示可以走的地方
			if_isKingDied(ThisGrid_Chess);//如果是王死了
			ThisGrid_Chess.setChessPlayer(Choose_Player);//該棋擁有者 改為吃掉者
			ThisGrid_Chess.setPosition(0);//坐標系清除
			ThisGrid_Chess.Notlive();//調整為死亡
			ThisGrid_Chess.DeclineChange_Chess();//判斷有無升變 並降變
						
			InputBreakInGrid(ThisGrid_Chess);//將死棋放入 吃掉者之打入盤
			RiseChess_Try();
			Pick_down_chess(actionListener);//放下棋子
			SwitchPlayer();//切換玩家
						
			return true;//回傳 動作成功
		} 
		return false;//回傳 動作失敗
	}
				
	// 放下棋子
	private void Pick_down_chess(ActionListener actionListener){
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
		Checker ChooseGrid = Shogi.CheckerGrid[Shogi.choose.getPosition()/10][Shogi.choose.getPosition()%10];

		String direction[]= {" Up"," Down"};
	//------------------------------Function------------------------------//
		if(Shogi.choose.Live()) {
			Grid_MarkErase(ChooseGrid);
			ChooseGrid.setChess(null);//將原先的棋格清空
			Grid_SetIcon(ChooseGrid,"");//將原先的棋格圖示清空
		}
		else
			Shogi.choose.Islive();//復活
		
		Shogi.choose.setPosition(this.getGrid_Id());//改變棋子自身計入的座標
		EnforceRiseChess();
		
		Grid_SetIcon(ThisGrid,"Picture\\"+ Shogi.choose.getChess_Class() + direction[Shogi.choose.getChessPlayer()] +".png");//在新的棋格顯示圖示
		ThisGrid.setChess(Shogi.choose);//在新的棋格寫入新的 選取變數(choose) 棋子
		
		Shogi.choose = null;//選取變數(choose) 清除指向 ->null
	}
				
	// 升變棋子按鈕啟用
	private void Button_RiseChess_Try(){
		int Choose_Y = Shogi.choose.getPosition()/10;
		int Choose_Player = Shogi.choose.getChessPlayer();
	//------------------------------Function------------------------------//
		if(Shogi.choose.CanRiseChange())
		if(Choose_Player==0 && Choose_Y<=2 && Choose_Y>=0
		|| Choose_Player==1 && Choose_Y<=8 && Choose_Y>=6) 
			Shogi.RiseChange[Choose_Player].RiseCanUse();
	}
	
	// 升變棋子按鈕停用
	private void Button_RiseChess_lose(){
		int Choose_Player = Shogi.choose.getChessPlayer();
	//------------------------------Function------------------------------//
		Shogi.RiseChange[Choose_Player].RiseCntUse();
		Shogi.RiseChange[Choose_Player].NoUseRise();
	}
		
	// 如果已點選升變 則升變
	private void RiseChess_Try(){
		int Choose_Player = Shogi.choose.getChessPlayer();
	//------------------------------Function------------------------------//
		if(Shogi.RiseChange[Choose_Player].UseRise())
			Shogi.choose.RiseChange_Chess();
		Shogi.RiseChange[Choose_Player].RiseCntUse();
		Shogi.RiseChange[Choose_Player].NoUseRise();
	}
	
	// 強制執行升變
	private void EnforceRiseChess(){
		if(Shogi.choose.EnforceRiseChange())
			Shogi.choose.RiseChange_Chess();
	}
				
	// 切換玩家
	private void SwitchPlayer(){
		Shogi.Now_Player = Shogi.Now_Player==0?1:0;
		if(Shogi.Now_Player==0){
			Shogi.After.setForeground(Color.black);
			Shogi.First.setForeground(Color.red);
		}else {
			Shogi.After.setForeground(Color.red);
			Shogi.First.setForeground(Color.black);
		}
	}
				
	// 放至打入盤
	private void InputBreakInGrid(Chess Chess){
		int ChessPlayer = Chess.getChessPlayer();
	//------------------------------Function------------------------------//
		for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
			BreakIn BreakIn = Shogi.BreakInGrid[ChessPlayer][j][i];
						
			if(BreakIn.getGrid_Class()==Chess.getChess_Class()) {//找到相同類型的打入盤格
				BreakIn.addChess(Chess); //將死棋放入打入盤格堆疊中
				BreakInGrid_TryEnable(BreakIn);
	}	}	}	}
				
	// 如果是王死了
	private void if_isKingDied(Chess ThisGrid_Chess) {
		if(ThisGrid_Chess.getChess_Class()=="After_king")
			Main_frame.Create_GameOver(0);
		if(ThisGrid_Chess.getChess_Class()=="First_king")	
			Main_frame.Create_GameOver(1);
	}
				
	private void Grid_MarkUp(Checker Grid){//標記格子
		Grid.setBackground(java.awt.Color.red);//被選取點 標記
	}
	private void Grid_MarkErase(JButton Grid){//擦除標記
		Grid.setBackground(java.awt.Color.white);//被選取點 標記擦除
	}
	private void BreakInGrid_TryEnable(BreakIn BreakIn) {//依擁有棋子數 啟用打入盤格
		if(BreakIn.getChess_quantity()!=0) //如果該打入盤格有>1的旗子
			BreakIn.setEnabled(true); //使按鈕可用
		else
			BreakIn.setEnabled(false); //使按鈕停用
	}
	private void Grid_SetIcon(JButton Grid ,String link) {//設置棋格圖片
		ImageIcon Icon = new ImageIcon(link);
		Grid.setIcon(Icon);
	}
	public void Reset(){ //重置棋格
		Chessman = null;
		Grid_SetIcon(this ,"");
	}



}
