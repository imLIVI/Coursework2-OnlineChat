package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String PATH_TO_LOG_FILE = "./file.log";

    private static void writeToLogFile(String infoForWriting) {
        try (BufferedWriter bufToLogFile = new BufferedWriter(new FileWriter(PATH_TO_LOG_FILE, true))) {
            bufToLogFile.write(String.format("[ %s ] %s\n", getDateTime(), infoForWriting));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = dateTimeFormatter.format(LocalDateTime.now());
        return dateTimeNow;
    }

    public static void printAndWriteInfo(String infoForWriting) {
        System.out.println(infoForWriting);
        writeToLogFile(infoForWriting);
    }
}
