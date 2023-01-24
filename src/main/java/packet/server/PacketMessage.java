package packet.server;

import logger.Logger;
import packet.Packet;
import server.ServerLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketMessage extends Packet {

    private String sender, message;

    public PacketMessage() {
    }

    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dos) {
        try {
            dos.writeUTF(sender);
            dos.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(DataInputStream dis) {
        try {
            message = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle() {
        sender = ServerLoader.getHandler(getSocket()).getClientName();
        ServerLoader.handlers.keySet().forEach(s -> ServerLoader.sendPacket(s, this));
        Logger.printAndWriteInfo(String.format("[%s] %s", sender, message));
    }
}
