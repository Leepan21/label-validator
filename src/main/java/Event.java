public record Event(String file, double start, double end, String label) {

    public boolean isValid() {
        return end > start;
    }
}