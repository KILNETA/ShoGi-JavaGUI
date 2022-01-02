package shogi;


public class Chess {
	
	
/*-------------------------Variable-------------------------*/
	
	
	private int Position = 0;
	private String Chess = null; //棋子種類
	private int ChessPlayer; //所屬玩家
	private boolean Live = true; //存活

	
	
/*-------------------------Function-------------------------*/
	
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
	
	//回覆可以升變的棋種
	public boolean CanRiseChange() {
		switch(this.Chess){
		case"Rook": case"Bishop": case"Silver general": case"Knight": case"Lance": case"Pawn":
			return true;
		}
		return false;
	}
	
	//回覆強制升變的棋種
	public boolean EnforceRiseChange() {
		if(this.Chess=="Pawn" && this.ChessPlayer==0 && this.Position/10<1
		|| this.Chess=="Pawn" && this.ChessPlayer==1 && this.Position/10>7) 
			return true;
		
		if(this.Chess=="Lance" && this.ChessPlayer==0 && this.Position/10<1
		|| this.Chess=="Lance" && this.ChessPlayer==1 && this.Position/10>7) 
			return true;
		
		if(this.Chess=="Knight" && this.ChessPlayer==0 && this.Position/10<2
		|| this.Chess=="Knight" && this.ChessPlayer==1 && this.Position/10>6) 
			return true;
		
		
		return false;
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
	public void CanMove (Checker Grid[][] ){
		switch(this.Chess){
		case "Pawn"			  : Pawn.CanMove(Grid ,this) ; 				break;
		case "Lance"		  : Lance.CanMove(Grid ,this) ; 				break;
		case "Knight"		  : Knight.CanMove(Grid ,this) ;				break;
		case "Silver general" : Silver_general.CanMove(Grid ,this) ;		break;
		case "Gold general"	  : Gold_general.CanMove(Grid ,this) ;		break;
		
		case "Bishop"		  : Bishop.CanMove(Grid ,this) ;				break;
		case "Rook"			  : Rook.CanMove(Grid ,this) ;				break;
		
		case "After_king"	  : King.CanMove(Grid ,this);				break;
		case "First_king"	  : King.CanMove(Grid ,this);				break;
		
		case"Promoted silver" : case"Promoted knight" : case"Promoted lance" : case"Promoted pawn" :
								Gold_general.CanMove(Grid ,this); 		break;
		case "Promoted bishop": King.CanMove(Grid ,this);
								Bishop.CanMove(Grid ,this) ;				break;
		case "Promoted rook"  : King.CanMove(Grid ,this); 			
								Rook.CanMove(Grid ,this) ;				break;
		}
	}
	
	//擦除已顯示可以走的地方
	public void EraseCanMove (Checker Grid[][]){
		switch(this.Chess){
		case "Pawn"			  : Pawn.ResetCanMove(Grid ,this) ; 			break;
		case "Lance"		  : Lance.ResetCanMove(Grid ,this) ; 		break;
		case "Knight"		  : Knight.ResetCanMove(Grid ,this) ;		break;
		case "Silver general" : Silver_general.ResetCanMove(Grid ,this);	break;
		case "Gold general"	  : Gold_general.ResetCanMove(Grid ,this);	break;
		
		case "Bishop"		  : Bishop.ResetCanMove(Grid ,this) ;		break;
		case "Rook"			  : Rook.ResetCanMove(Grid ,this) ;			break;
		
		case "After_king"	  : King.ResetCanMove(Grid ,this);			break;
		case "First_king"	  : King.ResetCanMove(Grid ,this);			break;
		
		case"Promoted silver" : case"Promoted knight" : case"Promoted lance" : case"Promoted pawn" :
								Gold_general.ResetCanMove(Grid ,this); 	break;
		case "Promoted bishop": King.ResetCanMove(Grid ,this);
								Bishop.ResetCanMove(Grid ,this) ;		break;
		case "Promoted rook"  : King.ResetCanMove(Grid ,this); 			
								Rook.ResetCanMove(Grid ,this) ;			break;
		}
	}
	
	//顯示可以走的地方	//打入
	public void CanMove_BreakIn (Checker Grid[][] ,Chess EnemyKing){
		for(int j=0;j<9;j++) {for(int i=0;i<9;i++) {
			if(Grid[j][i].getChess()==null && Grid[j][i].getText()=="") 
				Grid[j][i].setText("●");
		}	}
		switch(this.Chess){
		case "Pawn"			  : Pawn.ResetCanMove(Grid ,EnemyKing) ;
								Pawn.BreakInDontMove(Grid ,this) ;
								Pawn.BreakInDontMove_InBottom(Grid ,this) ;	break;
		case "Lance"		  : Lance.ResetCanMove(Grid ,EnemyKing) ;
								Lance.BreakInDontMove_InBottom(Grid ,this) ;break;
		case "Knight"		  : Knight.ResetCanMove(Grid ,EnemyKing) ;
								Knight.BreakInDontMove_InBottom(Grid ,this) ;break;
		case "Silver general" : Silver_general.ResetCanMove(Grid ,EnemyKing);break;
		case "Gold general"	  : Gold_general.ResetCanMove(Grid ,EnemyKing);	break;
			
		case "Bishop"		  : Bishop.ResetCanMove(Grid ,EnemyKing) ;		break;
		case "Rook"			  : Rook.ResetCanMove(Grid ,EnemyKing) ;		break;
		}
	}
	
	//擦除已顯示可以走的地方 //打入
	public void EraseCanMove_BreakIn (Checker Grid[][]){
		for(int j=0;j<9;j++) {for(int i=0;i<9;i++) {
			if(Grid[j][i].getText()=="●") Grid[j][i].setText("");
	}	}	}
	
	
	
	
}

//移動
class Move{
	private static int playerN[]= {-1,1};
	private static int Position;
	
