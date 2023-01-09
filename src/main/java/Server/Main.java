package Server;

import ReaderSettingsFile.ReaderSettingFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static int port;

    public static void main(String[] args) {
        System.out.println("Server started");

        // Reading the port and IP from the settings file
        ReaderSettingFile readerSettingFile = new ReaderSettingFile();
        port = readerSettingFile.getPort();
        
    }
}