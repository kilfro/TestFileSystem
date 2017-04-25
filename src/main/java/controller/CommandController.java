package controller;

import exception.InterruptOperationException;
import model.Pair;
import system.InMemorySystem;
import system.SystemInterface;

import util.ConsoleHelper;

/**
 * Created by kirill on 21.04.17.
 */
public class CommandController {
    private SystemInterface system = new InMemorySystem();
    private ConsoleHelper console = new ConsoleHelper();

    public void runSystem() {
        while (true) {
            try {
                console.printMessage(system.pwd() + ": ");
                Pair pair = parseCommand(console.readString());
                String operation = pair.getOperation();
                String value = pair.getNames();
                if ("exit".equals(operation)) {
                    console.askExit();
                } else if ("help".equals(operation)) {
                    console.printHelp(value);
                } else if ("cd".equals(operation)) {
                    system.cd(value);
                } else if ("mkdir".equals(operation)) {
                    system.mkdir(value);
                } else if ("mkfile".equals(operation)) {
                    system.mkfile(value);
                } else if ("ls".equals(operation)) {
                    console.printContent(system.ls());
                } else {
                    console.printMessage("Неверная команда!\n");
                }
            } catch (InterruptOperationException e) {
                console.printMessage(e.getMessage());
                break;
            } catch (Exception e) {
                console.printMessage(e.getMessage());
            }
        }
    }

    private Pair parseCommand(String c) {
        String operation;
        StringBuilder names;
        Pair pair;
        String command = c.trim();
        if (command.contains(" ")) {
            operation = command.substring(0, command.indexOf(" "));
            names = new StringBuilder(command.substring(command.indexOf(" ") + 1).trim());
            if ("/".equals(names.toString())) {
                names.insert(0, "/");
            } else if (names.indexOf("/") != 0) {
                names.insert(0, "/").insert(0, system.pwd());
            }
            pair = new Pair(operation, names.toString().substring(1));
        } else {
            pair = new Pair(command, null);
        }
        return pair;
    }
}