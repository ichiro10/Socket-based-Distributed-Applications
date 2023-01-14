package clientServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) throws IOException {
		
	    Socket soc = new Socket("localhost", 9997);
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Veuillez saisir votre ClientName:");
	    String str = sc.nextLine();
	    
	    DataOutputStream d = new DataOutputStream(soc.getOutputStream());
	    d.writeUTF(str);
	    d.flush();
	    
	    DataInputStream dis = new DataInputStream(soc.getInputStream());
		String m = (String)dis.readUTF(); 
		System.out.println(m);
		
		soc.close(); 
	}

}
