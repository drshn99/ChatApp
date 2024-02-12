package Project.ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{

	
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private boolean done;
	
	//In the run method, it connects to the server at the IP address "127.0.0.1" and port 9999.
	//It initializes input and output streams, starts an InputHandler thread to handle user input, and continuously reads and prints messages from the server.
	@Override
	public void run() {
		try {
			client =new Socket("127.0.0.1", 9999);
			out =new PrintWriter(client.getOutputStream(),true);
			in= new BufferedReader(new InputStreamReader(client.getInputStream()));
		InputHandler inHandler=new InputHandler();
		Thread t=new Thread(inHandler);
		t.start();
		
		String inMessage;
		while((inMessage=in.readLine())!=null) {
			System.out.println(inMessage);
		}
		} catch (IOException e) {
			shutdown();
		}
		
	}
	
	public void shutdown() {
		done=true;
		try {
			in.close();
			out.close();
			if(!client.isClosed()) {
				client.close();
			}
			}
			catch (IOException e) {
				
			}
		
	}
	
	//It reads user input from the console and sends it to the server. If the user types "/quit," it also shuts down the client.
	class InputHandler implements Runnable{
		@Override
		public void run() {
		try {
			BufferedReader inReader=new BufferedReader(new InputStreamReader(System.in));
			while(!done) {
				String message=inReader.readLine();
				
				if (message.equals("/quit")) {
					out.println(message);
					inReader.close();
					shutdown();
				}else {
					out.println(message);
				}
				
				}
		} catch (IOException e) {
			shutdown();
		}
			
		}
	}
	//The main method creates an instance of the Client class and calls its run method to start the client.
	public static void main(String[] args) {
		Client client=new Client();
		client.run();
	}
}
