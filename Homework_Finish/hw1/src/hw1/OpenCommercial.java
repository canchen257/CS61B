package hw1;
import java.net.*;
import java.io.*;
//import java.util.Scanner;  Applied when using Scanner class.

 /** A class that provides a main function to read five lines of
 *   a commercial Web page and print them in reverse order, given
 *   name of a company.
 */

public class OpenCommercial {
	public static void main(String[] arg) throws Exception{
		BufferedReader keyboard;
		String inputLine;
		BufferedReader content;
		keyboard = new BufferedReader(new InputStreamReader(System.in));   
		
		
		/** We can also use Scanner class to input
		 *  Scanner keyboard = new Scanner(System.in);
		 *  inputLine = keyboard.next();  // Scanner class does not include readLine command!
		 */
		
		System.out.print("Please enter the name of a company(without space): ");
		System.out.flush();   // Make sure the line is printed immediately.
		inputLine = keyboard.readLine();
		
		URL u = new URL("http://www." + inputLine + ".com/");
		content = new BufferedReader(new InputStreamReader(u.openStream()));
		String[] s = new String[5];
		for(int i =0;i<5;i++){
			s[i] = content.readLine();
		}
		for(int i=4; i>-1;i--){
			System.out.println(s[i]);
		}
	}
}
