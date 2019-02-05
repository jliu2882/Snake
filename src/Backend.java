import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Backend {
    private static final String dbFile = "db.csv";

    public static void write(String data) throws IOException {
        PrintWriter buffer = new PrintWriter(new BufferedWriter(new FileWriter(Backend.dbFile, true)));

        buffer.println(data);
        buffer.close();
    }

    public static void write(String[] data) throws IOException {
        Backend.write(Arrays.stream(data).collect(Collectors.joining(",")));
    }

    public static void write(List<String> data) throws IOException {
        Backend.write(data.stream().collect(Collectors.joining(",")));
    }
}
