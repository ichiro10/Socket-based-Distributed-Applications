package clientServer;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		try (ServerSocket ss = new ServerSocket(9997, 3)) {
		   while (true) {
			 Socket soc= ss.accept();
			 
			 DataInputStream dis = new DataInputStream(soc.getInputStream());
			 String client_name = (String)dis.readUTF();
			
			 DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			 dos.writeUTF("Hello "+ client_name + "!" );
			 dos.flush();
			 
			 File clients_directory = new File("/home/ichiro19/Desktop/Clients");
				if(!(clients_directory.exists() && clients_directory.isDirectory())) {
					clients_directory.mkdir(); 
				}
			 
			 String file_name = (String)dis.readUTF();
			 
	
	          File sourceFile = new File("Downloads/"+file_name);		
			  FileInputStream fis = new FileInputStream(sourceFile);
		
			  byte[] buffer = new byte[4096];
			  int bytesRead;
			  while ((bytesRead = fis.read(buffer)) != -1) {
			      dos.write(buffer, 0, bytesRead);
			    }
			 
			  fis.close();
		      dos.close();
			 
		     
			 }
		   
		}catch (IOException e) {
			e.printStackTrace();
		}

}
}
