package shogi;

import java.awt.EventQueue;
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

	
	private JFrame frame;
	//�ѽL�W��9x9�ӴѽL��
	JButton CheckerGrid[][] = new JButton[9][9];
	CheckerGrid_Data CheckerB[][]= new CheckerGrid_Data[9][9];
	//��Ӫ��a �U20�ӴѤl
	Chess Player[] = new Chess[40];
	Chess choose = null;


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
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setTitle("\u5C07\u68CB\u5927\u5E2B");
		//�e���j�p
		frame.setBounds(100, 100, 900, 900);
		//�]�m�q�{�����ާ@
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������e���� &�@�]�m�G��
		frame.getContentPane().setLayout(null);
		//���ε{���Y��
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));
		
		//��l��9x9���ѽL��
		for(int j=0;j<9;j++){
			for(int i=0;i<9;i++){
				//�s�ث��s
				CheckerGrid[j][i] = new JButton();
				//���s�ʧ@��ť��
				CheckerGrid[j][i].addActionListener(CheckerB[j][i]= new CheckerGrid_Data( ((j+1)*10)+i+1){
					
					public void actionPerformed(ActionEvent e) {
		/*�Ӯ�t���Ѥl*/	if(getChess()!=null )
			/*������Ѥl*/		if(choose == null ) {
								Pick_up_chess(this.getGrid_Id());				//���_�Ѥl
								choose.CanMove(CheckerGrid ,CheckerB);			//��ܥi�H�����a��
							}
			/*�w����Ѥl*/		else {
								if(Pick_back_chess(this.getGrid_Id())); 		//�P�_ + �Ѥl��^���
								else Eat_EnemyChess(this.getGrid_Id()); 		//�P�_ + �Y���Ĥ�Ѥl
							}
			
		/*�Ӯ椣�t����*/	else 
			/*�w����Ѥl*/		if(choose != null ) Move_chess(this.getGrid_Id());	//�P�_ + ���ʴѤl
			
					}	});
				//���s��m&�j�p
				CheckerGrid[j][i].setBounds(558-(66*i), 30+(80*j), 66, 80);
				//��e���W��ܫ��s
				frame.getContentPane().add(CheckerGrid[j][i]);
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
		
		//�аO�ѽL (�s�边��)	--------------------//
		JPanel panel = new JPanel();		//
		panel.setBounds(30, 30, 594, 720);	//
		frame.getContentPane().add(panel);	//
		//�аO�ѽL (�s�边��)	--------------------//

	}
	
	/**
	 * ��l�ƪ��a�Ѥl
	 */
	private void initialize_PlayerChess()
	{
		String Chess[]= {"After_king","Rook","Bishop","Gold general","Silver general","Knight","Lance","Pawn","First_king"};
		Player[4]=new Chess(Chess[0],0);
		Player[24]=new Chess(Chess[8],1);
		
		Player[9]=new Chess(Chess[1],0);
		Player[10]=new Chess(Chess[2],0);
		Player[29]=new Chess(Chess[2],1);
		Player[30]=new Chess(Chess[1],1);
		for(int i=0;i<=1;i++) {
			for(int j=0;j<=1;j++) {
				Player[3+(i*20)+(j*2)]=new Chess(Chess[3],i);
				Player[2+(i*20)+(j*4)]=new Chess(Chess[4],i);
				Player[1+(i*20)+(j*6)]=new Chess(Chess[5],i);
				Player[0+(i*20)+(j*8)]=new Chess(Chess[6],i);
			}
			for(int j=0;j<9;j++) {
				Player[11+(i*20)+j]=new Chess(Chess[7],i);
	}	}	}
	
	/**
	 * ��l�ƴѤl�\��
	 */
	private void initialize_CheckerChess(){
		String direction[]= {" Up"," Down"};
		for(int j=0;j<=1;j++){
			for(int i=0;i<9;i++){
				CheckerGrid[6-(j*4)][i].setIcon(new ImageIcon("Picture\\"+ Player[11+(j*20)+i].getChess() + direction[Player[11+(j*20)+i].getChessPlayer()] +".png"));
				CheckerB[6-(j*4)][i].setChess(Player[11+(j*20)+i]);
				Player[11+(j*20)+i].setPosition(CheckerB[6-(j*4)][i].getGrid_Id());
			}
			for(int i=0;i<2;i++){
				CheckerGrid[7-(j*6)][1+(i*6)].setIcon(new ImageIcon("Picture\\"+ Player[9+(j*20)+i].getChess() + direction[Player[9+(j*20)+i].getChessPlayer()] +".png"));
				CheckerB[7-(j*6)][1+(i*6)].setChess(Player[9+(j*20)+i]);
				Player[9+(j*20)+i].setPosition(CheckerB[7-(j*6)][(1+(i*6))].getGrid_Id());
			}
			for(int i=0;i<9;i++){
				CheckerGrid[8-(j*8)][i].setIcon(new ImageIcon("Picture\\"+ Player[0+(j*20)+i].getChess() + direction[Player[0+(j*20)+i].getChessPlayer()] +".png"));
				CheckerB[8-(j*8)][i].setChess(Player[0+(j*20)+i]);
				Player[0+(j*20)+i].setPosition(CheckerB[8-(j*8)][i].getGrid_Id());
	}	}	}
	
	/**
	 * ���_�Ѥl
	 */
	private void Pick_up_chess(int Grid_Number){
		CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].setBackground(java.awt.Color.red);
		choose = CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].getChess();
	}
	
	/**
	 * ��²��ʴѤl
	 */
	private void Move_chess(int Grid_Number){

		if( "��" == CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].getText()){
			choose.ResetCanMove(CheckerGrid ,CheckerB );		//���� ��~��ܥi�H�����a��
			RiseChange_Chess();
			Pick_down_chess(Grid_Number);
	}	}
	
	/**
	 * �Ѥl��^���
	 */
	private boolean Pick_back_chess(int Grid_Number){

		if(choose.getPosition() == Grid_Number){
			choose.ResetCanMove(CheckerGrid ,CheckerB );		//���� ��~��ܥi�H�����a��
			CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].setBackground(java.awt.Color.white);
			choose = null;
			
			return true;
	} return false;	}
	
	/*
	 * �Y���Ĥ�Ѥl
	 */
	private boolean Eat_EnemyChess(int Grid_Number){
		Chess ThisGrid_Chess = CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].getChess();
		if( choose.getChessPlayer() != ThisGrid_Chess.getChessPlayer() 
		&& 	"��" == CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].getText()){
			choose.ResetCanMove(CheckerGrid ,CheckerB );		//���� ��~��ܥi�H�����a��
			ThisGrid_Chess.setChessPlayer(choose.getChessPlayer());
			ThisGrid_Chess.setPosition(0);
			ThisGrid_Chess.Notlive();
			ThisGrid_Chess.DeclineChange_Chess();
			
			RiseChange_Chess();
			Pick_down_chess(Grid_Number); //��U�Ѥl
			return true;
	} return false;	}
	
	/*
	 * ��U�Ѥl
	 */
	private void Pick_down_chess(int Grid_Number){
		String direction[]= {" Up"," Down"};
		
		CheckerGrid[(choose.getPosition()/10)-1][(choose.getPosition()%10)-1].setBackground(java.awt.Color.white);
		CheckerB[(choose.getPosition()/10)-1][(choose.getPosition()%10)-1].setChess(null);
		CheckerGrid[(choose.getPosition()/10)-1][(choose.getPosition()%10)-1].setIcon(new ImageIcon());
		
		CheckerGrid[(Grid_Number/10)-1][(Grid_Number%10)-1].setIcon(new ImageIcon("Picture\\"+ choose.getChess() + direction[choose.getChessPlayer()] +".png"));
		CheckerB[(Grid_Number/10)-1][(Grid_Number%10)-1].setChess(choose);
		choose.setPosition(Grid_Number);
		choose = null;
	}
	
	/*
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
}