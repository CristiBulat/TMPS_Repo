package md.utm.tmps.domain.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Command Pattern
// Invoker class - executes commands and manages history for undo
public class OrderCommandInvoker {
    private final Stack<OrderCommand> commandHistory;
    private final List<String> executionLog;

    public OrderCommandInvoker() {
        this.commandHistory = new Stack<>();
        this.executionLog = new ArrayList<>();
    }

    public void executeCommand(OrderCommand command) {
        System.out.println("\n   ═══════════════════════════════════════");
        System.out.printf("   🎮 INVOKER: Processing '%s' command\n", command.getCommandName());
        System.out.println("   ═══════════════════════════════════════");

        command.execute();

        if (command.isReversible()) {
            commandHistory.push(command);
        }

        executionLog.add(String.format("Executed: %s", command.getCommandName()));
    }

    public void undoLastCommand() {
        if (commandHistory.isEmpty()) {
            System.out.println("\n   ⚠️  No commands to undo!");
            return;
        }

        OrderCommand lastCommand = commandHistory.pop();

        System.out.println("\n   ═══════════════════════════════════════");
        System.out.printf("   🎮 INVOKER: Undoing '%s' command\n", lastCommand.getCommandName());
        System.out.println("   ═══════════════════════════════════════");

        lastCommand.undo();
        executionLog.add(String.format("Undone: %s", lastCommand.getCommandName()));
    }

    public void undoAllCommands() {
        System.out.println("\n   ═══════════════════════════════════════");
        System.out.println("   🎮 INVOKER: Undoing ALL commands");
        System.out.println("   ═══════════════════════════════════════");

        while (!commandHistory.isEmpty()) {
            OrderCommand command = commandHistory.pop();
            System.out.printf("\n   Undoing: %s\n", command.getCommandName());
            command.undo();
            executionLog.add(String.format("Undone: %s", command.getCommandName()));
        }
    }

    public int getHistorySize() {
        return commandHistory.size();
    }

    public void displayHistory() {
        System.out.println("\n   ═══════════════════════════════════════");
        System.out.println("   📜 COMMAND EXECUTION LOG");
        System.out.println("   ═══════════════════════════════════════");

        if (executionLog.isEmpty()) {
            System.out.println("   No commands executed yet.");
        } else {
            for (int i = 0; i < executionLog.size(); i++) {
                System.out.printf("   %d. %s\n", i + 1, executionLog.get(i));
            }
        }

        System.out.printf("\n   Commands available for undo: %d\n", commandHistory.size());
    }

    public void clearHistory() {
        commandHistory.clear();
        executionLog.clear();
        System.out.println("   🗑️  Command history cleared");
    }
}