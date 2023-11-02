import java.util.Arrays;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class Terminal {
    private Parser parser;
    private ArrayList<String> myCommands;

    public Terminal() {
        parser = new Parser();
        myCommands = new ArrayList<>();
    }

    // Implement ls command
    public void ls() {
        File currentDir = new File(System.getProperty("user.dir"));

        if (currentDir.isDirectory()) {
            File[] files = currentDir.listFiles();
            if (files != null) {
                Arrays.sort(files);
                for (File file : files) {
                    System.out.println(file.getName());
                }
            } else {
                System.out.println("No files or directories found in the current directory.");
            }
        } else {
            System.out.println("Not a valid directory.");
        }
    }


    // Implement ls-r command
    public void lsR() {
        String currentDir = System.getProperty("user.dir");
        File dir = new File(currentDir);

        if (dir.isDirectory()) {
            String[] files = dir.list();
            List<String> fileList = Arrays.asList(files);
            Collections.sort(fileList, Collections.reverseOrder());
            for (String file : fileList) {
                System.out.println(file);
            }
        } else {
            System.out.println("Not a valid directory.");
        }
    }

    //Implement touch command
    public void touch(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            System.out.println("File already exists: " + file.getAbsolutePath());
            return; // Exit the function early
        }
        else {

            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create the file.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    //Implement history command
    public void history() {
        System.out.println("Output:");
        int commandNum = 1;
        while (true) {
            for (String command : myCommands) {
                System.out.println(commandNum + " " + command);
                commandNum++;
            }
            break;
        }
    }
    public void chooseCommandAction(String input) {
        myCommands.add(input);
        if (parser.parse(input)) {
            String commandName = parser.getCommandName();
            switch (commandName) {
                case "ls":
                    ls();
                    break;
                case "ls-r":
                    lsR();
                    break;
                case "touch":
                    if (parser.getArguments().length == 1) {
                        touch(parser.getArguments()[0]);
                    } else {
                        System.out.println("Invalid 'touch' command. Usage: touch <file-path>");
                    }
                    break;
                case "history":
                    history();
                    break;
                default:
                    System.out.println("Command not found: " + commandName);
            }
        }
    }


    //main
    public static void main(String[] args) {
        boolean status = true;
        Scanner scanner = new Scanner(System.in);
        Terminal terminal = new Terminal();
        while (status) {
            System.out.print("> ");
            String myInput = scanner.nextLine();
            if (myInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the CLI.");
                break;
            }
            else
            {terminal.chooseCommandAction(myInput);}
        }
        scanner.close();
    }
}

