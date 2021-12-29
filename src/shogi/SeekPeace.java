package shogi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SeekPeace extends JButton {
	
	private int GridPlayer; //所屬玩家
	
	//建構者
	public SeekPeace(int GridPlayer){
		this.GridPlayer = GridPlayer;
		
		//初始化3x3的棋盤格
		new JButton();//新建按鈕
		setText("求和");
		setForeground(Color.RED);
		setBackground(Color.RED);
		setFont(new Font("標楷體", Font.BOLD, 14));
		setBounds(955-(945*GridPlayer), 440-(125*GridPlayer), 65, 25);
		setFocusPainted(false);//去除聚焦線
		addActionListener(new ActionListener(){//按鈕動作監聽器
			public void actionPerformed(ActionEvent e) {

			}	});
	}
}