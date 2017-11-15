package socket.commands;

import CLTrequests.changeColorRequest;
import SRVevents.changeColorEvent;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;
import CLTrequests.Request;

import java.util.ArrayList;

/**
 * Created by Slothan/Dennis Lindt on 09.11.2017.
 */
public class changeColorCommand implements Command {

    private changeColorRequest request;
    private ClientListener clientListener;
    private ClientAPI clientAPI;
    private ArrayList<String> colors;

    public changeColorCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
        this.clientAPI = clientListener.getClientAPI();
    }

    public void put(Request r) { this.request = (changeColorRequest) r;}
    public void exec() {
        changeColorEvent changeColorEvent = clientListener.getLobby().replaceColor(clientListener.getUser(), )

        this.clientListener.send(response);
    }



}
