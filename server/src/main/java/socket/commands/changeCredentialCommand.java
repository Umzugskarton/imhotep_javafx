package socket.commands;

import CLTrequests.Request;
import CLTrequests.changeCredentialRequest;
import SRVevents.changeCredentialEvent;
import socket.ClientAPI;
import socket.ClientListener;
import user.User;

/**
 * Created by Slothan on 23.10.2017.
 */
public class changeCredentialCommand implements Command {
    private changeCredentialRequest request;
    private ClientListener clientListener;
    private ClientAPI clientAPI;

    public changeCredentialCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
        this.clientAPI = clientListener.getClientAPI();
    }

    public void put(Request r) { this.request = (changeCredentialRequest) r;}

    public void exec() {
        changeCredentialEvent response = this.clientAPI.changeCredential(this.request, this.clientListener.getUser());
        response.setEmail(request.getEmail());
        this.clientListener.send(response);
    }
}
