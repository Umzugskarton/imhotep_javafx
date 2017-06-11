package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javafx.application.Platform;
import main.SceneController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerListener implements Runnable {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  private Socket serverSocket;
  private SceneController sceneController;

  public ServerListener(Socket serverSocket, SceneController sceneController) {
    this.serverSocket = serverSocket;
    this.sceneController = sceneController;
  }

  @Override
  public void run() {
    try {
      log.info("Serverthread gestartet!");

      BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

      String receivedMsg = null;
      while ((receivedMsg = in.readLine()) != null) {
        log.info("Nachricht erhalten: " + receivedMsg);

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
            } else if (command.equals("userlist") && request.containsKey("users")) {
              JSONArray userArray = (JSONArray) request.get("users");

              // Workaround: JavaFX Elemente können außerhalb der Applikation normalerweise nicht verändert werden
              // http://stackoverflow.com/questions/17850191/why-am-i-getting-java-lang-illegalstateexception-not-on-fx-application-thread
              Platform.runLater(
                  () -> {
                    if (this.sceneController.getMainmenuPresenter() != null) {
                     this.sceneController.getMainmenuPresenter().updateUserlist(userArray);
                    }
                  }
              );
            }
          }
        } catch (ParseException pe) {
          log.error("Ungültige Nachricht erhalten " + receivedMsg, pe);
        }
      }

      log.info("Serverthread " + Thread.currentThread().getId() + " beendet!");
    } catch (IOException ex) {
      log.error("Ein Fehler ist aufgetreten", ex);
    }
  }
}
