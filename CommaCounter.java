import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CommaCounter {

	public static void main(String[] args) {
		//the best way to prove your love for someone:
		//count all the commas in "War and Peace" for them
		//omit the fact it involved 20 lines of code
		
		String s = File.separator;
		
		int commaCounter = 0;
		
		try(FileInputStream book = new FileInputStream("C:" + s + "Users" + s + "User" + s + "Desktop" + s + "warAndPeace.txt");) {
			int b = book.read();
			while(b != -1) {
				if((char)b == ',') {
					commaCounter++;
				}
				b = book.read();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No such file!");
		} catch (IOException e1) {
			System.out.println("SOmething went wrong!");
		}
		
		System.out.println(commaCounter);
		
	}
}
