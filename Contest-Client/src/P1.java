import java.io.*;

public class P1 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(/*new FileReader("DATA1.txt")*/new InputStreamReader(System.in));
		for(int cn = 0; cn < 5; cn++){
			String answer = "";
			for(int i = 0; i < 7; i++)
				answer+=(in.readLine().lastIndexOf('#')+1);
			in.readLine();
			System.out.println(answer);
		}
		in.close();
	}
}
