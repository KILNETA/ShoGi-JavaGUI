package shogi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

import java.io.File;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	  *啟動應用程序。
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
	}	}	});	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//創立主畫面
		frame = new JFrame();
		frame.setResizable(false);
		//介面標題
		frame.setTitle("\u5C07\u68CB\u5927\u5E2B \u767B\u5165");
		//畫面大小
		frame.setBounds(100, 100, 450, 300);
		//設置默認關閉操作
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//獲取內容窗格 &　設置佈局
		frame.getContentPane().setLayout(null);
		//應用程式縮圖
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));
		
		
		//介面GUI	----------------------------------------//
		textField = new JTextField();
		textField.setBounds(145, 130, 200, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(145, 174, 200, 24);
		frame.getContentPane().add(passwordField);
		
		JButton Login = new JButton("\u767B\u5165");
		Login.setBounds(282, 220, 80, 26);
		Login.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				String password = new String(passwordField.getPassword());
		        /* 讀入TXT檔案 */
				
				//檢查帳號密碼是否填寫正確
				if( textField.getText().compareTo("")!=0 && password.compareTo("")!=0 ) {
		        try {
		        	//如果檔案不存在 創建空檔案
		        	if(!new File("Login.txt").exists()) new File("Login.txt").createNewFile();
		        
		            InputStreamReader reader = new InputStreamReader(    new FileInputStream(new File("Login.txt"))    ); // 建立一個輸入流物件reader
		            BufferedReader br = new BufferedReader(reader); // 建立一個物件，它把檔案內容轉成計算機能讀懂的語言
		            String line = "";
		            
		            while (line != null) {
		                line = br.readLine(); // 一次讀入一行資料
		                
		                if(line!=null) {
		                    String[] splitted = line.split(" ");       
		                    if(splitted[0].compareTo(textField.getText())==0 && splitted[1].compareTo(password)==0)
		                    {
		                    	System.out.println("登入成功");
		                    	return;
		            }	}	}
		            System.out.println("帳號密碼錯誤");
		            return;
		        }
		        catch (Exception e1) {
		            e1.printStackTrace();
		        }
		}	}	});
		frame.getContentPane().add(Login);
		
		JButton TryItOut = new JButton("\u8A66\u73A9");
		TryItOut.setBounds(177, 220, 80, 26);
		TryItOut.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
		           frame.dispose();
		}	});
		frame.getContentPane().add(TryItOut);
		
		JButton Register = new JButton("\u8A3B\u518A");
		Register.setBounds(72, 220, 80, 26);
		Register.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				String password = new String(passwordField.getPassword());
				
				//檢查帳號密碼是否填寫正確
				if( textField.getText().compareTo("")!=0 && password.compareTo("")!=0 ) {
				try {
			        //如果檔案不存在 創建空檔案
			    	if(	!new File("Login.txt").exists()	) new File("Login.txt").createNewFile();
			    	
			           	InputStreamReader reader = new InputStreamReader(    new FileInputStream(new File("Login.txt"))    ); // 建立一個輸入流物件reader
			           	BufferedReader br = new BufferedReader(reader); // 建立一個物件，它把檔案內容轉成計算機能讀懂的語言
			           	String line = "";
			            
			           	while (	line != null	) {
			               	line = br.readLine(); // 一次讀入一行資料
			               	if(	line!=null	) {
			                   	String[] splitted = line.split(" ");    
			                   	if(splitted[0].compareTo(textField.getText())==0){
			                   		System.out.println("此帳戶已被註冊");	return;
			        }   }	}	}
				catch (Exception e1) { e1.printStackTrace();	}
					
			        try {	        	
			            /* 寫入txt檔案 */
			            BufferedWriter out = new BufferedWriter(new FileWriter("Login.txt",true));
			            out.write(textField.getText() + " " + password + "\r\n"); // \r\n即為換行
			            out.close(); // 最後記得關閉檔案
			        } 
			        catch (Exception e1) { e1.printStackTrace();	}	
			    }
				else; //輸出未正確輸出入帳號密碼-----------------------------------++++
		}	}	);
		frame.getContentPane().add(Register);
		
		JLabel ShoGi_LOGO = new JLabel(new ImageIcon("Picture\\First_king Up.png"));
		ShoGi_LOGO.setHorizontalAlignment(SwingConstants.LEADING);
		ShoGi_LOGO.setText("");
		ShoGi_LOGO.setBounds(184, 10, 66, 80);
		frame.getContentPane().add(ShoGi_LOGO);
		
		JLabel ShoGi_Titel = new JLabel("\u5C07\u68CB\u5927\u5E2B");
		ShoGi_Titel.setFont(new Font("標楷體", Font.BOLD, 20));
		ShoGi_Titel.setHorizontalAlignment(SwingConstants.CENTER);
		ShoGi_Titel.setBounds(172, 90, 90, 24);
		frame.getContentPane().add(ShoGi_Titel);
		
		JLabel textField_Titel = new JLabel("\u5E33\u3000\u865F\uFF1A");
		textField_Titel.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_Titel.setBounds(78, 134, 57, 15);
		frame.getContentPane().add(textField_Titel);
		
		JLabel PasswordField_Titel = new JLabel("\u5BC6\u3000\u78BC\uFF1A");
		PasswordField_Titel.setHorizontalAlignment(SwingConstants.RIGHT);
		PasswordField_Titel.setBounds(78, 178, 57, 15);
		frame.getContentPane().add(PasswordField_Titel);
		//介面GUI	----------------------------------------//
	}
}
