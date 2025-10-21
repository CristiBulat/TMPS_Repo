package md.utm.tmps.isp;

// A class that only needs to worry about formatting to JSON.
public class JsonFormatter implements Formattable {
    @Override
    public String format(String message) {
        // Simple JSON-like format
        return String.format("{\"message\": \"%s\"}", message);
    }
}