package com.oracle.primenumber;

public class PrimeNumber {
	static int a = 3;
	static int sum = 2;
	static int i = 2;
	public static void main(String[] args) {
		for(a=3;a<10000;a++) {
			for(i=2;i<a;i++) {
				if(a%i!=0&&i==(a-1)) {
					sum += a;
					
				}
				else if(a%i==0)
				    break;
								
			}
	
				

		}
		System.out.println(sum);
	}
	
}
