package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import json.ServerCommands;
import org.json.simple.JSONObject;

public class Server {
	private int port;
	private ServerSocket serverSocket = null;
	private ClientAPI clientAPI = null;
	private ArrayList<ClientListener> connectedClients = new ArrayList<ClientListener>();
	
	public Server() {
		this.port = 47096;
		this.clientAPI = new ClientAPI();
		this.init();
	}
	
	private void init() {
		try {
				this.serverSocket = new ServerSocket(this.port);
				System.out.println("[SERVER] Server auf Port " + this.port + " gestartet");
	    } catch (IOException e) {
	      System.out.println("[SERVER] Server konnte auf Port " + this.port + " nicht gestartet werden: " + e.getMessage());
	      System.exit(-1);
	    }
	}
	
	public void run() {
		while (true) {
	      try {
	        Socket clientSocket = this.serverSocket.accept();

					this.addClient(clientSocket);
	      } catch (IOException e) {
	        System.out.println("[SERVER] Anfrage auf Port " + this.port + " konnte nicht verarbeitet werden");
	        System.exit(-1);
	      }
	    }
	}

	public void addClient(Socket clientSocket) {
		System.out.println ("[SERVER] Ein neuer Client hat sich verbunden");

		ClientListener clientListener = new ClientListener(this, clientSocket, this.clientAPI);
		Thread thread = new Thread(clientListener);
		thread.start();

		System.out.println ("[SERVER] Thread " + thread.getId() + " gestartet");

		this.connectedClients.add(clientListener);

		System.out.println ("[SERVER] Thread " + thread.getId() + " zur Liste der verbundenen Clients hinzugefuegt");
	}

	public void removeClient(ClientListener clientListener) {
		System.out.println ("[SERVER] Thread " + clientListener.getThread().getId() + ": Client hat die Verbindung beendet");

		if(this.connectedClients.contains(clientListener)) {
			this.connectedClients.remove(clientListener);

			System.out.println ("[SERVER] Thread " + clientListener.getThread().getId() + " von der Liste der verbundenen Clients entfernt");
		}
	}

	public void sendToAll(JSONObject json) {
		for (ClientListener clientListener : connectedClients) {
			clientListener.send(json);
		}
	}
}
