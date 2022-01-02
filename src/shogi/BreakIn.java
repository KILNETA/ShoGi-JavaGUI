package shogi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;

public class BreakIn  extends JButton {
	
	
/*-------------------------Variable-------------------------*/
	
	
	private int Grid_Id = 0 ;
	private String Grid_ChessName = null; //��l����l�ݩ�
	private int GridPlayer; //���ݪ��a
	private Stack<Chess> Chessman = new Stack<Chess>() ; //�b�Ӯ檺�Ѥl


/*-------------------------Function-------------------------*/
	
	//�غc��
	public BreakIn(String Grid_ChessName ,int GridPlayer ,int Grid_Id){
		this.Grid_ChessName = Grid_ChessName;
		this.GridPlayer = GridPlayer;
		this.Grid_Id = Grid_Id ;
		
		//��l��3x3���ѽL��
		new JButton();//�s�ث��s
		setFocusPainted(false);//�h���E�J�u
		setVerticalTextPosition(JButton.CENTER);//�]�m�����奻 �m��
		setHorizontalTextPosition(JButton.CENTER);//�]�m�����奻 �m��
		setForeground(Color.BLUE);//�奻�C�� ����
		setEnabled(false);//�T��ϥ�
		addActionListener(new ActionListener(){//���s�ʧ@��ť��
			public void actionPerformed(ActionEvent e) {
				if(Shogi.choose == null){
					if( Pick_up_chess(this) )//���_�Ѥl
					Shogi.choose.CanMove_BreakIn (Shogi.CheckerGrid ,Shogi.Now_Player!=0?Shogi.PlayerChess[4]:Shogi.PlayerChess[24]);//��ܥi�H�����a��
				}	
				else Pick_back_chess(this);//�P�_ + �Ѥl��^���
				}
			});
		setBounds(822+66*(Grid_Id%10)-(812*GridPlayer), 510+80*(Grid_Id/10)-(480*GridPlayer), 66, 80);//���s��m&�j�p
	}

	public int getGrid_Id(){
		return this.Grid_Id;
	}
	
	//�o���֦����a
	public int getPlayer(){
		return this.GridPlayer;
	}
	
	//������l�ݩ�
	public String getGrid_Class(){
		return this.Grid_ChessName;
	}
	
	//���X �Ѥl
	public Chess reduceChess(){
		return this.Chessman.pop();
	}
	
	//�����ثe�t�����Ѥl��
	public int getChess_quantity(){
		return this.Chessman.size();
	}
		
	//�K�J �Ѥl
	public void addChess(Chess choose){
		this.Chessman.push(choose);
	}
	
	// ���_�Ѥl
	private boolean Pick_up_chess(ActionListener actionListener){//���J�L
		Chess Chess = this.reduceChess();
		int ChessPlayer = this.getPlayer();
		JButton ThisGrid = Shogi.BreakInGrid[ChessPlayer][this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(ChessPlayer == Shogi.Now_Player){//�P�_������Ѥl �O���O �ۤv��
			Grid_MarkUp(ThisGrid);
			Shogi.choose = Chess;//����ܼ�(choose) ���V -> �Q������Ѥl
			return true;//�^�ǿ�����\
		}
		this.addChess(Chess);//��^���J�ѽL
		return false;//�^�ǿ������
	}
	
	// �Ѥl��^���
	private void Pick_back_chess(ActionListener actionListener){//���J�L
		JButton ThisGrid = Shogi.BreakInGrid[Shogi.choose.getChessPlayer()][this.getGrid_Id()/10][this.getGrid_Id()%10];
	//------------------------------Function------------------------------//
		if(this.getGrid_Class() == Shogi.choose.getChess_Class() && !Shogi.choose.Live()) {
			Shogi.choose.EraseCanMove_BreakIn (Shogi.CheckerGrid );//���� ��~��ܥi�H�����a��
			Grid_MarkErase(ThisGrid);//�N����аO���Q����I �аO����
			this.addChess(Shogi.choose);
			Shogi.choose = null;
	}	}
	
	
	private void Grid_MarkUp(JButton Grid){//�аO��l
		Grid.setBackground(Color.red);//�Q����I �аO
	}
	private void Grid_MarkErase(JButton Grid){//�����аO
		Grid.setBackground(Color.white);//�Q����I �аO����
	}
	public void Reset(){//���m�Ѯ�
		Chessman.clear();
	}
}
