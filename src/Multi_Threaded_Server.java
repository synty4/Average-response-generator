import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class Multi_Threaded_Server {
	
    public static void main(String[] args) throws IOException {
    	
    	if (args.length != 2) 
    	{
    		System.err.println("Usage: java Multi_Server <port number> <pool size>");
    		System.exit(1);
    	}
    	
    	int port = Integer.parseInt(args[0]);
    	final ExecutorService pool = Executors.newFixedThreadPool(Integer.parseInt(args[1]));
    	
    	if (port <= 0 || port > 65536) {
			System.err.println("Invalid Port number.");
			System.exit(1);
		}
        boolean listening = true;
       

        try (ServerSocket server_Sock = new ServerSocket(port)) 
        { 
            while ( listening ) 
            {
	            //new Server_Thread( server_Sock.accept() ).start();
	            pool.execute(new Server_Thread(server_Sock.accept()));
	        }
	    } catch (IOException e) 
        {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
    
}

/*

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Multi_Threaded_Server implements Runnable{

    protected int server_port  ;
    protected int pool_size =0;
    protected ServerSocket server_socket = null;
    protected boolean isStopped     = false;
    
    protected Thread running_thread = null;
    protected ExecutorService threadPool;

    public  Multi_Threaded_Server(int port, int user_input_pool_size){
        this.server_port = port;
        this.threadPool  = Executors.newFixedThreadPool(pool_size);
    }

    public void run(){
    	
        synchronized(this){
            this.running_thread = Thread.currentThread();
        }
        
        openServerSocket();
        
        while(! isStopped()){
            Socket client_socket = null;
            try {
                client_socket = this.server_socket.accept();
            } 
            catch (IOException e)
            {
                if(isStopped()) {
                	
                    System.out.println("Server Interrupted.") ;
                    break;
                }
                
                throw new RuntimeException( "Exception caught when trying to listen on port for a connection");
            }
            
            this.threadPool.execute( new Server_Thread(client_socket));
        }
        this.threadPool.shutdown();
        System.out.println("Server Stopped.") ;
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.server_socket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.server_socket = new ServerSocket(this.server_port);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port" + this.server_port, e);
        }
    }
    
    public static void main(String args[]){
    	
    	if (args.length != 2) 
    	{
    		System.err.println("Usage: java Multi_Server <port number> <pool size>");
    		System.exit(1);
    	}
    	int port = Integer.parseInt(args[0]);
    	int pool_size = Integer.parseInt(args[1]);
        
        Multi_Threaded_Server server = new Multi_Threaded_Server(port, pool_size);
        new Thread(server).start();

        try{
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
 
        
    	
    }
}
*/