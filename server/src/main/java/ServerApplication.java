import socket.ClientAPI;
import socket.Server;

public class ServerApplication {

  private static final int SERVER_PORT = 47096;

  public static void main(String[] args) {
    ClientAPI clientApi = new ClientAPI();
    Server server = new Server(clientApi, SERVER_PORT);
    server.run();
  }
}
