import java.io.*;
import java.net.*;
import java.util.Random;
public class Client {
	private static final String MATRICE = "1 2 3 4, 5 4 6 2, 7 8 9 1, 4 2 1 5";
	
	public static void main(String[] args) throws IOException {
		
    	Helper helper = new Helper();
    	Random random = new Random(); //for client_number
        String server_input;
        String client_input;
        long sent_request_time = 0;
        long received_response_time = 0;
        long response_time = 0; 
        String[] server_data = new String[2];
    	
        if (args.length != 3) 
        {
            System.err.println("Usage: java Client <IP address> <port number> <matrix exponent>");
            System.exit(1);
        }
        
        String ipaddress = args[0];
        int port = Integer.parseInt(args[1]);
        int exponent = Integer.parseInt(args[2]);
        
    	/*
    	 * Creates a server socket with the entered IP address and port number
    	 * Gets the socket's output stream and 
    	 * opens a PrintWriter on it( used for sending a request to the Server)
    	 * Gets the socket's input stream and
    	 * opens a BufferReader on it ( used for receiving the Server's response)
    	 */
        
        try 
        (
            Socket socket = new Socket(ipaddress, port);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        ) 
        {            
             /*
              * Gets the user's input from standard input
              * and sending it to the server.
              * After sending the entire string,
              * get the system's current  time in Milliseconds
              * and save its value in a variable named 'sent_request_time' 
              * that will be used later
              */
              
             //client_input = input_stream_reader.readLine(); 
             int client_number = random.nextInt(100);
             client_input = client_number + "|" + MATRICE + " /" + exponent;
             output.println(client_input);
             sent_request_time = System.currentTimeMillis();
             
     		 System.out.println("\n");
     		 System.out.println("/////////////////////////////////////////////////////////////");
     		 System.out.println("/////////////////  CLIENT SENT MESSAGE  /////////////////////");
     		 System.out.println("/////////////////////////////////////////////////////////////");
     		 System.out.println("\n");
     		 System.out.println("\n");
     		 System.out.println("Client Number: " + client_number);
           	 System.out.println("Client Matrix: " + "\n ");
           	 
           	 Matrice matrice= new Matrice(4,4);
           	 matrice=matrice.matricePow(4);
           	 int k=1;
           	 for(int i=0; i<matrice.getRows(); i++) {
       			for(int j=0; j<matrice.getColumns(); j++) {
       				 matrice.setValue(i, j, k);
       				 k++;
       			}
       		 }
           	 
           	 //helper.PrintMatrix(MATRICE);
           	 System.out.println(matrice.toString());
           	 System.out.println("Client Exponent: " + exponent + "\n ");
           	 System.out.println("\n");
             
             
            /*
             *  Gets the resulting Matrix from the Server
             *  Then get the System's current time after 
             *  receiving the entire string and save its value
             *  into a variable 'received_response_time'
             */
             
            
            while ((server_input = input.readLine()) != null) 
            {   
            	received_response_time          = System.currentTimeMillis();
            	server_data 		            = server_input.split("\\|");
				if ( ( Integer.parseInt(server_data[0]) == client_number ) && (Integer.parseInt(server_data[1]) == client_number + 1)) 
	     		
				  System.out.println("/////////////////////////////////////////////////////////////");
	     		System.out.println("/////////////  SERVER RECEIVED MESSAGE  /////////////////////");
	     		System.out.println("/////////////////////////////////////////////////////////////");
	     		System.out.println("\n");
	     		System.out.println("\n");
	     		System.out.println("Client Number: " + server_data[0] + "\n");
	     		System.out.println("Server Acknowledgement: " +  server_data[1] + "\n");
	     		System.out.println("Server Resulting Matrix: \n");
          helper.PrintMatrix(server_data[2]);
        	System.out.println("\n");
         	System.out.println("\n");
            	
         		break;
        }
            
            /*
             * Computing the response time
             */
            response_time = received_response_time - sent_request_time;  
     		    System.out.println("/////////////////////////////////////////////////////////////");
     		    System.out.println("////////////////  SERVER RESPONSE TIME  /////////////////////");
     		    System.out.println("/////////////////////////////////////////////////////////////");
    		    System.out.println("\n");
    		    System.out.println("\n");
            System.out.println("Sent request time     : " + sent_request_time      + " Miliseconds \n");
            System.out.println("Received response time: " + received_response_time + " Miliseconds \n");
            System.out.println("Response time         : " + response_time             + " Miliseconds");
            System.exit(1);
        } 
        catch (UnknownHostException e) 
        {
            System.err.println("Invalid hostname " + ipaddress);
            System.exit(1);
        } 
        catch (IOException e) 
        {
        	System.err.println("Couldn't get I/O for the connection to " +
                ipaddress);
            System.exit(1);
        }
       
    }
}