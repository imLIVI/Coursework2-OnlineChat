import org.junit.jupiter.api.*;
import reader.ReaderSettingFile;

public class ReaderSettingFileTest {
    private static ReaderSettingFile readerSettingFile;

    @BeforeAll
    public static void init() {
        System.out.println("Running tests...");
        readerSettingFile = new ReaderSettingFile();
    }

    @BeforeEach
    public void startOfTest() {
        System.out.println("---------------------------------------\n[START OF THE TEST]");
    }

    @Test
    @DisplayName("Проверка корректности считывания порта")
    public void testReadingPort_fail() {
        // Arrange
        int amount = 8000;
        // Act
        int result = readerSettingFile.getPort();
        // Assert
        Assertions.assertNotEquals(result, amount);
    }

    @Test
    @DisplayName("Проверка корректности считывания порта")
    public void testReadingPort_correct() {
        // Arrange
        int amount = 8080;
        // Act
        int result = readerSettingFile.getPort();
        // Assert
        Assertions.assertEquals(result, amount);
    }

    @Test
    @DisplayName("Проверка корректности считывания IP-адреса")
    public void testReadingIp_fail() {
        // Arrange
        String amount = "110.52.85.82";
        // Act
        String result = readerSettingFile.getIp();
        // Assert
        Assertions.assertNotEquals(result, amount);
    }

    @Test
    @DisplayName("Проверка корректности считывания IP-адреса")
    public void testReadingIp_correct() {
        // Arrange
        String amount = "127.0.0.1";
        // Act
        String result = readerSettingFile.getIp();
        // Assert
        Assertions.assertEquals(result, amount);
    }

    @AfterEach
    public void endOfTest() {
        System.out.println("[END OF THE TEST]\n---------------------------------------");
    }
}
