package lingxing;

import java.util.Scanner;

public class LingXing {

	private static int c=0;
	private static int z;
	private static int j = 0;


	public static void main(String[] args) {

		Scanner sz = new Scanner(System.in);
		int s = sz.nextInt();
		 z = (s-1)/2;
		for(int i = 0; i < s-1; i ++) {
			if(i<=z) {
				
				xie();
				//System.out.printf("%9s\n","1");
				
				}
			}
		}
		

     private static void xie() {
		int k = z;
		if(c<k) {
			System.out.print(" ");
			if(c==k-1)
				xiexing();
			c++;
			 xie();
		}
		else 
			c = 0;
			
     }
     private static void xiexing() {
 		int k = z;
 		
 		if(c == 0) {
 			System.out.println("*");
 			return;
 		}
 		
 		else if(j < c) {
 			j++;
			 xiexing();
 		}
 			
      }
}
