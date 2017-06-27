import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Single_Server_Thread implements Runnable {
	String inputString;
	Socket socket;
	Boolean is_done = false;
	
	String result;
	Protocol protocol = new Protocol();
	
	public Single_Server_Thread(Socket client_socket, String client_input) {
		
		this. inputString = client_input;
		this.socket       = client_socket;
    }

	public void run(){
	
		System.out.println("client request: " + inputString);
		try(
			PrintWriter out =  new PrintWriter( this.socket.getOutputStream(), true);	
		)
		{
			out.println(protocol.processInput(this.inputString));
			System.out.println("Sent response : " + protocol.processInput(this.inputString));
			this.is_done = true;
		}
		catch(IOException e){
			System.err.println("Couldn't get I/O for the Client Socket");
	        System.exit(1);
			
		}
		
	}
}
