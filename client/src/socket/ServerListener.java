package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerListener implements Runnable {
    private Socket serverSocket;

    public ServerListener(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId() + " gestartet!");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            String receivedMsg = null;
            while ((receivedMsg = in.readLine()) != null)
            {
                System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId() + ": Message vom Server erhalten " + receivedMsg);
            }

            System.out.println("[CLIENT] Serverthread " + Thread.currentThread().getId() + " beendet!");
        } catch (IOException ex) {
            System.out.println("[CLIENT] Fehler: " + ex.getMessage());
        }
    }
}
