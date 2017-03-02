package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyMaker {

	public static void main(String[] args) {
		String sep = File.separator;
		
		//using absolute paths made more sense
		//that's how you do it in the terminal, isn't it?
		try {
			makeCopy("C:"+sep+"Users"+sep+"User"+sep+"workspace_java"+sep+"Lesson3Homework", "C:"+sep+"Users"+sep+"User"+sep+"Desktop"+sep+"copy");
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		} catch (IOException e) {
			System.err.println("Something went terribly wrong! ");
		}
	}
	
	static void makeCopy(String origPath, String copyPath) throws FileNotFoundException, IOException {
		File orig = new File(origPath);
		
		if(!orig.exists())
			throw new FileNotFoundException("Wrong path or unexisting file!");
		
		File copy = new File(copyPath);
		copy.mkdir();
		
		for (int i = 0; i < orig.listFiles().length; i++) {
			if(orig.listFiles()[i].isFile()) {
				File temp = new File(copy.getAbsolutePath()+File.separator+orig.listFiles()[i].getName());
				temp.createNewFile();
				
				
				try (FileInputStream fis = new FileInputStream(orig.listFiles()[i]); 
					 FileOutputStream fos = new FileOutputStream(temp);){
					int b = fis.read();
					while(b != -1) {
						fos.write(b);
						fos.flush();
						b = fis.read();
					}
					
				} catch (IOException e) {
					System.out.println("It seems the streams are leaky... Copy process failed!");
				}
			}
			else {
				makeCopy(orig.listFiles()[i].getPath(), copy.getPath()+File.separator+orig.listFiles()[i].getName());
			}
		}
	}
}
