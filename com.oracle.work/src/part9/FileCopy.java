package part9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopy {
	public static void main(String[] args) {
		try {
			copy("E:\\A","E:\\C");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void copy(String fromPath, String toPath) throws Exception{
		File fromFile = new File(fromPath);
		String[] son = fromFile.list();
		for(int i=0; i<son.length; i++) {
			File fromSonFile = new File(fromFile,son[i]);
			File toFile = new File(toPath,son[i]);
			String jdFromSon = fromSonFile.getAbsolutePath();
			String jdTo = toFile.getAbsolutePath();
			if(fromSonFile.isDirectory()) {
				toFile.mkdir();
				copy(jdFromSon,jdTo);
				
			}
			else {
				FileInputStream in = new FileInputStream(jdFromSon);
				FileOutputStream out = new FileOutputStream(jdTo);
				
				byte[] bs = new byte[100];
				int length;
				while((length=in.read(bs))!=-1) {
					out.write(bs,0,length);
				}
			}
		}
	}
}
