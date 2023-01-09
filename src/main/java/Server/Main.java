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

        // Reading the port from the settings file
        ReaderSettingFile readerSettingFile = new ReaderSettingFile();
        port = readerSettingFile.getPort();

        // Start Server
        startServer(logger);
    }

    public static void startServer(Logger logger) {
        logger.printAndWriteInfo("Server started");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                logger.printAndWriteInfo("[SERVER] new connection to the port: " + clientSocket.getPort());

                final String name = in.readLine();
                out.println(String.format("Hello %s, you port is %d", name, clientSocket.getPort()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}