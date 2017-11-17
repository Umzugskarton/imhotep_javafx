package socket.commands;

import CLTrequests.Request;
import CLTrequests.setReadyRequest;
import SRVevents.setReadyEvent;
import socket.ClientListener;

/**
 * Created by Slothan on 16.11.2017.
 */
public class setReadyCommand implements Command {

    private ClientListener clientListener;
    private setReadyRequest request;

    public setReadyCommand(ClientListener clientListener) {
        this.clientListener = clientListener;

    }

    public void put(Request r) { this.request = (setReadyRequest) r;}

    public void exec() {
        setReadyEvent setReadyEvent = clientListener.getLobby().setReady(clientListener.getUser());
        this.clientListener.send(setReadyEvent);

    }



}
