package commands;

import requests.IRequest;

public interface Command {

    void exec();

    void put(IRequest r);
}
