package socket.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import requests.IRequest;
import socket.ClientListener;

import java.util.HashMap;

public class CommandFactory {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private HashMap<String, Command> Dict = new HashMap<>();

    public CommandFactory(ClientListener clientListener){
        Dict.put("register", new RegisterCommand(clientListener));
        Dict.put("login", new LoginCommand(clientListener));
        Dict.put("logout", new LogoutCommand(clientListener));
        Dict.put("userList", new UserlistCommand(clientListener));
        Dict.put("whisper", new WhisperCommand(clientListener));
        Dict.put("chat", new ChatCommand(clientListener));
        Dict.put("create", new CreateCommand(clientListener));
        Dict.put("join", new JoinCommand(clientListener));
        Dict.put("lobbylist", new LobbylistCommand(clientListener));
        Dict.put("changeCredential", new ChangeCredentialCommand(clientListener));
        Dict.put("changeColor", new ChangeColorCommand(clientListener));
        Dict.put("setReady", new SetReadyCommand(clientListener));
        Dict.put("startGame", new StartGameCommand(clientListener));
        Dict.put("leaveLobby", new leaveLobbyCommand(clientListener));
    }

    public Command getCommand(IRequest request){
        log.debug("BIN BEI: " +request.getClass().getSimpleName());
        Command c = Dict.get(request.getType());
        c.put(request);
        return c;
    }
}
