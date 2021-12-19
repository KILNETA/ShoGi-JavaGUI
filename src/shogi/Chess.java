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
	
	public int getPosition(){
		return this.Position;
	}
	public void setPosition(int Position){
		this.Position = Position;
	}
	
	public void Islive(){
		this.Live = true;
	}
	public void Notlive(){
		this.Live = false;
	}
	
	public int getChessPlayer(){
		return this.ChessPlayer;
	}
	public void setChessPlayer(int Player){
		this.ChessPlayer = Player;
	}
	
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
	
	public String getChess_Class(){
		return this.Chess;
	}
	
	public void CanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][]){
		switch(this.Chess){
		case "Pawn"			  : Pawn.CanMove(CheckerGrid ,CheckerB ,this) ; 			break;
		case "Lance"		  : Lance.CanMove(CheckerGrid ,CheckerB ,this) ; 			break;
		case "Knight"		  : Knight.CanMove(CheckerGrid ,CheckerB ,this) ;			break;
		case "Silver general" : Silver_general.CanMove(CheckerGrid ,CheckerB ,this) ;	break;
		case "Gold general"	  : Gold_general.CanMove(CheckerGrid ,CheckerB ,this) ;		break;
		
		case "Bishop"		  : Bishop.CanMove(CheckerGrid ,CheckerB ,this) ;			break;
		case "Rook"			  : Rook.CanMove(CheckerGrid ,CheckerB ,this) ;				break;
		
		case "After_king"	  : King.CanMove(CheckerGrid ,CheckerB ,this);				break;
		case "First_king"	  : King.CanMove(CheckerGrid ,CheckerB ,this);				break;
		
		case"Promoted silver" : case"Promoted knight" : case"Promoted lance" : case"Promoted pawn" :
								Gold_general.CanMove(CheckerGrid ,CheckerB ,this); 		break;
		case "Promoted bishop": King.CanMove(CheckerGrid ,CheckerB ,this);
								Bishop.CanMove(CheckerGrid ,CheckerB ,this) ;			break;
		case "Promoted rook"  : King.CanMove(CheckerGrid ,CheckerB ,this); 			
								Rook.CanMove(CheckerGrid ,CheckerB ,this) ;				break;
		}
	}
	
	public void ResetCanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][]){
		switch(this.Chess){
		case "Pawn"			  : Pawn.ResetCanMove(CheckerGrid ,CheckerB ,this) ; 			break;
		case "Lance"		  : Lance.ResetCanMove(CheckerGrid ,CheckerB ,this) ; 			break;
		case "Knight"		  : Knight.ResetCanMove(CheckerGrid ,CheckerB ,this) ;			break;
		case "Silver general" : Silver_general.ResetCanMove(CheckerGrid ,CheckerB ,this);	break;
		case "Gold general"	  : Gold_general.ResetCanMove(CheckerGrid ,CheckerB ,this);		break;
		
		case "Bishop"		  : Bishop.ResetCanMove(CheckerGrid ,CheckerB ,this) ;			break;
		case "Rook"			  : Rook.ResetCanMove(CheckerGrid ,CheckerB ,this) ;			break;
		
		case "After_king"	  : King.ResetCanMove(CheckerGrid ,CheckerB ,this);				break;
		case "First_king"	  : King.ResetCanMove(CheckerGrid ,CheckerB ,this);				break;
		
		case"Promoted silver" : case"Promoted knight" : case"Promoted lance" : case"Promoted pawn" :
								Gold_general.ResetCanMove(CheckerGrid ,CheckerB ,this); 	break;
		case "Promoted bishop": King.ResetCanMove(CheckerGrid ,CheckerB ,this);
								Bishop.ResetCanMove(CheckerGrid ,CheckerB ,this) ;			break;
		case "Promoted rook"  : King.ResetCanMove(CheckerGrid ,CheckerB ,this); 			
								Rook.ResetCanMove(CheckerGrid ,CheckerB ,this) ;			break;
		}
	}
}

class Move{
	private static int playerN[]= {-1,1};
	private static int Position;
	
	public static boolean Show_MovingPoint (String T ,JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess ,int MoveY,int MoveX) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int Direction = playerN[player];
		int PositionY = Position/10-1+(Direction*MoveY);
		int PositionX = Position%10-1+MoveX;
		
		//ActionListener
		if(	CheckerB[PositionY][PositionX].getChess() == null
		||	CheckerB[PositionY][PositionX].getChess().getChessPlayer()!=player) {
			CheckerGrid[PositionY][PositionX].setText(T);
			
			if(	CheckerB[PositionY][PositionX].getChess() != null
			&&	CheckerB[PositionY][PositionX].getChess().getChessPlayer()!=player)
				return false; 
		}else	return false;
		 		return true ;
	}
}

class Pawn{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;

		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,1,0);
	}
	public static void ResetCanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,1,0);
	}
}

class Lance{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		//ActionListener
		for(int i=1;;i++)
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,i,0)) break;}
		else	break;
	}
	public static void ResetCanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		for(int i=1;;i++)
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,i,0)) break;}
		else	break;
	}
}

class Knight{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;

		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<8 && PositionX>-1)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,2,1);
		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<9 && PositionX>0)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,2,-1);
	}
	public static void ResetCanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<8 && PositionX>-1)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,2,1);
		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<9 && PositionX>0)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,2,-1);
	}
}

class Silver_general{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;

		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,1,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2!= PositionX)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,-1,i-2);
	}	}
	public static void ResetCanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,1,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2!= PositionX)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,-1,i-2);
	}	}
}

class Gold_general{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;

		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 ==PositionX)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,-1,i-2);
	}	}
	public static void ResetCanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 ==PositionX)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,-1,i-2);
	}	}
}

class King{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;

		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 )
			Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,-1,i-2);
	}	}
	public static void ResetCanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 )
			Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,-1,i-2);
	}	}
}

class Rook{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		//ActionListener
		for(int i=1;;i++) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=1;;i++) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,0,i)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,0,i)) break;}
		else	break;}
	}
	public static void ResetCanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		for(int i=1;;i++) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=1;;i++) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,0,i)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,0,i)) break;}
		else	break;}
	}
}

class Bishop{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		//ActionListener
		for(int j=1,i=1;;j++,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=1;;j--,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=1,i=-1;;j++,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=-1;;j--,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("●",CheckerGrid ,CheckerB ,TChess ,j,i)) break;}
		else	break;}
	}
	public static void ResetCanMove (JButton CheckerGrid[][] ,CheckerGrid_Data CheckerB[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int PositionY = Position/10-1;
		int PositionX = Position%10-1;
		
		for(int j=1,i=1;;j++,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=1;;j--,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=1,i=-1;;j++,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=-1;;j--,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Show_MovingPoint("",CheckerGrid ,CheckerB ,TChess ,j,i)) break;}
		else	break;}
	}
}


