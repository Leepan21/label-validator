import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EventReaderTest {

    @Test
    void skipsHeaderAndParsesRows() {
        List<String> lines = List.of(
                "file,start,end,label",
                "rec1.wav,1.0,2.5,door_open",
                "rec2.wav,0.5,1.0,footsteps"
        );
        List<Event> events = EventReader.parseLines(lines);
        assertEquals(2, events.size());
        assertEquals("footsteps", events.get(1).label());
    }

    @Test
    void skipsEmptyLines() {
        List<String> lines = List.of(
                "file,start,end,label",
                "",
                "rec1.wav,1.0,2.5,door_open"
        );
        List<Event> events = EventReader.parseLines(lines);
        assertEquals(1, events.size());
    }
}