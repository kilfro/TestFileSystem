package controller;

import exception.InterruptOperationException;
import system.InMemorySystem;
import system.SystemInterface;

import util.ConsoleHelper;

/**
 * Created by kirill on 21.04.17.
 */
public class CommandController {
    private static CommandController instance;
    private SystemInterface system = new InMemorySystem();
    private ConsoleHelper console = new ConsoleHelper();

    private CommandController() {

    }

    public static CommandController getInstance() {
        return instance == null ? new CommandController() : instance;
    }

    public void runSystem() {
        while (true) {
            try {
                console.printMessage(system.pwd() + ": ");
                String[] args = console.readString().split(" ");
                String operation = args[0];
                String value;
                try {
                    value = args[1];
                } catch (IndexOutOfBoundsException e) {
                    value = null;
                }
                switch (operation) {
                    case "exit":
                        console.askExit();
                        break;
                    case "help":
                        console.printHelp(value);
                        break;
                    case "cd":
                        system.cd(value);
                        break;
                    case "mkdir":
                        system.mkdir(value);
                        break;
                    case "mkfile":
                        system.mkfile(value);
                        break;
                    case "ls":
                        console.printContent(system.ls());
                        break;
                    default:
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
}