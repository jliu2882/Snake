import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Backend {
    private static final String dbFile = "HighScores.csv";

    public static void write(String data) throws IOException {
        PrintWriter buffer = new PrintWriter(new BufferedWriter(new FileWriter(Backend.dbFile, true)));

        buffer.println(data);
        buffer.close();
    }

    public static List<List<String>> read(int lines) {
        CSVUtilities helper = new CSVUtilities(new File(Backend.dbFile));

        List<List<String>> data = new ArrayList<>();
        List<String> columns = helper.getColumnHeaders();

        IntStream.range(0, lines).forEach(i -> {
            List<String> rowData = new ArrayList<>();

            IntStream.range(0, columns.size()).forEach(col -> {
                //
                // TODO: Reduce I/O operations
                List<String> columnData = helper.getDataString(col + 1);
                if (i < columnData.size()) rowData.add(columnData.get(i));
            });
            if (rowData.size() > 0) data.add(rowData);
        });
        return data;
    }

    public static List<List<String>> read() {
        return Backend.read((new CSVUtilities(new File(Backend.dbFile))).getDataString(1).size());
    }

    public static void write(String[] data) throws IOException {
        Backend.write(Arrays.stream(data).collect(Collectors.joining(",")));
    }

    public static void write(List<String> data) throws IOException {
        Backend.write(data.stream().collect(Collectors.joining(",")));
    }

}
