package shogi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;

public class BreakIn  extends JButton {
	
	
/*-------------------------Variable-------------------------*/
	
	
	private int Grid_Id = 0 ;
	private String Grid_ChessName = null; //格子的格子屬性
	private int GridPlayer; //所屬玩家
	private Stack<Chess> Chessman = new Stack<Chess>() ; //在該格的棋子


/*-------------------------Function-------------------------*/
	
	//建構者
	public BreakIn(String Grid_ChessName ,int GridPlayer ,int Grid_Id){
		this.Grid_ChessName = Grid_ChessName;
		this.GridPlayer = GridPlayer;
		this.Grid_Id = Grid_Id ;
		
		//初始化3x3的棋盤格
		new JButton();//新建按鈕
		setFocusPainted(false);//去除聚焦線
		setVerticalTextPosition(JButton.CENTER);//設置垂直文本 置底
		setHorizontalTextPosition(JButton.CENTER);//設置水平文本 置中
		setForeground(Color.BLUE);//文本顏色 紅色
		setEnabled(false);//禁止使用
		addActionListener(new ActionListener(){//按鈕動作監聽器
			public void actionPerformed(ActionEvent e) {
				if(Shogi.choose == null){
					if( Pick_up_chess(this) )//拿起棋子
					Shogi.choose.CanMove_BreakIn (Shogi.CheckerGrid ,Shogi.Now_Player!=0?Shogi.PlayerChess[4]:Shogi.PlayerChess[24]);//顯示可以走的地方
				}	
				else Pick_back_chess(this);//判斷 + 棋子放回原位
				}
			});
		setBounds(822+66*(Grid_Id%10)-(812*GridPlayer), 510+80*(Grid_Id/10)-(480*GridPlayer), 66, 80);//按鈕位置&大小
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
	
	// 拿起棋子
	private boolean Pick_up_chess(ActionListener actionListener){//打入盤
		Chess Chess = this.reduceChess();
		int ChessPlayer = this.getPlayer();
		JButton ThisGrid = Shogi.BreakInGrid[ChessPlayer][this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(ChessPlayer == Shogi.Now_Player){//判斷選取的棋子 是不是 自己的
			Grid_MarkUp(ThisGrid);
			Shogi.choose = Chess;//選取變數(choose) 指向 -> 被選取的棋子
			return true;//回傳選取成功
		}
		this.addChess(Chess);//放回打入棋盤
		return false;//回傳選取失敗
	}
	
	// 棋子放回原位
	private void Pick_back_chess(ActionListener actionListener){//打入盤
		JButton ThisGrid = Shogi.BreakInGrid[Shogi.choose.getChessPlayer()][this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(this.getGrid_Class() == Shogi.choose.getChess_Class() && !Shogi.choose.Live()) {
			Shogi.choose.EraseCanMove_BreakIn (Shogi.CheckerGrid );//擦掉 剛才顯示可以走的地方
			Grid_MarkErase(ThisGrid);//將原先標記的被選取點 標記擦除
			this.addChess(Shogi.choose);
			Shogi.choose = null;
	}	}
	
	
	private void Grid_MarkUp(JButton Grid){//標記格子
		Grid.setBackground(Color.red);//被選取點 標記
	}
	private void Grid_MarkErase(JButton Grid){//擦除標記
		Grid.setBackground(Color.white);//被選取點 標記擦除
	}
	public void Reset(){//重置棋格
		Chessman.clear();
	}
}
