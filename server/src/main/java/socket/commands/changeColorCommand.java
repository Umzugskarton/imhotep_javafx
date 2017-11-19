package socket.commands;

import CLTrequests.changeColorRequest;
import SRVevents.changeColorEvent;
import lobby.Lobby;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;
import CLTrequests.Request;
import user.User;

import java.util.ArrayList;

/**
 * Created by Slothan/Dennis Lindt on 09.11.2017.
 */
public class changeColorCommand implements Command {

    private changeColorRequest request;
    private ClientListener clientListener;
    private ArrayList<String> colors;

    public changeColorCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
    }

    public void put(Request r) { this.request = (changeColorRequest) r;}
    public void exec() {
        Lobby lobby = clientListener.getLobby();
        changeColorEvent changeColorEvent = lobby.replaceColor(clientListener.getUser());
        User[] users = lobby.getUsers();
        for (User tempUser : users) {
            if (tempUser != null) {
                clientListener.getServer().sendTo(changeColorEvent, tempUser.getUsername());
            }
        }
    }



}
