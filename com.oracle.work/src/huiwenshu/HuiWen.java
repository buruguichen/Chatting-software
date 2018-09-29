package huiwenshu;

import java.util.Scanner;

public class HuiWen {

	public static void main(String[] args) {
		Scanner sz = new Scanner(System.in);
		String str = sz.nextLine();
		int[] number = nu(str);
	
		for(int i = 0;i < number.length/2; i ++) {
			int j = number.length-1-i;
			boolean a = number[i] == number[j];
			
			
			if(a == true && (i == number.length/2-1))
				System.out.println("是回文数");
			else if(a == false) {
				System.out.println("不是回文数");
			    break;
			}
			else
				a = true;
		}
	}
	private static int[] nu(String str) {
		int[] num = new int[str.length()];
		for(int i = 0; i < str.length(); i ++) {
			num[i] = Integer.parseInt(str.substring(i, i+1));
		}
		return num;
	}

}
