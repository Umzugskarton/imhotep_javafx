package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.application.Platform;
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
      while ((receivedMsg = in.readLine()) != null) {
        System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId()
            + ": Nachricht vom Server erhalten " + receivedMsg);

        JSONParser parser = new JSONParser();
        try {
          Object obj = parser.parse(receivedMsg);
          JSONObject request = (JSONObject) obj;

          if (request.containsKey("command")) {
            String command = (String) request.get("command");

            if (command.equals("register") && request.containsKey("message")) {
              String message = (String) request.get("message");

              // Workaround: JavaFX Elemente können außerhalb der Applikation normalerweise nicht verändert werden
              // http://stackoverflow.com/questions/17850191/why-am-i-getting-java-lang-illegalstateexception-not-on-fx-application-thread
              Platform.runLater(
                  () -> {
                    this.sceneController.getRegistrationPresenter().processRegisterResponse(message);
                  }
              );
            } else if (command.equals("login") && request.containsKey("message") && request.containsKey("success")) {
              String message = (String) request.get("message");
              Boolean success = (Boolean) request.get("success");

              // Workaround: JavaFX Elemente können außerhalb der Applikation normalerweise nicht verändert werden
              // http://stackoverflow.com/questions/17850191/why-am-i-getting-java-lang-illegalstateexception-not-on-fx-application-thread
              Platform.runLater(
                  () -> {
                    this.sceneController.getLoginPresenter().processLoginResponse(success, message);
                  }
              );
            }
          }
        } catch (ParseException pe) {
          System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId()
              + ": Ungültige Nachricht erhalten " + receivedMsg + ": " + pe);
        }
      }

      System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId() + " beendet!");
    } catch (IOException ex) {
      System.out.println(
          "[CLIENT] Serverthread " + Thread.currentThread().getId() + ": " + ex.getMessage());
    }
  }
}
