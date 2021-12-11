package shogi;

import java.awt.event.ActionListener;

public abstract class CheckerGrid_Data implements ActionListener {
	
	private int Grid_Id = 0; //格子的編號
	private Chess Chessman = null; //在該格的棋子
	
	//建構者
	public CheckerGrid_Data(int i){
		this.Grid_Id = i;
	}
	
	//回傳 格子編號
	public int getGrid_Id(){
		return this.Grid_Id;
	}
	
	//回傳 棋子
	public Chess getChess(){
		return this.Chessman;
	}
	
	//改變 棋子
	public void setChess(Chess Chessman){
		this.Chessman = Chessman;
	}
}