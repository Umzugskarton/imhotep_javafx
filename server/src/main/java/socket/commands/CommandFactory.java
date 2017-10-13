package socket.commands;


import CLTrequests.Request;
import CLTrequests.*;
import socket.ClientListener;

import java.util.HashMap;

public class CommandFactory {


    private HashMap<String, Command> Dict = new HashMap<>();

    public CommandFactory(ClientListener clientListener, Request bare){
        Dict.put("register", new registerCommand(clientListener, bare));
        Dict.put("login", new loginCommand(clientListener, bare));
        Dict.put("logout", new logoutCommand(clientListener));
        Dict.put("userlist", new userlistCommand(clientListener));
        Dict.put("whisper", new whisperCommand(clientListener, bare));
        Dict.put("chat", new chatCommand(clientListener, bare));
    }

    public Command getCommand(String c){
            return Dict.get(c);
    }
}
