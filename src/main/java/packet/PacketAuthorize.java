package packet;

import logger.Logger;
import server.ServerLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketAuthorize extends Packet {
    private String name = "None";

    public PacketAuthorize() {
    }

    public PacketAuthorize(String name) {
        this.name = name;
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) {
        try {
            dos.writeUTF(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(DataInputStream dis) {
        try {
            name= dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle() {
        ServerLoader.getHandler(getSocket()).setClientName(name);
        Logger.printAndWriteInfo("Authorized new socket: " + name);
    }
}
