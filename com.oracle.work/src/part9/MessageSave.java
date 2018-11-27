package part9;

import java.io.*;

public class MessageSave {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new FileWriter("1/message.txt",true));
		while(true) {
			out.write(in.readLine());
			out.newLine();
			out.flush();
		}
	}
}
