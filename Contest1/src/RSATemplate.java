import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  CCC 2007 J1: Who is in the Middle
 *  @author ???
 *  @version September 2014
 */
public class RSATemplate
{

	public static void main(String[] args) throws FileNotFoundException
	{
		// Use this scanner to test some bowl weights by typing them in
		Scanner in = new Scanner(System.in);
		
		// This scanner is for judging
		// x is the number of the test file
		// Scanner in = new Scanner(new File("j2.x.in"));
		
		// Read in the lower and upper limits
		System.out.println("Enter lower limit of range: ");
		int lowerLimit = in.nextInt();
		
		System.out.println("Enter upper limit of range: ");
		int upperLimit = in.nextInt();
		
		int RSAcount = 0;
		
		// Test each number to see if it is an RSA number
		for(int i = lowerLimit; i <= upperLimit; i++)
		{
			if(RSA(i))
				RSAcount++;
		}

		// Print out the number of RSA numbers
		System.out.println("The number of RSA numbers between " +  
				lowerLimit + " and " + upperLimit + " is " + RSAcount); 
		
	}
	
	/**
	 * Tests if n is a RSA number
	 * @param n the number to test
	 * @return	true if n is an RSA number
	 * 			false otherwise
	 */
	public static boolean RSA (int n)
	{
		// Fill me out!!
		return true;
	}
}