package business;
public class ExporterFactory {
    public static Exporter createExporter(String format) {
        if (format.equalsIgnoreCase("csv")) {
            return new CsvExporter();
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + format);
        }
    }
}
