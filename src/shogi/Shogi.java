package shogi;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class Shogi {

	
/*-------------------------Variable-------------------------*/	

	
	private JFrame Shogi_frame;
	//�ѽL�W��9x9�ӴѽL��
	JButton CheckerGrid[][] = new JButton[9][9];
	CheckerGrid_Data CheckerB[][]= new CheckerGrid_Data[9][9];
	//���J�L�W��3x3�ӴѽL�� (�U���)
	JButton BreakInGrid[][][] = new JButton[2][3][3];
	BreakIn_Data BreakInB[][][]= new BreakIn_Data[2][3][3];
	//��Ӫ��a �U20�ӴѤl
	Chess PlayerChess[] = new Chess[40];
	Chess choose = null;
	int Now_Player = 0;


/*-------------------------Function-------------------------*/


	/**
	 * �Ұ����ε{�ǡC
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Shogi window = new Shogi();
					Login login = new Login(window.Shogi_frame);
				} 
				catch (Exception e) {
					e.printStackTrace();
	}	}	});	}

	/**
	 * �Ы����ε{�ǡC
	 */
	public Shogi() {
		initialize();
	}

	/**
	 * ��l�Ʈج[�����e�C
	 */
	private void initialize() {
		//�ХߥD�e��
		Shogi_frame = new JFrame();
		//�վ㵡�f�j�p(����)
		Shogi_frame.setResizable(false);
		//���ε��f (����n�J���f�n�J���\)
		Shogi_frame.setEnabled(false);
		//���f���D
		Shogi_frame.setTitle("\u5C07\u68CB\u5927\u5E2B");
		//�e���j�p
		Shogi_frame.setBounds(100, 100, 1040, 810);
		//�]�m�q�{�����ާ@
		Shogi_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������e���� &�@�]�m�G��
		Shogi_frame.getContentPane().setLayout(null);
		//���ε{���Y��
		Shogi_frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));
		
		//��l��9x9���ѽL��
		for(int j=0;j<9;j++){
			for(int i=0;i<9;i++){
				//�s�ث��s
				CheckerGrid[j][i] = new JButton();
				//���s�ʧ@��ť��
				CheckerGrid[j][i].addActionListener(CheckerB[j][i]= new CheckerGrid_Data( ((j+1)*10)+i+1){
					
					public void actionPerformed(ActionEvent e) {
		/*�Ӯ�t���Ѥl*/	if(getChess()!=null )
		/*������Ѥl*/			if(choose == null ) {
								if( Pick_up_chess(this.getGrid_Id()) ) {			//���_�Ѥl
									choose.CanMove(CheckerGrid ,CheckerB); }		//��ܥi�H�����a��
							}
		/*�w����Ѥl*/			else {
								if(Pick_back_chess(this.getGrid_Id())); 		//�P�_ + �Ѥl��^���
								else Eat_EnemyChess(this.getGrid_Id()); 		//�P�_ + �Y���Ĥ�Ѥl
							}
			
		/*�Ӯ椣�t����*/	else 
		/*�w����Ѥl*/			if(choose != null ) Move_chess(this.getGrid_Id());	//�P�_ + ���ʴѤl
			
					}	});
				//���s��m&�j�p
				CheckerGrid[j][i].setBounds(746-(66*i), 30+(80*j), 66, 80);
				//��e���W��ܫ��s
				Shogi_frame.getContentPane().add(CheckerGrid[j][i]);
				//�h���E�J�u
				CheckerGrid[j][i].setFocusPainted(false);
				//�]�m�����奻 �m��
				CheckerGrid[j][i].setVerticalTextPosition(JButton.CENTER);
				//�]�m�����奻 �m��
				CheckerGrid[j][i].setHorizontalTextPosition(JButton.CENTER);
				//�奻�C�� ����
				CheckerGrid[j][i].setForeground(Color.red);
				
				//CheckerGrid[j][i].setText("��");
		}	}
		//��l�ƪ��a�Ѥl
		initialize_PlayerChess();
		//��l�ƴѤl�\��
		initialize_CheckerChess();
		
		//��l��3x3���ѽL��
		for(int P=0;P<2;P++) {for(int j=0;j<3;j++){for(int i=0;i<3;i++){
			String Chess[]= {null,"Pawn",null,"Bishop","Lance","Rook","Gold general","Knight","Silver general",
					"Silver general","Knight","Gold general","Rook","Lance","Bishop",null,"Pawn",null};
			//�s�ث��s
			BreakInGrid[P][j][i] = new JButton();
			//���s�ʧ@��ť��
			BreakInGrid[P][j][i].addActionListener(BreakInB[P][j][i]= new BreakIn_Data(Chess[(9*P)+(j*3)+i] ,P){
				public void actionPerformed(ActionEvent e) {

				}	});
			//���s��m&�j�p
			BreakInGrid[P][j][i].setBounds(822+(66*i)-(812*P), 510+(80*j)-(480*P), 66, 80);
			//��e���W��ܫ��s
			Shogi_frame.getContentPane().add(BreakInGrid[P][j][i]);
			//�h���E�J�u
			BreakInGrid[P][j][i].setFocusPainted(false);
			//�]�m�����奻 �m��
			BreakInGrid[P][j][i].setVerticalTextPosition(JButton.CENTER);
			//�]�m�����奻 �m��
			BreakInGrid[P][j][i].setHorizontalTextPosition(JButton.CENTER);
			//�奻�C�� ����
			BreakInGrid[P][j][i].setForeground(Color.BLUE);
			//�T��ϥ�
			BreakInGrid[P][j][i].setEnabled(false);
		}	}	}
		//��l�ƥ��J�L�\��
		initialize_BreakInGrid();
		
		
		//�аO�ѽL (�s�边��)	----------------------------//
		JPanel panel = new JPanel();				//
		panel.setBounds(218, 30, 594, 720);			//
		Shogi_frame.getContentPane().add(panel);	//
													//
		JPanel panel_1 = new JPanel();				//
		panel_1.setBounds(10, 30, 198, 240);		//
		Shogi_frame.getContentPane().add(panel_1);	//
		//�аO�ѽL (�s�边��)	----------------------------//
	}
	
	/**
	 * ��l�ƪ��a�Ѥl
	 */
	private void initialize_PlayerChess()
	{
		String Chess[]= {"After_king","Rook","Bishop","Gold general","Silver general","Knight","Lance","Pawn","First_king"};
		PlayerChess[4]=new Chess(Chess[0],0);
		PlayerChess[24]=new Chess(Chess[8],1);
		
		PlayerChess[9]=new Chess(Chess[1],0);
		PlayerChess[10]=new Chess(Chess[2],0);
		PlayerChess[29]=new Chess(Chess[2],1);
		PlayerChess[30]=new Chess(Chess[1],1);
		for(int i=0;i<=1;i++) {
			for(int j=0;j<=1;j++) {
				PlayerChess[3+(i*20)+(j*2)]=new Chess(Chess[3],i);
				PlayerChess[2+(i*20)+(j*4)]=new Chess(Chess[4],i);
				PlayerChess[1+(i*20)+(j*6)]=new Chess(Chess[5],i);
				PlayerChess[0+(i*20)+(j*8)]=new Chess(Chess[6],i);
			}
			for(int j=0;j<9;j++) {
				PlayerChess[11+(i*20)+j]=new Chess(Chess[7],i);
	}	}	}
	
	/**
	 * ��l�ƴѤl�\��
	 */
	private void initialize_CheckerChess(){
		String direction[]= {" Up"," Down"};
		for(int j=0;j<=1;j++){
			for(int i=0;i<9;i++){
				CheckerGrid[6-(j*4)][i].setIcon(new ImageIcon("Picture\\"+ PlayerChess[11+(j*20)+i].getChess_Class() + direction[PlayerChess[11+(j*20)+i].getChessPlayer()] +".png"));
				CheckerB[6-(j*4)][i].setChess(PlayerChess[11+(j*20)+i]);
				PlayerChess[11+(j*20)+i].setPosition(CheckerB[6-(j*4)][i].getGrid_Id());
			}
			for(int i=0;i<2;i++){
				CheckerGrid[7-(j*6)][1+(i*6)].setIcon(new ImageIcon("Picture\\"+ PlayerChess[9+(j*20)+i].getChess_Class() + direction[PlayerChess[9+(j*20)+i].getChessPlayer()] +".png"));
				CheckerB[7-(j*6)][1+(i*6)].setChess(PlayerChess[9+(j*20)+i]);
				PlayerChess[9+(j*20)+i].setPosition(CheckerB[7-(j*6)][(1+(i*6))].getGrid_Id());
			}
			for(int i=0;i<9;i++){
				CheckerGrid[8-(j*8)][i].setIcon(new ImageIcon("Picture\\"+ PlayerChess[0+(j*20)+i].getChess_Class() + direction[PlayerChess[0+(j*20)+i].getChessPlayer()] +".png"));
				CheckerB[8-(j*8)][i].setChess(PlayerChess[0+(j*20)+i]);
				PlayerChess[0+(j*20)+i].setPosition(CheckerB[8-(j*8)][i].getGrid_Id());
	}	}	}
	
	/**
	 * ��l�ƥ��J�L�\��
	 */
	private void initialize_BreakInGrid() {
		String direction[]= {" Up"," Down"};
		
		//�פJ�Ϥ�
		for(int P=0;P<2;P++){	for(int j=0;j<3;j++){	for(int i=0;i<3;i++){
				BreakInGrid[P][j][i].setIcon(new ImageIcon("Picture\\"+ BreakInB[P][j][i].getGrid_Class() + direction[BreakInB[P][j][i].getPlayer()] +".png"));
	}	}	}	}
	
	/**
	 * ���_�Ѥl
	 */
	private boolean Pick_up_chess(int Grid_Number){
		//�P�_������Ѥl �O���O �ۤv��
		if(CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].getChess().getChessPlayer() == Now_Player){
			//�Хܬ���~�ج�����ؼ�
			CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].setBackground(java.awt.Color.red);
			//����ܼ�(choose) ���V -> �Q������Ѥl
			choose = CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].getChess();
			//�^�ǿ�����\
			return true;
		}
		//�^�ǿ������
		return false;
	}
	
	/**
	 * ��²��ʴѤl
	 */
	private void Move_chess(int Grid_Number){
		//�P�_�����ʪ��a�� �O�_ �w�Q�аO���i�樫
		if( CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].getText() == "��" ){
			//���� ��~��ܥi�H�����a��
			choose.ResetCanMove(CheckerGrid ,CheckerB );
			RiseChange_Chess();	//���ܴѤl
			Pick_down_chess(Grid_Number); //��U�Ѥl
			SwitchPlayer(); //�������a
	}	}
	
	/**
	 * �Ѥl��^���
	 */
	private boolean Pick_back_chess(int Grid_Number){
		//�P�_����^���a�� �O�_ ����_�I
		if(choose.getPosition() == Grid_Number){
			//���� ��~��ܥi�H�����a��
			choose.ResetCanMove(CheckerGrid ,CheckerB );
			//�N����аO���Q����I �аO����
			CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].setBackground(java.awt.Color.white);
			//����ܼ�(choose) �M�����V ->null
			choose = null;
			//�^�� �ʧ@���\
			return true;
		} 
		//�^�� �ʧ@����
		return false;	
	}
	
	
	
	/**
	 * �Y���Ĥ�Ѥl
	 */
	private boolean Eat_EnemyChess(int Grid_Number){
		//�]�ܼ�(ThisGrid_Chess) ���ӴѮ�W���Ѥl
		Chess ThisGrid_Chess = CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].getChess();
		
		//�P�_�ӴѬO�_���Ĥ�Ѥl && �P�_�����ʪ��a�� �O�_ �w�Q�аO���i�樫
		if( choose.getChessPlayer() != ThisGrid_Chess.getChessPlayer()
		&& 	CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].getText() == "��" ){
			
			//���� ��~��ܥi�H�����a��
			choose.ResetCanMove(CheckerGrid ,CheckerB );
			//�ӴѾ֦��� �אּ�Y����
			ThisGrid_Chess.setChessPlayer(choose.getChessPlayer());
			//���Шt�M��
			ThisGrid_Chess.setPosition(0);
			 //�վ㬰���`
			ThisGrid_Chess.Notlive();
			 //�P�_���L���� �í���
			ThisGrid_Chess.DeclineChange_Chess();
			
			 //�N���ѩ�J �Y���̤����J�L
			InputBreakInGrid(ThisGrid_Chess);
			
			//���դ��ܦۤv
			RiseChange_Chess();
			 //��U�Ѥl
			Pick_down_chess(Grid_Number);
			 //�������a
			SwitchPlayer();
			//�^�� �ʧ@���\
			return true;
		} 
		//�^�� �ʧ@����
		return false;
	}
	
	/**
	 * ��U�Ѥl
	 */
	private void Pick_down_chess(int Grid_Number){
		String direction[]= {" Up"," Down"};
		
		//�N����аO���Q����I �аO����
		CheckerGrid[(choose.getPosition()/10)-1][(choose.getPosition()%10)-1].setBackground(java.awt.Color.white);
		//�N������Ѯ�M��
		CheckerB[(choose.getPosition()/10)-1][(choose.getPosition()%10)-1].setChess(null);
		//�N������Ѯ�ϥܲM��
		CheckerGrid[(choose.getPosition()/10)-1][(choose.getPosition()%10)-1].setIcon(new ImageIcon());
		
		//�b�s���Ѯ���ܹϥ�
		CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].setIcon(new ImageIcon("Picture\\"+ choose.getChess_Class() + direction[choose.getChessPlayer()] +".png"));
		//�b�s���Ѯ�g�J�s�� ����ܼ�(choose) �Ѥl
		CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].setChess(choose);
		//���ܴѤl�ۨ��p�J���y��
		choose.setPosition(Grid_Number);
		//����ܼ�(choose) �M�����V ->null
		choose = null;
	}
	
	/**
	 * ���ܴѤl
	 */
	private void RiseChange_Chess(){
		if(choose.getChessPlayer()==0 && choose.getPosition()/10<=3 && choose.getPosition()/10>=1) {
			choose.RiseChange_Chess();
		}
		if(choose.getChessPlayer()==1 && choose.getPosition()/10<=9 && choose.getPosition()/10>=7) {
			choose.RiseChange_Chess();
		}
	}
	
	/*
	 * �������a
	 */
	private void SwitchPlayer(){
		Now_Player = Now_Player==1?0:1;
		/*
		if(Now_Player==0) {
			String direction[]= {" Up"," Down"};
		for(int i=0;i<9;i++)	{for(int j=0;j<9;j++) {
				CheckerGrid[j][i].setBounds(681-(66*i), 30+(80*j), 66, 80);
				if(CheckerB[j][i].getChess()!=null)
				CheckerGrid[j][i].setIcon(new ImageIcon("Picture\\"+ CheckerB[j][i].getChess().getChess() + direction[CheckerB[j][i].getChess().getChessPlayer()] +".png"));
		}	}	}
		else {
			String direction[]= {" Down"," Up"};
			for(int i=0;i<9;i++)	{for(int j=0;j<9;j++) {
					CheckerGrid[8-j][i].setBounds(681-(66*i), 30+(80*j), 66, 80);
					if(CheckerB[8-j][i].getChess()!=null)
					CheckerGrid[8-j][i].setIcon(new ImageIcon("Picture\\"+ CheckerB[8-j][i].getChess().getChess() + direction[CheckerB[8-j][i].getChess().getChessPlayer()] +".png"));
		}	}	}
		*/
	}
	
	/**
	 * ��ܥ��J�L
	 */
	private void InputBreakInGrid(Chess ThisGrid_Chess){
		for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
			//���ۦP���������J�L��
			if(ThisGrid_Chess.getChess_Class() == BreakInB[ThisGrid_Chess.getChessPlayer()][j][i].getGrid_Class()) {
				BreakInB[ThisGrid_Chess.getChessPlayer()][j][i].addChess(ThisGrid_Chess); //�N���ѩ�J���J�L����|��
				if(BreakInB[ThisGrid_Chess.getChessPlayer()][j][i].getChess_quantity()!=0) //�p�G�ӥ��J�L�榳>1���X�l
					BreakInGrid[ThisGrid_Chess.getChessPlayer()][j][i].setEnabled(true); //�ϫ��s�i��
				else
					BreakInGrid[ThisGrid_Chess.getChessPlayer()][j][i].setEnabled(false); //�ϫ��s����
			}
			
	}	}	}
	
	
}