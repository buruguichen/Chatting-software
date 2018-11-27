package part9;

import java.io.File;

public class FileTree {
	public static void main(String[] args) {
		showAllFile("E:\\图片",0);
	}
	
	public static void showAllFile(String path,int n) {
		File file = new File(path);
		String[] son = file.list();
		try{
			for(int i=0; i<son.length; i++) {
				File sonFile = new File(path, son[i]);
				String jueduipath = sonFile.getAbsolutePath();
				if(sonFile.isDirectory()) {
					for(int p=0; p<n; p++) {
						System.out.print("   ");
					}
					System.out.println("Dir: " + son[i]);
					showAllFile(jueduipath,(n+1));
				}
				else {
					for(int p=0; p<n; p++) {
						System.out.print("   ");
					}
					System.out.println("File: " + son[i]);
				}
			}
			
		}catch(Exception e){
			
		}
		
	}
}
