package shogi;

import java.awt.event.ActionListener;

public abstract class CheckerGrid_Data implements ActionListener {
	
	private int Grid_Id = 0; //��l���s��
	private Chess Chessman = null; //�b�Ӯ檺�Ѥl
	
	//�غc��
	public CheckerGrid_Data(int i){
		this.Grid_Id = i;
	}
	
	//�^�� ��l�s��
	public int getGrid_Id(){
		return this.Grid_Id;
	}
	
	//�^�� �Ѥl
	public Chess getChess(){
		return this.Chessman;
	}
	
	//���� �Ѥl
	public void setChess(Chess Chessman){
		this.Chessman = Chessman;
	}
}