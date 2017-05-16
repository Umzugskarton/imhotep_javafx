package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.scene.Scene;
import main.SceneController;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerListener implements Runnable {
    private Socket serverSocket;
    private SceneController sceneController;

    public ServerListener(Socket serverSocket, SceneController sceneController) {
        this.serverSocket = serverSocket;
        this.sceneController = sceneController;
    }

    @Override
    public void run() {
        try {
            System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId() + " gestartet!");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            String receivedMsg = null;
            while ((receivedMsg = in.readLine()) != null)
            {
                System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId() + ": Nachricht vom Server erhalten " + receivedMsg);

                JSONParser parser = new JSONParser();
                try {
                    Object obj = parser.parse(receivedMsg);
                    JSONObject request = (JSONObject) obj;

                    if(request.containsKey("command")) {
                        String command = (String) request.get("command");
                        JSONObject response = null;

                        if (command.equals("register")) {
                            String message = (String) request.get("message");
                            //this.sceneController.getRegistrationPresenter().setResult(message);
                        } else if (command.equals("login")) {
                            String message = (String) request.get("message");
                            //this.sceneController.getLoginPresenter().setResult(message);
                        }
                    }
                } catch (ParseException pe){
                    System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId() + ": Ungueltige Nachricht erhalten " + receivedMsg + ": " + pe);
                }
            }

            System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId() + " beendet!");
        } catch (IOException ex) {
            System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId() + ": " + ex.getMessage());
        }
    }
}
