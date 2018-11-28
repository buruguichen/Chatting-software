package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import chatModel.Message;
import chatModel.User;

public class Server {
	private ServerSocket server;
	private Map<Long, ObjectOutputStream> allSockets;
	public Server() {
		try {
			server = new ServerSocket(10086);
			allSockets = new HashMap<>();
			while(true) {
				Socket client = server.accept();
				
				MessageReciverThread thread = new MessageReciverThread(client);
				thread.start();
				System.out.println(client.getInetAddress().getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
	}
	class MessageReciverThread extends Thread{
		private Socket c;
		private ObjectInputStream in;
		private ObjectOutputStream out;
		
		public MessageReciverThread(Socket c) {
			this.c = c;
			try {
				in = new ObjectInputStream(c.getInputStream());
				out = new ObjectOutputStream(c.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void run() {
			try {
				while(true) {
					Message message = (Message)in.readObject();
//					System.out.println("123456" + message);
//					System.out.println(message.getType());
					if(message.getType().equals("register")) {
						long accountNumber = UserOperate.register(message.getFrom());
						System.out.println("注册信息为：" + message);
						
						Message registerResult = new Message();
						registerResult.setContent(String.valueOf(accountNumber));
						out.writeObject(registerResult);
						out.flush();
					}else if(message.getType().equals("login")) {
						User loginUser = message.getFrom();
						User user = UserOperate.login(loginUser.getAccountNumber(), loginUser.getPassword());
						System.out.println("登陆者为：" + user);
						if(user != null) {
							allSockets.put(user.getAccountNumber(), out);
						}
						Message loginResult = new Message();
						loginResult.setFrom(user);
						loginResult.setAllUser(UserOperate.listAllUserNames());
						out.writeObject(loginResult);
						out.flush();
					}else if(message.getType().equals("textMessage")) {
						System.out.println("接收到文本消息:" + message.getContent());
						long reciveUserNumber = message.getTo().getAccountNumber();
						if(allSockets.containsKey(reciveUserNumber)) {
							message.setDate(new Date().toLocaleString());
							allSockets.get(reciveUserNumber).writeObject(message);
							allSockets.get(reciveUserNumber).flush();
							System.out.println("转出消息");
						}
						else {
							System.out.println("此用户不在线，不转发");
						}
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
