package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chatModel.Message;
import chatModel.User;

public class RegisterFrame extends JFrame{
	private LoginFrame loginFrame;
	private JLabel newNameLabel,newPasswordLabel,sexLabel,ageLabel,signatureLabel;
	private JTextField newName,age;
	private JPasswordField newPassword;
	private JButton registerButton,returnButton;
	private JRadioButton man,woman;
	private ButtonGroup sexGroup;
	private JTextArea signature;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public RegisterFrame(LoginFrame loginFrame, ObjectInputStream in, ObjectOutputStream out) {
		this.in = in;
		this.out = out;
		this.loginFrame = loginFrame;
		this.setSize(600, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("注册");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initComponent();
		this.paintComponents(this.getGraphics());
	}
	
	public void initComponent() {
		newNameLabel = new JLabel("请输入昵称：");
		newNameLabel.setBounds(50, 70, 120, 30);
		newNameLabel.setFont(new Font("楷体", Font.BOLD, 18));
		this.add(newNameLabel);
		
		newName = new JTextField();
		newName.setBounds(170, 70, 180, 30);
		this.add(newName);
		
		newPasswordLabel = new JLabel("请输入密码：");
		newPasswordLabel.setBounds(50, 130, 120, 30);
		newPasswordLabel.setFont(new Font("楷体", Font.BOLD, 18));
		this.add(newPasswordLabel);
		
		newPassword = new JPasswordField();
		newPassword.setBounds(170, 130, 180, 30);
		this.add(newPassword);
		
		sexLabel = new JLabel("性别");
		sexLabel.setBounds(50,190,50,30);
		sexLabel.setFont(new Font("楷体", Font.BOLD, 18));
		this.add(sexLabel);
		
		man = new JRadioButton("男");
		man.setBounds(70,230,50,20);
		man.setFont(new Font("楷体", Font.BOLD, 18));
		this.add(man);
		
		woman = new JRadioButton("女");
		woman.setSelected(true);
		woman.setBounds(170,230,50,20);
		woman.setFont(new Font("楷体", Font.BOLD, 18));
		this.add(woman);
		
		sexGroup = new ButtonGroup();
		sexGroup.add(man);
		sexGroup.add(woman);
		
		signatureLabel = new JLabel("个性签名");
		signatureLabel.setBounds(260, 190, 100, 30);
		signatureLabel.setFont(new Font("楷体", Font.BOLD, 18));
		this.add(signatureLabel);
		
		signature = new JTextArea();
		signature.setBounds(260, 230, 250, 100);
		signature.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(signature);
		
		ageLabel = new JLabel("年龄");
		ageLabel.setBounds(50,270,50,20);
		ageLabel.setFont(new Font("楷体", Font.BOLD, 18));
		this.add(ageLabel);
		
		age = new JTextField();
		age.setBounds(60, 300, 150, 30);
		this.add(age);
		
		registerButton = new JButton("立即注册");
		registerButton.addActionListener(new registerActionListener());
		registerButton.setBounds(70, 370, 170, 50);
		registerButton.setFont(new Font("楷体", Font.BOLD, 18));
		this.add(registerButton);
		
		returnButton = new JButton("返回登录");
		returnButton.setBounds(330, 370, 170, 50);
		returnButton.setFont(new Font("楷体", Font.BOLD, 18));
		returnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterFrame.this.loginFrame.setVisible(true);
				RegisterFrame.this.dispose();
			}
		});
		this.add(returnButton);
			
	}
	
	class registerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(newName.getText().equals("") | newPassword.getText().equals("") | age.getText().equals("")) {
				JOptionPane.showMessageDialog(RegisterFrame.this, "昵称、密码、年龄不能为空，请检查", "注册结果", JOptionPane.ERROR_MESSAGE);
			}
			else {
				String nickname = newName.getText();
				String password = newPassword.getText();
				String sex = man.isSelected()?"男":"女";
				String age = RegisterFrame.this.age.getText();
				String signature = RegisterFrame.this.signature.getText();
				User registerUser = new User(nickname,password,sex,age,signature);
				Message registerRequest = new Message();
				registerRequest.setFrom(registerUser);
				registerRequest.setType("register");
				try {
					out.writeObject(registerRequest);
					out.flush();
					Message result = (Message)in.readObject();
					String accountNumber = result.getContent();
					if(accountNumber.equals("-1")) {
						JOptionPane.showMessageDialog(RegisterFrame.this, "注册失败！请重试！", "注册结果", JOptionPane.ERROR_MESSAGE);
					}
					else {
						String resultNumber = "注册成功！您的账号为：" + accountNumber + ",是否立即登录";
						int yourChoice = JOptionPane.showConfirmDialog(RegisterFrame.this, resultNumber, "注册结果", JOptionPane.INFORMATION_MESSAGE);
						if(yourChoice == 0) {
							RegisterFrame.this.loginFrame.setVisible(true);
							RegisterFrame.this.dispose();
						}
					}				
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		}
	}
	
}
