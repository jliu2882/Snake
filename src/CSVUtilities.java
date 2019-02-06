
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSVUtilities {
    private List<String> CSVData;
    private int numColumns;

    public CSVUtilities (File csv) {
        this.CSVData = new ArrayList<>();
        this.numColumns = -1;

        try {
            FileReader input = new FileReader(csv);
            BufferedReader br = new BufferedReader(input);

            String line = br.readLine();

            while (line != null) {
                String[] attrs = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                for (String attr : attrs) this.CSVData.add(attr);
                this.numColumns = this.numColumns < 0 ? attrs.length : numColumns;
                line = br.readLine();
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public List<String> getColumnHeaders() {
        return this.CSVData.subList(0, this.numColumns);
    }

    public List<String> getDataString(int column) {
        List<String> out = new ArrayList<>();
        int i = column - 1 + this.numColumns;

        while (i < this.CSVData.size()) {
            out.add(this.CSVData.get(i));
            i = i + this.numColumns;
        }

        return out;
    }

    public List<Integer> getDataInt(int column) {
        List<Integer> out = new ArrayList<>();
        int i = column - 1 + this.numColumns;

        while (i < this.CSVData.size()) {
            if (this.CSVData.get(i).length() == 0) {
                out.add(0);
            } else {
                out.add(Integer.parseInt(this.CSVData.get(i)));
            }

            i = i + this.numColumns;
        }

        return out;
    }

    public List<Double> getDataDouble(int column) {
        List<Double> out = new ArrayList<>();
        int i = column - 1 + this.numColumns;

        while (i < this.CSVData.size()) {
            if (this.CSVData.get(i).length() == 0) {
                out.add(0d);
            } else {
                out.add(Double.parseDouble(this.CSVData.get(i)));
            }
            i = i + this.numColumns;
        }

        return out;
    }
}