	public static boolean Set_MovingPoint (String T ,Checker Grid[][] ,Chess TChess ,int MoveY,int MoveX) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int Direction = playerN[player];
		int PositionY = Position/10+(Direction*MoveY);
		int PositionX = Position%10+MoveX;
		
		//ActionListener
		if(	Grid[PositionY][PositionX].getChess() == null
		||	Grid[PositionY][PositionX].getChess().getChessPlayer()!=player) {
			Grid[PositionY][PositionX].setText(T);
			
			if(	Grid[PositionY][PositionX].getChess() != null
			&&	Grid[PositionY][PositionX].getChess().getChessPlayer()!=player)
				return false; 
		}else	return false;
		 		return true ;
	}
}

class Pawn{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1)
			Move.Set_MovingPoint("●",Grid ,TChess ,1,0);
	}
	public static void ResetCanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1)
			Move.Set_MovingPoint("",Grid ,TChess ,1,0);
	}
	public static void BreakInDontMove (Checker Grid[][] ,Chess TChess) {
		for(int i=0;i<9;i++) {	for(int j=0;j<9;j++) {
			Chess HChess = Grid[j][i].getChess();
			if(	HChess != null
			&&	HChess.getChessPlayer() == TChess.getChessPlayer()
			&&	HChess.getChess_Class() == TChess.getChess_Class()	) {
				for(int y=0;y<9;y++)
					Grid[y][i].setText("");
				break;
	}	}	}	}
	public static void BreakInDontMove_InBottom (Checker Grid[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int Bottom[] = {0,8};
		for(int x=0;x<9;x++)
			Grid[Bottom[player]][x].setText("");
				
}	}	

class Lance{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;;i++)
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,TChess ,i,0)) break;}
		else	break;
	}
	public static void ResetCanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;;i++)
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("",Grid ,TChess ,i,0)) break;}
		else	break;
	}
	public static void BreakInDontMove_InBottom (Checker Grid[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int Bottom[] = {0,8};
		for(int x=0;x<9;x++)
			Grid[Bottom[player]][x].setText("");
				
}	}

