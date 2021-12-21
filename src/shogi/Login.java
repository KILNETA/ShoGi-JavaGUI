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
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Login {

	private JFrame Main_frame;
	private JFrame Login_frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	  *�Ұ����ε{�ǡC
	 */


	/**
	 * Create the application.
	 */
	public Login(JFrame Main_frame) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.Main_frame = Main_frame;
		
		initialize();
		Login_frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//�ХߥD�e��
		Login_frame = new JFrame();
		Login_frame.setResizable(false);
		//�������D
		Login_frame.setTitle("\u5C07\u68CB\u5927\u5E2B \u767B\u5165");
		//�e���j�p
		Login_frame.setBounds(100, 100, 450, 300);
		//�]�m�q�{�����ާ@
		Login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������e���� &�@�]�m�G��
		Login_frame.getContentPane().setLayout(null);
		//���ε{���Y��
		Login_frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));
		
		
		//����GUI	----------------------------------------//
		JLabel Point_Text = new JLabel("");
		Point_Text.setFont(new Font("�з���", Font.PLAIN, 15));
		Point_Text.setHorizontalAlignment(SwingConstants.CENTER);
		Point_Text.setBounds(0, 188, 434, 24);
		Login_frame.getContentPane().add(Point_Text);
		
		textField = new JTextField();
		textField.setBounds(145, 115, 200, 24);
		Login_frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(145, 159, 200, 24);
		Login_frame.getContentPane().add(passwordField);
		
		JButton Login = new JButton("\u767B\u5165");
		Login.setBounds(282, 220, 80, 26);
		Login.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				String password = new String(passwordField.getPassword());
				
				//�ˬd�b���K�X�O�_��g���T �b���K�X��g���~
				if( textField.getText().compareTo("")==0 || password.compareTo("")==0 ) {
					Point_Text.setText("�����T��J�b���αK�X"); //��X �����T��X�J�b���K�X
					return; //�����ާ@����
				}
				
				//�ˬd�b���K�X�O�_��g���T �b���K�X�O�_���S��Ÿ�
				String regEx = "[ _`~!@#$%^&*()+=|{}��:;��,\\[\\].<>/?~�I@#�D%�K�K&*�]�^�X�X+|{}�i�j���F�G�������C�A�B�H]|\n|\r|\t";
				if( Pattern.compile(regEx).matcher(textField.getText()).find() 
				||  Pattern.compile(regEx).matcher(password).find()) {

					Point_Text.setText("�b���αK�X�t���S��Ÿ�"); //��X �b���K�X�t���S��Ÿ�
					return; //�����ާ@����
				}
					
				/* Ū�JTXT�ɮ� */
		        try {
		        	//�p�G�ɮפ��s�b �Ыت��ɮ�
		        	if(!new File("Login.txt").exists()) new File("Login.txt").createNewFile();
		        
		            InputStreamReader reader = new InputStreamReader(    new FileInputStream(new File("Login.txt"))    ); // �إߤ@�ӿ�J�y����reader
		            BufferedReader br = new BufferedReader(reader); // �إߤ@�Ӫ���A�����ɮפ��e�ন�p�����Ū�����y��
		            String line = "";
		            
		            while (line != null) {
		                line = br.readLine(); // �@��Ū�J�@����
		                
		                if(line!=null) {
		                    String[] splitted = line.split(" ");       
		                    if(splitted[0].compareTo(textField.getText())==0 && splitted[1].compareTo(password)==0)
		                    {
		                    	Point_Text.setText("�Τ� "+textField.getText()+" �n�J���\");
		                    	Main_frame.setEnabled(true);
		                    	Main_frame.setVisible(true);
		                    	Login_frame.dispose();
		                    	return;
		            }	}	}
		            Point_Text.setText("�d�L���b�� �� �K�X���~");
		            return;
		        }
		        catch (Exception e1) {
		            e1.printStackTrace();
		}	}	});
		Login_frame.getContentPane().add(Login);
		
		JButton TryItOut = new JButton("\u8A66\u73A9");
		TryItOut.setBounds(177, 220, 80, 26);
		TryItOut.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				Point_Text.setText("�w��ժ�");
				Main_frame.setEnabled(true);
				Main_frame.setVisible(true);
				Login_frame.dispose();
		}	});
		Login_frame.getContentPane().add(TryItOut);
		
		JButton Register = new JButton("\u8A3B\u518A");
		Register.setBounds(72, 220, 80, 26);
		Register.addActionListener(	new ActionListener() {
			public void actionPerformed(ActionEvent e4) {
				String password = new String(passwordField.getPassword());
				
				//�ˬd�b���K�X�O�_��g���T �b���K�X��g���~
				if( textField.getText().compareTo("")==0 || password.compareTo("")==0 ) {
					Point_Text.setText("�����T��J�b���αK�X"); //��X �����T��X�J�b���K�X
					return; //�����ާ@����
				}
				
				//�ˬd�b���K�X�O�_��g���T �b���K�X�O�_���S��Ÿ�
				String regEx = "[ _`~!@#$%^&*()+=|{}��:;��,\\[\\].<>/?~�I@#�D%�K�K&*�]�^�X�X+|{}�i�j���F�G�������C�A�B�H]|\n|\r|\t";
				if( Pattern.compile(regEx).matcher(textField.getText()).find() 
				||  Pattern.compile(regEx).matcher(password).find()) {

					Point_Text.setText("�b���αK�X�t���S��Ÿ�"); //��X �b���K�X�t���S��Ÿ�
					return; //�����ާ@����
				}
				
				try {
			        //�p�G�ɮפ��s�b �Ыت��ɮ�
			    	if(	!new File("Login.txt").exists()	) new File("Login.txt").createNewFile();
			    	
			           	InputStreamReader reader = new InputStreamReader(    new FileInputStream(new File("Login.txt"))    ); // �إߤ@�ӿ�J�y����reader
			           	BufferedReader br = new BufferedReader(reader); // �إߤ@�Ӫ���A�����ɮפ��e�ন�p�����Ū�����y��
			           	String line = "";
			            
			           	while (	line != null	) {
			               	line = br.readLine(); // �@��Ū�J�@����
			               	if(	line!=null	) {
			                   	String[] splitted = line.split(" ");    
			                   	if(splitted[0].compareTo(textField.getText())==0){
			                   		Point_Text.setText(textField.getText()+" ���b��w�Q���U");	return;
			        }   }	}	}
				catch (Exception e1) { e1.printStackTrace();	}
					
			        try {	        	
			            /* �g�Jtxt�ɮ� */
			            BufferedWriter out = new BufferedWriter(new FileWriter("Login.txt",true));
			            out.write(textField.getText() + " " + password + "\r\n"); // \r\n�Y������
			            out.close(); // �̫�O�o�����ɮ�
			            Point_Text.setText(textField.getText()+" �b�����U���\");
			        } 
			        catch (Exception e1) { e1.printStackTrace();	}	
		}	}	);
		Login_frame.getContentPane().add(Register);
		
		JLabel ShoGi_LOGO = new JLabel(new ImageIcon("Picture\\First_king Up.png"));
		ShoGi_LOGO.setHorizontalAlignment(SwingConstants.LEADING);
		ShoGi_LOGO.setText("");
		ShoGi_LOGO.setBounds(184, 5, 66, 80);
		Login_frame.getContentPane().add(ShoGi_LOGO);
		
		JLabel ShoGi_Titel = new JLabel("\u5C07\u68CB\u5927\u5E2B");
		ShoGi_Titel.setFont(new Font("�з���", Font.BOLD, 20));
		ShoGi_Titel.setHorizontalAlignment(SwingConstants.CENTER);
		ShoGi_Titel.setBounds(172, 80, 90, 24);
		Login_frame.getContentPane().add(ShoGi_Titel);
		
		JLabel textField_Titel = new JLabel("\u5E33\u3000\u865F\uFF1A");
		textField_Titel.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_Titel.setBounds(78, 119, 57, 15);
		Login_frame.getContentPane().add(textField_Titel);
		
		JLabel PasswordField_Titel = new JLabel("\u5BC6\u3000\u78BC\uFF1A");
		PasswordField_Titel.setHorizontalAlignment(SwingConstants.RIGHT);
		PasswordField_Titel.setBounds(78, 163, 57, 15);
		Login_frame.getContentPane().add(PasswordField_Titel);

		//����GUI	----------------------------------------//
	}
}
