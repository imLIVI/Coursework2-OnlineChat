package server;

import packet.Packet;
import packet.server.PacketManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket client;
    private String name = "Anonymous";

    public ClientHandler(Socket client) {
        this.client = client;
    }

    public String getClientName() {
        return this.name;
    }

    public void setClientName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            readData();
            // чтобы не было беск. while (ибо слишком будет слишком большая нагрузка на сервер)
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readData() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            // проверяем, что есть данные для считывания
            if (dis.available() <= 0)
                return;
            short id = dis.readShort();
            // создаем пустой пакет
            Packet packet = PacketManager.getPacket(id);
            // устанавливаем сокет
            packet.setSocket(client);
            // считываем пакет
            packet.read(dis);
            // обрабатываем пакет - указываем какому клиенту пакет принадлежит
            packet.handle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
