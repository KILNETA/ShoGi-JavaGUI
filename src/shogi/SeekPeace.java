package shogi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SeekPeace extends JButton {
	
	private int GridPlayer; //���ݪ��a
	
	//�غc��
	public SeekPeace(int GridPlayer){
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

			}	});
	}
}