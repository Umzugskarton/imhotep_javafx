package socket.commands;

import CLTrequests.Request;

public interface Command {
    void exec();
    void put(Request r);
}
