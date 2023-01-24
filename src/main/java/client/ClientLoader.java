package client;

import packet.Packet;
import packet.PacketAuthorize;
import packet.client.PacketManager;
import packet.client.PacketMessage;
import reader.ReaderSettingFile;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientLoader {
    private static Socket socket;
    private static boolean sentName = false;
    private static int port;
    private static String ip;

    public static void main(String[] args) {
        // Reading the port and IP from the settings file
        ReaderSettingFile readerSettingFile = new ReaderSettingFile();
        port = readerSettingFile.getPort();
        ip = readerSettingFile.getIp();

        // Connect to server
        connect();
        handle();
        end();
    }

    private static void connect() {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        new Thread(() -> {
            System.out.println("Write your name and message: ");
            while (true) {
                try {
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    if (dis.available() <= 0) {
                        continue;
                    }
                    short id = dis.readShort();
                    Packet packet = PacketManager.getPacket(id);
                    packet.read(dis);
                    packet.handle();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        readChat();
    }

    private static void readChat() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("/exit")) {
                    end();
                }
                if (!sentName) {
                    sentName = true;
                    sendPacket(new PacketAuthorize(line));
                    continue;
                }
                sendPacket(new PacketMessage(null, line));
            }
        }
    }

    public static void sendPacket(Packet packet) {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeShort(packet.getId());
            packet.write(dos);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void end() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
