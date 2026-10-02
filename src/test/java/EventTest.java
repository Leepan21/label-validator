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

    @Test
    void invalidWhenStartIsNegative() {
        Event e = new Event("rec1.wav", -1.0, 2.0, "door_open");
        assertFalse(e.isValid());
    }
    @Test
    void parsesCsvLine() {
        Event e = Event.fromCsvLine("rec1.wav,1.0,2.5,door_open");
        assertEquals("rec1.wav", e.file());
        assertEquals(1.0, e.start());
        assertEquals(2.5, e.end());
        assertEquals("door_open", e.label());
    }
    @Test
    void knownLabelIsAccepted() {
        Event e = new Event("rec1.wav", 1.0, 2.0, "door_open");
        assertTrue(e.hasKnownLabel());
    }

    @Test
    void unknownLabelIsRejected() {
        Event e = new Event("rec1.wav", 1.0, 2.0, "dog_bark");
        assertFalse(e.hasKnownLabel());
    }
}