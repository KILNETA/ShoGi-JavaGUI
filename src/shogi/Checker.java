package shogi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.JButton;

public class Checker extends JButton {
	
	
/*-------------------------Variable-------------------------*/
	
	
	private Shogi Main_frame;
	private int Grid_Id = 0; //��l���s��
	private Chess Chessman = null; //�b�Ӯ檺�Ѥl


/*-------------------------Function-------------------------*/
	
	// �غc��
	public Checker(Shogi Main_frame,int i){
		this.Main_frame = Main_frame;
		this.Grid_Id = i;
		
		new JButton();//�s�ث��s
		setFocusPainted(false);//�h���E�J�u
		setVerticalTextPosition(JButton.CENTER);//�]�m�����奻 �m��
		setHorizontalTextPosition(JButton.CENTER);//�]�m�����奻 �m��
		setForeground(Color.red);//�奻�C�� ����
		addActionListener(new ActionListener(){//���s�ʧ@��ť��
			public void actionPerformed(ActionEvent e) {
/*�Ӯ�t���Ѥl*/if(getChess()!=null )
/*������Ѥl*/		if(Shogi.choose == null ) {
						if( Pick_up_chess(this) ) { 				//���_�Ѥl
							Button_RiseChess_Try();					//���ձҥΤ��ܥ\��
							Shogi.choose.CanMove(Shogi.CheckerGrid);//��ܥi�H�����a��
						}
					}
/*�w����Ѥl*/		else {
						if(Pick_back_chess(this)); 		//�P�_ + �Ѥl��^���
						else Eat_EnemyChess(this); 		//�P�_ + �Y���Ĥ�Ѥl
					}
									
/*�Ӯ椣�t����*/else 
/*�w����Ѥl*/		if(Shogi.choose != null ) Move_chess(this);	//�P�_ + ���ʴѤl
		}	});
		setBounds(746-(66*(i%10)), 30+(80*(i/10)), 66, 80);//���s��m&�j�p
	}
	
	// �^�� ��l�s��
	public int getGrid_Id(){
		return this.Grid_Id;
	}
	
	// �^�� �Ѥl
	public Chess getChess(){
		return this.Chessman;
	}
	
