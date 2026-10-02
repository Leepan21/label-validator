import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void validWhenEndAfterStart() {
        Event e = new Event("rec1.wav", 1.0, 2.5, "door_open");
        assertTrue(e.isValid());
    }

    @Test
    void invalidWhenEndBeforeStart() {
        Event e = new Event("rec1.wav", 3.0, 2.0, "door_open");
        assertFalse(e.isValid());
    }
}