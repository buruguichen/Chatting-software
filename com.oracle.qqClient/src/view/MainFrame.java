package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import chatModel.User;

public class MainFrame extends JFrame{
	private JPanel contentPanel;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private User loginUser;
	private List<String> allUsers;
	private Map<String, ChatFrame> allChatWindows;
	
	public MainFrame (User loginUser, List<String> allUsers, ObjectInputStream in, ObjectOutputStream out) {
		this.loginUser = loginUser;
		this.allUsers = allUsers;
		this.in = in;
		this.out = out;
		this.allChatWindows = new HashMap<>();
		this.setSize(350, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5,5,5,5));
		this.setContentPane(contentPanel);
		contentPanel.setLayout(null);
		initComponent();
		this.printComponents(this.getGraphics());
	}
	
	public void initComponent() {
		
		
		JLabel loginUserName = new JLabel(loginUser.getNickname());
		loginUserName.setBounds(20, 10, 300, 30);
		loginUserName.setFont(new Font("",Font.BOLD,18));
		contentPanel.add(loginUserName);
		
		JLabel loginUserSignature = new JLabel(loginUser.getSignature());
		loginUserSignature.setBounds(20,50,300,30);
		loginUserSignature.setBorder(BorderFactory.createLineBorder(Color.gray));
		contentPanel.add(loginUserSignature);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("所有好友");
		for(String name: allUsers) {
			MutableTreeNode  oneUser = new DefaultMutableTreeNode(name);
			root.add(oneUser);
		}
		JTree tree = new JTree(root);
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && e.getButton() == 1) {
					if(tree.getSelectionPath().getPathCount() == 2) {
						String userName = tree.getSelectionPath().getLastPathComponent().toString();
						if(allChatWindows.containsKey(userName)) {
							allChatWindows.get(userName).setVisible(true);
						}
						else {
							ChatFrame c = new ChatFrame(userName, in, out);
							allChatWindows.put(userName, c);
						}
					}
				}
			}
		});
		
		JScrollPane scroll = new JScrollPane(tree);
		scroll.setBounds(30, 100, 280, 550);
		contentPanel.add(scroll);
	}

}
