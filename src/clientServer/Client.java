package clientServer;
import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) throws IOException {
		
	    Socket soc = new Socket("localhost", 9997);
	    
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Please enter your ClientName:");
	    String client_name = sc.nextLine();
	    
	    DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
	    dos.writeUTF(client_name);
	    dos.flush();
	    
	    DataInputStream dis = new DataInputStream(soc.getInputStream());
		String m = (String)dis.readUTF(); 
		System.out.println(m);

		
		File file = new File("/home/ichiro19/Desktop/Clients/"+client_name);
        if (!file.exists() && file.mkdir()) {
                System.out.println("Your personal directory is created! it's located in /home/ichiro19/Desktop/Clients/");
             } 
        else {
               System.out.println("You have already a personal directory!it's located in /home/ichiro19/Desktop/Clients/");
            }
       
		
	    System.out.println("Please enter the name of the file to download:");
	    String file_name = sc.nextLine();
	    
	    
	    FileOutputStream fos = new FileOutputStream("/home/ichiro19/Desktop/Clients/"+client_name+"/"+file_name);
	    dos.writeUTF(file_name);
	    dos.flush();
	    
	    byte[] buffer = new byte[4096];
	    int bytesRead;
	    while ((bytesRead = dis.read(buffer)) != -1){
	      fos.write(buffer, 0, bytesRead);
	    }
	    
	    fos.close();
	    sc.close();
		soc.close(); 
	}

}
