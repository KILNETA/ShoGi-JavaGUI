package shogi;

import java.awt.event.ActionListener;
import java.util.Stack;

public abstract class BreakIn_Data implements ActionListener {
	
	private String Grid_ChessName = null; //��l����l�ݩ�
	private int GridPlayer; //���ݪ��a
	Stack<Chess> Chessman = new Stack<Chess>() ; //�b�Ӯ檺�Ѥl
	
	//�غc��
	public BreakIn_Data(String Grid_ChessName ,int GridPlayer){
		this.Grid_ChessName = Grid_ChessName;
		this.GridPlayer = GridPlayer;
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
}
