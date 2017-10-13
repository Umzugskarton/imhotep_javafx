package socket.commands;


import CLTrequests.Request;
import socket.ClientListener;

import java.util.HashMap;

public class CommandFactory {


    private HashMap<String, Command> Dict = new HashMap<>();

    public CommandFactory(ClientListener clientListener){
        Dict.put("register", new registerCommand(clientListener));
        Dict.put("login", new loginCommand(clientListener));
        Dict.put("logout", new logoutCommand(clientListener));
        Dict.put("userlist", new userlistCommand(clientListener));
        Dict.put("whisper", new whisperCommand(clientListener));
        Dict.put("chat", new chatCommand(clientListener));
    }

    public Command getCommand(Request request){
        Command c = Dict.get(request.getRequest());
        c.put(request);
        return c;
    }
}
