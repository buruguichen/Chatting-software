package com.hbuas.wlbc5;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UDPFrame extends JFrame {

	DatagramSocket s;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDPFrame frame = new UDPFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public UDPFrame() {
		try {
			s = new DatagramSocket(10001);
		}catch (SocketException e2) {
			e2.printStackTrace();
		}
		setTitle("UDP聊天(接收端口10086)");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblip = new JLabel("远程IP:");
		lblip.setFont(new Font("宋体", Font.BOLD, 18));
		lblip.setBounds(10, 35, 77, 26);
		contentPane.add(lblip);
		
		textField = new JTextField();
		textField.setBounds(85, 35, 106, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("远程端口：");
		label.setFont(new Font("宋体", Font.BOLD, 18));
		label.setBounds(213, 35, 95, 26);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(304, 35, 77, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("昵称：");
		label_1.setFont(new Font("宋体", Font.BOLD, 18));
		label_1.setBounds(408, 35, 58, 26);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(468, 35, 95, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setEnabled(false);
				textField_1.setEnabled(false);
				textField_2.setEnabled(false);
				button.setEnabled(false);
				
			}
		});
		button.setBounds(610, 32, 104, 33);
		contentPane.add(button);
		
		JLabel label_2 = new JLabel("消息记录");
		label_2.setFont(new Font("宋体", Font.BOLD, 18));
		label_2.setBounds(10, 86, 77, 26);
		contentPane.add(label_2);
		
		JTextArea textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setBounds(10, 122, 704, 247);
		textArea.setBorder(BorderFactory.createLineBorder(Color.gray));
		sp.setBounds(10, 122, 704, 247);
		contentPane.add(sp);
		
		JLabel lblNewLabel = new JLabel("发送信息");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 379, 77, 26);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 415, 592, 59);
		textArea_1.setBorder(BorderFactory.createLineBorder(Color.gray));
		contentPane.add(textArea_1);
		
		JButton button_1 = new JButton("发送");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = textArea_1.getText();
				String reciveIP = textField.getText();
				String recivePort = textField_1.getText();
				String nickname = textField_2.getText();
				
				//使用UDP的api发送文本
				try {
					new Thread() {
						public void run() {
							try {
									InetAddress local = InetAddress.getLocalHost();
									String ms = nickname + "(" + local.toString() + ")#" + new Date().toLocaleString() 
												+ "\r\n" + message;
									InetAddress address = InetAddress.getByName(reciveIP);
									DatagramPacket packet = new DatagramPacket(ms.getBytes(),ms.getBytes().length,address,Integer.parseInt(recivePort));
									s.send(packet);
									textArea_1.setText("");
									textArea.append(ms + "\r\n\r\n");
									textArea.setCaretPosition(textArea.getText().length());
							}catch(Exception e) {
					
							}
							
						};{};
					}.start();
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(621, 415, 93, 45);
		contentPane.add(button_1);
		
		//用UDP接收信息
		try {
			DatagramSocket recive = new DatagramSocket(10086);
			
			new Thread() {
				public void run() {
					try {
						while(true) {
							byte[] datas = new byte[recive.getReceiveBufferSize()];
							
							DatagramPacket packet = new DatagramPacket(datas,recive.getReceiveBufferSize());
							recive.receive(packet);
							textArea.append(new String(datas) + "\r\n\r\n");
							textArea.setCaretPosition(textArea.getText().length());
						}
					}catch(Exception e) {
			
					}
					
				};{};
				}.start();
			}catch (Exception e1) {
				e1.printStackTrace();
		}
	}
}
