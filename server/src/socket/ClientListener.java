package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class ClientListener implements Runnable {
    private Socket clientSocket = null;
    private ClientAPI clientAPI = null;

    public ClientListener(Socket clientSocket, ClientAPI clientAPI) {
        this.clientSocket = clientSocket;
        this.clientAPI = clientAPI;
    }

    @Override
    public void run() {
        try {
            System.out.println("[SERVER] Thread " + Thread.currentThread().getId() + " gestartet!");

            PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String receivedMsg = null;
            while ((receivedMsg = in.readLine()) != null)
            {
                System.out.println("[SERVER] Thread " + Thread.currentThread().getId() + ": Message erhalten " + receivedMsg);

                JSONParser parser = new JSONParser();
                try{
                    Object obj = parser.parse(receivedMsg);
                    JSONObject jsonObject = (JSONObject) obj;

                    if(jsonObject.containsKey("command")) {
                        String command = (String) jsonObject.get("command");
                        JSONObject response = null;

                        if (command.equals("register")) {
                            if (jsonObject.containsKey("username") && jsonObject.containsKey("password") && jsonObject.containsKey("email")) {
                                response = this.clientAPI.register(jsonObject);
                            }
                        } else if (command.equals("login")) {
                            if ((jsonObject.containsKey("username") || jsonObject.containsKey("email")) && jsonObject.containsKey("password")) {
                                response = this.clientAPI.login(jsonObject);
                            }
                        }

                        out.println(response.toString());
                    } else {
                        out.println("fehler: unbekannter befehl");
                    }
                } catch (ParseException pe){
                    System.out.println("Die erhaltene Nachricht ist kein JSON: " + pe);
                }
            }

            System.out.println("[SERVER] Thread " + Thread.currentThread().getId() + " beendet!");
        } catch (IOException ex) {
            System.out.println("[SERVER] Fehler :" + ex.getMessage());
        }
    }
}