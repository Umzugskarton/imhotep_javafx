package socket.commands;

import CLTrequests.changeColorRequest;
import SRVevents.changeColorEvent;
import lobby.Lobby;
import socket.ClientListener;
import CLTrequests.Request;
import data.User;

import java.util.ArrayList;


public class ChangeColorCommand implements Command {

    private changeColorRequest request;
    private ClientListener clientListener;
    private ArrayList<String> colors;

    public ChangeColorCommand(ClientListener clientListener) {
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
