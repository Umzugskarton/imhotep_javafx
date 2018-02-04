import org.mockito.Mock;
import socket.ClientAPI;
import socket.ClientListener;
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

    public void runTest() {
    }
}
