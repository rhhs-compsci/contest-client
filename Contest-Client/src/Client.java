import java.io.*;
import java.net.*;

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
			PrintWriter p = new PrintWriter(socket_output, true);
			p.println(user_name);
			p.println(problem_name);
			p.println(file_array.length);
			p.println(file_name);
			socket_output.write(file_array, 0, file_array.length);
			socket_output.flush();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputStream socket_input = socket.getInputStream();
			BufferedReader sir = new BufferedReader(new InputStreamReader(socket_input));
			String output = sir.readLine();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
