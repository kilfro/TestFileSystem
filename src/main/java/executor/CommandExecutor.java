package executor;

import exception.AlreadyExistsException;
import exception.InterruptOperationException;
import exception.NotFoundException;
import model.Pair;
import system.InMemorySystem;
import util.ConsoleHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirill on 05.05.17.
 */
public class CommandExecutor {
    private InMemorySystem system = new InMemorySystem();
    private static Map<String, Command> commandMap = new HashMap<>();
    static {
        commandMap.put("help", new HelpCommand());
        commandMap.put("exit", new ExitCommand());
        commandMap.put("pwd", new PwdCommand());
        commandMap.put("cd", new CdCommand());
        commandMap.put("ls", new LsCommand());
        commandMap.put("mkdir", new MkDirCommand());
        commandMap.put("mkfile", new MkFileCommand());
    }

    private Pair parseCommand(String c) {
        String operation;
        StringBuilder names;
        Pair pair;
        String command = c.trim();
        //Если после trim() в строке все еще присутствуют проблы - команда передана с аргументом
        if (command.contains(" ")) {
            operation = command.substring(0, command.indexOf(" ")).toLowerCase();
            names = new StringBuilder(command.substring(command.indexOf(" ") + 1).trim());
            if ("/".equals(names.toString())) {
                names.insert(0, "/");
                //Если аргумент начинается с '/' - уже используется абсолютный путь
            } else if (names.indexOf("/") != 0) {
                names.insert(0, "/").insert(0, system.getPwd());
            }
            pair = new Pair(operation, names.toString().substring(1));
        } else {
            pair = new Pair(command.toLowerCase(), null);
        }
        return pair;
    }

    public void execute() {
        while (true) {
            try {
                commandMap.get("pwd").execute(null, system);
                ConsoleHelper.printMessage("/: ");
                Pair pair = parseCommand(ConsoleHelper.readString());
                commandMap.get(pair.getOperation()).execute(pair.getNames(), system);
            } catch (NotFoundException | AlreadyExistsException e) {
                ConsoleHelper.printMessage(e.getMessage());
            } catch (InterruptOperationException e) {
                ConsoleHelper.printMessage(e.getMessage());
                break;
            }
        }
    }
}