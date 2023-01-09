package ReaderSettingsFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderSettingFile {
    private static final String PATH_TO_SETTINGS_FILE = "./settings.txt";
    private static String ip;
    private static int port;

    public ReaderSettingFile() {
        readSettingsFile();
    }

    private void readSettingsFile() {
        try (BufferedReader infoSettingsFile = new BufferedReader(new FileReader(PATH_TO_SETTINGS_FILE))) {
            String line;
            while ((line = infoSettingsFile.readLine()) != null) {
                String[] infoArray = line.split(" = ");
                if (line.contains("ip"))
                    ip = infoArray[1];
                else if (line.contains("port")) {
                    port = Integer.parseInt(infoArray[1]);
                } else {
                    throw new IncorrectSettingsFile("[ERROR] The settings.txt must contain information " +
                            "about the port and ip address!");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IncorrectSettingsFile e) {
            e.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
