package md.utm.tmps.domain.command;

// Command Pattern
// Command interface - defines execute and undo operations
public interface OrderCommand {
    void execute();
    void undo();
    String getCommandName();
    boolean isReversible();
}