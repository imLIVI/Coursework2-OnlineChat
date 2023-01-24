package packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public abstract class Packet {
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public abstract short getId();

    public abstract void write(DataOutputStream dos);

    public abstract void read(DataInputStream dis);

    public abstract void handle();
}
