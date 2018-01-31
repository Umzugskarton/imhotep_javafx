package socket.commands;

import CLTrequests.IRequest;
import CLTrequests.changeColorRequest;
import SRVevents.changeColorEvent;
import lobby.Lobby;
import socket.ClientListener;
import data.user.User;

import java.util.ArrayList;


public class ChangeColorCommand implements Command {

    private changeColorRequest request;
    private ClientListener clientListener;
    private ArrayList<String> colors;

    public ChangeColorCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
    }

    public void put(IRequest r) { this.request = (changeColorRequest) r;}

    public void exec() {
        Lobby lobby = clientListener.getLobbyByID(request.getLobbyId());
        changeColorEvent changeColorEvent = lobby.replaceColor(clientListener.getUser());
        User[] users = lobby.getUsers();
        for (User tempUser : users) {
            if (tempUser != null) {
                clientListener.getServer().sendTo(changeColorEvent, tempUser.getUsername());
            }
        }
    }
}
