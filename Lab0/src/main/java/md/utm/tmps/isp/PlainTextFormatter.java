package md.utm.tmps.isp;

// A class that handles plain text formatting.
public class PlainTextFormatter implements Formattable {
    @Override
    public String format(String message) {
        return "PlainText: " + message;
    }
}