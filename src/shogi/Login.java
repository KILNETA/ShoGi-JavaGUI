package shogi;

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
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Login extends JFrame  {
	
	
/*-------------------------Variable-------------------------*/
	

	private JFrame Main_frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel Point_Text ;

	
	
/*-------------------------Function-------------------------*/
	
	//創建應用程序。
	public Login(JFrame Main_frame) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.Main_frame = Main_frame;
		
		initialize();
		setVisible(true);
	}

	//初始化框架的內容。
	private void initialize() {
		//創立主畫面
		new JFrame();
		//不可調整大小
		setResizable(false);
		//介面標題
		setTitle("\u5C07\u68CB\u5927\u5E2B \u767B\u5165");
		//畫面大小
		setBounds(100, 100, 450, 300);
		//設置默認關閉操作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//獲取內容窗格 &　設置佈局
		getContentPane().setLayout(null);
		//應用程式縮圖
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));
		//窗口創建置中於屏幕
		setLocationRelativeTo(null);
		
		
		//介面GUI	----------------------------------------//
		Create_GeneralInterface();
		Create_RegisterButton();
		Create_TryItOutButton();
		Create_LoginButton();
		//介面GUI	----------------------------------------//
	}
	
	//創建介面普通GUI
	private void Create_GeneralInterface(){
		
		Point_Text = new JLabel("");
		Point_Text.setFont(new Font("標楷體", Font.PLAIN, 15));
		Point_Text.setHorizontalAlignment(SwingConstants.CENTER);
		Point_Text.setBounds(0, 188, 434, 24);
		getContentPane().add(Point_Text);
		
		textField = new JTextField();
		textField.setBounds(145, 115, 200, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(145, 159, 200, 24);
		getContentPane().add(passwordField);
		
		JLabel ShoGi_LOGO = new JLabel(new ImageIcon("Picture\\First_king Up.png"));
		ShoGi_LOGO.setHorizontalAlignment(SwingConstants.LEADING);
		ShoGi_LOGO.setText("");
		ShoGi_LOGO.setBounds(184, 5, 66, 80);
		getContentPane().add(ShoGi_LOGO);
		
		JLabel ShoGi_Titel = new JLabel("\u5C07\u68CB\u5927\u5E2B");
		ShoGi_Titel.setFont(new Font("標楷體", Font.BOLD, 20));
		ShoGi_Titel.setHorizontalAlignment(SwingConstants.CENTER);
		ShoGi_Titel.setBounds(172, 80, 90, 24);
		getContentPane().add(ShoGi_Titel);
		
		JLabel textField_Titel = new JLabel("\u5E33\u3000\u865F\uFF1A");
		textField_Titel.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_Titel.setBounds(78, 119, 57, 15);
		getContentPane().add(textField_Titel);
		
		JLabel PasswordField_Titel = new JLabel("\u5BC6\u3000\u78BC\uFF1A");
		PasswordField_Titel.setHorizontalAlignment(SwingConstants.RIGHT);
		PasswordField_Titel.setBounds(78, 163, 57, 15);
		getContentPane().add(PasswordField_Titel);	
	}
	
	//創建介面註冊按鈕GUI
	private void Create_RegisterButton() {
		JButton Register = new JButton("\u8A3B\u518A");
		Register.setBounds(72, 220, 80, 26);
		Register.addActionListener(	new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				
				if(	has_InputError(password) || has_SpecialSymbol(password)	)	return;
				
				try {
			        //如果檔案不存在 創建空檔案
					has_AccountSecretFile();
			    	
			        InputStreamReader reader = new InputStreamReader(    new FileInputStream(new File("Login.txt"))    ); // 建立一個輸入流物件reader
			        BufferedReader br = new BufferedReader(reader); // 建立一個物件，它把檔案內容轉成計算機能讀懂的語言
			        String line = "";
			            
			        while (	line != null	) {
			        	line = br.readLine(); // 一次讀入一行資料
			        	
			            if(	line!=null && has_SameUser(line) ) {
			            	Point_Text.setText(textField.getText()+" 此帳戶已被註冊");	
			                return;
			    }   }	}
				catch (Exception e1) 
				{ e1.printStackTrace();}
			Write_newAccountSecret_inFile(password);	
		}	}	);
		getContentPane().add(Register);
	}
	
	//創建介面試玩按鈕GUI
	private void Create_TryItOutButton() {
		JButton TryItOut = new JButton("\u8A66\u73A9");
		TryItOut.setBounds(177, 220, 80, 26);
		TryItOut.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point_Text.setText("歡迎試玩");
				Enabled_MainFrame_and_Close_LoginFrame();
		}	});
		getContentPane().add(TryItOut);
	}
	
	//創建介面登入按鈕GUI
	private void Create_LoginButton() {
		JButton Login = new JButton("\u767B\u5165");
		Login.setBounds(282, 220, 80, 26);
		Login.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());	
				
				if(	has_InputError(password) || has_SpecialSymbol(password)	)	return;
				/* 讀入TXT檔案 */
		        try {
		        	//如果檔案不存在 創建空檔案
		        	has_AccountSecretFile();
		        
		            InputStreamReader reader = new InputStreamReader(    new FileInputStream(new File("Login.txt"))    ); // 建立一個輸入流物件reader
		            BufferedReader br = new BufferedReader(reader); // 建立一個物件，它把檔案內容轉成計算機能讀懂的語言
		            String line = "";
		            
		            while (line != null) {
		                line = br.readLine(); // 一次讀入一行資料
		                
		                if(line!=null && has_SameAccountSecret(line ,password)) {
		                	Point_Text.setText("用戶 "+textField.getText()+" 登入成功");
		                	Enabled_MainFrame_and_Close_LoginFrame();
		                    return;
		            }	}	
		            Point_Text.setText("查無此帳號 或 密碼錯誤");
		            return;
		        }
		        catch (Exception e1) {
		            e1.printStackTrace();
		}	}	});
		getContentPane().add(Login);
	}
	
	//寫入新帳密 -> 帳密資料庫
	private void Write_newAccountSecret_inFile(String password){
		try {	        	
            /* 寫入txt檔案 */
            BufferedWriter out = new BufferedWriter(new FileWriter("Login.txt",true));
            out.write(textField.getText() + " " + password + "\r\n"); // \r\n即為換行
            out.close(); // 最後記得關閉檔案
            Point_Text.setText(textField.getText()+" 帳號註冊成功");
        } 
        catch (Exception e1) { e1.printStackTrace();	}	
	}
	
	//檢查帳號密碼是否填寫正確 帳號密碼填寫錯誤
	private boolean has_InputError(String password) {
		if( textField.getText().compareTo("")==0 || password.compareTo("")==0 ) {
			Point_Text.setText("未正確輸入帳號及密碼"); //輸出 未正確輸出入帳號密碼
			return true;
		}
		return false;
	}
	
	//檢查帳號密碼是否填寫正確 帳號密碼是否有特殊符號
	private boolean has_SpecialSymbol(String password) {
		String regEx = "[ _`~!@#$%^&*()+=|{}‘:;‘,\\[\\].<>/?~！@#¥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
		if( Pattern.compile(regEx).matcher(textField.getText()).find() 
		||  Pattern.compile(regEx).matcher(password).find()) {

			Point_Text.setText("帳號或密碼含有特殊符號"); //輸出 帳號密碼含有特殊符號
			return true;
		}
		return false;
	}
	
	//檢查帳密資料庫是否存在 若無則創建
	private void has_AccountSecretFile() {
		if(!new File("Login.txt").exists()) {
			try {
				new File("Login.txt").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
	}	}	}

	//搜尋表單有無相對應的密碼組
	private boolean has_SameAccountSecret(String line ,String password){
		String[] splitted = line.split(" ");       
        if(splitted[0].compareTo(textField.getText())==0 && splitted[1].compareTo(password)==0){
        	return true;
        }
		return false;
	}
	
	//搜尋表單有無相同用戶名
	private boolean has_SameUser(String line){
		String[] splitted = line.split(" ");       
	    if(splitted[0].compareTo(textField.getText())==0){
	        return true;
	    }
		return false;
	}

	//啟用並顯示主介面  關閉登入介面
	//顯示主畫面 and 關閉登入介面
	private void Enabled_MainFrame_and_Close_LoginFrame() {
		Main_frame.setEnabled(true);
	    Main_frame.setVisible(true);
	    	
	    dispose();
		}
	
}
