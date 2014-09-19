import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Client {
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new FileReader("config"));
			String server_host = br.readLine();
			String user_name = br.readLine();
			String problem_name = br.readLine();
			String project_name = br.readLine();
			String file_name = br.readLine();
			br.close();
			
			Socket socket = new Socket(server_host, 63400);
			
			File file = new File("../"+project_name+"/src/"+file_name);
			byte[] file_array = new byte [(int)file.length()];
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			bis.read(file_array, 0, file_array.length);
			bis.close();
			
			OutputStream socket_output = socket.getOutputStream();
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket_output));
//			writer.write(user_name+"\n");
//			writer.write(problem_name+"\n");
//			writer.write(String.valueOf(file_array.length)+"\n");
//			writer.write(file_name+ "\n");
//			writer.flush();
//			writer.close();
			socket_output.write((user_name + "\n").getBytes());
			socket_output.write((problem_name + "\n").getBytes());
			socket_output.write((file_name + "\n").getBytes());
			socket_output.write((String.valueOf(file_array.length) + "\n").getBytes());
			socket_output.write(file_array);
			socket_output.flush();
			
			InputStream socket_input = socket.getInputStream();
			char next = 0;
			while((next = (char)socket_input.read())!='\u0004')
				System.out.print(next);
			
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
