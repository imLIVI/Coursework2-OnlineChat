import org.junit.jupiter.api.*;
import packet.Packet;
import packet.PacketAuthorize;
import packet.client.PacketMessage;
import packet.server.PacketManager;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

public class PacketManagerTest {
    private static Packet packet;

    @BeforeAll
    public static void init() {
        System.out.println("Running tests...");
    }

    @BeforeEach
    public void startOfTest() {
        System.out.println("---------------------------------------\n[START OF THE TEST]");
    }

    @Test
    @DisplayName("Проверка корректности получения класса пакета")
    public void testGetPacket_correct() {
        // Act
        packet = PacketManager.getPacket((short) 1);
        // Assert
        assertThat(packet, instanceOf(PacketAuthorize.class));
    }

    @Test
    @DisplayName("Проверка корректности получения класса пакета")
    public void testGetPacket_fail() {
        // Act
        packet = PacketManager.getPacket((short) 1);
        // Assert
        assertNotSame(packet, instanceOf(PacketMessage.class));
    }

    @AfterEach
    public void endOfTest() {
        System.out.println("[END OF THE TEST]\n---------------------------------------");
    }
}
