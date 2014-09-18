import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  CCC 2007 J1: Who is in the Middle
 *  @author ???
 *  @version September 2014
 */
public class MiddleBearTemplate
{

	public static void main(String[] args) throws FileNotFoundException
	{
		
		// I am a sample if
		// I have a sample boolean evaluation and 
		// a sample print statement
		if(1 > 2 || (9 > 7 && 4 > 3))
			System.out.println("I am a print statement!!");
		
		// Use this scanner to test some bowl weights by typing them in
		Scanner in = new Scanner(System.in);
		
		// This scanner is for judging
		// x is the number of the test file
		// Scanner in = new Scanner(new File("j1.x.in"));
		
		// Read the weights of each bowl
		int bearA = in.nextInt();
		int bearB = in.nextInt();
		int bearC = in.nextInt();
		
		// Fill out these if statements
		// It's dangerous to go alone, take these!
		// || <-- or operator
		// && <-- and operator
		// () <-- brackets
		
		if (true)
		    System.out.println(bearA);
		

		else if (true)
		    System.out.println(bearB);
		    

		else
		    System.out.println(bearC);

	}

}
