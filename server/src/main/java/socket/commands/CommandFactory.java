package socket.commands;


import org.json.simple.JSONObject;
import socket.ClientListener;

import java.util.HashMap;

public class CommandFactory {


    private HashMap<String, command> Dict = new HashMap<>();

    public CommandFactory(ClientListener clientListener, JSONObject j){
        Dict.put("register", new registerCommand(clientListener, j));
        Dict.put("login", new loginCommand(clientListener, j));
        Dict.put("logout", new logoutCommand(clientListener, j));
        Dict.put("userlist", new userlistCommand(clientListener, j));
        Dict.put("whisper", new whisperCommand(clientListener, j));
        Dict.put("chat", new chatCommand(clientListener, j));
    }

    public command getCommand(String c){
            command e = Dict.get(c);
            return e;
    }
}
