package packet.client;

import logger.Logger;
import packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketMessage extends Packet {

    private String sender, message;

    public PacketMessage() {
    }

    public PacketMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dos) {
        try {
            dos.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read(DataInputStream dis) {
        try {
            sender = dis.readUTF();
            message = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle() {
        //Logger.printAndWriteInfo(String.format("[%s] %s", sender, message));
        System.out.println(String.format("[%s] %s", sender, message));
    }
}
