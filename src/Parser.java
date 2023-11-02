import java.util.Arrays;

public class Parser {
    private String commandName;
    private String[] args;

    public boolean parse(String input) {
        char dash = '-';
        String[] parts = input.split(" ");
        commandName = parts[0];

        if (parts.length > 1) {
            String part2 = parts[1];
            if (part2.charAt(0) == dash) {
                commandName += part2;
                args = Arrays.copyOfRange(parts, 2, parts.length);
            } else {
                args = Arrays.copyOfRange(parts, 1, parts.length);
            }
        } else {
            args = new String[0]; // No arguments
        }
        return true;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArguments() {
        return args;
    }
}