package shogi;

import javax.swing.JButton;

public class Chess {
	
	private int Position = 0;
	private String Chess = null; //棋子種類
	private int ChessPlayer; //所屬玩家
	private boolean Live = true; //存活
	
	//建構者
	public Chess(String Chess,int ChessPlayer){
		this.Chess = Chess;
		this.ChessPlayer = ChessPlayer;
	}
	
	//得知位置
	public int getPosition(){
		return this.Position;
	}
	//改變位置
	public void setPosition(int Position){
		this.Position = Position;
	}
	
	//復活
	public void Islive(){
		this.Live = true;
	}
	//死亡
	public void Notlive(){
		this.Live = false;
	}
	public boolean Live(){
		return this.Live;
	}
	
	//得知擁有玩家
	public int getChessPlayer(){
		return this.ChessPlayer;
	}
	//改變擁有玩家
	public void setChessPlayer(int Player){
		this.ChessPlayer = Player;
	}
	
	//升變
	public void RiseChange_Chess(){
		switch(this.Chess){
		case "Rook"			  : this.Chess = "Promoted rook" ;  break;
		case "Bishop"		  : this.Chess = "Promoted bishop" ;break; 
		case "Silver general" : this.Chess = "Promoted silver" ;break;
		case "Knight"		  : this.Chess = "Promoted knight" ;break;
		case "Lance"		  : this.Chess = "Promoted lance" ; break;
		case "Pawn"			  : this.Chess = "Promoted pawn" ; 	break;
		}
	}
	
	//降變
	public void DeclineChange_Chess(){
		switch(this.Chess){
		case "Promoted rook"  : this.Chess = "Rook" ; 			break;
		case "Promoted bishop": this.Chess = "Bishop" ; 		break;
		case "Promoted silver": this.Chess = "Silver general" ; break;
		case "Promoted knight": this.Chess = "Knight" ; 		break;
		case "Promoted lance" : this.Chess = "Lance" ; 			break;
		case "Promoted pawn"  : this.Chess = "Pawn" ; 			break;
		}
	}
	
	//得知棋子種類
	public String getChess_Class(){
		return this.Chess;
	}
	
	//顯示可以走的地方
	public void CanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][]){
		switch(this.Chess){
		case "Pawn"			  : Pawn.CanMove(Grid ,Grid_Data ,this) ; 				break;
		case "Lance"		  : Lance.CanMove(Grid ,Grid_Data ,this) ; 				break;
		case "Knight"		  : Knight.CanMove(Grid ,Grid_Data ,this) ;				break;
		case "Silver general" : Silver_general.CanMove(Grid ,Grid_Data ,this) ;		break;
		case "Gold general"	  : Gold_general.CanMove(Grid ,Grid_Data ,this) ;		break;
		
		case "Bishop"		  : Bishop.CanMove(Grid ,Grid_Data ,this) ;				break;
		case "Rook"			  : Rook.CanMove(Grid ,Grid_Data ,this) ;				break;
		
		case "After_king"	  : King.CanMove(Grid ,Grid_Data ,this);				break;
		case "First_king"	  : King.CanMove(Grid ,Grid_Data ,this);				break;
		
		case"Promoted silver" : case"Promoted knight" : case"Promoted lance" : case"Promoted pawn" :
								Gold_general.CanMove(Grid ,Grid_Data ,this); 		break;
		case "Promoted bishop": King.CanMove(Grid ,Grid_Data ,this);
								Bishop.CanMove(Grid ,Grid_Data ,this) ;				break;
		case "Promoted rook"  : King.CanMove(Grid ,Grid_Data ,this); 			
								Rook.CanMove(Grid ,Grid_Data ,this) ;				break;
		}
	}
	
	//擦除已顯示可以走的地方
	public void EraseCanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][]){
		switch(this.Chess){
		case "Pawn"			  : Pawn.ResetCanMove(Grid ,Grid_Data ,this) ; 			break;
		case "Lance"		  : Lance.ResetCanMove(Grid ,Grid_Data ,this) ; 		break;
		case "Knight"		  : Knight.ResetCanMove(Grid ,Grid_Data ,this) ;		break;
		case "Silver general" : Silver_general.ResetCanMove(Grid ,Grid_Data ,this);	break;
		case "Gold general"	  : Gold_general.ResetCanMove(Grid ,Grid_Data ,this);	break;
		
		case "Bishop"		  : Bishop.ResetCanMove(Grid ,Grid_Data ,this) ;		break;
		case "Rook"			  : Rook.ResetCanMove(Grid ,Grid_Data ,this) ;			break;
		
		case "After_king"	  : King.ResetCanMove(Grid ,Grid_Data ,this);			break;
		case "First_king"	  : King.ResetCanMove(Grid ,Grid_Data ,this);			break;
		
		case"Promoted silver" : case"Promoted knight" : case"Promoted lance" : case"Promoted pawn" :
								Gold_general.ResetCanMove(Grid ,Grid_Data ,this); 	break;
		case "Promoted bishop": King.ResetCanMove(Grid ,Grid_Data ,this);
								Bishop.ResetCanMove(Grid ,Grid_Data ,this) ;		break;
		case "Promoted rook"  : King.ResetCanMove(Grid ,Grid_Data ,this); 			
								Rook.ResetCanMove(Grid ,Grid_Data ,this) ;			break;
		}
	}
	
	//顯示可以走的地方	//打入
	public void CanMove_BreakIn (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess EnemyKing){
		for(int j=0;j<9;j++) {for(int i=0;i<9;i++) {
			if(Grid_Data[j][i].getChess()==null && Grid[j][i].getText()=="") 
				Grid[j][i].setText("●");
		}	}
		switch(this.Chess){
		case "Pawn"			  : Pawn.ResetCanMove(Grid ,Grid_Data ,EnemyKing) ; 		break;
		case "Lance"		  : Lance.ResetCanMove(Grid ,Grid_Data ,EnemyKing) ; 		break;
		case "Knight"		  : Knight.ResetCanMove(Grid ,Grid_Data ,EnemyKing) ;		break;
		case "Silver general" : Silver_general.ResetCanMove(Grid ,Grid_Data ,EnemyKing);break;
		case "Gold general"	  : Gold_general.ResetCanMove(Grid ,Grid_Data ,EnemyKing);	break;
			
		case "Bishop"		  : Bishop.ResetCanMove(Grid ,Grid_Data ,EnemyKing) ;		break;
		case "Rook"			  : Rook.ResetCanMove(Grid ,Grid_Data ,EnemyKing) ;			break;
		}
	}
	
	//擦除已顯示可以走的地方 //打入
	public void EraseCanMove_BreakIn (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][]){
		for(int j=0;j<9;j++) {for(int i=0;i<9;i++) {
			if(Grid[j][i].getText()=="●") Grid[j][i].setText("");
	}	}	}
	
	
	
	
}

