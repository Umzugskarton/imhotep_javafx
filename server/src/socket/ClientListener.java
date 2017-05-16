package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import sun.net.ConnectionResetException;

public class ClientListener implements Runnable {
    private Server server = null;
    private Socket clientSocket = null;
    private ClientAPI clientAPI = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public ClientListener(Server server, Socket clientSocket, ClientAPI clientAPI) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.clientAPI = clientAPI;

        try {
            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("[SERVER] Fehler: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String receivedMsg = null;
            while ((receivedMsg = in.readLine()) != null) {
                System.out.println("[SERVER] Thread " + Thread.currentThread().getId() + ": Nachricht erhalten " + receivedMsg);

                JSONParser parser = new JSONParser();
                try {
                    Object obj = parser.parse(receivedMsg);
                    JSONObject request = (JSONObject) obj;

                    if(request.containsKey("command")) {
                        String command = (String) request.get("command");
                        JSONObject response = null;

                        if (command.equals("register")) {
                            response = this.clientAPI.register(request);
                        } else if (command.equals("login")) {
                            response = this.clientAPI.login(request);
                        }

                        this.send(response);
                    }
                } catch (ParseException pe){
                    System.out.println("[SERVER] Thread " + Thread.currentThread().getId() + ": Ungueltige Nachricht erhalten " + receivedMsg + ": " + pe);
                }
            }
        } catch (IOException ex) {
            System.out.println("[SERVER] Thread " + Thread.currentThread().getId() + ": " + ex.getMessage());
        } finally {
            this.server.removeClient(this);
        }
    }

    private void send(JSONObject json) {
        if(this.out != null) {
            this.out.println(json.toString());
            this.out.flush();
        }
    }

    public Thread getThread() {
        return Thread.currentThread();
    }
}