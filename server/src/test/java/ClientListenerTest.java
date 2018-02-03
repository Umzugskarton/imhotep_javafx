import org.mockito.Mock;
import socket.ClientAPI;
import socket.Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientListenerTest {

    @Mock
    Server server;

    @Mock
    ObjectOutputStream oc;

    @Mock
    ObjectInputStream ic;

    @Mock
    ClientAPI clientAPI;
}