//移動
class Move{
	private static int playerN[]= {-1,1};
	private static int Position;
	
	public static boolean Set_MovingPoint (String T ,JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess ,int MoveY,int MoveX) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int Direction = playerN[player];
		int PositionY = Position/10+(Direction*MoveY);
		int PositionX = Position%10+MoveX;
		
		//ActionListener
		if(	Grid_Data[PositionY][PositionX].getChess() == null
		||	Grid_Data[PositionY][PositionX].getChess().getChessPlayer()!=player) {
			Grid[PositionY][PositionX].setText(T);
			
			if(	Grid_Data[PositionY][PositionX].getChess() != null
			&&	Grid_Data[PositionY][PositionX].getChess().getChessPlayer()!=player)
				return false; 
		}else	return false;
		 		return true ;
	}
}

class Pawn{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,1,0);
	}
	public static void ResetCanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,1,0);
	}
}

class Lance{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;;i++)
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,i,0)) break;}
		else	break;
	}
	public static void ResetCanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;;i++)
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,i,0)) break;}
		else	break;
	}
}

class Knight{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<8 && PositionX>-1)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,2,1);
		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<9 && PositionX>0)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,2,-1);
	}
	public static void ResetCanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<8 && PositionX>-1)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,2,1);
		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<9 && PositionX>0)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,2,-1);
	}
}

class Silver_general{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,1,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2!= PositionX)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,-1,i-2);
	}	}
	public static void ResetCanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,1,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2!= PositionX)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,-1,i-2);
	}	}
}

class Gold_general{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 ==PositionX)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,-1,i-2);
	}	}
	public static void ResetCanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 ==PositionX)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,-1,i-2);
	}	}
}

class King{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 )
			Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,-1,i-2);
	}	}
	public static void ResetCanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 )
			Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,-1,i-2);
	}	}
}

class Rook{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		//ActionListener
		for(int i=1;;i++) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=1;;i++) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,0,i)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,0,i)) break;}
		else	break;}
	}
	public static void ResetCanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;;i++) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=1;;i++) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,0,i)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,0,i)) break;}
		else	break;}
	}
}

class Bishop{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		//ActionListener
		for(int j=1,i=1;;j++,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=1;;j--,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=1,i=-1;;j++,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=-1;;j--,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,Grid_Data ,TChess ,j,i)) break;}
		else	break;}
	}
	public static void ResetCanMove (JButton Grid[][] ,CheckerGrid_Data Grid_Data[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int j=1,i=1;;j++,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=1;;j--,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=1,i=-1;;j++,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=-1;;j--,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,Grid_Data ,TChess ,j,i)) break;}
		else	break;}
	}
}


