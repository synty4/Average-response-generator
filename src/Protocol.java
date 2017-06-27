
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

public class Protocol {
    
    private final int WAITING           =		     0;
    private final int CONNECTED         = 		     1;    
    private int state                   =      WAITING;
    
    private String[] client_data        = new String[3]; 
    private String client_number        =          null;
    private String message_from_client  =          null;
    int sent_ack                        =			  0;
    private String sent_response        =          null;
    private String client_response      =          null;

    
    public String processInput( String input_from_client ) {

      if( input_from_client != null )
       {	
        	client_data     	   = input_from_client.split("\\|");
        	client_number   	   = client_data[0];
        	message_from_client    = client_data[1];
        		
        	System.out.println("message_from_client: " + message_from_client);
        		
        	String[] matrix_data   = message_from_client.split("\\/");
            sent_ack			   = Integer.parseInt(client_number) + 1;
        	Helper helper = new Helper();
        		
        		
        	Computation pmatrice   = new Computation();
            pmatrice.ParseMatrix(message_from_client);
        	sent_response          = pmatrice.ToString();
        		
        	System.out.println("////////////////////////////////////////////////////////////");
         	System.out.println("////////////////  CLIENT RECEIVED  MESSAGE  ////////////////");
         	System.out.println("////////////////////////////////////////////////////////////");	
         	System.out.println("\n");
         	System.out.println("\n");
        	System.out.println("Client Number: " + client_number + "\n");
        	System.out.println("Client Matrice: "+ "\n");
        		
        	System.out.println(matrix_data[0] +"\n");
       		System.out.println("Client Exponent: "+ matrix_data[1] +"\n");         		System.out.println("\n");
       
      		System.out.println("////////////////////////////////////////////////////////////");
        	System.out.println("//////////////////  SERVER SENT RESPONSE  //////////////////");
         	System.out.println("////////////////////////////////////////////////////////////");
        	System.out.println("\n");
       		System.out.println("\n");
       		System.out.println("Client Number: " + client_number + "\n");
       		System.out.println("Client Acknowledgement: " + sent_ack + "\n");
       		helper.PrintMatrix(sent_response);
        		
        		//string to be sent
       		client_response = client_number + "|" + sent_ack + "|" + sent_response;
        		
        }
        
        return client_response;
    }
}
