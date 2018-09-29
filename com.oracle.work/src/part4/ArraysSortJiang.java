package part4;

import java.util.Arrays;
import java.util.Random;

public class ArraysSortJiang {
	private static Random rand = new Random();
	private static int[] s = new int[30];
	public static void main(String[] args) {
		
		
		for(int i=0;i<s.length;i++) {
			s[i] = rand.nextInt(100);
			System.out.print(s[i]+",");
		}
		System.out.println("---");
		chongfu();
		Arrays.sort(s);
		for(int q=0;q<s.length/2;q++) {
			int zh = 0;
			zh=s[q];
			s[q]=s[s.length-1-q];
			s[s.length-1-q]=zh;
			
		}
			
		for(int j=0;j<s.length;j++)
			System.out.print(s[j]+",");
		
		
	}	
	private static void chongfu() {
		int m;
		int pd = 0;
		for(m=0;m<s.length;m++) {
			
			for(int n=m+1;n<s.length;n++) {
				
					if(s[m]==s[n]) {
						s[n] = rand.nextInt(100);
						pd ++;
					}
				}
			
		}
		if(pd == 0)
			 return ;
		else {
			chongfu();
			
		}

		
	}

}
