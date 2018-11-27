package part9;

import java.io.File;


public class Homework {
	public static void main(String[] args) {
		try {
			createWork("E:\\A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createWork(String path) throws Exception{
		File file = new File(path,"大学作业/2016");
		file.mkdirs();
		String juedui = file.getAbsolutePath();
		for(int i=1; i<13; i++) {
			String s = i + "月";
			File yfFile = new File(juedui,s);
			yfFile.mkdir();
			String yfJuedui = yfFile.getAbsolutePath();
			if(i==4 | i==6 | i==9 | i==11) {
				for(int j=1; j<31; j++) {
					String t = j + "号";
					File tsFile = new File(yfJuedui,t);
					tsFile.mkdir();
					String tsJuedui = tsFile.getAbsolutePath();
					String zym = "2016年" + i + "月" + j + "日.txt";
					File zyFile = new File(tsJuedui,zym);
					zyFile.createNewFile();
				}
			}
			else if(i==2) {
				for(int j=1; j<30; j++) {
					String t = j + "号";
					File tsFile = new File(yfJuedui,t);
					tsFile.mkdir();
					String tsJuedui = tsFile.getAbsolutePath();
					String zym = "2016年" + i + "月" + j + "日.txt";
					File zyFile = new File(tsJuedui,zym);
					zyFile.createNewFile();
				}
			}
			else {
				for(int j=1; j<32; j++) {
					String t = j + "号";
					File tsFile = new File(yfJuedui,t);
					tsFile.mkdir();
					String tsJuedui = tsFile.getAbsolutePath();
					String zym = "2016年" + i + "月" + j + "日.txt";
					File zyFile = new File(tsJuedui,zym);
					zyFile.createNewFile();
				}
			}
			
		}
	}
}
