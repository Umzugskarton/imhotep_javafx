package socket.commands;

import CLTrequests.Request;
import CLTrequests.changeEmailRequest;
import SRVevents.changeEmailEvent;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

/**
 * Created by Slothan on 23.10.2017.
 */
public class changeEmailCommand implements Command {
    private changeEmailRequest request;
    private ClientListener clientListener;
    private ClientAPI clientAPI;

    public changeEmailCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
        this.clientAPI = clientListener.getClientAPI();
    }

    public void put(Request r) { this.request = (changeEmailRequest) r;}

    public void exec() {
        changeEmailEvent response = this.clientAPI.changeEmail(this.request, this.clientListener.getUser());
        this.clientListener.send(response);
    }
}
