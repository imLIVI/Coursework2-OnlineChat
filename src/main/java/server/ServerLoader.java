package server;

import logger.Logger;
import packet.Packet;
import reader.ReaderSettingFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerLoader {
    private static ServerSocket server;
    public static Map<Socket, ClientHandler> handlers = new HashMap<>();
    public static int port;

    public static void main(String[] args) {
        // Reading the port from the settings file
        ReaderSettingFile readerSettingFile = new ReaderSettingFile();
        port = readerSettingFile.getPort();

        // Work with Server
        start();
        handle();
        end();
    }

    private static void start() {
        try {
            server = new ServerSocket(port);
            Logger.printAndWriteInfo("Server started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        new Thread(() -> {
            while (true) {
                // виснем, пока к серверу не подключатся
                try {
                    Socket client = server.accept();
                    // когда клиент подключился, то создаем обработчик для него,
                    // т.е. читаем сообщения от клиента
                    ClientHandler handler = new ClientHandler(client);
                    handler.start();
                    // после обработки кладем клиента и его обработчик в мапу
                    ServerLoader.handlers.put(client, handler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        readChat();
    }

    // проверка на завершение работы сервера
    private static void readChat() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("/exit"))
                    end();
                else
                    System.out.println("[ERROR] Unknown command!");
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ClientHandler getHandler(Socket socket) {
        return handlers.get(socket);
    }

    // отправка пакета
    public static void sendPacket(Socket receiver, Packet packet) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(receiver.getOutputStream());
            dos.writeShort(packet.getId());
            packet.write(dos);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void end() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}