import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public record Report(int total, int invalid, int unknownLabels,
                     int overlappingPairs, Map<String, Integer> countsPerLabel) {

    public static Report from(List<Event> events) {
        int invalid = 0;
        int unknown = 0;
        Map<String, Integer> counts = new TreeMap<>();

        for (Event e : events) {
            if (!e.isValid()) {
                invalid++;
            }
            if (!e.hasKnownLabel()) {
                unknown++;
            }
            counts.merge(e.label(), 1, Integer::sum);
        }

        int overlaps = 0;
        for (int i = 0; i < events.size(); i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).overlaps(events.get(j))) {
                    overlaps++;
                }
            }
        }

        return new Report(events.size(), invalid, unknown, overlaps, counts);
    }
}