package part8;

public class Printxh {
	private static Object o = new Object();
	
	public static void printNum() {
		
			synchronized (o) {
				for(int i=1; i<100; i ++) {
				if(i%2 == 0) {
					try {
						System.out.print(i + "  ");
						o.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.print(i + "  ");
					o.notifyAll();
				}
			}
			
		}
	}
	
	public static void printCode() {
		
				synchronized (o) {
					for(char c=97; c<123; c ++) {
						if(Character.isLowerCase(c)) {
						try {
							System.out.print(c + "  ");
							o.wait();
							o.notifyAll();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				System.out.printf(c + "  ");
			}
		}
	}
		
}
