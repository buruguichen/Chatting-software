package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chatModel.Message;
import chatModel.User;

public class Server {
	private ServerSocket server;
	public Server() {
		try {
			server = new ServerSocket(10086);
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
					System.out.println(message);
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
						Message loginResult = new Message();
						loginResult.setFrom(user);
						loginResult.setAllUser(UserOperate.listAllUserNames());
						out.writeObject(loginResult);
						out.flush();
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
