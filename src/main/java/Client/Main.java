package Client;

import ReaderSettingsFile.ReaderSettingFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static int port;
    public static String ip;

    public static void main(String[] args) {
        // Reading the port and IP from the settings file
        ReaderSettingFile readerSettingFile = new ReaderSettingFile();
        port = readerSettingFile.getPort();
        ip = readerSettingFile.getIp();

        try (Socket clientSocket = new Socket(ip, port)) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Vika");

            String response = in.readLine();
            System.out.println(response);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
