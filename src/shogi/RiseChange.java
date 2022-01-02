package shogi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RiseChange extends JButton {
	
	
/*-------------------------Variable-------------------------*/
	
	
	private int GridPlayer; //���ݪ��a
	private boolean UseRise = false ;


/*-------------------------Function-------------------------*/
	
	//�غc��
	public RiseChange(int GridPlayer){
		this.GridPlayer = GridPlayer;
		
		//��l��3x3���ѽL��
		new JButton();//�s�ث��s
		setText("����");
		setFont(new Font("�з���", Font.BOLD, 14));
		setBounds(822-(742*GridPlayer), 440-(125*GridPlayer), 128, 25);
		setFocusPainted(false);//�h���E�J�u
		setEnabled(false);//�T��ϥ�
		addActionListener(new ActionListener(){//���s�ʧ@��ť��
			public void actionPerformed(ActionEvent e) {
				if(Shogi.choose != null)
					ChangeUseRise();
	}	});	}
	
	//�^�� �O�_�w��ܤ���
	public boolean UseRise() {
		return UseRise;
	}
	
	//���ܫ��s
	public void ChangeUseRise() {
		UseRise = UseRise ? false : true ;
		ChangeColor();
	}
	
	//�]�m���s�ƾ� (���i��)
	public void NoUseRise() {
		UseRise = false;
		ChangeColor();
	}
	
	//�^�� ���s�O�_����
	public boolean RiseIsEnabled() {
		return isEnabled();
	}
	
	//�]�m���s (�i��)
	public void RiseCanUse() {
		setEnabled(true);
	}
	
	//�]�m���s (���i��)
	public void RiseCntUse() {
		setEnabled(false);
	}
	
	//�]�m���s�C��
	public void ChangeColor() {
		if(UseRise)
			setBackground(Color.ORANGE);
		else
			setBackground(Color.WHITE);
	}
}