package main;

import socket.Server;

public class ServerApplication {

  public static void main(String[] args) {
    Server server = new Server();
    server.run();
  }
}
