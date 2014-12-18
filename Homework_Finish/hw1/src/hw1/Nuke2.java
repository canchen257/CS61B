package hw1;
import java.util.Scanner;

public class Nuke2 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String inputString;
		
		System.out.println("Please enter a string: ");
		inputString = keyboard.next();
		
		StringBuilder newString = new StringBuilder(inputString);
		newString.deleteCharAt(1);
		System.out.println("The new string: " + newString.toString());

	}

}
