package commands.lobby;

import commands.Command;
import events.app.game.StartGameEvent;
import lobby.Lobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.IRequest;
import requests.lobby.StartGameRequest;
import socket.ClientListener;

public class StartGameCommand implements Command {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private StartGameRequest request;
    private ClientListener clientListener;

    public StartGameCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
    }

    public void put(IRequest r) {
        request = (StartGameRequest) r;
    }

    public void exec() {
        Lobby lobby = clientListener.getLobbyByID(request.getLobbyId());
        for (boolean b : lobby.getReady()) {
            if (!b) {
                log.info("Kann nicht starten: Nicht alle bereit");
                return;
            }
        }
        clientListener.getServer().sendToLobby(new StartGameEvent(lobby.getLobbyID()), lobby);
        lobby.startGame(clientListener);
    }
}
