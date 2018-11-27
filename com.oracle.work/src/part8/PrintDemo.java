package part8;

public class PrintDemo extends Thread{
	
	public static void main(String[] args) {
		
		new Thread(){
			public void run(){
				Printxh.printNum();
			}
		}.start();
		
		new Thread(){
			public void run(){
				Printxh.printCode();
			}
		}.start();
	}
}
