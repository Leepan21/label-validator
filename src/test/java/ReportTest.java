import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void countsAllProblems() {
        List<Event> events = List.of(
                new Event("rec1.wav", 1.0, 3.0, "door_open"),
                new Event("rec1.wav", 2.0, 4.0, "door_open"),   // overlaps the first
                new Event("rec1.wav", 5.0, 4.0, "footsteps"),   // invalid times
                new Event("rec2.wav", 0.0, 1.0, "dog_bark")     // unknown label
        );

        Report report = Report.from(events);

        assertEquals(4, report.total());
        assertEquals(1, report.invalid());
        assertEquals(1, report.unknownLabels());
        assertEquals(1, report.overlappingPairs());
        assertEquals(Map.of("door_open", 2, "footsteps", 1, "dog_bark", 1),
                report.countsPerLabel());
    }

    @Test
    void emptyListGivesEmptyReport() {
        Report report = Report.from(List.of());

        assertEquals(0, report.total());
        assertEquals(0, report.invalid());
        assertEquals(0, report.unknownLabels());
        assertEquals(0, report.overlappingPairs());
        assertTrue(report.countsPerLabel().isEmpty());
    }
}