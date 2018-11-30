package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chatModel.LoginState;
import chatModel.Message;
import chatModel.User;

public class LoginFrame extends JFrame{
	
	private JLabel usernameLabel,userpasswordLabel;
	private JTextField username;
	private JPasswordField userpassword;
	private JCheckBox rememberPassword,autoLogin;
	private JButton loginButton,registerButton;
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
		
		try {
			client = new Socket("localhost",10086);
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		initComponent();
		this.paintComponents(this.getGraphics());
		
	}

	public void initComponent() {
		
		usernameLabel = new JLabel("账号:");
		usernameLabel.setBounds(100, 90, 50, 30);
		this.add(usernameLabel);
		
		username = new JTextField();
		username.setBounds(150, 90, 180, 30);
		this.add(username);
		
		userpasswordLabel = new JLabel("密码:");
		userpasswordLabel.setBounds(100, 130, 50, 30);
		this.add(userpasswordLabel);
		
		userpassword = new JPasswordField();
		userpassword.setBounds(150, 130, 180, 30);
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
		
		//判断记住密码、自动登录、账号和密码框的值
		judgeLoginState(username, userpassword, autoLogin, rememberPassword);
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
					if(loginUser.getAccountNumber() == -1) {
						JOptionPane.showMessageDialog(LoginFrame.this, "此账号已登录！", "登录结果", JOptionPane.ERROR_MESSAGE);
					}
					else if(loginUser == null) {
						JOptionPane.showMessageDialog(LoginFrame.this, "登录失败，账号或密码有误！", "登录结果", JOptionPane.ERROR_MESSAGE);
					}
					else {
						if(rememberPassword.isSelected()) {
							LoginState state = new LoginState();
							state.setRemberedUser(loginUser);
							state.setRememberState("true");
							if(autoLogin.isSelected()) {
								state.setAutoLoginState("true");
							}else {
								state.setAutoLoginState("false");
							}
							File file = new File("rememberData/loginState.data");
							ObjectOutputStream outData = new ObjectOutputStream(new FileOutputStream(file));
							outData.writeObject(state);
							outData.flush();
							outData.close();
						}else {
							LoginState state = new LoginState();
							state.setRemberedUser(null);
							state.setRememberState("false");
							state.setAutoLoginState("false");
							File file = new File("rememberData/loginState.data");
							ObjectOutputStream outData = new ObjectOutputStream(new FileOutputStream(file));
							outData.writeObject(state);
							outData.flush();
							outData.close();
						}
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
	
	
	
	//判断记住密码、自动登录、账号和密码框的值
	public void judgeLoginState(JTextField username, JPasswordField userpassword, JCheckBox autoLogin, JCheckBox rememberPassword) {
		try {
			File stateFile = new File("rememberData/loginState.data");
			ObjectInputStream inData = new ObjectInputStream(new FileInputStream(stateFile));
			LoginState state = (LoginState)inData.readObject();
			if(state.getRememberState().equals("true")) {
				username.setText(String.valueOf(state.getRemberedUser().getAccountNumber()));
				userpassword.setText(state.getRemberedUser().getPassword());
				rememberPassword.setSelected(true);
			}
			if(state.getAutoLoginState().equals("true")) {
				autoLogin.setSelected(true);
//				long accountNumber = Long.parseLong(username.getText());
//				String password = userpassword.getText();
//				User user = new User(accountNumber,password);
//				Message loginRequest = new Message();
//				loginRequest.setFrom(user);
//				loginRequest.setType("login");
//				try {
//					out.writeObject(loginRequest);
//					out.flush();
//					Message loginResult = (Message)in.readObject();
//					User loginUser = loginResult.getFrom();
//					Map<Long, String> map = loginResult.getAllUser();
//					for(Long key: map.keySet()) {
//						String value = map.get(key);
//						System.out.println(key + " " + value);
//					}
//					if(loginUser.getAccountNumber() == -1) {
//						JOptionPane.showMessageDialog(LoginFrame.this, "此账号已登录！", "登录结果", JOptionPane.ERROR_MESSAGE);
//					}else {
//						MainFrame main = new MainFrame(loginUser,map,in,out);
//						LoginFrame.this.dispose();
//					}
//				}catch(Exception e1) {
//					e1.printStackTrace();
//				}
//				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
	}
}
