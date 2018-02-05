package connection;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.IRequest;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    private final EventBus eventBus;

    private Socket socket;

    private ConnectionInput input;
    private ConnectionOutput output;

    // Socketdaten
    private String host = null;
    private int port;

    public Connection() {
        this.eventBus = new EventBus();
    }

    public Connection(String host, Integer port, EventBus eventBus) {
        this.host = host;
        this.port = port;
        this.eventBus = eventBus;
        bind();
        init();
    }

    public Connection(EventBus eventBus) {
        this("localhost", 47096, eventBus);
    }

    private void bind() {
        this.eventBus.register(this);
    }


    private void init() {
        try {
            this.socket = new Socket(this.host, this.port);
            this.output = new ConnectionOutput(this.socket);
            this.input = new ConnectionInput(this.socket, this.eventBus);
        } catch (UnknownHostException ex) {
            logger.error(
                    "UnknownHostException bei Verbindung zu Host bei Host: " + this.host + " und Port: "
                            + this.port, ex);
            this.eventBus.post(new ConnectionErrorExeption());
        } catch (IOException ex) {
            logger.error("IOException bei Verbindung zu Host bei Host: " + this.host
                    + " und Port: " + this.port, ex);
            this.eventBus.post(new ConnectionErrorExeption());
        } catch (ConnectionErrorExeption e) {
            logger.error("Verbindung unterbrochen");
            this.eventBus.post(e);
            //closeConnection();
        }
    }

    private void closeConnection() {
        this.input = null;
        this.output = null;
    }

    public void send(IRequest request) {
        try {
            this.output.send(request);
        } catch (ConnectionErrorExeption e) {
            logger.error("Verbindung unterbrochen");
            closeConnection();
        }
    }

    @Subscribe
    public void onConnectionErrorExeption(ConnectionErrorExeption e) {
        logger.error("ConnectionErrorExeption über EventBus angenommen");
        //closeConnection();
        //this.eventBus.post(new ShowServerSettingsEvent());
    }
}
