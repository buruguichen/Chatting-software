package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatFrame extends JFrame{
	private JTextArea chatShow, chatSend;
	private JScrollPane showScroll, sendScroll;
	private JButton sendButton, backButton;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String friendName;
	
	public ChatFrame(String friendName, ObjectInputStream in, ObjectOutputStream out) {
		this.friendName = friendName;
		this.in = in;
		this.out = out;
		this.setSize(600, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("与" + friendName + "聊天中");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		initComponent();
		this.paintComponents(this.getGraphics());
	}
	
	public void initComponent() {
		showScroll = new JScrollPane();
		showScroll.setBounds(20, 20, 550, 230);
		this.add(showScroll);
		
		chatShow = new JTextArea();
		chatShow.setEditable(false);
		showScroll.setViewportView(chatShow);
		
		sendScroll = new JScrollPane();
		sendScroll.setBounds(20, 290, 550, 120);
		this.add(sendScroll);
		
		chatSend = new JTextArea();
		sendScroll.setViewportView(chatSend);
		
		sendButton = new JButton("发送");
		sendButton.addActionListener(new sendActionListener());
		sendButton.setBounds(370, 420, 90, 30);
		this.add(sendButton);
		
		backButton = new JButton("关闭");
		backButton.setBounds(480, 420, 90, 30);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChatFrame.this.dispose();
			}
			
		});
		this.add(backButton);
	}
	
	class sendActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String willSendText = chatSend.getText();
			chatSend.setText("");
		}
	}
}
