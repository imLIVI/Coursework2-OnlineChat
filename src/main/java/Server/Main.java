package Server;

import Logger.Logger;
import ReaderSettingsFile.ReaderSettingFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static int port;

    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.printAndWriteInfo("Server started");

        // Reading the port from the settings file
        ReaderSettingFile readerSettingFile = new ReaderSettingFile();
        port = readerSettingFile.getPort();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                System.out.println("New connection. Port: " + clientSocket.getPort());

                final String name = in.readLine();
                out.println(String.format("Hello %s, you port is %d", name, clientSocket.getPort()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}