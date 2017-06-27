/*
 * @Autor: Syntyche Shimbi December 2016
 * 
 * This class simulate the sending of multiple random client requests from
 * 		---multiple independent clients 
 * 		---the interval between request is exponentially distributed
 * 
 * It calculates and display the mean time response depending on the request rate
 * 
 * param:  "Usage: java Load_Generator <host name> <port number> "
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;


public class Load_Generator{
	
	private static final int MIN = 1;
	private static final int MAX = 1000;
	private static final String MATRIX = "1 2 3 4, 5 4 6 2, 7 8 9 1, 4 2 1 5";
	//private static int EXPOSANT = (int) Math.random()*(MAX - MIN); //Math.random() returns double															
	
	public static void main(String[] args) throws IOException 
    {
		
		if (args.length != 4) 
		{
			System.err.println( "Usage: java Client <IP address> <port number> <number of clients> <interval rate>");
			System.exit(1);
		}

		String ipaddress = args[0];
		int port = Integer.parseInt(args[1]);
		int nber_of_clients = Integer.parseInt(args[2]); 
		int rate = Integer.parseInt(args[3]); 
     
		String[][] client_list = new String[nber_of_clients][7]; 
		String response_time;
		String server_response;
		int count = 0; //for counting response messages
		Helper helper = new Helper();
		
		String server_received_message;
		String client_sent_message;
		int k;
		final long time = 1000; //one second
		String[] server_data = new String[2];
		Random random_number;
		
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
		 
		 
			System.out.println("Sending Messages");
		 		
			for(k = 0; k < nber_of_clients; k++ )
			{	
				random_number = new Random(1);	
				int exponent = random_number.nextInt( MAX - MIN +k);
				client_sent_message = k + "|" + MATRIX + " /" + exponent;		
				output.println(client_sent_message); 
		 	
				//generating an exponentially distributed time interval 
				helper.generate_exponential_number(time, rate);
		 	
				//saving Client data 
				client_list[k][0] = Integer.toString(k);
				client_list[k][1] = client_sent_message;          //client sent message
				client_list[k][2] = Long.toString(System.currentTimeMillis()); //the time the message is sent tot he server
				client_list[k][3] = null;							// response time
				client_list[k][4] = null;							// the server response (message)		
				client_list[k][5] = Integer.toString(exponent);			
				client_list[k][6] = null;
				
				System.out.println("\n");
	     		System.out.println("/////////////////////////////////////////////////////////////");
	     		System.out.println("/////////////////  CLIENT SENT MESSAGE  /////////////////////");
	     		System.out.println("/////////////////////////////////////////////////////////////");
	     		System.out.println("\n");
	     		System.out.println("\n");
	     		System.out.println("Client Number: " + k);
	           	System.out.println("Client Matrix: " + "\n ");
	           	
	           	Matrice matrice= new Matrice(4,4);
	           	matrice=matrice.matricePow(4);
	           	int z=1;
	           	
	           	 for(int i=0; i<matrice.getRows(); i++) {
	       			for(int j=0; j<matrice.getColumns(); j++) {
	       				 matrice.setValue(i, j, z);
	       				 z++;
	       			}
	       		 }
	           	 
	           	 //helper.PrintMatrix(MATRICE);
	           	 System.out.println(matrice.toString());
	           	 System.out.println("Client Exponent: " + exponent + "\n ");
	           	 System.out.println("\n");
			}			
		 	
			//System.out.println("Checking if socket is closed: "+ socket.isClosed());
			//System.out.println("check if input is null: " + (server_received_message =  input.readLine()).equals(null));
			
			while(!( (server_received_message =  input.readLine()).equals(null)))
			{			
				long server_response_time 		= System.currentTimeMillis();
				server_data 		      		= server_received_message.split("\\|");
				int server_sent_client_number  	= Integer.parseInt(server_data[0]);
		 	    	 				 
				/*
				 * checks if the received message is valid i.e it corresponds to a valid client_number
				 * checks if the received ack number is valid
				 * get the server's response time and its response
				 */
				
				if( 0 <= server_sent_client_number && server_sent_client_number < nber_of_clients )
				{
					
					if((Integer.parseInt(server_data[1])) == server_sent_client_number + 1)
					{
						long converted_client_sent_time = Long.parseLong(client_list[server_sent_client_number][2]);
						response_time = Long.toString((server_response_time - converted_client_sent_time));
						client_list[server_sent_client_number][3] = response_time;
						client_list[server_sent_client_number][4] = server_data[2];
						client_list[server_sent_client_number][6] = Long.toString(server_response_time);
		 					
						System.out.println("/////////////////////////////////////////////////////////////");
			     		System.out.println("/////////////  SERVER RECEIVED MESSAGE  /////////////////////");
			     		System.out.println("/////////////////////////////////////////////////////////////");
			     		System.out.println("\n");
			     		System.out.println("\n");
			     		System.out.println("Client Number: " + client_list[server_sent_client_number][0] + "\n");
			     		System.out.println("Server Acknowledgement: " +  server_data[1] + "\n");
			     		System.out.println("Server Resulting Matrix: \n");
		            	helper.PrintMatrix(server_data[2]);
		        		System.out.println("\n");
		         		System.out.println("\n");
		         		
		         		System.out.println("/////////////////////////////////////////////////////////////");
		         		System.out.println("////////////////  SERVER RESPONSE TIME  /////////////////////");
		         		System.out.println("/////////////////////////////////////////////////////////////");
		        		System.out.println("\n");
		        		System.out.println("\n");
		                System.out.println("Sent request time     : " + converted_client_sent_time      + " Miliseconds \n");
		                System.out.println("Received response time: " + server_response_time + " Miliseconds \n");
		                System.out.println("Response time         : " + response_time             + " Miliseconds");      
						
					}
					
					count = count + 1;
    					 
				}
		    		
				if(count == nber_of_clients)
				{	
					System.out.println("value equal to the nber of clients");
					break;	
				}
		    		
			}
			            
			/*
			 * Computes the average of all clients response time
			 * and clears the client_list array
			 */
			
			
			System.out.println("///////////////////////////////////////////////////////////////");
     		System.out.println("/////////////////  AVERAGE RESPONSE TIME  /////////////////////");
     		System.out.println("///////////////////////////////////////////////////////////////");
    		System.out.println("\n");
    		System.out.println("\n");
    		int Average_response_time = 0;
			int sum = 0;
			
			for(int j=0; j < nber_of_clients; j++)
			{	
				System.out.println("Client Number: "  + Integer.parseInt(client_list[j][0]));
				System.out.println("Exponent used: "  + client_list[j][5]);
				System.out.println("Sent time : " 	  + client_list[j][2]);
				System.out.println("Received time : " + client_list[j][6]);
				System.out.println("Response time: "  + Integer.parseInt(client_list[j][3]));
				System.out.println("\n");
				sum += Integer.parseInt(client_list[j][3]);
			}
 		 
			if(nber_of_clients <= 0)
			{
				System.out.println("Cannot compute average response time: Number of clients is invalid");
			}
			Average_response_time = sum/nber_of_clients;
			
			System.out.println("\n");
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("\n");
            System.out.println("Average Server Response time: " + "\n" );
            System.out.println("//////////////////////////////////////"); 
            System.out.println("		"+ Average_response_time + " Miliseconds ");
            System.out.println("//////////////////////////////////////");


 		 	helper.clear(client_list);

	    
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



