package chatroom.project4;

import java.util.function.Consumer;
import java.io.Serializable;


public class ServerNetwork extends Network {

    private int port;

    public ServerNetwork(int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.port = port;
    }

    @Override
    protected String getIP() {
        return null;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    protected boolean isServer() {
        return true;
    }
}