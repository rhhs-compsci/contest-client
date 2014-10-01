import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client {
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new FileReader("config"));
			String server_host = br.readLine();
			String user_name = br.readLine();
			br.close();
			
			Gui gui = new Gui();
			gui.ipField.setText(server_host);
			gui.usernameField.setText(user_name);
			gui.setVisible(true);
			
			while (gui.isVisible()) System.out.print("\0"); // super hacky, only works when debugging or printing for some reason
			server_host = gui.ipField.getText();
			user_name = gui.usernameField.getText();
			String problem_name = gui.probNameField.getText();
			
			Socket socket = new Socket(server_host, 63400);
			
			File file = new File(gui.filename);
			byte[] file_array = new byte [(int)file.length()];
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			bis.read(file_array, 0, file_array.length);
			bis.close();
			
			OutputStream socket_output = socket.getOutputStream();
			
			socket_output.write((user_name + "\n").getBytes());
			socket_output.write((problem_name + "\n").getBytes());
			socket_output.write((file.getName() + "\n").getBytes());
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
class Gui extends JFrame
{
	public JPanel contentPane;
	public JTextField ipField;
	public JTextField usernameField;
	public JTextField probNameField;
	public JLabel lblCurFile;
	public String filename = "";
	
	public void close()
	{
		this.setVisible(false);
	}
	
	public Gui()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIpAddress = new JLabel("IP Address: ");
		GridBagConstraints gbc_lblIpAddress = new GridBagConstraints();
		gbc_lblIpAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpAddress.anchor = GridBagConstraints.EAST;
		gbc_lblIpAddress.gridx = 0;
		gbc_lblIpAddress.gridy = 0;
		contentPane.add(lblIpAddress, gbc_lblIpAddress);
		
		ipField = new JTextField();
		GridBagConstraints gbc_ipField = new GridBagConstraints();
		gbc_ipField.insets = new Insets(0, 0, 5, 0);
		gbc_ipField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ipField.gridx = 1;
		gbc_ipField.gridy = 0;
		contentPane.add(ipField, gbc_ipField);
		ipField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 2;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		usernameField = new JTextField();
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 2;
		contentPane.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		JLabel lblProblemName = new JLabel("Problem Name:");
		GridBagConstraints gbc_lblProblemName = new GridBagConstraints();
		gbc_lblProblemName.anchor = GridBagConstraints.EAST;
		gbc_lblProblemName.insets = new Insets(0, 0, 5, 5);
		gbc_lblProblemName.gridx = 0;
		gbc_lblProblemName.gridy = 4;
		contentPane.add(lblProblemName, gbc_lblProblemName);
		
		probNameField = new JTextField();
		GridBagConstraints gbc_probNameField = new GridBagConstraints();
		gbc_probNameField.insets = new Insets(0, 0, 5, 0);
		gbc_probNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_probNameField.gridx = 1;
		gbc_probNameField.gridy = 4;
		contentPane.add(probNameField, gbc_probNameField);
		probNameField.setColumns(10);
		
		JLabel lblSelectedFile = new JLabel("Selected File:");
		GridBagConstraints gbc_lblSelectedFile = new GridBagConstraints();
		gbc_lblSelectedFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectedFile.gridx = 0;
		gbc_lblSelectedFile.gridy = 6;
		contentPane.add(lblSelectedFile, gbc_lblSelectedFile);
		
		lblCurFile = new JLabel("");
		GridBagConstraints gbc_lblCurFile = new GridBagConstraints();
		gbc_lblCurFile.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurFile.gridx = 1;
		gbc_lblCurFile.gridy = 6;
		contentPane.add(lblCurFile, gbc_lblCurFile);
		
		final JFileChooser fc = new JFileChooser();
		
		JButton btnBrowse = new JButton("Browse...");
		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
		gbc_btnBrowse.insets = new Insets(0, 0, 0, 5);
		gbc_btnBrowse.gridx = 0;
		gbc_btnBrowse.gridy = 7;
		btnBrowse.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				fc.showOpenDialog(contentPane);
				lblCurFile.setText(fc.getSelectedFile().getAbsolutePath());
				filename = fc.getSelectedFile().getAbsolutePath();
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		contentPane.add(btnBrowse, gbc_btnBrowse);
		
		JButton btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 0, 5);
		gbc_btnSubmit.gridx = 0;
		gbc_btnSubmit.gridy = 8;
		btnSubmit.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (!fc.isShowing()) {
					close();
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		contentPane.add(btnSubmit, gbc_btnSubmit);
	}
}