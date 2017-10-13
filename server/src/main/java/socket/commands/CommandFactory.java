package socket.commands;


import CLTrequests.Request;
import CLTrequests.*;
import socket.ClientListener;

import java.util.HashMap;

public class CommandFactory {


    private HashMap<String, Command> Dict = new HashMap<>();

    public CommandFactory(ClientListener clientListener, Request j){
        Dict.put("register", new registerCommand(clientListener, (registerRequest)j));
        Dict.put("login", new loginCommand(clientListener, (loginRequest) j));
        Dict.put("logout", new logoutCommand(clientListener, (logoutRequest) j));
        Dict.put("userlist", new userlistCommand(clientListener));
        Dict.put("whisper", new whisperCommand(clientListener, (whisperRequest) j));
        Dict.put("chat", new chatCommand(clientListener, (chatRequest)j));
    }

    public Command getCommand(String c){
            return Dict.get(c);
    }
}
