package connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.IRequest;
import requests.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionOutput {

  private ObjectOutputStream oos;

  private final Logger logger = LoggerFactory.getLogger(getClass().getName());


  public ConnectionOutput(Socket socket) throws ConnectionErrorExeption {
    try {
      this.oos = new ObjectOutputStream(socket.getOutputStream());
    } catch (IOException e) {
      throw new ConnectionErrorExeption(e);
    }
  }

  public void send(IRequest request) throws ConnectionErrorExeption {
    try {
      this.oos.writeObject(request);
      this.oos.flush();
    } catch (IOException e) {
      throw new ConnectionErrorExeption(e);
    }

    logger.debug("Nachricht gesendet: " + request.toString());
  }
}
