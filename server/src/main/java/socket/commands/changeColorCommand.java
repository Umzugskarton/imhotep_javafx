package socket.commands;

import CLTrequests.changeColorRequest;
import SRVevents.changeColorEvent;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;
import CLTrequests.Request;

/**
 * Created by Slothan on 09.11.2017.
 */
public class changeColorCommand implements Command {

    private changeColorRequest request;
    private ClientListener clientListener;
    private ClientAPI clientAPI;

    public changeColorCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
        this.clientAPI = clientListener.getClientAPI();
    }

    public void put(Request r) { this.request = (changeColorRequest) r;}
    public void exec() {
        changeColorEvent response = this.clientAPI.changeColor(this.request);
        this.clientListener.send(response);
    }



}
