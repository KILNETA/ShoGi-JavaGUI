package shogi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RiseChange extends JButton {
	
	private int GridPlayer; //���ݪ��a
	private boolean UseRise = false ;
	
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
				if(Shogi.choose != null) {
					ChangeUseRise();
				}
			}	});
	}
	
	public boolean UseRise() {
		return UseRise;
	}
	
	public void ChangeUseRise() {
		UseRise = UseRise ? false : true ;
		ChangeColor();
	}
	
	public void NoUseRise() {
		UseRise = false;
		ChangeColor();
	}
	
	public boolean RiseIsEnabled() {
		return isEnabled();
	}
	
	public void RiseCanUse() {
		setEnabled(true);
	}
	
	public void RiseCntUse() {
		setEnabled(false);
	}
	
	public void ChangeColor() {
		if(UseRise)
			setBackground(Color.YELLOW);
		else
			setBackground(Color.WHITE);
	}
}