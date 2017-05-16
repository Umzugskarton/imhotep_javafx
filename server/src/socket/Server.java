package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private int port;
	private ServerSocket socket = null;
	private ClientAPI clientAPI = null;
	
	public Server() {
		this.port = 47096;
		this.clientAPI = new ClientAPI();
		this.init();
	}
	
	public Server(int port) {
		this.port = port;
		this.clientAPI = new ClientAPI();
		this.init();
	}
	
	private void init() {
		try {
			this.socket = new ServerSocket(this.port);
	    } catch (IOException e) {
	      System.out.println("Server konnte nicht gestartet werden: " + e.getMessage());
	      System.exit(-1);
	    }
	}
	
	public void run() {
		while (true) {
	      try {
	        System.out.println ("[SERVER] Auf Client warten...");
	        Socket clientSocket = this.socket.accept();
	        
	        System.out.println ("[SERVER] Neuer Client");
	        Thread thread = new Thread(new ClientListener(clientSocket, clientAPI));
	        thread.start();
			
			System.out.println ("[SERVER] Thread fuer neuen Client gestartet");
	      }
	      catch (IOException e)
	      {
	        System.out.println("[SERVER] Anfrage auf Port " + this.port + " konnte nicht verarbeitet werden");
	        System.exit(-1);
	      }
	    }
	}
}
