import java.io.*;
import java.util.Arrays;

import javax.swing.JFrame;

public class P1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[] nums = new int[] {Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine())};
		Arrays.sort(nums);
		
		System.out.println(nums[1]);
		
		in.close();
	}
}
