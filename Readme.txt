Readme
******

Setting the classPath for jar files
-----------------------------------

In windows
----------

set CLASSPATH=.;C:/DIR/Project2_Arch/src/lib/jcommon-1.0.12.jar;C:/DIR/Project2_Arch/src/lib/jfreechart-1.0.8.jar;C:/DIR/Project2_Arch/src/lib/uncommons-maths-1.2.3.jar;C:/DIR/Project2_Arch/src/lib/uncommons-maths-demo-1.2.3.jar

In Linux(Ubuntu)
----------------

set CLASSPATH=.:/lib/jcommon-1.0.12.jar:/lib/jfreechart-1.0.8.jar:/lib/uncommons-maths-1.2.3.jar:/lib/uncommons-maths-demo-1.2.3.jar

Compile and run the server
Javac Protocol.java Server.java


Run one Client and one server program
-------------------------------------

Run the Server program from command line:
	--javac Server.java
	--java Server <port_number>

Run the Client program from command line:
	--javac Client.java
	--java Client <hot name> <port number>



	
	