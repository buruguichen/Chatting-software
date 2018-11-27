package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import chatModel.User;

public class UserOperate {
	
	private static long originalAccountNumber = 10000;
	
	//登录方法
	public static User login(long accountNumber,String password) {
		try {
			File file = new File("datas");
			File[] childs = file.listFiles();
			for(int i=0; i<childs.length; i++) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(childs[i]));
				User user = (User)in.readObject();
				if(accountNumber==user.getAccountNumber() && password.equals(user.getPassword())) {
					return user;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	//注册方法
	public static long register(User user) {
		long accountNumber;
		File file = new File("datas");
		String[] childs = file.list();
		
		if(childs.length != 0) {
			String a = childs[childs.length-1].substring(0, childs[childs.length-1].length()-5);
			accountNumber = Long.parseLong(a) + 1; 
		}
		else
			accountNumber = originalAccountNumber + 1;
		user.setAccountNumber(accountNumber);
		try {
			File f = new File("datas/" + accountNumber + ".data");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(user);
			out.flush();
			out.close();
			return accountNumber;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//读取注册的所有用户的账号和昵称
	public static List<String> listAllUserNames(){
		List<String> allNames = new ArrayList<>();
		File file = new File("datas");
		File[] childs = file.listFiles();
		for(int i=0; i<childs.length; i++) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(childs[i]));
				String nickName = ((User)in.readObject()).getNickname();
				String accountNumber = childs[i].getName();
				allNames.add(nickName + "(" + accountNumber.substring(0, accountNumber.length()-5) + ")");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return allNames;
	}
	public static void main(String[] args) {
		User a1 = new User("zero","123456","man","18","king gad");
		User a2 = new User("kami","000000","man","23","wo ta xi kami");
		register(a1);
		register(a2);
		System.out.println(a1);
		System.out.println(a2);
		List<String> list = listAllUserNames();
		for(String a: list) {
			System.out.println(a);
		}
		
	}

}

