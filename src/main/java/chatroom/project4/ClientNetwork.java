package chatroom.project4;

import java.util.function.Consumer;
import java.io.Serializable;


public class ClientNetwork extends Network {

    private String ip;
    private int port;

    public ClientNetwork(String ip, int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }
}


