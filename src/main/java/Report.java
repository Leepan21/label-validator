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

    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total events: ").append(total).append("\n");
        sb.append("Invalid times: ").append(invalid).append("\n");
        sb.append("Unknown labels: ").append(unknownLabels).append("\n");
        sb.append("Overlapping pairs: ").append(overlappingPairs).append("\n");
        sb.append("Events per label:\n");
        for (Map.Entry<String, Integer> entry : countsPerLabel.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}