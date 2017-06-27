

import java.net.*;
import java.util.concurrent.LinkedTransferQueue;
import java.io.*;

//public class Server_Thread extends Thread {
public class Multi_Server_Thread implements Runnable{
    
	private Socket client_socket = null;
	
	public Multi_Server_Thread(Socket client_socket) {
		
        this.client_socket = client_socket;
    }
	
    public void run() {

        try (
            
        	PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        ) 
        {
            String inputLine, outputLine;
            Protocol protocol = new Protocol();
           // outputLine = protocol.processInput(null);

            while((inputLine = in.readLine()) != null) 
            {  
            	System.out.println("Input data: " + inputLine	);
            	//in_waiting_line.add(inputLine);
            	//while(in_waiting_line.isEmpty()){
            		//in_waiting_line.poll();
            		outputLine = protocol.processInput(inputLine);
            		System.out.println("Before sending to the client");
            		out.println(outputLine);
            		System.out.println("After sending to the client" + outputLine);
            	//}
             }
           // client_socket.close();
        } catch (IOException e)
        
        {
        	
        	System.out.println("Exception caught when trying to listen on port for a connection");

        }
    }
}