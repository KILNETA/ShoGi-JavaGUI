package shogi;

import java.awt.event.ActionListener;
import java.util.Stack;

public abstract class BreakIn_Data implements ActionListener {
	
	private int Grid_Id = 0 ;
	private String Grid_ChessName = null; //格子的格子屬性
	private int GridPlayer; //所屬玩家
	private Stack<Chess> Chessman = new Stack<Chess>() ; //在該格的棋子
	
	//建構者
	public BreakIn_Data(String Grid_ChessName ,int GridPlayer ,int Grid_Id){
		this.Grid_ChessName = Grid_ChessName;
		this.GridPlayer = GridPlayer;
		this.Grid_Id = Grid_Id ;
	}
	
	public int getGrid_Id(){
		return this.Grid_Id;
	}
	
	//得知擁有玩家
	public int getPlayer(){
		return this.GridPlayer;
	}
	
	//給予格子屬性
	public String getGrid_Class(){
		return this.Grid_ChessName;
	}
	
	//拿出 棋子
	public Chess reduceChess(){
		return this.Chessman.pop();
	}
	
	//給予目前含有之棋子數
	public int getChess_quantity(){
		return this.Chessman.size();
	}
		
	//添入 棋子
	public void addChess(Chess choose){
		this.Chessman.push(choose);
	}
}
