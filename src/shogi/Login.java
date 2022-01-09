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
	
	//�Ы����ε{�ǡC
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

	//��l�Ʈج[�����e�C
	private void initialize() {
		//�ХߥD�e��
		new JFrame();
		//���i�վ�j�p
		setResizable(false);
		//�������D
		setTitle("\u5C07\u68CB\u5927\u5E2B \u767B\u5165");
		//�e���j�p
		setBounds(100, 100, 450, 300);
		//�]�m�q�{�����ާ@
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������e���� &�@�]�m�G��
		getContentPane().setLayout(null);
		//���ε{���Y��
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));
		//���f�Ыظm����̹�
		setLocationRelativeTo(null);
		
		
		//����GUI	----------------------------------------//
		Create_GeneralInterface();
		Create_RegisterButton();
		Create_TryItOutButton();
		Create_LoginButton();
		//����GUI	----------------------------------------//
	}
	
	//�Ыؤ������qGUI
	private void Create_GeneralInterface(){
		
		Point_Text = new JLabel("");
		Point_Text.setFont(new Font("�з���", Font.PLAIN, 15));
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
		ShoGi_Titel.setFont(new Font("�з���", Font.BOLD, 20));
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
	
	//�Ыؤ������U���sGUI
	private void Create_RegisterButton() {
		JButton Register = new JButton("\u8A3B\u518A");
		Register.setBounds(72, 220, 80, 26);
		Register.addActionListener(	new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				
				if(	has_InputError(password) || has_SpecialSymbol(password)	)	return;
				
				try {
			        //�p�G�ɮפ��s�b �Ыت��ɮ�
					has_AccountSecretFile();
			    	
			        InputStreamReader reader = new InputStreamReader(    new FileInputStream(new File("Login.txt"))    ); // �إߤ@�ӿ�J�y����reader
			        BufferedReader br = new BufferedReader(reader); // �إߤ@�Ӫ���A�����ɮפ��e�ন�p�����Ū�����y��
			        String line = "";
			            
			        while (	line != null	) {
			        	line = br.readLine(); // �@��Ū�J�@����
			        	
			            if(	line!=null && has_SameUser(line) ) {
			            	Point_Text.setText(textField.getText()+" ���b��w�Q���U");	
			                return;
			    }   }	}
				catch (Exception e1) 
				{ e1.printStackTrace();}
			Write_newAccountSecret_inFile(password);	
		}	}	);
		getContentPane().add(Register);
	}
	
	//�Ыؤ����ժ����sGUI
	private void Create_TryItOutButton() {
		JButton TryItOut = new JButton("\u8A66\u73A9");
		TryItOut.setBounds(177, 220, 80, 26);
		TryItOut.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point_Text.setText("�w��ժ�");
				Enabled_MainFrame_and_Close_LoginFrame();
		}	});
		getContentPane().add(TryItOut);
	}
	
	//�Ыؤ����n�J���sGUI
	private void Create_LoginButton() {
		JButton Login = new JButton("\u767B\u5165");
		Login.setBounds(282, 220, 80, 26);
		Login.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());	
				
				if(	has_InputError(password) || has_SpecialSymbol(password)	)	return;
				/* Ū�JTXT�ɮ� */
		        try {
		        	//�p�G�ɮפ��s�b �Ыت��ɮ�
		        	has_AccountSecretFile();
		        
		            InputStreamReader reader = new InputStreamReader(    new FileInputStream(new File("Login.txt"))    ); // �إߤ@�ӿ�J�y����reader
		            BufferedReader br = new BufferedReader(reader); // �إߤ@�Ӫ���A�����ɮפ��e�ন�p�����Ū�����y��
		            String line = "";
		            
		            while (line != null) {
		                line = br.readLine(); // �@��Ū�J�@����
		                
		                if(line!=null && has_SameAccountSecret(line ,password)) {
		                	Point_Text.setText("�Τ� "+textField.getText()+" �n�J���\");
		                	Enabled_MainFrame_and_Close_LoginFrame();
		                    return;
		            }	}	
		            Point_Text.setText("�d�L���b�� �� �K�X���~");
		            return;
		        }
		        catch (Exception e1) {
		            e1.printStackTrace();
		}	}	});
		getContentPane().add(Login);
	}
	
	//�g�J�s�b�K -> �b�K��Ʈw
	private void Write_newAccountSecret_inFile(String password){
		try {	        	
            /* �g�Jtxt�ɮ� */
            BufferedWriter out = new BufferedWriter(new FileWriter("Login.txt",true));
            out.write(textField.getText() + " " + password + "\r\n"); // \r\n�Y������
            out.close(); // �̫�O�o�����ɮ�
            Point_Text.setText(textField.getText()+" �b�����U���\");
        } 
        catch (Exception e1) { e1.printStackTrace();	}	
	}
	
	//�ˬd�b���K�X�O�_��g���T �b���K�X��g���~
	private boolean has_InputError(String password) {
		if( textField.getText().compareTo("")==0 || password.compareTo("")==0 ) {
			Point_Text.setText("�����T��J�b���αK�X"); //��X �����T��X�J�b���K�X
			return true;
		}
		return false;
	}
	
	//�ˬd�b���K�X�O�_��g���T �b���K�X�O�_���S��Ÿ�
	private boolean has_SpecialSymbol(String password) {
		String regEx = "[ _`~!@#$%^&*()+=|{}��:;��,\\[\\].<>/?~�I@#�D%�K�K&*�]�^�X�X+|{}�i�j���F�G�������C�A�B�H]|\n|\r|\t";
		if( Pattern.compile(regEx).matcher(textField.getText()).find() 
		||  Pattern.compile(regEx).matcher(password).find()) {

			Point_Text.setText("�b���αK�X�t���S��Ÿ�"); //��X �b���K�X�t���S��Ÿ�
			return true;
		}
		return false;
	}
	
	//�ˬd�b�K��Ʈw�O�_�s�b �Y�L�h�Ы�
	private void has_AccountSecretFile() {
		if(!new File("Login.txt").exists()) {
			try {
				new File("Login.txt").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
	}	}	}

	//�j�M��榳�L�۹������K�X��
	private boolean has_SameAccountSecret(String line ,String password){
		String[] splitted = line.split(" ");       
        if(splitted[0].compareTo(textField.getText())==0 && splitted[1].compareTo(password)==0){
        	return true;
        }
		return false;
	}
	
	//�j�M��榳�L�ۦP�Τ�W
	private boolean has_SameUser(String line){
		String[] splitted = line.split(" ");       
	    if(splitted[0].compareTo(textField.getText())==0){
	        return true;
	    }
		return false;
	}

	//�ҥΨ���ܥD����  �����n�J����
	//��ܥD�e�� and �����n�J����
	private void Enabled_MainFrame_and_Close_LoginFrame() {
		Main_frame.setEnabled(true);
	    Main_frame.setVisible(true);
	    	
	    dispose();
		}
	
}
