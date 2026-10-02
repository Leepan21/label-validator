import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: give the path to a labels CSV file");
            return;
        }
        List<Event> events = EventReader.readFile(Path.of(args[0]));
        System.out.println(Report.from(events).format());
    }
}