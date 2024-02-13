package Project.ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{
	
	//List of clients connected 
	private ArrayList<ConnectionHandler> connections;
	private ServerSocket server;
	private boolean done;
	private ExecutorService pool;
	
	
	
	
	public Server() {
		connections=new ArrayList<>();
		done=false;
	}
	
	//The run method initializes a ServerSocket on port 9999, listens for incoming client connections, and spawns a ConnectionHandler thread for each connected client 
	
	@Override
	public void run() {
		
		try {
			// server socket for server in java we only need port number we dont need IP unless we need one manually 
			 server=new ServerSocket(9999);
			 pool =Executors.newCachedThreadPool();
			 while(!done) {
				 
			// server has accept method and it returns a client socket
			Socket client =server.accept();
			ConnectionHandler handler=new ConnectionHandler(client);
			connections.add(handler);
			pool.execute(handler);
			 }
		} catch (IOException e) {
			shutdown();
			
		}
		
		
	}
	
	
	//broadcast function for messages to all the connected clients
	public void broadcast(String message) {
		for (ConnectionHandler ch : connections) {
			if(ch!=null) {
				ch.sendMessage(message);
			}
		}
	}
	
	//shutdown function to shutdown the connections 
	public void shutdown() {
			try {
				done=true;
				if (!server.isClosed()) {
				server.close();
				}
				for(ConnectionHandler ch :connections) {
					ch.shutdown();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	// This class handles clients connection by passing the client manage individual clients connection 
	class ConnectionHandler implements  Runnable {
		
		//socket for clients
		private Socket client;
		
		// Get from client
		private BufferedReader in;
		// Write something to client/output
		private PrintWriter out;
		
		private String nickname;
		// Constructor
		public ConnectionHandler(Socket client) {
			this.client=client;
		}
		
		//The run method initializes the input and output streams, asks the client for a nickname, and broadcasts the join message to all clients.
		public void run() {
			
			try {
				//initialize print writer we need output and autoflush is true we dont need to manually flush the stream in order to send the messages
				out=new PrintWriter(client.getOutputStream(), true);
				//initialize 
				in= new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				//send to client
				out.println("Please enter a nickname: ");
				nickname=in.readLine();
				System.out.println(nickname+" connected");
				broadcast(nickname +" joined the chat!");
				
				String message;
				while((message=in.readLine()) !=null) {
					if (message.startsWith("/nick ")) {
						String[] messageSplit= message.split(" ",2);
						if(messageSplit.length==2) {
							broadcast(nickname +" renamed themselves to " +messageSplit[1]);
							System.out.println(nickname +" renamed themselves to " +messageSplit[1]);
							nickname=messageSplit[1];
							out.println("Successfully changed nickname to "+nickname);
						}else {
							out.println("No nickname provided!");
						}
					} else if (message.startsWith("/quit")) {
						broadcast(nickname+" left the chat");
						shutdown();
					}else {
						broadcast(nickname+": "+message);
					}
				}
			} catch (IOException e) {
				shutdown();
			}
			
			
			
		}
		//Function to send message
		public void sendMessage(String message) {
			out.println(message);
		}
		//The shutdown method closes the input, output, and the client socket.
		public void shutdown() {
			try {
			in.close();
			out.close();
			
			if(!client.isClosed()) {
				client.close();
			}
			}catch(IOException e) {
				
			}
		}
	}
	//The main method creates an instance of the Server class and calls its run method to start the server.
	public static void main(String[] args) {
		Server server=new Server();
		server.run();
	}
}

