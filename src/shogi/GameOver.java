package shogi;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class GameOver extends JFrame {
	
	
/*-------------------------Variable-------------------------*/
	
	
	private Shogi Main_frame;


/*-------------------------Function-------------------------*/
	
	//創建應用程序。
	public GameOver(Shogi Main_frame,int whoWin) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.Main_frame = Main_frame;
		
		initialize(whoWin);
		setVisible(true);
	}

	//初始化框架的內容。
	private void initialize(int whoWin) {
		//創立主畫面
		new JFrame();
		//不可調整大小
		setResizable(false);
		//介面標題
		setTitle("\u5C07\u68CB\u5927\u5E2B \u52DD\u8CA0");
		//畫面大小
		setBounds(100, 100, 266, 150);
		//設置默認關閉操作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//獲取內容窗格 &　設置佈局
		getContentPane().setLayout(null);
		//應用程式縮圖
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));
		//窗口創建置中於屏幕
		setLocationRelativeTo(null);
		//設置主窗口停用
		Main_frame.getFrame().setEnabled(false);
		
		//介面GUI	----------------------------------------//
		JButton NewGame = new JButton("\u958B\u65B0\u4E00\u5C40");
		NewGame.setBounds(153, 78, 87, 23);
		NewGame.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_frame.Reset_allChecker();
				Main_frame.getFrame().setEnabled(true);
				dispose();
		}	});
		getContentPane().add(NewGame);
		
		JButton OverEnd = new JButton("\u7D50\u675F\u904A\u6232");
		OverEnd.setBounds(10, 78, 87, 23);
		OverEnd.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
		}	});
		getContentPane().add(OverEnd);
		
		JLabel Winner = new JLabel();
		Winner.setForeground(Color.RED);
		Winner.setFont(new Font("標楷體", Font.BOLD, 20));
		Winner.setHorizontalAlignment(SwingConstants.CENTER);
		Winner.setBounds(10, 30, 230, 30);
		getContentPane().add(Winner);
		
		switch(whoWin){
		case 0:Winner.setText("先手 勝利"); break;
		case 1:Winner.setText("後手 勝利"); break;
		case 2:Winner.setText("和局"); break;
		}
		
		//介面GUI	----------------------------------------//
	}
}
