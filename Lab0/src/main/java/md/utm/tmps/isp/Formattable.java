package md.utm.tmps.isp;

// Interface Segregation Principle (ISP)
// A small, specific interface for formatting messages.
public interface Formattable {
    String format(String message);
}
