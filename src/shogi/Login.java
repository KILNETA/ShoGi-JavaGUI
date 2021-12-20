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
	  *�Ұ����ε{�ǡC
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
		//�ХߥD�e��
		frame = new JFrame();
		frame.setResizable(false);
		//�������D
		frame.setTitle("\u5C07\u68CB\u5927\u5E2B \u767B\u5165");
		//�e���j�p
		frame.setBounds(100, 100, 450, 300);
		//�]�m�q�{�����ާ@
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������e���� &�@�]�m�G��
		frame.getContentPane().setLayout(null);
		//���ε{���Y��
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\40491\\eclipse-workspace\\ShoGi-JavaGUI\\Picture\\ShoGi.png"));
		
		
		//����GUI	----------------------------------------//
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
		        /* Ū�JTXT�ɮ� */
				
				//�ˬd�b���K�X�O�_��g���T
				if( textField.getText().compareTo("")!=0 && password.compareTo("")!=0 ) {
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
		                    	System.out.println("�n�J���\");
		                    	return;
		            }	}	}
		            System.out.println("�b���K�X���~");
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
				
				//�ˬd�b���K�X�O�_��g���T
				if( textField.getText().compareTo("")!=0 && password.compareTo("")!=0 ) {
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
			                   		System.out.println("���b��w�Q���U");	return;
			        }   }	}	}
				catch (Exception e1) { e1.printStackTrace();	}
					
			        try {	        	
			            /* �g�Jtxt�ɮ� */
			            BufferedWriter out = new BufferedWriter(new FileWriter("Login.txt",true));
			            out.write(textField.getText() + " " + password + "\r\n"); // \r\n�Y������
			            out.close(); // �̫�O�o�����ɮ�
			        } 
			        catch (Exception e1) { e1.printStackTrace();	}	
			    }
				else; //��X�����T��X�J�b���K�X-----------------------------------++++
		}	}	);
		frame.getContentPane().add(Register);
		
		JLabel ShoGi_LOGO = new JLabel(new ImageIcon("Picture\\First_king Up.png"));
		ShoGi_LOGO.setHorizontalAlignment(SwingConstants.LEADING);
		ShoGi_LOGO.setText("");
		ShoGi_LOGO.setBounds(184, 10, 66, 80);
		frame.getContentPane().add(ShoGi_LOGO);
		
		JLabel ShoGi_Titel = new JLabel("\u5C07\u68CB\u5927\u5E2B");
		ShoGi_Titel.setFont(new Font("�з���", Font.BOLD, 20));
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
		//����GUI	----------------------------------------//
	}
}
