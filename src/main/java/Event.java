public record Event(String file, double start, double end, String label) {

    public boolean isValid() {
        return start >= 0 && end > start;
    }

    public boolean hasKnownLabel() {
        return Labels.ALLOWED.contains(label);
    }

    public boolean overlaps(Event other) {
        return file.equals(other.file)
                && label.equals(other.label)
                && start < other.end
                && other.start < end;
    }

    public static Event fromCsvLine(String line) {
        String[] parts = line.split(",");
        return new Event(
                parts[0].trim(),
                Double.parseDouble(parts[1].trim()),
                Double.parseDouble(parts[2].trim()),
                parts[3].trim()
        );
    }
}