	// ���� �Ѥl
	public void setChess(Chess Chessman){
		this.Chessman = Chessman;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	
	// ���_�Ѥl
	private boolean Pick_up_chess(ActionListener actionListener){//�D�ѽL
		Chess Chess = this.getChess();
		int ChessPlayer = Chess.getChessPlayer();
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(ChessPlayer == Shogi.Now_Player){//�P�_������Ѥl �O���O �ۤv��
			Grid_MarkUp(ThisGrid);
			Shogi.choose = Chess;//����ܼ�(choose) ���V -> �Q������Ѥl
			return true;//�^�ǿ�����\
		}
		return false;//�^�ǿ������
	}
		
	// ��²��ʴѤl
	private void Move_chess(ActionListener actionListener){
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if( ThisGrid.getText() == "��" && Shogi.choose.Live() ){//�P�_�����ʪ��a�� �O�_ �w�Q�аO���i�樫
			Shogi.choose.EraseCanMove(Shogi.CheckerGrid);//���� ��~��ܥi�H�����a��
			RiseChess_Try();
			Pick_down_chess(actionListener); //��U�Ѥl
			SwitchPlayer(); //�������a
		}
		else if( ThisGrid.getText() == "��" && !Shogi.choose.Live() ){//�P�_�����ʪ��a�� �O�_ �w�Q�аO���i�樫
			Shogi.choose.EraseCanMove_BreakIn (Shogi.CheckerGrid);//���� ��~��ܥi�H�����a��
						
			for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
				int ChessPlayer = Shogi.choose.getChessPlayer();
				BreakIn BreakIn = Shogi.BreakInGrid[ChessPlayer][j][i];
							
				if(BreakIn.getGrid_Class()==Shogi.choose.getChess_Class()) { //���ۦP���������J�L��
					Grid_MarkErase(BreakIn);
					BreakInGrid_TryEnable(BreakIn);
			}	}	}

			Pick_down_chess(actionListener); //��U�Ѥl
			SwitchPlayer(); //�������a
		}
	}
		
	// �Ѥl��^���
	private boolean Pick_back_chess(ActionListener actionListener){//�D�ѽL
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(Shogi.choose.getPosition() == this.getGrid_Id()){//�P�_����^���a�� �O�_ ����_�I
			Shogi.choose.EraseCanMove(Shogi.CheckerGrid);//���� ��~��ܥi�H�����a��
			Grid_MarkErase(ThisGrid);//�N����аO���Q����I �аO����
			Button_RiseChess_lose();
			Shogi.choose = null;//����ܼ�(choose) �M�����V ->null
			return true;//�^�� �ʧ@���\
		} 
		return false;//�^�� �ʧ@����
	}
				
	// �Y���Ĥ�Ѥl
	private boolean Eat_EnemyChess(ActionListener actionListener){
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
		int Choose_Player = Shogi.choose.getChessPlayer();
		Chess ThisGrid_Chess = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10].getChess();//�]�ܼ�(ThisGrid_Chess) ���ӴѮ�W���Ѥl
	//------------------------------Function------------------------------//
		//�P�_�ӴѬO�_���Ĥ�Ѥl && �P�_�����ʪ��a�� �O�_ �w�Q�аO���i�樫
		if( Choose_Player != ThisGrid_Chess.getChessPlayer()	&& 	ThisGrid.getText() == "��" ){
						
			Shogi.choose.EraseCanMove(Shogi.CheckerGrid);//���� ��~��ܥi�H�����a��
			if_isKingDied(ThisGrid_Chess);//�p�G�O�����F
			ThisGrid_Chess.setChessPlayer(Choose_Player);//�ӴѾ֦��� �אּ�Y����
			ThisGrid_Chess.setPosition(0);//���Шt�M��
			ThisGrid_Chess.Notlive();//�վ㬰���`
			ThisGrid_Chess.DeclineChange_Chess();//�P�_���L���� �í���
						
			InputBreakInGrid(ThisGrid_Chess);//�N���ѩ�J �Y���̤����J�L
			RiseChess_Try();
			Pick_down_chess(actionListener);//��U�Ѥl
			SwitchPlayer();//�������a
						
			return true;//�^�� �ʧ@���\
		} 
		return false;//�^�� �ʧ@����
	}
				
	// ��U�Ѥl
	private void Pick_down_chess(ActionListener actionListener){
		Checker ThisGrid = Shogi.CheckerGrid[this.getGrid_Id()/10][this.getGrid_Id()%10];
		Checker ChooseGrid = Shogi.CheckerGrid[Shogi.choose.getPosition()/10][Shogi.choose.getPosition()%10];

		String direction[]= {" Up"," Down"};
	//------------------------------Function------------------------------//
		if(Shogi.choose.Live()) {
			Grid_MarkErase(ChooseGrid);
			ChooseGrid.setChess(null);//�N������Ѯ�M��
			Grid_SetIcon(ChooseGrid,"");//�N������Ѯ�ϥܲM��
		}
		else
			Shogi.choose.Islive();//�_��
		
		Shogi.choose.setPosition(this.getGrid_Id());//���ܴѤl�ۨ��p�J���y��
		EnforceRiseChess();
		
		Grid_SetIcon(ThisGrid,"Picture\\"+ Shogi.choose.getChess_Class() + direction[Shogi.choose.getChessPlayer()] +".png");//�b�s���Ѯ���ܹϥ�
		ThisGrid.setChess(Shogi.choose);//�b�s���Ѯ�g�J�s�� ����ܼ�(choose) �Ѥl
		
		Shogi.choose = null;//����ܼ�(choose) �M�����V ->null
	}
				
	// ���ܴѤl���s�ҥ�
	private void Button_RiseChess_Try(){
		int Choose_Y = Shogi.choose.getPosition()/10;
		int Choose_Player = Shogi.choose.getChessPlayer();
	//------------------------------Function------------------------------//
		if(Shogi.choose.CanRiseChange())
		if(Choose_Player==0 && Choose_Y<=2 && Choose_Y>=0
		|| Choose_Player==1 && Choose_Y<=8 && Choose_Y>=6) 
			Shogi.RiseChange[Choose_Player].RiseCanUse();
	}
	
	// ���ܴѤl���s����
	private void Button_RiseChess_lose(){
		int Choose_Player = Shogi.choose.getChessPlayer();
	//------------------------------Function------------------------------//
		Shogi.RiseChange[Choose_Player].RiseCntUse();
		Shogi.RiseChange[Choose_Player].NoUseRise();
	}
		
	// �p�G�w�I����� �h����
	private void RiseChess_Try(){
		int Choose_Player = Shogi.choose.getChessPlayer();
	//------------------------------Function------------------------------//
		if(Shogi.RiseChange[Choose_Player].UseRise())
			Shogi.choose.RiseChange_Chess();
		Shogi.RiseChange[Choose_Player].RiseCntUse();
		Shogi.RiseChange[Choose_Player].NoUseRise();
	}
	
	// �j��������
	private void EnforceRiseChess(){
		if(Shogi.choose.EnforceRiseChange())
			Shogi.choose.RiseChange_Chess();
	}
				
	// �������a
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
				
	// ��ܥ��J�L
	private void InputBreakInGrid(Chess Chess){
		int ChessPlayer = Chess.getChessPlayer();
	//------------------------------Function------------------------------//
		for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
			BreakIn BreakIn = Shogi.BreakInGrid[ChessPlayer][j][i];
						
			if(BreakIn.getGrid_Class()==Chess.getChess_Class()) {//���ۦP���������J�L��
				BreakIn.addChess(Chess); //�N���ѩ�J���J�L����|��
				BreakInGrid_TryEnable(BreakIn);
	}	}	}	}
				
	// �p�G�O�����F
	private void if_isKingDied(Chess ThisGrid_Chess) {
		if(ThisGrid_Chess.getChess_Class()=="After_king")
			Main_frame.Create_GameOver(0);
		if(ThisGrid_Chess.getChess_Class()=="First_king")	
			Main_frame.Create_GameOver(1);
	}
				
	private void Grid_MarkUp(Checker Grid){//�аO��l
		Grid.setBackground(java.awt.Color.red);//�Q����I �аO
	}
	private void Grid_MarkErase(JButton Grid){//�����аO
		Grid.setBackground(java.awt.Color.white);//�Q����I �аO����
	}
	private void BreakInGrid_TryEnable(BreakIn BreakIn) {//�֦̾��Ѥl�� �ҥΥ��J�L��
		if(BreakIn.getChess_quantity()!=0) //�p�G�ӥ��J�L�榳>1���X�l
			BreakIn.setEnabled(true); //�ϫ��s�i��
		else
			BreakIn.setEnabled(false); //�ϫ��s����
	}
	private void Grid_SetIcon(JButton Grid ,String link) {//�]�m�Ѯ�Ϥ�
		ImageIcon Icon = new ImageIcon(link);
		Grid.setIcon(Icon);
	}
	public void Reset(){ //���m�Ѯ�
		Chessman = null;
		Grid_SetIcon(this ,"");
	}



}
