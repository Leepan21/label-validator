import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EventReader {

    public static List<Event> parseLines(List<String> lines) {
        List<Event> events = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isBlank()) {
                continue;
            }
            events.add(Event.fromCsvLine(line));
        }
        return events;
    }

    public static List<Event> readFile(Path path) throws IOException {
        return parseLines(Files.readAllLines(path));
    }
}