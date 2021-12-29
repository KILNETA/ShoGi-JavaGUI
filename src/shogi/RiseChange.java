package shogi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RiseChange extends JButton {
	
	private int GridPlayer; //所屬玩家
	private boolean UseRise = false ;
	
	//建構者
	public RiseChange(int GridPlayer){
		this.GridPlayer = GridPlayer;
		
		//初始化3x3的棋盤格
		new JButton();//新建按鈕
		setText("升變");
		setFont(new Font("標楷體", Font.BOLD, 14));
		setBounds(822-(742*GridPlayer), 440-(125*GridPlayer), 128, 25);
		setFocusPainted(false);//去除聚焦線
		setEnabled(false);//禁止使用
		addActionListener(new ActionListener(){//按鈕動作監聽器
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