class Knight{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<8 && PositionX>-1)
			Move.Set_MovingPoint("●",Grid ,TChess ,2,1);
		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<9 && PositionX>0)
			Move.Set_MovingPoint("●",Grid ,TChess ,2,-1);
	}
	public static void ResetCanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<8 && PositionX>-1)
			Move.Set_MovingPoint("",Grid ,TChess ,2,1);
		if(PositionY+playerN[player]*2<9 && PositionY+playerN[player]*2>-1 && PositionX<9 && PositionX>0)
			Move.Set_MovingPoint("",Grid ,TChess ,2,-1);
	}
	public static void BreakInDontMove_InBottom (Checker Grid[][] ,Chess TChess) {
		int player = TChess.getChessPlayer();
		int Bottom[] = {0,1,7,8};
		for(int x=0;x<9;x++) {
			Grid[Bottom[2*player]][x].setText("");
			Grid[Bottom[2*player+1]][x].setText("");	
}	}	}

class Silver_general{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("●",Grid ,TChess ,1,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2!= PositionX)
			Move.Set_MovingPoint("●",Grid ,TChess ,-1,i-2);
	}	}
	public static void ResetCanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("",Grid ,TChess ,1,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2!= PositionX)
			Move.Set_MovingPoint("",Grid ,TChess ,-1,i-2);
	}	}
}

class Gold_general{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("●",Grid ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Set_MovingPoint("●",Grid ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 ==PositionX)
			Move.Set_MovingPoint("●",Grid ,TChess ,-1,i-2);
	}	}
	public static void ResetCanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("",Grid ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Set_MovingPoint("",Grid ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 ==PositionX)
			Move.Set_MovingPoint("",Grid ,TChess ,-1,i-2);
	}	}
}

class King{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;

		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("●",Grid ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Set_MovingPoint("●",Grid ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 )
			Move.Set_MovingPoint("●",Grid ,TChess ,-1,i-2);
	}	}
	public static void ResetCanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;i<=3;i++) {
		if(PositionY+playerN[player]<9 && PositionY+playerN[player]>-1 && PositionX+i-2<9 && PositionX+i-2>-1)
			Move.Set_MovingPoint("",Grid ,TChess ,1,i-2);
		if(PositionY+playerN[player]*0<9 && PositionY+playerN[player]*0>-1 && PositionX+i-2<9 && PositionX+i-2>-1 && PositionX-i+2 !=PositionX)
			Move.Set_MovingPoint("",Grid ,TChess ,0,i-2);
		if(PositionY+playerN[player]*-1<9 && PositionY+playerN[player]*-1>-1 && PositionX+i-2<9 && PositionX+i-2>-1 )
			Move.Set_MovingPoint("",Grid ,TChess ,-1,i-2);
	}	}
}

class Rook{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		//ActionListener
		for(int i=1;;i++) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=1;;i++) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,TChess ,0,i)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,TChess ,0,i)) break;}
		else	break;}
	}
	public static void ResetCanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int i=1;;i++) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("",Grid ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=1;;i++) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,TChess ,0,i)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionY+(playerN[player]*i)<9 && PositionY+(playerN[player]*i)>-1) {
			if(!Move.Set_MovingPoint("",Grid ,TChess ,i,0)) break;}
		else	break;}
		
		for(int i=-1;;i--) {
		if(PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,TChess ,0,i)) break;}
		else	break;}
	}
}

class Bishop{
	private static int playerN[]= {-1,1};
	private static int Position;
	public static void CanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		//ActionListener
		for(int j=1,i=1;;j++,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=1;;j--,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=1,i=-1;;j++,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=-1;;j--,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("●",Grid ,TChess ,j,i)) break;}
		else	break;}
	}
	public static void ResetCanMove (Checker Grid[][] ,Chess TChess) {
		Position = TChess.getPosition();
		int player = TChess.getChessPlayer();
		int PositionY = Position/10;
		int PositionX = Position%10;
		
		for(int j=1,i=1;;j++,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=1;;j--,i++) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=1,i=-1;;j++,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,TChess ,j,i)) break;}
		else	break;}
		
		for(int j=-1,i=-1;;j--,i--) {
		if(PositionY+(playerN[player]*j)<9 && PositionY+(playerN[player]*j)>-1 && PositionX+i<9 && PositionX+i>-1) {
			if(!Move.Set_MovingPoint("",Grid ,TChess ,j,i)) break;}
		else	break;}
	}
}


