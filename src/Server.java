import java.net.*;
import java.util.concurrent.LinkedTransferQueue;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) 
        {
            System.err.println("Usage: Server <port number>");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
    	LinkedTransferQueue<String> in_waiting_line = new LinkedTransferQueue<String>();
    	LinkedTransferQueue<String> out_waiting_line = new LinkedTransferQueue<String>();
    	//final ExecutorService pool = Executors.newFixedThreadPool(1);
    	boolean listening = true;
     
    	/*
    	 * Creates a server socket.
    	 * Accepts the connection from the client.
    	 * Gets the socket's output stream and 
    	 * opens a PrintWriter on it( used for sending a response to the Client)
    	 * Gets the socket's input stream and
    	 *  opens a BufferReader on it ( used for receiving the Client's request)
    	 */
        
        try ( 
            ServerSocket server_sock = new ServerSocket(port);
            Socket client_sock = server_sock.accept();
            PrintWriter out =  new PrintWriter( client_sock.getOutputStream(), true); //for server response
            BufferedReader in = new BufferedReader( new InputStreamReader( client_sock.getInputStream())); //for user input
        ) 
        {
        
            String inputLine, outputLine="", request_from_queue;
            Protocol protocol = new Protocol();
            
            
            /*
         	 * Creates a matrix from the String message received from the Client
         	 * carries out the client's requested calculation
         	 * and returns the result to the Client
         	 */
            
            while ((inputLine = in.readLine()) != null) 
            {	
            	in_waiting_line.add(inputLine);
            	if((request_from_queue = in_waiting_line.poll()) != null){
            		
                	outputLine = protocol.processInput(inputLine);
                    out.println(outputLine);
            	}
            }

        }
        catch (IOException e)
        {
            System.out.println("Exception caught when trying to listen on port "+ port + " for a connection");
            
        }
    }
}