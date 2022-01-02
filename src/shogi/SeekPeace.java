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
	private int GridPlayer; //���ݪ��a
	private boolean Surrender = false; //���ݪ��a


/*-------------------------Function-------------------------*/
	
	//�غc��
	public SeekPeace(Shogi Main_frame,int GridPlayer){
		this.Main_frame = Main_frame;
		this.GridPlayer = GridPlayer;
		
		//��l��3x3���ѽL��
		new JButton();//�s�ث��s
		setText("�D�M");
		setForeground(Color.RED);
		setBackground(Color.RED);
		setFont(new Font("�з���", Font.BOLD, 14));
		setBounds(955-(945*GridPlayer), 440-(125*GridPlayer), 65, 25);
		setFocusPainted(false);//�h���E�J�u
		addActionListener(new ActionListener(){//���s�ʧ@��ť��
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
	//�]�m�Ҧ��ѽL (���i��)
	private void UnEnabled_allCheckers(){
		for(int p=0;p<2;p++) {for(int y=0;y<3;y++) {for(int x=0;x<3;x++) {
			Shogi.BreakInGrid[p][y][x].setEnabled(false);
		}	}	}
		for(int y=0;y<9;y++) {for(int x=0;x<9;x++) {
			Shogi.CheckerGrid[y][x].setEnabled(false);
	}	}	}
	
	//�]�m�Ҧ��ѽL (�i��)
	private void Enabled_allCheckers(){
		for(int p=0;p<2;p++) {for(int y=0;y<3;y++) {for(int x=0;x<3;x++) {
			BreakInGrid_TryEnable(Shogi.BreakInGrid[p][y][x]);
		}	}	}
		for(int y=0;y<9;y++) {for(int x=0;x<9;x++) {
			Shogi.CheckerGrid[y][x].setEnabled(true);
	}	}	}
	
	// ��ܥ��J�L
	private void InputBreakInGrid(Chess Chess){
		int ChessPlayer = Chess.getChessPlayer();
	//------------------------------Function------------------------------//
		for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
			BreakIn BreakIn = Shogi.BreakInGrid[ChessPlayer][j][i];
							
			if(BreakIn.getGrid_Class()==Chess.getChess_Class()) {//���ۦP���������J�L��
				BreakIn.addChess(Chess); //�N���ѩ�J���J�L����|��
				BreakInGrid_TryEnable(BreakIn);
				Shogi.choose.EraseCanMove_BreakIn (Shogi.CheckerGrid );//���� ��~��ܥi�H�����a��
				BreakIn.setBackground(Color.white);//�Q����I �аO����;//�N����аO���Q����I �аO����
				Shogi.choose = null;
				break;
	}	}	}	}
	
	//�֦̾��Ѥl�� �ҥΥ��J�L��
	private void BreakInGrid_TryEnable(BreakIn BreakIn) {
		if(BreakIn.getChess_quantity()!=0) //�p�G�ӥ��J�L�榳>1���X�l
			BreakIn.setEnabled(true); //�ϫ��s�i��
		else
			BreakIn.setEnabled(false); //�ϫ��s����
	}
	
	// �Ѥl��^��� //���J�L
	private void PickBack_chess_BreakIn(){
		InputBreakInGrid(Shogi.choose);
	}
	
	// �Ѥl��^���//�D�ѽL
	private void PickBack_chess_Checker(){
		Checker ThisGrid = Shogi.CheckerGrid[Shogi.choose.getPosition()/10][Shogi.choose.getPosition()%10];
	//------------------------------Function------------------------------//
		if(Shogi.choose.getPosition() == Shogi.choose.getPosition()){//�P�_����^���a�� �O�_ ����_�I
			Shogi.choose.EraseCanMove(Shogi.CheckerGrid);//���� ��~��ܥi�H�����a��
			ThisGrid.setBackground(Color.white);//�N����аO���Q����I �аO����
			Button_RiseChess_lose();
			Shogi.choose = null;//����ܼ�(choose) �M�����V ->null
	} 	}
	
	// ���ܴѤl���s����
	private void Button_RiseChess_lose(){
		int Choose_Player = Shogi.choose.getChessPlayer();
	//------------------------------Function------------------------------//
		Shogi.RiseChange[Choose_Player].RiseCntUse();
		Shogi.RiseChange[Choose_Player].NoUseRise();
	}
	
	//���䤤�@�ӤH�D�M
	private boolean haveOnePeace() {
		boolean After_Surrender = Shogi.SeekPeace[0].Surrender();
		boolean First_Surrender = Shogi.SeekPeace[1].Surrender();
		
		if(After_Surrender || First_Surrender)
			return true;
		else
			return false;
	}
	
	//�����M�� �I�s��������
	private void PeaceRound() {
		boolean After_Surrender = Shogi.SeekPeace[0].Surrender();
		boolean First_Surrender = Shogi.SeekPeace[1].Surrender();
		
		if(After_Surrender && First_Surrender){
			Main_frame.Create_GameOver(2);
		}
	}
	
	//�^�� �O�_�n�D�D�M
	private boolean Surrender() {
		return Surrender;
	}
	
	//�]�m����C�����
	private void SeekPeace_setColor() {
		
		if(!Surrender) {
			setForeground(Color.RED);
			setBackground(Color.RED);
		}else {
			setForeground(Color.BLUE);
			setBackground(Color.BLUE);
		}
	}
	
	//���m���s
	public void Reset(){
		setForeground(Color.RED);
		setBackground(Color.RED);
		Surrender = false;
		Enabled_allCheckers();
	}
	
}