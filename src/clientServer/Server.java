package clientServer;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		try (ServerSocket ss = new ServerSocket(9997, 3)) {
			while (true) {
			 Socket soc= ss.accept();
			 
			 DataInputStream dis = new DataInputStream(soc.getInputStream());
			 String str = (String)dis.readUTF();
			
			 DataOutputStream d = new DataOutputStream(soc.getOutputStream());
			 d.writeUTF("Hello "+ str + "!" );
			 d.flush();
			 d.close();
			}
		}

}
}
