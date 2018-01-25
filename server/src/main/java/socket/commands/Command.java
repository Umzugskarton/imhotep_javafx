package socket.commands;

import CLTrequests.IRequest;

public interface Command {

  void exec();

  void put(IRequest r);
}
