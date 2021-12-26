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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Shogi {

	
/*-------------------------Variable-------------------------*/	

	
	private JFrame Shogi_frame;
	//�ѽL�W��9x9�ӴѽL��
	JButton CheckerGrid[][] = new JButton[9][9];
	CheckerGrid_Data CheckerB[][]= new CheckerGrid_Data[9][9];
	//���J�L�W��3x3�ӴѽL�� (�U���)
	JButton BreakInGrid[][][] = new JButton[2][3][3];
	BreakIn_Data BreakInB[][][]= new BreakIn_Data[2][3][3];
	
	JLabel After = new JLabel();
	JLabel First = new JLabel();
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
		Shogi_frame = new JFrame();//�ХߥD�e��
		Shogi_frame.setResizable(false);//�վ㵡�f�j�p(����)
		Shogi_frame.setEnabled(false);//���ε��f (����n�J���f�n�J���\)
		Shogi_frame.setTitle("\u5C07\u68CB\u5927\u5E2B");//���f���D
		Shogi_frame.setBounds(100, 100, 1046, 819);//�e���j�p
		Shogi_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�e���j�p
		Shogi_frame.getContentPane().setLayout(null);//������e���� &�@�]�m�G��
		Shogi_frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));//���ε{���Y��
		
		Create_Chessboard();
		Create_BreakInboard();
		

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(822, 510, 198, 240);
		Shogi_frame.getContentPane().add(panel_2);
		
		After = new JLabel("\u25B2\u5148\u624B\u2500\u2500\u2500\u2500\u2500\u2500");
		After.setFont(new Font("�з���", Font.BOLD, 20));
		After.setHorizontalAlignment(SwingConstants.LEFT);
		After.setBounds(822, 471, 198, 29);
		Shogi_frame.getContentPane().add(After);
		After.setForeground(Color.red);
		
		First = new JLabel("\u2500\u2500\u2500\u2500\u2500\u2500\u5F8C\u624B\u25BD");
		First.setHorizontalAlignment(SwingConstants.RIGHT);
		First.setFont(new Font("�з���", Font.BOLD, 20));
		First.setBounds(10, 280, 198, 29);
		Shogi_frame.getContentPane().add(First);
		
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
	 * ���m9x9�ѽL��
	 */
	private void Create_Chessboard(){
		//��l��9x9���ѽL��
		for(int j=0;j<9;j++){	for(int i=0;i<9;i++){
			CheckerGrid[j][i] = new JButton();//�s�ث��s
			CheckerGrid[j][i].setFocusPainted(false);//�h���E�J�u
			CheckerGrid[j][i].setVerticalTextPosition(JButton.CENTER);//�]�m�����奻 �m��
			CheckerGrid[j][i].setHorizontalTextPosition(JButton.CENTER);//�]�m�����奻 �m��
			CheckerGrid[j][i].setForeground(Color.red);//�奻�C�� ����
			CheckerGrid[j][i].addActionListener(CheckerB[j][i]= new CheckerGrid_Data( (j*10)+i ){//���s�ʧ@��ť��
				public void actionPerformed(ActionEvent e) {
	/*�Ӯ�t���Ѥl*/if(getChess()!=null )
	/*������Ѥl*/		if(choose == null ) {
							if( Pick_up_chess(this) ) 			//���_�Ѥl
							choose.CanMove(CheckerGrid ,CheckerB); 		//��ܥi�H�����a��
						}
	/*�w����Ѥl*/		else {
							if(Pick_back_chess(this)); 		//�P�_ + �Ѥl��^���
							else Eat_EnemyChess(this); 		//�P�_ + �Y���Ĥ�Ѥl
						}
					
	/*�Ӯ椣�t����*/else 
	/*�w����Ѥl*/		if(choose != null ) Move_chess(this);	//�P�_ + ���ʴѤl
					
			}	});
			CheckerGrid[j][i].setBounds(746-(66*i), 30+(80*j), 66, 80);//���s��m&�j�p
			Shogi_frame.getContentPane().add(CheckerGrid[j][i]);//��e���W��ܫ��s
		}	}
		//��l�ƪ��a�Ѥl
		initialize_PlayerChess();
		//��l�ƴѤl�\��
		initialize_CheckerChess();
	}
	
	/**
	 * ���m3x3�ѽL��
	 */
	private void Create_BreakInboard(){
		String Chess[]= {null,"Pawn",null,"Bishop","Lance","Rook","Gold general","Knight","Silver general",
				 "Silver general","Knight","Gold general","Rook","Lance","Bishop",null,"Pawn",null};
		//��l��3x3���ѽL��
		for(int P=0;P<2;P++) {for(int j=0;j<3;j++){for(int i=0;i<3;i++){
			BreakInGrid[P][j][i] = new JButton();//�s�ث��s
			BreakInGrid[P][j][i].setFocusPainted(false);//�h���E�J�u
			BreakInGrid[P][j][i].setVerticalTextPosition(JButton.CENTER);//�]�m�����奻 �m��
			BreakInGrid[P][j][i].setHorizontalTextPosition(JButton.CENTER);//�]�m�����奻 �m��
			BreakInGrid[P][j][i].setForeground(Color.BLUE);//�奻�C�� ����
			BreakInGrid[P][j][i].setEnabled(false);//�T��ϥ�
			BreakInGrid[P][j][i].addActionListener(BreakInB[P][j][i]= new BreakIn_Data(Chess[(9*P)+(j*3)+i] ,P , (j*10)+i){//���s�ʧ@��ť��
				public void actionPerformed(ActionEvent e) {
					if(choose == null){
						if( Pick_up_chess(this) )//���_�Ѥl
						choose.CanMove_BreakIn (CheckerGrid ,CheckerB ,Now_Player!=0?PlayerChess[4]:PlayerChess[24]);//��ܥi�H�����a��
					}	
					else Pick_back_chess(this);//�P�_ + �Ѥl��^���
					}
				});
			BreakInGrid[P][j][i].setBounds(822+(66*i)-(812*P), 510+(80*j)-(480*P), 66, 80);//���s��m&�j�p
			Shogi_frame.getContentPane().add(BreakInGrid[P][j][i]);//��e���W��ܫ��s
		}	}	}
		//��l�ƥ��J�L�\��
		initialize_BreakInGrid();
	}
	
	// ��l�ƪ��a�Ѥl
	private void initialize_PlayerChess()
	{
		String Chess[]= {"After_king","Rook","Bishop","Gold general","Silver general","Knight","Lance","Pawn","First_king"};
		PlayerChess[4]=new Chess(Chess[0],0);
		PlayerChess[24]=new Chess(Chess[8],1);
		
		PlayerChess[9]=new Chess(Chess[1],0);
		PlayerChess[10]=new Chess(Chess[2],0);
		PlayerChess[29]=new Chess(Chess[2],1);
		PlayerChess[30]=new Chess(Chess[1],1);
		for(int i=0;i<=1;i++) {	for(int j=0;j<=1;j++) {
				PlayerChess[3+(i*20)+(j*2)]=new Chess(Chess[3],i);
				PlayerChess[2+(i*20)+(j*4)]=new Chess(Chess[4],i);
				PlayerChess[1+(i*20)+(j*6)]=new Chess(Chess[5],i);
				PlayerChess[0+(i*20)+(j*8)]=new Chess(Chess[6],i);
			}
			for(int j=0;j<9;j++) {
				PlayerChess[11+(i*20)+j]=new Chess(Chess[7],i);
	}	}	}
	
	// ��l�ƴѤl�\��
	private void initialize_CheckerChess(){
		String direction[]= {" Up"," Down"};
		for(int j=0;j<=1;j++){
			for(int i=0;i<9;i++){
				Grid_SetIcon(CheckerGrid[6-(j*4)][i],"Picture\\"+ PlayerChess[11+(j*20)+i].getChess_Class() + direction[PlayerChess[11+(j*20)+i].getChessPlayer()] +".png");
				CheckerB[6-(j*4)][i].setChess(PlayerChess[11+(j*20)+i]);
				PlayerChess[11+(j*20)+i].setPosition(CheckerB[6-(j*4)][i].getGrid_Id());
			}
			for(int i=0;i<2;i++){
				Grid_SetIcon(CheckerGrid[7-(j*6)][1+(i*6)],"Picture\\"+ PlayerChess[9+(j*20)+i].getChess_Class() + direction[PlayerChess[9+(j*20)+i].getChessPlayer()] +".png");
				CheckerB[7-(j*6)][1+(i*6)].setChess(PlayerChess[9+(j*20)+i]);
				PlayerChess[9+(j*20)+i].setPosition(CheckerB[7-(j*6)][(1+(i*6))].getGrid_Id());
			}
			for(int i=0;i<9;i++){
				Grid_SetIcon(CheckerGrid[8-(j*8)][i],"Picture\\"+ PlayerChess[0+(j*20)+i].getChess_Class() + direction[PlayerChess[0+(j*20)+i].getChessPlayer()] +".png");
				CheckerB[8-(j*8)][i].setChess(PlayerChess[0+(j*20)+i]);
				PlayerChess[0+(j*20)+i].setPosition(CheckerB[8-(j*8)][i].getGrid_Id());
	}	}	}
	
	// ��l�ƥ��J�L�\��
	private void initialize_BreakInGrid() {
		String direction[]= {" Up"," Down"};
	//------------------------------Function------------------------------//
		for(int P=0;P<2;P++){	for(int j=0;j<3;j++){	for(int i=0;i<3;i++){//�פJ�Ϥ�
			Grid_SetIcon(BreakInGrid[P][j][i] ,"Picture\\"+ BreakInB[P][j][i].getGrid_Class() + direction[BreakInB[P][j][i].getPlayer()] +".png");
	}	}	}	}
	

	// ���_�Ѥl
	private boolean Pick_up_chess(CheckerGrid_Data Grid){//�D�ѽL
		Chess Chess = Grid.getChess();
		int ChessPlayer = Chess.getChessPlayer();
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(ChessPlayer == Now_Player){//�P�_������Ѥl �O���O �ۤv��
			Grid_MarkUp(ThisGrid);
			choose = Chess;//����ܼ�(choose) ���V -> �Q������Ѥl
			return true;//�^�ǿ�����\
		}
		return false;//�^�ǿ������
	}
	private boolean Pick_up_chess(BreakIn_Data Grid){//���J�L
		Chess Chess = Grid.reduceChess();
		int ChessPlayer = Grid.getPlayer();
		JButton ThisGrid = BreakInGrid[ChessPlayer][Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(ChessPlayer == Now_Player){//�P�_������Ѥl �O���O �ۤv��
			Grid_MarkUp(ThisGrid);
			choose = Chess;//����ܼ�(choose) ���V -> �Q������Ѥl
			return true;//�^�ǿ�����\
		}
		Grid.addChess(Chess);//��^���J�ѽL
		return false;//�^�ǿ������
	}
	
	// ��²��ʴѤl
	private void Move_chess(CheckerGrid_Data Grid){
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if( ThisGrid.getText() == "��" && choose.Live() ){//�P�_�����ʪ��a�� �O�_ �w�Q�аO���i�樫
			choose.EraseCanMove(CheckerGrid ,CheckerB );//���� ��~��ܥi�H�����a��
			RiseChange_Chess();	//���ܴѤl
			Pick_down_chess(Grid); //��U�Ѥl
			SwitchPlayer(); //�������a
		}
		else if( ThisGrid.getText() == "��" && !choose.Live() ){//�P�_�����ʪ��a�� �O�_ �w�Q�аO���i�樫
			choose.EraseCanMove_BreakIn (CheckerGrid ,CheckerB);//���� ��~��ܥi�H�����a��
			choose.Islive();//�_��
			
			for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
				int ChessPlayer = choose.getChessPlayer();
				JButton Grid1 = BreakInGrid[ChessPlayer][j][i];
				BreakIn_Data GridData = BreakInB[ChessPlayer][j][i];
				
				if(GridData.getGrid_Class()==choose.getChess_Class()) { //���ۦP���������J�L��
					Grid_MarkErase(Grid1);
					BreakInGrid_TryEnable(Grid1, GridData);
			}	}	}

			Pick_down_chess(Grid); //��U�Ѥl
			SwitchPlayer(); //�������a
		}
	}
	
	// �Ѥl��^���
	private boolean Pick_back_chess(CheckerGrid_Data Grid){//�D�ѽL
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(choose.getPosition() == Grid.getGrid_Id()){//�P�_����^���a�� �O�_ ����_�I
			choose.EraseCanMove(CheckerGrid ,CheckerB );//���� ��~��ܥi�H�����a��
			Grid_MarkErase(ThisGrid);//�N����аO���Q����I �аO����
			choose = null;//����ܼ�(choose) �M�����V ->null
			return true;//�^�� �ʧ@���\
		} 
		return false;//�^�� �ʧ@����
	}
	private void Pick_back_chess(BreakIn_Data Grid){//���J�L
		JButton ThisGrid = BreakInGrid[choose.getChessPlayer()][Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(Grid.getGrid_Class() == choose.getChess_Class() && !choose.Live()) {
			choose.EraseCanMove_BreakIn (CheckerGrid ,CheckerB);//���� ��~��ܥi�H�����a��
			Grid_MarkErase(ThisGrid);//�N����аO���Q����I �аO����
			Grid.addChess(choose);
			choose = null;
		}
	}
	
	
	// �Y���Ĥ�Ѥl
	private boolean Eat_EnemyChess(CheckerGrid_Data Grid){
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
		int Choose_Player = choose.getChessPlayer();
		Chess ThisGrid_Chess = CheckerB[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10].getChess();//�]�ܼ�(ThisGrid_Chess) ���ӴѮ�W���Ѥl
	//------------------------------Function------------------------------//
		//�P�_�ӴѬO�_���Ĥ�Ѥl && �P�_�����ʪ��a�� �O�_ �w�Q�аO���i�樫
		if( Choose_Player != ThisGrid_Chess.getChessPlayer()	&& 	ThisGrid.getText() == "��" ){
			
			choose.EraseCanMove(CheckerGrid ,CheckerB );//���� ��~��ܥi�H�����a��
			ThisGrid_Chess.setChessPlayer(Choose_Player);//�ӴѾ֦��� �אּ�Y����
			ThisGrid_Chess.setPosition(0);//���Шt�M��
			ThisGrid_Chess.Notlive();//�վ㬰���`
			ThisGrid_Chess.DeclineChange_Chess();//�P�_���L���� �í���
			
			InputBreakInGrid(ThisGrid_Chess);//�N���ѩ�J �Y���̤����J�L
			
			RiseChange_Chess();//���դ��ܦۤv
			Pick_down_chess(Grid);//��U�Ѥl
			SwitchPlayer();//�������a
			
			return true;//�^�� �ʧ@���\
		} 
		return false;//�^�� �ʧ@����
	}
	
	// ��U�Ѥl
	private void Pick_down_chess(CheckerGrid_Data Grid){
		JButton ThisGrid = CheckerGrid[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
		CheckerGrid_Data ThisGrid_Data = CheckerB[Grid.getGrid_Id()/10][Grid.getGrid_Id()%10];
		
		JButton ChooseGrid = CheckerGrid[choose.getPosition()/10][choose.getPosition()%10];
		CheckerGrid_Data ChooseGrid_Data = CheckerB[choose.getPosition()/10][choose.getPosition()%10];

		String direction[]= {" Up"," Down"};
	//------------------------------Function------------------------------//
		Grid_EraseMark(ChooseGrid);
		ChooseGrid_Data.setChess(null);//�N������Ѯ�M��
		Grid_SetIcon(ChooseGrid,"");//�N������Ѯ�ϥܲM��
		
		Grid_SetIcon(ThisGrid,"Picture\\"+ choose.getChess_Class() + direction[choose.getChessPlayer()] +".png");//�b�s���Ѯ���ܹϥ�
		ThisGrid_Data.setChess(choose);//�b�s���Ѯ�g�J�s�� ����ܼ�(choose) �Ѥl
		
		choose.setPosition(Grid.getGrid_Id());//���ܴѤl�ۨ��p�J���y��
		choose = null;//����ܼ�(choose) �M�����V ->null
	}
	
	// ���ܴѤl
	private void RiseChange_Chess(){
		int Choose_Y = choose.getPosition()/10;
		int Choose_Player = choose.getChessPlayer();
	//------------------------------Function------------------------------//
		if(Choose_Player==0 && Choose_Y<=2 && Choose_Y>=0) choose.RiseChange_Chess();
		if(Choose_Player==1 && Choose_Y<=8 && Choose_Y>=6) choose.RiseChange_Chess();
	}
	
	// �������a
	private void SwitchPlayer(){
		Now_Player = Now_Player==1?0:1;
		First.setForeground(Color.red);
		if(Now_Player==1){
			After.setForeground(Color.black);
			First.setForeground(Color.red);
		}else {
			After.setForeground(Color.red);
			First.setForeground(Color.black);
		}
	}
	
	// ��ܥ��J�L
	private void InputBreakInGrid(Chess Chess){
		int ChessPlayer = Chess.getChessPlayer();
	//------------------------------Function------------------------------//
		for(int j=0;j<3;j++) { for(int i=0;i<3;i++) {
			JButton Grid = BreakInGrid[ChessPlayer][j][i];
			BreakIn_Data GridData = BreakInB[ChessPlayer][j][i];
			
			if(GridData.getGrid_Class()==Chess.getChess_Class()) {//���ۦP���������J�L��
				GridData.addChess(Chess); //�N���ѩ�J���J�L����|��
				BreakInGrid_TryEnable(Grid, GridData);
	}	}	}	}
	
	
	private void Grid_MarkUp(JButton Grid){//�аO��l
		Grid.setBackground(java.awt.Color.red);//�Q����I �аO
	}
	private void Grid_EraseMark(JButton Grid){//�����аO
		Grid.setBackground(java.awt.Color.white);//�N����аO���Q����I �аO����
	}
	private void BreakInGrid_TryEnable(JButton Grid , BreakIn_Data GridData) {//�֦̾��Ѥl�� �ҥΥ��J�L��
		if(GridData.getChess_quantity()!=0) //�p�G�ӥ��J�L�榳>1���X�l
			Grid.setEnabled(true); //�ϫ��s�i��
		else
			Grid.setEnabled(false); //�ϫ��s����
	}
	private void Grid_MarkErase(JButton Grid){//�����аO
		Grid.setBackground(java.awt.Color.white);//�Q����I �аO����
	}
	private void Grid_SetIcon(JButton Grid ,String link) {//�]�m�Ѯ�Ϥ�
		ImageIcon Icon = new ImageIcon(link);
		Grid.setIcon(Icon);
	}
}