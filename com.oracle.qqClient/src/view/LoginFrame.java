package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chatModel.Message;
import chatModel.User;

public class LoginFrame extends JFrame{
	
	private JLabel usernameLabel,userpasswordLabel;
	private JTextField username;
	private JPasswordField userpassword;
	private JCheckBox rememberPassword,autoLogin;
	private JButton loginButton,registerButton;
	private MyMouseListener allMyMouseLister;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket client;
	
	public LoginFrame() {
		this.setSize(450, 350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("QQ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		allMyMouseLister = new MyMouseListener();
		initComponent();
		this.paintComponents(this.getGraphics());
		
		try {
			client = new Socket("localhost",10086);
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void initComponent() {
		usernameLabel = new JLabel("账号:");
		usernameLabel.setBounds(100, 90, 50, 30);
		this.add(usernameLabel);
		
		username = new JTextField();
		username.setBounds(150, 90, 180, 30);
		username.addMouseListener(allMyMouseLister);
		this.add(username);
		
		userpasswordLabel = new JLabel("密码:");
		userpasswordLabel.setBounds(100, 130, 50, 30);
		this.add(userpasswordLabel);
		
		userpassword = new JPasswordField();
		userpassword.setBounds(150, 130, 180, 30);
		userpassword.addMouseListener(allMyMouseLister);
		this.add(userpassword);
		
		autoLogin = new JCheckBox("自动登录");
		autoLogin.setBounds(100, 175, 80, 20);
		this.add(autoLogin);
		
		rememberPassword = new JCheckBox("记住密码");
		rememberPassword.setBounds(200, 175, 80, 20);
		this.add(rememberPassword);
		
		loginButton = new JButton("登录");
		loginButton.setBounds(100, 205, 230, 40);
		loginButton.addActionListener(new loginActionListener());
		this.add(loginButton);
		
		registerButton = new JButton("注册账号");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame register = new RegisterFrame(LoginFrame.this,in,out);
				LoginFrame.this.setVisible(false);
				
			}
		});
		registerButton.setBounds(20, 280, 90, 20);
		this.add(registerButton);
	}
	public static void main(String[] args) {
		LoginFrame login = new LoginFrame();
	}
	
	
	class loginActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(username.getText().equals("") | userpassword.getText().equals("")) {
				JOptionPane.showMessageDialog(LoginFrame.this, "账号或密码不能为空！", "登陆结果", JOptionPane.ERROR_MESSAGE);
			}
			else {
				long accountNumber = Long.parseLong(username.getText());
				String password = userpassword.getText();
				User user = new User(accountNumber,password);
				Message loginRequest = new Message();
				loginRequest.setFrom(user);
				loginRequest.setType("login");
				try {
					out.writeObject(loginRequest);
					out.flush();
					Message loginResult = (Message)in.readObject();
					User loginUser = loginResult.getFrom();
					if(loginUser == null) {
						JOptionPane.showMessageDialog(LoginFrame.this, "登录失败，账号或密码有误！", "登录结果", JOptionPane.ERROR_MESSAGE);
					}
					else {
						MainFrame main = new MainFrame(loginUser,loginResult.getAllUser(),in,out);
						LoginFrame.this.dispose();
//						System.out.println("登录：" + loginResult.getFrom());
					}
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	class MyMouseListener extends MouseAdapter{
		
	@Override
		public void mouseEntered(MouseEvent e) {
			username.setBorder(BorderFactory.createLineBorder(Color.red));
			userpassword.setBorder(BorderFactory.createLineBorder(Color.red));
		}
	
		public void mouseExited(MouseEvent e) {
			username.setBorder(BorderFactory.createLineBorder(Color.gray));
			userpassword.setBorder(BorderFactory.createLineBorder(Color.gray));
		}
	}
}
